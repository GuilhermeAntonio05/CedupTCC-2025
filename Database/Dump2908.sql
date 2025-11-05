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
    Nome varchar(255) not null
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
values ("pago"),("atrasado"),("cancelado");

-- Torax
INSERT INTO exercicios (Grupo_Muscular, Nome) VALUES
('Peito', 'Supino reto'),
('Peito', 'Supino inclinado'),
('Peito', 'Crucifixo com halteres'),
('Peito', 'Crossover na polia'),
('Peito', 'Flexão de braço'),
('Peito', 'Peck deck');

-- Tríceps
INSERT INTO exercicios (Grupo_Muscular, Nome) VALUES
('Triceps', 'Tríceps testa'),
('Triceps', 'Tríceps na polia'),
('Triceps', 'Mergulho entre bancos'),
('Triceps', 'Tríceps coice'),
('Triceps', 'Tríceps francês'),
('Triceps', 'Tríceps banco');

-- Abdomen
INSERT INTO exercicios (Grupo_Muscular, Nome) VALUES
('Abdomen', 'Abdominal reto'),
('Abdomen', 'Prancha frontal'),
('Abdomen', 'Abdominal infra'),
('Abdomen', 'Elevação de pernas'),
('Abdomen', 'Abdominal oblíquo'),
('Abdomen', 'Abdominal com bola');

-- Costas
INSERT INTO exercicios (Grupo_Muscular, Nome) VALUES
('Costas', 'Puxada frontal'),
('Costas', 'Remada curvada'),
('Costas', 'Remada unilateral'),
('Costas', 'Levantamento terra'),
('Costas', 'Barra fixa'),
('Costas', 'Pullover');

-- Quadríceps
INSERT INTO exercicios (Grupo_Muscular, Nome) VALUES
('Quadriceps', 'Agachamento livre'),
('Quadriceps', 'Cadeira extensora'),
('Quadriceps', 'Leg press'),
('Quadriceps', 'Avanço com barra'),
('Quadriceps', 'Agachamento frontal'),
('Quadriceps', 'Step up');

-- Glúteos
INSERT INTO exercicios (Grupo_Muscular, Nome) VALUES
('Gluteos', 'Glúteo na máquina'),
('Gluteos', 'Elevação pélvica'),
('Gluteos', 'Agachamento sumô'),
('Gluteos', 'Cadeira abdutora'),
('Gluteos', 'Stiff'),
('Gluteos', 'Kickback');

-- Posterior de coxa (Hamstring)
INSERT INTO exercicios (Grupo_Muscular, Nome) VALUES
('Posterior', 'Mesa flexora'),
('Posterior', 'Stiff com barra'),
('Posterior', 'Cadeira flexora'),
('Posterior', 'Good morning'),
('Posterior', 'Agachamento sumô'),
('Posterior', 'Flexão nórdica');

-- Panturrilhas (Calf)
INSERT INTO exercicios (Grupo_Muscular, Nome) VALUES
('Panturrilhas', 'Gêmeos em pé'),
('Panturrilhas', 'Gêmeos sentado'),
('Panturrilhas', 'Subida em degrau'),
('Panturrilhas', 'Pular corda'),
('Panturrilhas', 'Gêmeos no leg press'),
('Panturrilhas', 'Gêmeos unilateral');

-- Ombros
INSERT INTO exercicios (Grupo_Muscular, Nome) VALUES
('Ombros', 'Desenvolvimento com halteres'),
('Ombros', 'Elevação lateral'),
('Ombros', 'Elevação frontal'),
('Ombros', 'Remada alta'),
('Ombros', 'Desenvolvimento militar'),
('Ombros', 'Arnold press');

-- Bíceps
INSERT INTO exercicios (Grupo_Muscular, Nome) VALUES
('Biceps', 'Rosca direta'),
('Biceps', 'Rosca alternada'),
('Biceps', 'Rosca martelo'),
('Biceps', 'Rosca concentrada'),
('Biceps', 'Rosca 21'),
('Biceps', 'Rosca Scott');

-- Antebraço
INSERT INTO exercicios (Grupo_Muscular, Nome) VALUES
('Antebraco', 'Rosca inversa'),
('Antebraco', 'Rosca punho sentado'),
('Antebraco', 'Farmer’s walk'),
('Antebraco', 'Toalha pull-up'),
('Antebraco', 'Extensão de punho'),
('Antebraco', 'Pronação e supinação com halteres');

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