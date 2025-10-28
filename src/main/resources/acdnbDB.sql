-- DROP DATABASE IF EXISTS smashDB;
CREATE DATABASE acdnbDB;

USE acdnbDB;

CREATE TABLE usuario(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL,
    celular VARCHAR(14),
    data_nascimento DATE NOT NULL,
    nome_social VARCHAR(100),
    genero VARCHAR(20),
    telefone VARCHAR(14),
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
);

CREATE TABLE responsavel(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf CHAR(11) NOT NULL,
    celular VARCHAR(14) NOT NULL,
    email VARCHAR(100) NOT NULL,
    rg VARCHAR(20) NOT NULL,
    telefone VARCHAR(14),
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
    celular VARCHAR(14),
    telefone VARCHAR(14),
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
    celular VARCHAR(14) NOT NULL,
    data_nascimento DATE NOT NULL,
    nome_social VARCHAR(100),
    genero VARCHAR(20),
    telefone VARCHAR(14),
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

insert into usuario
    (nome, email, senha, celular, data_nascimento, data_inclusao)
values
    ('User', 'user@adm.com', '$2a$10$UM8lVJYL2yz5nhvlcD6Oh.vQkGEl/klH..96PzoVwd3HYXzvD33k.', '(11)99999-9999', '1990-01-01', now());

insert into valor_mensalidade
    (data_inclusao, valor, desconto, manual)
values
    (now(), 120.00, false, false);

-- insert into horario_pref
--     (horario_aula_inicio, horario_aula_fim, data_inclusao)
-- values
--     ('14:00', '17:00', now()),
--     ('18:00', '20:00', now()),
--     ('20:00', '22:00', now());
--
-- insert into usuario
--     (nome, email, senha, celular, data_nascimento, genero, deletado, usuario_inclusao_id, data_inclusao)
-- values
--     ('Walter', 'walter@acdnb.com', '$2a$10$UM8lVJYL2yz5nhvlcD6Oh.vQkGEl/klH..96PzoVwd3HYXzvD33k.', '(11) 98380-6989', '1969-03-02', 'Masculino', false, 1, now()),
--     ('Lucas Militão', 'militao@acdnb.com', '$2a$10$UM8lVJYL2yz5nhvlcD6Oh.vQkGEl/klH..96PzoVwd3HYXzvD33k.', '(11) 98380-6989', '1995-01-12', 'Masculino', false, 1, now()),
--     ('Akira', 'akira@acdnb.com', '$2a$10$UM8lVJYL2yz5nhvlcD6Oh.vQkGEl/klH..96PzoVwd3HYXzvD33k.', '(11) 98380-6989', '1974-12-11', 'Masculino', false, 1, now()),
--     ('Paulo', 'paulo@acdnb.com', '$2a$10$UM8lVJYL2yz5nhvlcD6Oh.vQkGEl/klH..96PzoVwd3HYXzvD33k.', '(11) 98380-6989', '1965-04-10', 'Masculino', false, 1, now());
--
-- -- Massa para apresentação
-- insert into lista_espera
--     (nome, email, celular, data_nascimento, genero, data_interesse, horario_pref_id, usuario_inclusao_id, data_inclusao)
-- values
--     ('Theo Daniel Ferreira', 'theo.ferreira@gmail.com', '(11) 98234-2127', '1998-05-15', 'Masculino', '2025-05-18', 1, 1, '2025-05-18');
--
INSERT INTO endereco
    (logradouro, num_log, bairro, cidade, estado, cep)
VALUES
    ('Avenida Maria Dias', 776, 'Jardim São Carlos', 'Bebedouro', 'SP', '14702-248'),
    ('Rua Capitão João Carlos', 864, 'Nossa Senhora do Ó', 'São Paulo', 'SP', '02926-060'),
    ('Rua Vitório', 288, 'Jardim Julieta', 'Itapevi', 'SP', '06653-400'),
    ('Rua Felício Atala', 457, 'Jardim Flórida', 'Bauru', 'SP', '17024-650'),
    ('Rua Odilon Chaves', 742, 'Jardim Nazareth', 'São Paulo', 'SP', '08150-560'),
    ('Avenida Maria Lopes Castilho', 952, 'Vila Espírito Santo', 'Sorocaba', 'SP', '18051-410'),
    ('Rua Rio dos Cedros', 849, 'Jardim Peri', 'São Paulo', 'SP', '02679-060'),
    ('Rua Joaquim Matos', 113, 'Vila Mangalot', 'São Paulo', 'SP', '05131-010'),
    ('Rua Antônio Fernando Arruda Moraes', 670, 'Vila Arruda', 'Itapetininga', 'SP', '18212-110'),
    ('Rua João Batista Carri', 949, 'Parque Residencial Maria Stella Faga', 'São Carlos', 'SP', '13568-410');
--
INSERT INTO aluno
    (nome, email, data_nascimento, cpf, rg, genero, celular, nacionalidade, naturalidade, telefone, profissao, ativo, atestado, deficiencia, autorizado, endereco_id, data_inclusao)
VALUES
    ('Giovanna Julia Assis', 'giovanna-assis81@gmail.com', '1979-06-09', '14389008803', '10.036.757-4', 'Feminino', '(17)98171-3456', 'Brasileira', 'Bebedouro', null, 'Engenheira de Software', true, true, 'Daltonismo', true, 1, '2025-06-01 09:00:00'),
    ('Yuri Enrico Thales Duarte', 'yuri_duarte@gmail.com', '1989-03-17', '08582254849', '11.874.451-3', 'Masculino', '(11)98755-1988', 'Brasileira', 'São Paulo', '(11) 2635-2938', 'Técnico de Enfermagem', true, true, null, true, 2, '2025-06-01 09:00:00'),
    ('Lucca Raimundo dos Santos', 'lucca.raimundo.dossantos@outlook.com', '1999-06-02', '08499656838', '26.150.856-8', 'Masculino', '(11)99574-1639', 'Brasileira', 'Itapevi', '(11) 2783-6298', 'Marceneiro', true, true, null, true, 3, '2025-06-01 09:00:00'),
    ('Sérgio Manuel Márcio da Mata', 'sergio_damata@hotmail.com', '1997-08-25', '18053949835', '48.206.398-1', 'Masculino', '(14)99518-6976', 'Brasileira', 'Bauru', '(14) 3630-6113', 'Estagiário', true, true, null, true, 4, '2025-06-01 09:00:00'),
    ('Carlos Eduardo Iago Ramos', 'carloseduardoramos@outlook.com', '1960-07-02', '07195674835', '15.526.220-8', 'Masculino', '(11)99788-5434', 'Brasileira', 'São Paulo', null, 'Designer Gráfico', true, true,'Perda auditiva parcial', true, 5, '2025-06-01 09:00:00'),
    ('Bruna Stefany Almeida', 'bruna.stefany.almeida@gmail.com', '1990-02-06', '63192900806', '48.688.760-1', 'Feminino', '(15)98726-7162', 'Brasileira', 'Sorocaba', '(15) 2625-4607', 'Pintora', true, true, null, true, 6, '2025-06-01 09:00:00'),
    ('Osvaldo Joaquim Julio Lopes', 'osvaldojoaquimlopes@hotmail.com', '2002-06-06', '58373390863', '22.492.238-5', 'Masculino', '(11)98748-1114', 'Brasileira', 'São Paulo', '(11) 2534-6517', 'Professor', true, true, 'Baixa visão', true, 7, '2025-06-01 09:00:00'),
    ('Lorena Rebeca Eliane Monteiro', 'lorena.rebeca.monteiro@hotmail.com', '1967-04-07', '39173602841', '33.813.771-3', 'Feminino', '(11)98619-7210', 'Brasileira', 'São Paulo', '(11) 2653-5312', 'Arquiteta', true, true, null, true, 8, '2025-06-01 09:00:00'),
    ('Julio Thomas Peixoto', 'julio_thomas_peixoto@gmail.com', '1974-06-12', '38544057829', '27.755.336-2', 'Masculino', '(15)99812-7129', 'Brasileira', 'Itapetininga', '(15) 3610-5532', 'Chef de Cozinha', true, true, null, true, 9, '2025-06-01 09:00:00'),
    ('Samuel Martin Fogaça', 'samuel.martin.fogaca@outlook.com', '1965-03-26', '39023853830', '42.596.317-2', 'Masculino', '(16)99122-7178', 'Brasileira', 'São Carlos', null, 'Analista de Dados', true, true, null, true, 10, '2025-06-01 09:00:00');
--

-- Aluno ID 1
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (1, 1, '2025-10-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (1, 1, '2025-11-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (1, 1, '2025-12-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (1, 1, '2026-01-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (1, 1, '2026-02-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (1, 1, '2026-03-12', 'PENDENTE', false);

-- Aluno ID 2
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (2, 1, '2025-10-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (2, 1, '2025-11-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (2, 1, '2025-12-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (2, 1, '2026-01-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (2, 1, '2026-02-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (2, 1, '2026-03-12', 'PENDENTE', false);

-- Aluno ID 3
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (3, 1, '2025-10-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (3, 1, '2025-11-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (3, 1, '2025-12-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (3, 1, '2026-01-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (3, 1, '2026-02-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (3, 1, '2026-03-12', 'PENDENTE', false);

-- Aluno ID 4
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (4, 1, '2025-10-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (4, 1, '2025-11-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (4, 1, '2025-12-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (4, 1, '2026-01-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (4, 1, '2026-02-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (4, 1, '2026-03-12', 'PENDENTE', false);

-- Aluno ID 5
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (5, 1, '2025-10-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (5, 1, '2025-11-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (5, 1, '2025-12-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (5, 1, '2026-01-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (5, 1, '2026-02-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (5, 1, '2026-03-12', 'PENDENTE', false);

-- Aluno ID 6
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (6, 1, '2025-10-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (6, 1, '2025-11-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (6, 1, '2025-12-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (6, 1, '2026-01-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (6, 1, '2026-02-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (6, 1, '2026-03-12', 'PENDENTE', false);

-- Aluno ID 7
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (7, 1, '2025-10-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (7, 1, '2025-11-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (7, 1, '2025-12-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (7, 1, '2026-01-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (7, 1, '2026-02-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (7, 1, '2026-02-12', 'PENDENTE', false);

-- Aluno ID 8
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (8, 1, '2025-10-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (8, 1, '2025-11-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (8, 1, '2025-12-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (8, 1, '2026-01-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (8, 1, '2026-02-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (8, 1, '2026-03-12', 'PENDENTE', false);

-- Aluno ID 9
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (9, 1, '2025-10-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (9, 1, '2025-11-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (9, 1, '2025-12-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (9, 1, '2026-01-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (9, 1, '2026-02-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (9, 1, '2026-03-12', 'PENDENTE', false);

-- Aluno ID 10
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (10, 1, '2025-10-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (10, 1, '2025-11-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (10, 1, '2025-12-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (10, 1, '2026-01-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (10, 1, '2026-02-12', 'PENDENTE', false);
INSERT INTO mensalidade (aluno_id, valor_mensalidade_id, data_vencimento, status_pagamento, alteracao_automatica) VALUES (10, 1, '2026-03-12', 'PENDENTE', false);


-- insert into valor_mensalidade
--     (data_inclusao, valor, desconto, manual)
-- values
--     (now(), 110.00, true, false);

-- UPDATE mensalidade
-- SET data_pagamento = '2025-06-01 10:00:00', status_pagamento = 'PAGO', forma_pagamento = 'Pix', alteracao_automatica = true, valor_mensalidade_id = 2
-- WHERE aluno_id in (3,5,7) AND data_vencimento = '2025-12-12';
--
-- UPDATE mensalidade
-- SET data_pagamento = '2025-06-09 00:00:00', status_pagamento = 'PAGO', forma_pagamento = 'Pix', alteracao_automatica = true
-- WHERE aluno_id in (3,5,7,8,10) AND data_vencimento = '2025-10-12';
--
-- UPDATE mensalidade
-- SET data_pagamento = '2025-06-09 00:00:00', status_pagamento = 'PAGO', forma_pagamento = 'Pix', alteracao_automatica = true
-- WHERE aluno_id in (3,5,7,8,10) AND data_vencimento = '2025-11-12';
--
-- UPDATE mensalidade
-- SET data_pagamento = '2025-06-09 00:00:00', status_pagamento = 'ATRASADO', forma_pagamento = 'Pix', alteracao_automatica = true
-- WHERE aluno_id in (1,2,4,6,9) AND data_vencimento = '2025-11-12';
--
-- UPDATE mensalidade
-- SET data_pagamento = '2025-04-10 00:00:00', status_pagamento = 'PAGO', forma_pagamento = 'Pix', alteracao_automatica = true
-- WHERE aluno_id in (1,2,3,4,5) AND data_vencimento = '2025-10-12';
--
-- UPDATE mensalidade
-- SET data_pagamento = '2025-04-20 00:00:00', status_pagamento = 'PAGO', forma_pagamento = 'Pix', alteracao_automatica = true
-- WHERE aluno_id in (6,7,8,9,10) AND data_vencimento = '2025-10-12';
