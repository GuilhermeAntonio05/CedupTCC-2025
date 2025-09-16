Drop database virtualgym;
create database virtualgym;
use virtualgym;

/* Lógico_1: */

CREATE TABLE Aluno (
    Aluno_ID int not null PRIMARY KEY auto_increment,
    Nome varchar(200) not null,
    Email varchar(200) not null UNIQUE,
    CPF varchar(11) not null UNIQUE,
    Peso decimal(5,2) not null,
    Telefone varchar(20) not null,
    Data_Nascimento date  not null,
    Senha varchar(200) not null,
    fk_Mensalidade_ID int,
    Data_Vencimento date not null,
    Genero char(1) not null
);

CREATE TABLE Funcionario (
    Nome varchar(200) not null,
    Email varchar(200) not null UNIQUE,
    CPF varchar(14) not null UNIQUE,
    Telefone varchar(20) not null,
    Data_Nascimento Date not null,
    Genero char(1) not null,
    Cargo varchar(200) not null,
    Salario decimal(7,2) not null,
    Funcionario_ID int PRIMARY KEY auto_increment
);

CREATE TABLE Exercicios (
    Exercicio_ID int not null PRIMARY KEY auto_increment,
    Grupo_Muscular varchar(255) not null,
    Nome varchar(255) not null
);

CREATE TABLE Mensalidade (
    Mensalidade_ID int PRIMARY KEY auto_increment,
    Estado varchar(200) not null UNIQUE
);

CREATE TABLE Treino (
    Treino_ID int not null PRIMARY KEY auto_increment,
    Serie tinyint not null,
    Repeticoes tinyint not null,
    fk_Funcionario_ID int
);

CREATE TABLE Historico (
    Historico_ID int PRIMARY KEY auto_increment,
    Peso text not null,
    fk_Treino_ID int not null
);

CREATE TABLE Aluno_Treino (
    fk_Treino_ID int not null,
    fk_Aluno_ID int not null,
    Aluno_Treino_ID int PRIMARY KEY auto_increment
);

CREATE TABLE Treino_Exercicios (
    fk_Treino_ID int not null,
    fk_Exercicio_ID int not null,
    Treino_Exercicios_ID int PRIMARY KEY auto_increment
);

CREATE TABLE Mensalidade_Seq (
    next_val int 
);

CREATE TABLE Aluno_Seq (
    next_val int 
);

CREATE TABLE Historico_Seq (
    next_val int 
);

CREATE TABLE Aluno_Treino_Seq (
    next_val int 
);

CREATE TABLE Treino_Seq (
    next_val int 
);

CREATE TABLE Treino_Exercicios_Seq (
    next_val int 
);

CREATE TABLE Exercicios_Seq (
    next_val int 
);

CREATE TABLE Funcionario_Seq (
    next_val int 
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
insert into funcionario_seq (next_val)value(1);
insert into aluno_seq (next_val)value(1);
insert into mensalidade_seq (next_val)value(1);
insert into treino_seq (next_val)value(1);
insert into exercicios_seq (next_val)value(1);
insert into historico_seq (next_val)value(1);
insert into aluno_treino_seq (next_val)value(1);
insert into treino_exercicios_seq (next_val)value(1);