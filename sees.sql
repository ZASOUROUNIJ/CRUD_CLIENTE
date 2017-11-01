DROP DATABASE  IF EXISTS sees;
CREATE DATABASE sees;
USE sees;

CREATE TABLE clientePessoaFisica(
		id INT NOT NULL AUTO_INCREMENT,
        cpf VARCHAR(55),
        nome VARCHAR(255),
        login VARCHAR(55),
        senha VARCHAR(55),
        endereco VARCHAR(255),
        telefone VARCHAR(20),
        PRIMARY KEY(id)
);
