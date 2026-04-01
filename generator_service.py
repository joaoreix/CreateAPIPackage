from java_templates import JavaTemplates


class GeneratorService:
    def __init__(self, api_name):
        self.api_name = api_name
        self.normal_fields = []
        self.array_fields = []
        self.package = f"ao.selenium.bkintegration.core.feature.{api_name}"

    def process_metadata(self, rows):
        for row in rows:
            nome = str(row[1]).strip().lower().replace('#', '')
            descricao = str(row[2]).strip()

            if '(' in descricao:
                self.array_fields.append(nome)
            else:
                self.normal_fields.append(nome)

    def get_rcode_field(self):
        return next((f for f in self.normal_fields if f.endswith('rcode')), "cspcrcode")

    def generate_request(self):
        fields = [f"        String {f}" for f in self.normal_fields]
        if self.array_fields:
            fields.append("\n        // Arrays - usando wrappers personalizados")
            for f in self.array_fields:
                fields.append(
                    f'        @JacksonXmlElementWrapper(localName = "{f}")\n        @JacksonXmlProperty(localName = "item")\n        List<String> {f}')
        return JavaTemplates.request_template(self.package, self.api_name, ",\n".join(fields))

    def generate_response(self):
        fields = [f"        String {f}" for f in self.normal_fields]
        if self.array_fields:
            fields.append("\n        // Arrays - usando wrappers personalizados")
            for f in self.array_fields:
                fields.append(f'        @JacksonXmlProperty(localName = "{f}")\n        ArrayWrapper {f}')
        return JavaTemplates.response_template(self.package, self.api_name, ",\n".join(fields), self.get_rcode_field())

    def generate_handler(self):
        # Lógica XML Payload
        xml_logic = []
        for f in self.normal_fields:
            xml_logic.append(f'        xmlBuilder.append(createXmlTag("{f}",request.{f}()));')

        if self.array_fields:
            xml_logic.append("\n        // Tratamento especial para os arrays")
            for f in self.array_fields:
                xml_logic.append(f'        xmlBuilder.append(createArrayTag("{f}",request.{f}()));')

        # Mapeamento do Construtor
        mapping = [f"                parsedXml.{f}()" for f in self.normal_fields + self.array_fields]
        mapping.append("                messages")

        return JavaTemplates.handler_template(self.package, self.api_name, "\n".join(xml_logic), ",\n".join(mapping))