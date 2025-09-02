Drop database virtualgym;
create database virtualgym;
use virtualgym;

/* Lógico_1: */

CREATE TABLE Aluno (
    Aluno_ID int not null PRIMARY KEY,
    Nome varchar(200) not null,
    Email varchar(200) not null,
    CPF varchar(11) not null,
    Peso decimal(5,2) not null,
    Telefone varchar(20) not null,
    Data_Nascimento date  not null,
    Genero char(1) not null,
    fk_Mensalidade_ID int,
    Data_Vencimento date not null,
    UNIQUE (Email, CPF)
);

CREATE TABLE Funcionario (
    Nome varchar(200) not null,
    Email varchar(200) not null,
    CPF varchar(14) not null,
    Telefone varchar(20) not null,
    Data_Nascimento Date not null,
    Genero char(1) not null,
    Cargo varchar(200) not null,
    Salario decimal(7,2) not null,
    Funcionario_ID int PRIMARY KEY,
    UNIQUE (Email, CPF)
);

CREATE TABLE Exercicios (
    Exercicio_ID int not null PRIMARY KEY,
    Grupo_Muscular varchar(255) not null,
    Nome varchar(255) not null
);

CREATE TABLE Mensalidade (
    Mensalidade_ID int PRIMARY KEY,
    Estado varchar(200) not null
);

CREATE TABLE Treino (
    Treino_ID int not null PRIMARY KEY,
    Serie tinyint not null,
    Repeticoes tinyint not null,
    fk_Funcionario_ID int
);

CREATE TABLE Historico (
    Historico_ID int PRIMARY KEY,
    Peso text not null,
    fk_Treino_ID int not null
);

CREATE TABLE Aluno_Treino (
    fk_Treino_ID int not null,
    fk_Aluno_ID int not null,
    Aluno_Treino_ID int PRIMARY KEY
);

CREATE TABLE Treino_Exercicios (
    fk_Treino_ID int not null,
    fk_Exercicio_ID int not null,
    Treino_Exercicios_ID int PRIMARY KEY
);
 
ALTER TABLE Aluno ADD CONSTRAINT FK_Aluno_2
    FOREIGN KEY (fk_Mensalidade_ID)
    REFERENCES Mensalidade (Mensalidade_ID)
    ON DELETE RESTRICT;
 
ALTER TABLE Treino ADD CONSTRAINT FK_Treino_2
    FOREIGN KEY (fk_Funcionario_ID)
    REFERENCES Funcionario (Funcionario_ID)
    ON DELETE RESTRICT;
 
ALTER TABLE Historico ADD CONSTRAINT FK_Historico_2
    FOREIGN KEY (fk_Treino_ID)
    REFERENCES Treino (Treino_ID)
    ON DELETE RESTRICT;
 
ALTER TABLE Aluno_Treino ADD CONSTRAINT FK_Aluno_Treino_1
    FOREIGN KEY (fk_Treino_ID)
    REFERENCES Treino (Treino_ID)
    ON DELETE RESTRICT;
 
ALTER TABLE Aluno_Treino ADD CONSTRAINT FK_Aluno_Treino_2
    FOREIGN KEY (fk_Aluno_ID)
    REFERENCES Aluno (Aluno_ID)
    ON DELETE RESTRICT;
 
ALTER TABLE Treino_Exercicios ADD CONSTRAINT FK_Treino_Exercicios_1
    FOREIGN KEY (fk_Treino_ID)
    REFERENCES Treino (Treino_ID)
    ON DELETE RESTRICT;
 
ALTER TABLE Treino_Exercicios ADD CONSTRAINT FK_Treino_Exercicios_2
    FOREIGN KEY (fk_Exercicio_ID)
    REFERENCES Exercicios (Exercicio_ID)
    ON DELETE RESTRICT;
    
create table funcionario_seq(
	next_val int primary key
);

create table aluno_seq(
	next_val int primary key
);

create table mensalidade_seq(
	next_val int primary key
);

create table treino_seq(
	next_val int primary key
);

create table exercicios_seq(
	next_val int primary key
);

create table historico_seq(
	next_val int primary key
);

create table aluno_treino_seq(
	next_val int primary key
);

create table treino_exercicios_seq(
	next_val int primary key
);

insert into funcionario_seq (next_val)value(1);
insert into aluno_seq (next_val)value(1);
insert into mensalidade_seq (next_val)value(1);
insert into treino_seq (next_val)value(1);
insert into exercicios_seq (next_val)value(1);
insert into historico_seq (next_val)value(1);
insert into aluno_treino_seq (next_val)value(1);
insert into treino_exercicios_seq (next_val)value(1);