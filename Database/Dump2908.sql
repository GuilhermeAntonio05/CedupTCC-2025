Drop database virtualgym;
create database virtualgym;
use virtualgym;

/* Lógico_1: */

CREATE TABLE aluno (
    Aluno_ID int PRIMARY KEY auto_increment,
    Nome varchar(200) not null,
    Email varchar(200) not null unique,
    CPF varchar(14) not null unique,
    Peso decimal(5,2) not null,
    Telefone varchar(20) not null,
    Data_Nascimento date  not null,
    Senha varchar(200) not null,
    fk_Mensalidade_ID int ,
    Data_Vencimento date not null,
    Data_Inscricao date not null,
    Genero enum("f","m") default "m" not null
);

CREATE TABLE funcionario (
    Nome varchar(200) not null,
    Email varchar(200) not null unique,
    CPF varchar(14) not null unique,
    Telefone varchar(20) not null,
    Data_Nascimento Date not null,
    Genero enum("f","m") default "m" not null,
    Cargo varchar(200) not null,
    Salario decimal(7,2) not null,
    Senha varchar(200) not null,
    Funcionario_ID int PRIMARY KEY auto_increment
);

CREATE TABLE exercicios (
    Exercicio_ID int PRIMARY KEY auto_increment,
    Grupo_Muscular varchar(255) not null,
    Nome varchar(255) not null,
    Link_Video_Exemplo text
);

CREATE TABLE mensalidade (
    Mensalidade_ID int PRIMARY KEY auto_increment,
    Estado varchar(200) not null UNIQUE
);

CREATE TABLE treino (
    Treino_ID int PRIMARY KEY auto_increment,
    Serie tinyint not null,
    Repeticoes tinyint not null,
    fk_Funcionario_ID int,
    fk_Exercicios_ID int
);

CREATE TABLE historico (
    Historico_ID int PRIMARY KEY auto_increment,
    Peso text not null,
    Data_Treino datetime not null,
    fk_Aluno_Treino_ID int
);

CREATE TABLE mensalidade_Seq (
    next_val int 
);

CREATE TABLE aluno_Seq (
    next_val int 
);

CREATE TABLE historico_Seq (
    next_val int 
);

CREATE TABLE treino_Seq (
    next_val int 
);

CREATE TABLE exercicios_Seq (
    next_val int 
);

CREATE TABLE funcionario_Seq (
    next_val int 
);

CREATE TABLE aluno_treino (
    Aluno_Treino_ID int auto_increment auto_increment PRIMARY KEY,
    fk_Aluno_ID int,
    fk_Treino_ID int
);

CREATE TABLE aluno_treino_Seq (
    next_val int 
);
 
ALTER TABLE aluno ADD CONSTRAINT FK_Aluno_2
    FOREIGN KEY (fk_Mensalidade_ID)
    REFERENCES Mensalidade (Mensalidade_ID)
    ON DELETE RESTRICT;
 
ALTER TABLE treino ADD CONSTRAINT FK_Treino_2
    FOREIGN KEY (fk_Funcionario_ID)
    REFERENCES Funcionario (Funcionario_ID)
    ON DELETE RESTRICT;
 
ALTER TABLE treino ADD CONSTRAINT FK_Treino_3
    FOREIGN KEY (fk_Exercicios_ID)
    REFERENCES Exercicios (Exercicio_ID);
 
ALTER TABLE historico ADD CONSTRAINT FK_Historico_2
    FOREIGN KEY (fk_Aluno_Treino_ID)
    REFERENCES aluno_treino (Aluno_Treino_ID)
    ON DELETE RESTRICT;
 
ALTER TABLE aluno_treino ADD CONSTRAINT FK_Aluno_Treino_2
    FOREIGN KEY (fk_Aluno_ID)
    REFERENCES Aluno (Aluno_ID);
    
ALTER TABLE Aluno_treino ADD CONSTRAINT FK_Aluno_Treino_3
    FOREIGN KEY (fk_Treino_ID)
    REFERENCES Treino (Treino_ID);

