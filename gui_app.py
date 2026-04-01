import customtkinter as ctkinter
from tkinter import messagebox
from db_config import load_environment, fetch_api_metadata
from generator_service import GeneratorService
from pathlib import Path
import threading
import os
import logging  # <-- Novo import
from datetime import datetime

# --- CONFIGURAÇÃO DE LOGS EM FICHEIRO ---
LOG_DIR = Path(r"C:\python\IntegracaoPhyton_AS400\CreateAPIPackage\logs")
LOG_DIR.mkdir(parents=True, exist_ok=True)
LOG_FILE = LOG_DIR / "app_debug.log"

# Configura o logger para gravar no ficheiro com timestamp
logging.basicConfig(
    filename=LOG_FILE,
    level=logging.DEBUG,
    format='%(asctime)s - %(levelname)s - %(message)s',
    encoding='utf-8'
)
class App(ctkinter.CTk):
    def __init__(self):
        super().__init__()
        # ... (mesma configuração de UI anterior) ...
        self.title("Java Generator Pro - AS400 Edition")
        self.geometry("700x550")

        # 1. Seleção de Ambiente
        self.label_env = ctkinter.CTkLabel(self, text="1. Selecione o Ambiente (Ficheiro .env):",
                                           font=ctkinter.CTkFont(weight="bold"))
        self.label_env.pack(pady=(15, 0))

        self.env_menu = ctkinter.CTkOptionMenu(self, values=[".env.keve", ".env.bci"], width=200)
        self.env_menu.pack(pady=10)

        # 2. Input da API
        self.label_api = ctkinter.CTkLabel(self, text="2. Nome da Transação (API):",
                                           font=ctkinter.CTkFont(weight="bold"))
        self.label_api.pack(pady=(10, 0))

        self.entry_api = ctkinter.CTkEntry(self, placeholder_text="Ex: GB5425", width=300)
        self.entry_api.pack(pady=10)

        # 3. Log Console
        self.label_log = ctkinter.CTkLabel(self, text="Log de Execução:")
        self.label_log.pack(pady=(10, 0), padx=25, anchor="w")

        self.textbox = ctkinter.CTkTextbox(self, height=220, width=650, font=("Consolas", 12))
        self.textbox.pack(pady=5, padx=20)

        # 4. Botão Gerar
        self.btn_generate = ctkinter.CTkButton(self, text="🚀 GERAR FICHEIROS JAVA",
                                               command=self.start_generation_thread,
                                               fg_color="#2c3e50", hover_color="#34495e",
                                               height=40, font=ctkinter.CTkFont(weight="bold"))
        self.btn_generate.pack(pady=20)

    def log(self, message):
        """Grava no ficheiro de debug e mostra na interface"""
        timestamp = datetime.now().strftime("%H:%M:%S")

        # 1. Mostra na UI (Textbox)
        self.textbox.insert("end", f"[{timestamp}] > {message}\n")
        self.textbox.see("end")

        # 2. Grava no ficheiro C:\...\logs\app_debug.log
        logging.info(message)

    def start_generation_thread(self):
        api_name = self.entry_api.get().strip().upper()
        selected_env = self.env_menu.get()

        if not api_name:
            messagebox.showwarning("Aviso", "Por favor, introduza o nome da API.")
            return

        self.textbox.delete("1.0", "end")  # Limpa o log anterior
        self.btn_generate.configure(state="disabled")
        threading.Thread(target=self.run_generation, args=(api_name, selected_env), daemon=True).start()

    def run_generation(self, api_name, env_file):
        error_msg = None
        try:
            # Início do log de sessão no ficheiro
            logging.info(f"--- NOVA SESSÃO: {api_name} ---")
            self.log(f"Iniciando processo para: {api_name}")
            self.log(f"Carregando ambiente: {env_file}...")

            # 1. Carregar Variáveis
            if not os.path.exists(env_file):
                raise FileNotFoundError(f"Ficheiro {env_file} não encontrado na pasta raiz!")

            load_environment(env_file)
            self.log("OK: Variáveis de ambiente carregadas.")

            # 2. Query ao AS400
            self.log(f"Conectando ao DB e buscando metadados para {api_name}DS...")
            rows = fetch_api_metadata(api_name)

            if not rows:
                self.log("AVISO: Nenhuma linha retornada da Query (SEQ 10).")
                self.after(0, lambda: messagebox.showwarning("Aviso",
                                                             f"Não foram encontrados campos para a API {api_name} no ambiente {env_file}."))
                return

            self.log(f"OK: {len(rows)} campos encontrados na base de dados.")

            # 3. Processamento de Campos
            self.log("Processando tipos de dados (D$DELE)...")
            service = GeneratorService(api_name)
            service.process_metadata(rows)

            self.log(f"   -> Campos Simples: {len(service.normal_fields)}")
            self.log(f"   -> Campos Array: {len(service.array_fields)}")

            # 4. Criação de Diretório
            output_dir = Path("generated_java") / api_name
            output_dir.mkdir(parents=True, exist_ok=True)
            self.log(f"Diretório de saída: {output_dir.absolute()}")

            # 5. GERAÇÃO DOS FICHEIROS (A parte que faltava)
            self.log("Gerando código fonte Java...")

            files_to_create = {
                f"{api_name}Request.java": service.generate_request(),
                f"{api_name}Response.java": service.generate_response(),
                f"{api_name}Handler.java": service.generate_handler()
            }

            for filename, content in files_to_create.items():
                file_path = output_dir / filename
                file_path.write_text(content, encoding='utf-8')
                self.log(f"   [CRIADO]: {filename}")

            self.log(f"\n✅ SUCESSO TOTAL!")
            self.log(f"Ambiente utilizado: {env_file}")
            self.after(0, lambda: messagebox.showinfo("Sucesso",
                                                      f"Os 3 ficheiros Java para {api_name} foram criados com sucesso!"))

        except Exception as e:
            error_msg = str(e)
            logging.error(f"Erro na geração da API {api_name}: {error_msg}", exc_info=True)
            self.log(f"❌ ERRO CRÍTICO: {error_msg}")
            self.after(0, lambda m=error_msg: messagebox.showerror("Erro de Execução", m))

        finally:
            self.btn_generate.configure(state="normal")
            logging.info(f"--- FIM DA SESSÃO ---\n")


if __name__ == "__main__":
    app = App()
    app.mainloop()