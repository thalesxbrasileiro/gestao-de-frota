CREATE DATABASE gestao_de_frota;

CREATE TABLE gestao_de_frota.cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    telefone VARCHAR(20)
);

CREATE TABLE gestao_de_frota.veiculo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(100) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    ano VARCHAR(4) NOT NULL,
    placa VARCHAR(20) UNIQUE NOT NULL,
    cliente_id INT,
    FOREIGN KEY (cliente_id) REFERENCES gestao_de_frota.cliente(id)
);