insert into funcionario_seq (next_val)value(1);
insert into aluno_seq (next_val)value(1);
insert into mensalidade_seq (next_val)value(1);
insert into treino_seq (next_val)value(1);
insert into exercicios_seq (next_val)value(1);
insert into historico_seq (next_val)value(1);
insert into aluno_treino_seq (next_val)value(1);
-- insert into treino_exercicios_seq (next_val)value(1);

insert into mensalidade (Estado) 
values ("pago"),("pendente"),("cancelado");

CREATE TABLE IF NOT EXISTS exercicios (
    Exercicio_ID int PRIMARY KEY auto_increment,
    Grupo_Muscular varchar(255) not null,
    Nome varchar(255) not null,
    Link_Video_Exemplo text
);

-- PEITO
INSERT INTO exercicios (Grupo_Muscular, Nome, Link_Video_Exemplo) VALUES
('Peito', 'Supino reto', 'https://www.youtube.com/watch?v=Cjh2fIMQHk0'),
('Peito', 'Supino inclinado', 'https://www.youtube.com/watch?v=WwPlIeUDeow'),
('Peito', 'Crucifixo com halteres', 'https://www.youtube.com/watch?v=ZjIKUMtW37c'),
('Peito', 'Crossover na polia', 'https://www.youtube.com/watch?v=pdMWt71MPlw'),
('Peito', 'Flexão de braço', 'https://www.youtube.com/watch?v=Ps8x8c__A3M'),
('Peito', 'Peck deck', 'https://www.youtube.com/watch?v=34WzvXIqv0w');

-- TRÍCEPS
INSERT INTO exercicios (Grupo_Muscular, Nome, Link_Video_Exemplo) VALUES
('Triceps', 'Tríceps testa', 'https://www.youtube.com/watch?v=VakpIeaaeXA'),
('Triceps', 'Tríceps na polia', 'https://www.youtube.com/watch?v=3gYbjJ5ZoJM'),
('Triceps', 'Mergulho entre bancos', 'https://www.youtube.com/watch?v=jH9RXQjbXqs'),
('Triceps', 'Tríceps coice', 'https://www.youtube.com/watch?v=oEborayJqP4'),
('Triceps', 'Tríceps francês', 'https://www.youtube.com/watch?v=0M-1walYH4Y'),
('Triceps', 'Tríceps banco', 'https://www.youtube.com/watch?v=dZTn_ZlDrEQ');

-- ABDÔMEN
INSERT INTO exercicios (Grupo_Muscular, Nome, Link_Video_Exemplo) VALUES
('Abdomen', 'Abdominal reto', 'https://www.youtube.com/watch?v=8Q7T7Wsok-w'),
('Abdomen', 'Prancha frontal', 'https://www.youtube.com/watch?v=pvIjsG5Svck'),
('Abdomen', 'Abdominal infra', 'https://www.youtube.com/watch?v=JB2oyawG9KI'),
('Abdomen', 'Elevação de pernas', 'https://www.youtube.com/watch?v=l4kQd9eWclE'),
('Abdomen', 'Abdominal oblíquo', 'https://www.youtube.com/watch?v=QW20cBOzayE'),
('Abdomen', 'Abdominal com bola', 'https://www.youtube.com/watch?v=Xy-HaYFQOCM');

-- COSTAS
INSERT INTO exercicios (Grupo_Muscular, Nome, Link_Video_Exemplo) VALUES
('Costas', 'Puxada frontal', 'https://www.youtube.com/watch?v=CAwf7n6Luuc'),
('Costas', 'Remada curvada', 'https://www.youtube.com/watch?v=vT2GjY_Umpw'),
('Costas', 'Remada unilateral', 'https://www.youtube.com/watch?v=pYcpY20QaE8'),
('Costas', 'Levantamento terra', 'https://www.youtube.com/watch?v=op9kVnSso6Q'),
('Costas', 'Barra fixa', 'https://www.youtube.com/watch?v=3YvfRx31xDE'),
('Costas', 'Pullover', 'https://www.youtube.com/watch?v=OfIv7B0QzX8');


