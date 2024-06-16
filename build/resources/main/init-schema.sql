DROP SCHEMA IF EXISTS building CASCADE;
CREATE SCHEMA building;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TABLE IF EXISTS building.buildings;
CREATE TABLE IF NOT EXISTS building.buildings
(
    id           uuid NOT NULL DEFAULT uuid_generate_v4(),
    name         character varying COLLATE pg_catalog."default",
    street       character varying COLLATE pg_catalog."default",
    number       character varying COLLATE pg_catalog."default",
    postal_code  character varying COLLATE pg_catalog."default",
    city         character varying COLLATE pg_catalog."default",
    country      character varying COLLATE pg_catalog."default",
    coordinate_x character varying COLLATE pg_catalog."default",
    coordinate_y character varying COLLATE pg_catalog."default",
    description  character varying COLLATE pg_catalog."default",
    CONSTRAINT buildings_pkey PRIMARY KEY (id)
);