-- DROP DATABASE IF EXISTS acdnbDB;
CREATE DATABASE acdnbDB;

USE smashDB;

CREATE TABLE usuario(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL,
    celular VARCHAR(11),
    data_nascimento DATE NOT NULL,
    nome_social VARCHAR(100),
    genero VARCHAR(20),
    telefone VARCHAR(11),
    cargo VARCHAR(50),
    data_inclusao DATE NOT NULL,
    usuario_inclusao_id INT,
    FOREIGN KEY (usuario_inclusao_id) REFERENCES usuario(id)
);

CREATE TABLE endereco(
    id INT AUTO_INCREMENT PRIMARY KEY,
    logradouro VARCHAR(100) NOT NULL,
    num_log VARCHAR(4) NOT NULL,
    bairro VARCHAR(50) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    estado VARCHAR(2) NOT NULL,
    cep VARCHAR(9) NOT NULL
)

CREATE TABLE responsavel(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf CHAR(11) NOT NULL,
    celular VARCHAR(11) NOT NULL,
    email VARCHAR(100) NOT NULL,
    rg VARCHAR(20) NOT NULL,
    telefone VARCHAR(11),
    nome_social VARCHAR(100),
    genero VARCHAR(20),
    profissao VARCHAR(50)
);

CREATE TABLE aluno(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    data_nascimento DATE NOT NULL,
    cpf CHAR(11),
    rg VARCHAR(20),
    nome_social VARCHAR(100),
    genero VARCHAR(20),
    celular VARCHAR(11),
    telefone VARCHAR(11),
    nacionalidade VARCHAR(50),
    naturalidade VARCHAR(50),
    profissao VARCHAR(50),
    deficiencia VARCHAR(100),
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    atestado BOOLEAN NOT NULL DEFAULT FALSE,
    autorizado BOOLEAN NOT NULL DEFAULT FALSE,
    data_inclusao DATE NOT NULL,
    endereco_id INT,
    usuario_inclusao_id INT,
    FOREIGN KEY (endereco_id) REFERENCES endereco(id),
    FOREIGN KEY (usuario_inclusao_id) REFERENCES usuario(id)
);

CREATE TABLE responsavel_aluno(
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_responsavel INT,
    id_aluno INT,
    FOREIGN KEY (id_responsavel) REFERENCES responsavel(id),
    FOREIGN KEY (id_aluno) REFERENCES aluno(id)
);

CREATE TABLE horario_preferencia(
    id INT AUTO_INCREMENT PRIMARY KEY,
    horario_aula_inicio TIME NOT NULL,
    horario_aula_fim TIME NOT NULL,
    data_inclusao DATE NOT NULL
);

CREATE TABLE lista_espera(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    celular VARCHAR(11) NOT NULL,
    data_nascimento DATE NOT NULL,
    nome_social VARCHAR(100),
    genero VARCHAR(20),
    telefone VARCHAR(11),
    data_inclusao DATE NOT NULL,
    data_interesse DATE NOT NULL,
    horario_preferencia_id INT,
    usuario_inclusao_id INT,
    FOREIGN KEY (horario_preferencia_id) REFERENCES horario_preferencia(id),
    FOREIGN KEY (usuario_inclusao_id) REFERENCES usuario(id)
);

CREATE TABLE valor_mensalidade(
    id INT AUTO_INCREMENT PRIMARY KEY,
    valor DECIMAL(10, 2) NOT NULL,
    manual BOOLEAN NOT NULL DEFAULT FALSE,
    desconto BOOLEAN NOT NULL DEFAULT FALSE,
    data_inclusao DATE NOT NULL
);

CREATE TABLE comprovante(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_remetente VARCHAR(100) NOT NULL,
    banco_origem VARCHAR(50) NOT NULL,
    data_envio DATE NOT NULL,
    conta_destino VARCHAR(20) NOT NULL,
    banco_destino VARCHAR(50) NOT NULL
);

CREATE TABLE mensalidade(
    id INT AUTO_INCREMENT PRIMARY KEY,
    data_vencimento DATE NOT NULL,
    data_pagamento DATE,
    status_pagamento ENUM('PENDENTE', 'PAGO', 'ATRASADO') NOT NULL DEFAULT 'PENDENTE',
    alteracao_automatica BOOLEAN NOT NULL DEFAULT FALSE,
    forma_pagamento ENUM('DINHEIRO', 'CARTAO', 'PIX'),
    aluno_id INT,
    valor_mensalidade_id INT,
    comprovante_id INT,
    FOREIGN KEY (aluno_id) REFERENCES aluno(id),
    FOREIGN KEY (valor_mensalidade_id) REFERENCES valor_mensalidade(id),
    FOREIGN KEY (comprovante_id) REFERENCES comprovante(id)
);