-- QUADRÍCEPS
INSERT INTO exercicios (Grupo_Muscular, Nome, Link_Video_Exemplo) VALUES
('Quadriceps', 'Agachamento livre', 'https://www.youtube.com/watch?v=aclHkVaku9U'),
('Quadriceps', 'Cadeira extensora', 'https://www.youtube.com/watch?v=YyzLJpK-LZU'),
('Quadriceps', 'Leg press', 'https://www.youtube.com/watch?v=IZxyjW7MPJQ'),
('Quadriceps', 'Avanço com barra', 'https://www.youtube.com/watch?v=QOVaaCMA9uE'),
('Quadriceps', 'Agachamento frontal', 'https://www.youtube.com/watch?v=6xwYI6K0bEs'),
('Quadriceps', 'Step up', 'https://www.youtube.com/watch?v=KC2yGY_o0vE');


-- GLÚTEOS
INSERT INTO exercicios (Grupo_Muscular, Nome, Link_Video_Exemplo) VALUES
('Gluteos', 'Glúteo na máquina', 'https://www.youtube.com/watch?v=eJzHpg7Yuqw'),
('Gluteos', 'Elevação pélvica', 'https://www.youtube.com/watch?v=SEdqd1n0cvg'),
('Gluteos', 'Agachamento sumô', 'https://www.youtube.com/watch?v=2vjPBrBU-TM'),
('Gluteos', 'Cadeira abdutora', 'https://www.youtube.com/watch?v=IcJr0QXBdag'),
('Gluteos', 'Stiff', 'https://www.youtube.com/watch?v=6qZ-cXo5wUE'),
('Gluteos', 'Kickback', 'https://www.youtube.com/watch?v=UMQv0f7Z0qA');


-- POSTERIOR DE COXA
INSERT INTO exercicios (Grupo_Muscular, Nome, Link_Video_Exemplo) VALUES
('Posterior', 'Mesa flexora', 'https://www.youtube.com/watch?v=oVf8-1qfH7E'),
('Posterior', 'Stiff com barra', 'https://www.youtube.com/watch?v=_oyxCn2iSjU'),
('Posterior', 'Cadeira flexora', 'https://www.youtube.com/watch?v=Ny3F60OQ7xE'),
('Posterior', 'Good morning', 'https://www.youtube.com/watch?v=vKPGeFRH2jI'),
('Posterior', 'Agachamento sumô', 'https://www.youtube.com/watch?v=2vjPBrBU-TM'),
('Posterior', 'Flexão nórdica', 'https://www.youtube.com/watch?v=E4NChx3pN9k');


-- PANTURRILHAS
INSERT INTO exercicios (Grupo_Muscular, Nome, Link_Video_Exemplo) VALUES
('Panturrilhas', 'Gêmeos em pé', 'https://www.youtube.com/watch?v=YMmgqO8Jo-k'),
('Panturrilhas', 'Gêmeos sentado', 'https://www.youtube.com/watch?v=Z5Q21u2Wl8U'),
('Panturrilhas', 'Subida em degrau', 'https://www.youtube.com/watch?v=YMmgqO8Jo-k'),
('Panturrilhas', 'Pular corda', 'https://www.youtube.com/watch?v=cY0RZ1iiuqA'),
('Panturrilhas', 'Gêmeos no leg press', 'https://www.youtube.com/watch?v=6Y3I8yWqG1U'),
('Panturrilhas', 'Gêmeos unilateral', 'https://www.youtube.com/watch?v=iGdcYVn0AAA');


-- OMBROS
INSERT INTO exercicios (Grupo_Muscular, Nome, Link_Video_Exemplo) VALUES
('Ombros', 'Desenvolvimento com halteres', 'https://www.youtube.com/watch?v=B-aVuyhvLHU'),
('Ombros', 'Elevação lateral', 'https://www.youtube.com/watch?v=3VcKaXpzqRo'),
('Ombros', 'Elevação frontal', 'https://www.youtube.com/watch?v=-t7fuZ0KhDA'),
('Ombros', 'Remada alta', 'https://www.youtube.com/watch?v=5nzRP1-Bzps'),
('Ombros', 'Desenvolvimento militar', 'https://www.youtube.com/watch?v=xt5mx5vCEmU'),
('Ombros', 'Arnold press', 'https://www.youtube.com/watch?v=vj2w851ZHRM');


