-- crie o BD oficina 
-- Exemplo de criacao de tabelas
    
CREATE TABLE IF NOT EXISTS public.cidade
(
    id SERIAL,
    nome character varying(80) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT cidade_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.cliente
(
    id SERIAL,
    nome character varying(80) COLLATE pg_catalog."default",
    cidade integer,
    CONSTRAINT cliente_pkey PRIMARY KEY (id),
    CONSTRAINT fg_cid FOREIGN KEY (cidade)
        REFERENCES public.cidade (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.produto
(
    id SERIAL,
    nome character varying(80) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT produto_pkey PRIMARY KEY (id)
);

-- fim do exemplo ---
