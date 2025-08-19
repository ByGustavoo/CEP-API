CREATE TABLE IF NOT EXISTS regioes (
    id SERIAL PRIMARY KEY,
    sigla VARCHAR(2) NOT NULL,
    nome VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS estados (
    id SERIAL PRIMARY KEY,
    id_regiao INT NOT NULL,
    sigla VARCHAR(2) NOT NULL,
    nome VARCHAR(20) NOT NULL,
    FOREIGN KEY (id_regiao) REFERENCES regioes(id)
);