-- BÍCEPS
INSERT INTO exercicios (Grupo_Muscular, Nome, Link_Video_Exemplo) VALUES
('Biceps', 'Rosca direta', 'https://www.youtube.com/watch?v=ykJmrZ5v0Oo'),
('Biceps', 'Rosca alternada', 'https://www.youtube.com/watch?v=soxrZlIl35U'),
('Biceps', 'Rosca martelo', 'https://www.youtube.com/watch?v=TwD-YGVP4Bk'),
('Biceps', 'Rosca concentrada', 'https://www.youtube.com/watch?v=ebq5O6K7e9I'),
('Biceps', 'Rosca 21', 'https://www.youtube.com/watch?v=H3L5Jt76y7Y'),
('Biceps', 'Rosca Scott', 'https://www.youtube.com/watch?v=F6xvHXuJZvM');

-- ANTEBRAÇO
INSERT INTO exercicios (Grupo_Muscular, Nome, Link_Video_Exemplo) VALUES
('Antebraco', 'Rosca inversa', 'https://www.youtube.com/watch?v=WLjI4UJumiE'),
('Antebraco', 'Rosca punho sentado', 'https://www.youtube.com/watch?v=zrxh2lcD-lE'),
('Antebraco', 'Farmer’s walk', 'https://www.youtube.com/watch?v=j81dJciRj9g'),
('Antebraco', 'Toalha pull-up', 'https://www.youtube.com/watch?v=CwmxZy0-yQY'),
('Antebraco', 'Extensão de punho', 'https://www.youtube.com/watch?v=Yy9DTKqF_gY'),
('Antebraco', 'Pronação e supinação com halteres', 'https://www.youtube.com/watch?v=73xupKjvAoU');

