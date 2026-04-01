import sys
from pathlib import Path
from db_config import fetch_api_metadata
from generator_service import GeneratorService


def main():
    api_name = input("📌 Nome da API (ex: GB5425): ").strip().upper()
    if not api_name:
        return

    try:
        print(f"🔍 Obtendo metadados para {api_name}...")
        rows = fetch_api_metadata(api_name)

        if not rows:
            print("❌ Nenhuma definição encontrada.")
            return

        service = GeneratorService(api_name)
        service.process_metadata(rows)

        # Criar diretório
        output_dir = Path("generated_java") / api_name
        output_dir.mkdir(parents=True, exist_ok=True)

        # Gerar os 3 ficheiros
        files = {
            f"{api_name}Request.java": service.generate_request(),
            f"{api_name}Response.java": service.generate_response(),
            f"{api_name}Handler.java": service.generate_handler()  # Handler garantido aqui
        }

        for filename, content in files.items():
            (output_dir / filename).write_text(content, encoding='utf-8')
            print(f"  ✓ {filename} gerado.")

        print(f"\n✅ Concluído! Pasta: {output_dir}")

    except Exception as e:
        print(f"💥 Erro fatal: {e}")


if __name__ == "__main__":
    main()