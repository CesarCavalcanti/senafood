create table cidade (
                        id bigint not null auto_increment,
                        nome varchar(60) not null,
                        estado_id bigint not null,

                        primary key (id)
) engine=InnoDB;

create table cozinha (
                         id bigint not null auto_increment,
                         nome varchar(255) not null,

                         primary key (id)
) engine=InnoDB;

CREATE TABLE estado (id bigint NOT NULL AUTO_INCREMENT,
                     nome varchar(255) NOT NULL,
                     PRIMARY KEY (id)) ENGINE=InnoDB;

CREATE TABLE forma_pagamento (id bigint NOT NULL AUTO_INCREMENT,
                              descricao varchar(255) NOT NULL,
                              PRIMARY KEY (id)) ENGINE=InnoDB;

CREATE TABLE grupo (id bigint NOT NULL AUTO_INCREMENT,
                    nome varchar(255) NOT NULL,
                    PRIMARY KEY (id)) ENGINE=InnoDB;

CREATE TABLE grupo_permissao (grupo_id bigint NOT NULL,
                              permissao_id bigint NOT NULL) ENGINE=InnoDB;

CREATE TABLE permissao (id bigint NOT NULL AUTO_INCREMENT,
                        descricao varchar(255) NOT NULL,
                        nome varchar(255) NOT NULL,
                        PRIMARY KEY (id)) ENGINE=InnoDB;

CREATE TABLE produto (id bigint NOT NULL AUTO_INCREMENT,
                      ativo bit NOT NULL,
                      descricao varchar(255) NOT NULL,
                      nome varchar(255) NOT NULL,
                      preco decimal(19, 2) NOT NULL,
                      restaurante_id bigint NOT NULL,
                      PRIMARY KEY (id)) ENGINE=InnoDB;

CREATE TABLE restaurante (id bigint NOT NULL AUTO_INCREMENT,
                          data_atualizacao datetime NOT NULL,
                          data_cadastro datetime NOT NULL,
                          endereco_bairro varchar(255),
                          endereco_cep varchar(255),
                          endereco_complemento varchar(255),
                          endereco_logradouro varchar(255),
                          endereco_numero varchar(255),
                          nome varchar(255) NOT NULL,
                          taxa_frete decimal(19, 2) NOT NULL,
                          cozinha_id bigint NOT NULL,
                          endereco_cidade_id bigint, PRIMARY KEY (id)) ENGINE=InnoDB;

CREATE TABLE restaurante_forma_pagamento (restaurante_id bigint NOT NULL,
                                          forma_pagamento_id bigint NOT NULL) ENGINE=InnoDB;

CREATE TABLE usuario (id bigint NOT NULL AUTO_INCREMENT,
                      data_cadastro datetime NOT NULL,
                      email varchar(255) NOT NULL,
                      nome varchar(255) NOT NULL,
                      senha varchar(255) NOT NULL,
                      PRIMARY KEY (id)) ENGINE=InnoDB;

CREATE TABLE usuario_grupo (usuario_id bigint NOT NULL,
                            grupo_id bigint NOT NULL) ENGINE=InnoDB;