INSERT INTO Treino (Serie, Repeticoes, fk_Funcionario_ID, fk_Exercicios_ID) VALUES
(3, 8, NULL, 1), (3, 10, NULL, 1), (3, 12, NULL, 1),
(3, 8, NULL, 2), (3, 10, NULL, 2), (3, 12, NULL, 2),
(3, 8, NULL, 3), (3, 10, NULL, 3), (3, 12, NULL, 3),
(3, 8, NULL, 4), (3, 10, NULL, 4), (3, 12, NULL, 4),
(3, 8, NULL, 5), (3, 10, NULL, 5), (3, 12, NULL, 5),
(3, 8, NULL, 6), (3, 10, NULL, 6), (3, 12, NULL, 6),
(3, 8, NULL, 7), (3, 10, NULL, 7), (3, 12, NULL, 7),
(3, 8, NULL, 8), (3, 10, NULL, 8), (3, 12, NULL, 8),
(3, 8, NULL, 9), (3, 10, NULL, 9), (3, 12, NULL, 9),
(3, 8, NULL, 10), (3, 10, NULL, 10), (3, 12, NULL, 10),
(3, 8, NULL, 11), (3, 10, NULL, 11), (3, 12, NULL, 11),
(3, 8, NULL, 12), (3, 10, NULL, 12), (3, 12, NULL, 12),
(3, 8, NULL, 13), (3, 10, NULL, 13), (3, 12, NULL, 13),
(3, 8, NULL, 14), (3, 10, NULL, 14), (3, 12, NULL, 14),
(3, 8, NULL, 15), (3, 10, NULL, 15), (3, 12, NULL, 15),
(3, 8, NULL, 16), (3, 10, NULL, 16), (3, 12, NULL, 16),
(3, 8, NULL, 17), (3, 10, NULL, 17), (3, 12, NULL, 17),
(3, 8, NULL, 18), (3, 10, NULL, 18), (3, 12, NULL, 18),
(3, 8, NULL, 19), (3, 10, NULL, 19), (3, 12, NULL, 19),
(3, 8, NULL, 20), (3, 10, NULL, 20), (3, 12, NULL, 20),
(3, 8, NULL, 21), (3, 10, NULL, 21), (3, 12, NULL, 21),
(3, 8, NULL, 22), (3, 10, NULL, 22), (3, 12, NULL, 22),
(3, 8, NULL, 23), (3, 10, NULL, 23), (3, 12, NULL, 23),
(3, 8, NULL, 24), (3, 10, NULL, 24), (3, 12, NULL, 24),
(3, 8, NULL, 25), (3, 10, NULL, 25), (3, 12, NULL, 25),
(3, 8, NULL, 26), (3, 10, NULL, 26), (3, 12, NULL, 26),
(3, 8, NULL, 27), (3, 10, NULL, 27), (3, 12, NULL, 27),
(3, 8, NULL, 28), (3, 10, NULL, 28), (3, 12, NULL, 28),
(3, 8, NULL, 29), (3, 10, NULL, 29), (3, 12, NULL, 29),
(3, 8, NULL, 30), (3, 10, NULL, 30), (3, 12, NULL, 30),
(3, 8, NULL, 31), (3, 10, NULL, 31), (3, 12, NULL, 31),
(3, 8, NULL, 32), (3, 10, NULL, 32), (3, 12, NULL, 32),
(3, 8, NULL, 33), (3, 10, NULL, 33), (3, 12, NULL, 33),
(3, 8, NULL, 34), (3, 10, NULL, 34), (3, 12, NULL, 34),
(3, 8, NULL, 35), (3, 10, NULL, 35), (3, 12, NULL, 35),
(3, 8, NULL, 36), (3, 10, NULL, 36), (3, 12, NULL, 36),
(3, 8, NULL, 37), (3, 10, NULL, 37), (3, 12, NULL, 37),
(3, 8, NULL, 38), (3, 10, NULL, 38), (3, 12, NULL, 38),
(3, 8, NULL, 39), (3, 10, NULL, 39), (3, 12, NULL, 39),
(3, 8, NULL, 40), (3, 10, NULL, 40), (3, 12, NULL, 40),
(3, 8, NULL, 41), (3, 10, NULL, 41), (3, 12, NULL, 41),
(3, 8, NULL, 42), (3, 10, NULL, 42), (3, 12, NULL, 42),
(3, 8, NULL, 43), (3, 10, NULL, 43), (3, 12, NULL, 43),
(3, 8, NULL, 44), (3, 10, NULL, 44), (3, 12, NULL, 44),
(3, 8, NULL, 45), (3, 10, NULL, 45), (3, 12, NULL, 45),
(3, 8, NULL, 46), (3, 10, NULL, 46), (3, 12, NULL, 46),
(3, 8, NULL, 47), (3, 10, NULL, 47), (3, 12, NULL, 47),
(3, 8, NULL, 48), (3, 10, NULL, 48), (3, 12, NULL, 48),
(3, 8, NULL, 49), (3, 10, NULL, 49), (3, 12, NULL, 49),
(3, 8, NULL, 50), (3, 10, NULL, 50), (3, 12, NULL, 50),
(3, 8, NULL, 51), (3, 10, NULL, 51), (3, 12, NULL, 51),
(3, 8, NULL, 52), (3, 10, NULL, 52), (3, 12, NULL, 52),
(3, 8, NULL, 53), (3, 10, NULL, 53), (3, 12, NULL, 53),
(3, 8, NULL, 54), (3, 10, NULL, 54), (3, 12, NULL, 54),
(3, 8, NULL, 55), (3, 10, NULL, 55), (3, 12, NULL, 55),
(3, 8, NULL, 56), (3, 10, NULL, 56), (3, 12, NULL, 56),
(3, 8, NULL, 57), (3, 10, NULL, 57), (3, 12, NULL, 57),
(3, 8, NULL, 58), (3, 10, NULL, 58), (3, 12, NULL, 58),
(3, 8, NULL, 59), (3, 10, NULL, 59), (3, 12, NULL, 59),
(3, 8, NULL, 60), (3, 10, NULL, 60), (3, 12, NULL, 60),
(3, 8, NULL, 61), (3, 10, NULL, 61), (3, 12, NULL, 61),
(3, 8, NULL, 62), (3, 10, NULL, 62), (3, 12, NULL, 62),
(3, 8, NULL, 63), (3, 10, NULL, 63), (3, 12, NULL, 63),
(3, 8, NULL, 64), (3, 10, NULL, 64), (3, 12, NULL, 64),
(3, 8, NULL, 65), (3, 10, NULL, 65), (3, 12, NULL, 65),
(3, 8, NULL, 66), (3, 10, NULL, 66), (3, 12, NULL, 66);