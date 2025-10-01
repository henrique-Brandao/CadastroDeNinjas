-- V2: Migrations para adicionar a coluna de RANK na tabela de cadastros

ALTER TABLE tb_cadastro
ADD COLUMN Rank VARCHAR(255);