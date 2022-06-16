# Executando o projeto.
Primeiramente, crie as tabelas no banco de dados com o seguinte código.

```sql
CREATE TABLE t_ht_usuario (
    cd_usuario NUMBER NOT NULL,
    nome       VARCHAR2(60) NOT NULL,
    idade      INT NOT NULL,
    dt_nasc    DATE NOT NULL,
    email      VARCHAR2(100) NOT NULL,
    altura     NUMBER(3, 2) NOT NULL,
    senha      VARCHAR2(100) NOT NULL,
    sexo       CHAR,
    CONSTRAINT usuario_pk PRIMARY KEY ( cd_usuario )
);

CREATE TABLE t_ht_registro_corporal (
    cd_registro NUMBER NOT NULL,
    cd_usuario  NUMBER NOT NULL,
    peso        NUMBER(4, 1) NOT NULL,
    dt_registro DATE NOT NULL,
    CONSTRAINT registro_corporal_pk PRIMARY KEY ( cd_registro ),
    CONSTRAINT t_usuario_fk FOREIGN KEY ( cd_usuario )
        REFERENCES t_ht_usuario ( cd_usuario )
);

CREATE TABLE t_ht_atividade_fisica (
    cd_atividade   NUMBER NOT NULL,
    cd_usuario     NUMBER NOT NULL,
    tipo_atividade VARCHAR2(40) NOT NULL,
    dt_atividade   DATE NOT NULL,
    duracao        DATE NOT NULL,
    nome_atividade VARCHAR2(60) NOT NULL,
    calorias       NUMBER(4),
    descricao      VARCHAR2(150),
    CONSTRAINT t_atividade_fisica_pk PRIMARY KEY ( cd_atividade ),
    CONSTRAINT t_usuario_t_atividade_fk FOREIGN KEY ( cd_usuario )
        REFERENCES t_ht_usuario ( cd_usuario )
);

CREATE TABLE t_ht_ingestao_agua (
    cd_ingestao NUMBER NOT NULL,
    cd_usuario  NUMBER NOT NULL,
    dt_ingestao DATE NOT NULL,
    qtd_agua    NUMBER(4, 3) NOT NULL,
    CONSTRAINT t_ingestao_agua_pk PRIMARY KEY ( cd_ingestao ),
    CONSTRAINT t_ingestao_agua_t_usuario_fk FOREIGN KEY ( cd_usuario )
        REFERENCES t_ht_usuario ( cd_usuario )
);

CREATE TABLE t_ht_refeicao (
    cd_refeicao   NUMBER NOT NULL,
    cd_usuario    NUMBER NOT NULL,
    dt_refeicao   DATE NOT NULL,
    desc_refeicao VARCHAR(200) NOT NULL,
    nome_refeicao VARCHAR(50) NOT NULL,
    CONSTRAINT t_refeicao_pk PRIMARY KEY ( cd_refeicao ),
    CONSTRAINT t_refeicao_t_usuario_fk FOREIGN KEY ( cd_usuario )
        REFERENCES t_ht_usuario ( cd_usuario )
);

CREATE TABLE t_ht_alimento (
    cd_alimento              NUMBER NOT NULL,
    nome_alimento            VARCHAR(50) NOT NULL,
    calorias_alimento        NUMBER(4) NOT NULL,
    macronutrientes_alimento VARCHAR(250) NOT NULL,
    CONSTRAINT t_ht_alimento_pk PRIMARY KEY ( cd_alimento )
);

CREATE TABLE t_ht_refeicao_alimento (
    cd_refeicao_alimento NUMBER NOT NULL,
    cd_refeicao          NUMBER NOT NULL,
    cd_alimento          NUMBER NOT NULL,
    quantidade           NUMBER(4) NOT NULL,
    tipo_medidade        VARCHAR(20) NOT NULL,
    CONSTRAINT t_ht_refeicao_alimentos_pk PRIMARY KEY ( cd_refeicao_alimento ),
    CONSTRAINT t_refeicao_alimentos_t_alimentos_fk FOREIGN KEY ( cd_alimento )
        REFERENCES t_ht_alimento ( cd_alimento ),
    CONSTRAINT t_refeicao_alimentos_t_refeicao_fk FOREIGN KEY ( cd_refeicao )
        REFERENCES t_ht_refeicao ( cd_refeicao )
);

CREATE SEQUENCE sq_t_ht_usuario MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE sq_t_ht_atividade_fisica MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE sq_t_ht_alimento MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE sq_t_ht_ingestao_agua MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE sq_t_ht_refeicao MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE sq_t_ht_refeicao_alimento MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE sq_t_ht_registro_corporal MINVALUE 1 START WITH 1 INCREMENT BY 1;


-- Caso seja necessário dropar alguma tabela.
DROP TABLE t_ht_refeicao_alimento;

DROP TABLE t_ht_alimento;

DROP TABLE t_ht_refeicao;

DROP TABLE t_ht_ingestao_agua;

DROP TABLE t_ht_atividade_fisica;

DROP TABLE t_ht_registro_corporal;

DROP TABLE t_ht_usuario;
```