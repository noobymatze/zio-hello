CREATE SCHEMA IF NOT EXISTS hello;
SET search_path TO hello, pg_catalog, public;

CREATE TABLE hello.users (
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE
);
