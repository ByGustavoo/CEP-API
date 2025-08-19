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

CREATE TABLE IF NOT EXISTS cidades (
   id SERIAL PRIMARY KEY,
   id_estado INT NOT NULL,
   cidade VARCHAR NOT NULL,
   codigo_ibge INT NOT NULL,
   FOREIGN KEY (id_estado) REFERENCES estados(id)
);