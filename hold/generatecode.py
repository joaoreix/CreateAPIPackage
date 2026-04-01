#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import os
import logging
from pathlib import Path
from dotenv import load_dotenv

# Configuração de logs
logging.basicConfig(level=logging.INFO, format='%(levelname)s: %(message)s')

try:
    from utils.db_connection import get_connection

    DB_CONNECTION_AVAILABLE = True
except ImportError:
    DB_CONNECTION_AVAILABLE = False

load_dotenv()


class TransactionHandlerJavaGenerator:
    def __init__(self):
        self.api_name = ""
        self.base_output_dir = Path("../generated_java")
        self.api_output_dir = None
        self.normal_fields = []  # Campos String simples
        self.array_fields = []  # Campos que contêm '(' em D$DELE
        self.package_base = "ao.selenium.bkintegration.core.feature"

    def get_api_info(self) -> bool:
        print("\n" + "=" * 70)
        print("GERADOR TRANSACTION HANDLER - VALIDAÇÃO EM D$DELE")
        print("=" * 70)

        self.api_name = input("\n📌 Nome da API (ex: GB5425): ").strip()
        if not self.api_name:
            return False

        # Mantém o case original para pasta e package
        self.api_output_dir = self.base_output_dir / self.api_name
        self.api_output_dir.mkdir(parents=True, exist_ok=True)
        return True

    def execute_query(self) -> bool:
        if not DB_CONNECTION_AVAILABLE:
            print("❌ Erro: Módulo de conexão não disponível.")
            return False

        try:
            conn = get_connection()
            cursor = conn.cursor()

            library = os.environ.get("LIBRARY")
            table_campos = os.environ.get("TABLE_NAME")
            table_valores = os.environ.get("TABLE_NAME1")
            entidade = f"{self.api_name}DS" if not self.api_name.endswith("DS") else self.api_name

            # Query conforme solicitado
            query = f"""
                WITH 
                CAMPOS AS (SELECT * FROM {library}.{table_campos} WHERE D$NENT = ?),
                VALORES AS (SELECT * FROM {library}.{table_valores} WHERE E$REF1 = ? AND E$SITU = 'V' AND E$SEQD = '10')

                SELECT C.D$NENT, C.D$NELE, C.D$DELE FROM CAMPOS C
                INNER JOIN VALORES V ON C.D$NENT = V.E$REF1 AND C.D$NELE = V.E$REF2
            """

            cursor.execute(query, (entidade, entidade))
            rows = cursor.fetchall()

            if not rows:
                print(f"⚠️ Nenhuma definição encontrada para {entidade} com SEQ 10.")
                return False

            self.normal_fields = []
            self.array_fields = []

            for row in rows:
                # row[1] = D$NELE (nome do campo)
                # row[2] = D$DELE (descrição onde validamos o array)
                nome_elemento = str(row[1]).strip().lower().replace('#', '')
                descricao = str(row[2]).strip()

                # Regra: Se a descrição (D$DELE) tem '(', é um array
                if '(' in descricao:
                    self.array_fields.append(nome_elemento)
                else:
                    self.normal_fields.append(nome_elemento)

            cursor.close()
            conn.close()
            return True
        except Exception as e:
            print(f"❌ Erro de Base de Dados: {e}")
            return False

    def generate_request(self) -> str:
        name = self.api_name
        pkg = f"{self.package_base}.{name}"
        code = f"package {pkg};\n\n"
        code += "import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;\n"
        code += "import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;\n\n"
        code += "import java.util.List;\n\n"
        code += f"public record {name}Request(\n"

        fields = [f"        String {f}" for f in self.normal_fields]
        if self.array_fields:
            fields.append("\n        // Arrays - usando wrappers personalizados")
            for f in self.array_fields:
                fields.append(
                    f'        @JacksonXmlElementWrapper(localName = "{f}")\n        @JacksonXmlProperty(localName = "item")\n        List<String> {f}')

        return code + ",\n".join(fields) + "\n\n) {}"

    def generate_response(self) -> str:
        name = self.api_name
        pkg = f"{self.package_base}.{name}"

        # Lógica para encontrar o campo que termina com 'rcode' dinamicamente
        rcode_field = next((f for f in self.normal_fields if f.endswith('rcode')), "cspcrcode")

        code = f"package {pkg};\n\n"
        code += "import ao.selenium.bkintegration.core.bootstrapper.transaction.TransactionResponse;\n"
        code += "import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;\n"
        code += "import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;\n"
        code += "import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;\n\n"
        code += "import java.util.List;\n\n"
        code += f'@JacksonXmlRootElement(localName = "XML{name}")\n'
        code += f"public record {name}Response(\n"

        fields = [f"        String {f}" for f in self.normal_fields]
        if self.array_fields:
            fields.append("\n        // Arrays - usando wrappers personalizados")
            for f in self.array_fields:
                fields.append(f'        @JacksonXmlProperty(localName = "{f}")\n        ArrayWrapper {f}')

        fields.append("\n        List<String> messages")
        code += ",\n".join(fields) + f"\n) implements TransactionResponse {{\n"
        # Inner Record ArrayWrapper
        code += """    public static record ArrayWrapper(
                    @JacksonXmlElementWrapper(useWrapping = false)
                    @JacksonXmlProperty(localName = "i")
                    List<String> i
            ) {
                public List<String> getValues() {
                    return i != null ? i : List.of();
                }
            }\n"""

        # Métodos da Interface com RCODE Dinâmico
        code += f"""
            @Override
            public String getReturnCode() {{
                return this.{rcode_field};
            }}

            @Override
            public List<String> getMessages() {{
                return this.messages;
            }}
        }}"""
        return code

    def generate_handler(self) -> str:
        name = self.api_name
        pkg = f"{self.package_base}.{name}"
        code = f"""package {pkg};

import ao.selenium.bkintegration.core.bootstrapper.transaction.AbstractTransactionHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class {name}Handler extends AbstractTransactionHandler<{name}Request, {name}Response> {{

    @Override
    public String getTransactionCode() {{
        return "{name}";
    }}

    @Override
    public Class<{name}Request> getRequestType() {{
        return {name}Request.class;
    }}

    @Override
    protected Class<{name}Response> getResponseType() {{
        return {name}Response.class;
    }}

    @Override
    protected String getXmlRootElementName() {{
        return "XML{name}";
    }}

    @Override
    public String buildRequestPayload({name}Request request) {{
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<XML{name}>");
"""
        for f in self.normal_fields:
            code += f'        xmlBuilder.append(createXmlTag("{f}",request.{f}()));\n'

        if self.array_fields:
            code += "\n        // Tratamento especial para os arrays\n"
            for f in self.array_fields:
                code += f'        xmlBuilder.append(createArrayTag("{f}",request.{f}()));\n'

        code += f"""
        xmlBuilder.append("</XML{name}>");
        return xmlBuilder.toString();
    }}

    private String createArrayTag(String tagName, List<String> values) {{
        if (values == null || values.isEmpty()) return "";
        StringBuilder arrayBuilder = new StringBuilder();
        arrayBuilder.append("<").append(tagName).append(">");
        for (String value : values) {{
            arrayBuilder.append("<i>").append(value).append("</i>");
        }}
        arrayBuilder.append("</").append(tagName).append(">");
        return arrayBuilder.toString();
    }}

    @Override
    protected {name}Response createResponse({name}Response parsedXml, List<String> messages) {{
        return new {name}Response(
"""
        # Ordem: Normais -> Arrays -> Messages
        map_fields = [f"                parsedXml.{f}()" for f in self.normal_fields]
        map_fields += [f"                parsedXml.{f}()" for f in self.array_fields]
        map_fields.append("                messages")

        code += ",\n".join(map_fields) + "\n        );\n    }\n"
        code += f"""
    public List<String> getArrayValues({name}Response.ArrayWrapper wrapper) {{
        return wrapper != null ? wrapper.getValues() : List.of();
    }}
}}"""
        return code

    def save_files(self):
        files = {
            f"{self.api_name}Request.java": self.generate_request(),
            f"{self.api_name}Response.java": self.generate_response(),
            f"{self.api_name}Handler.java": self.generate_handler()
        }
        for filename, content in files.items():
            path = self.api_output_dir / filename
            with open(path, 'w', encoding='utf-8') as f:
                f.write(content)
            print(f"  ✓ {filename}")

    def run(self):
        if self.get_api_info() and self.execute_query():
            self.save_files()
            print(f"\n✅ Concluído! Tudo gerado em: {self.api_output_dir}")


if __name__ == "__main__":
    TransactionHandlerJavaGenerator().run()