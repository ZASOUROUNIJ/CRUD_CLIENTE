DROP DATABASE  IF EXISTS sees;
CREATE DATABASE sees;
USE sees;

CREATE TABLE usuario(
 
	id_usuario INT AUTO_INCREMENT NOT NULL ,
	login   VARCHAR(30) NOT NULL,
	senha   VARCHAR(30) NOT NULL,
   	PRIMARY KEY(id_usuario)

);

CREATE TABLE clientePessoaFisica(
		id INT NOT NULL AUTO_INCREMENT,
        cpf VARCHAR(55),
        nome VARCHAR(255),
        login VARCHAR(55),
        senha VARCHAR(55),
        endereco VARCHAR(255),
        telefone VARCHAR(20),
        id_usuario_cadastro	INT,
        PRIMARY KEY(id),
        FOREIGN KEY(id_usuario_cadastro) REFERENCES usuario(id_usuario)
);

INSERT INTO usuario(login, senha) VALUES('admin','admin');

SELECT * FROM clientePessoaFisica;