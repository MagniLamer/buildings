DROP SCHEMA IF EXISTS building CASCADE;
CREATE SCHEMA building;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TABLE IF EXISTS building.buildings;
CREATE TABLE IF NOT EXISTS building.buildings
(
    id          uuid                                           NOT NULL DEFAULT uuid_generate_v4(),
    name        character varying COLLATE pg_catalog."default" ,
    address_id  bigint                                         ,
    description character varying COLLATE pg_catalog."default" ,
    CONSTRAINT buildings_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS building.address CASCADE;
CREATE TABLE building.address
(
    id           serial PRIMARY KEY,
    street       character varying COLLATE pg_catalog."default" ,
    number       integer                                        ,
    postal_code  character varying COLLATE pg_catalog."default" ,
    city         character varying COLLATE pg_catalog."default" ,
    country      character varying COLLATE pg_catalog."default" ,
    coordinate_x character varying COLLATE pg_catalog."default",
    coordinate_y character varying COLLATE pg_catalog."default",
    building_id  uuid
);

ALTER TABLE building.address
    ADD CONSTRAINT "FK_BUILDING_ID" FOREIGN KEY (building_id)
        REFERENCES building.buildings (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID;