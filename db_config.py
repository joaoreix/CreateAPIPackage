import os
import pyodbc
from dotenv import load_dotenv

def load_environment(env_path):
    """Carrega um ficheiro .env específico e limpa o cache anterior"""
    load_dotenv(env_path, override=True)

def get_db_connection():
    # Agora os valores virão do .env que foi carregado por último
    conn_str = (
        f"DRIVER={os.getenv('DRIVER')};"
        f"SYSTEM={os.getenv('SYSTEM')};"
        f"UID={os.getenv('UID')};"
        f"PWD={os.getenv('PWD')};"
    )
    return pyodbc.connect(conn_str)


def fetch_api_metadata(api_name):
    library = os.getenv("LIBRARY")
    table_campos = os.getenv("TABLE_NAME")
    table_valores = os.getenv("TABLE_NAME1")
    entidade = f"{api_name}DS" if not api_name.endswith("DS") else api_name

    query = f"""
        WITH 
        CAMPOS AS (SELECT * FROM {library}.{table_campos} WHERE D$NENT = ?),
        VALORES AS (SELECT * FROM {library}.{table_valores} WHERE E$REF1 = ? AND E$SITU = 'V' AND E$SEQD = '10')

        SELECT C.D$NENT, C.D$NELE, C.D$DELE FROM CAMPOS C
        INNER JOIN VALORES V ON C.D$NENT = V.E$REF1 AND C.D$NELE = V.E$REF2
    """

    with get_db_connection() as conn:
        cursor = conn.cursor()
        cursor.execute(query, (entidade, entidade))
        return cursor.fetchall()