DROP DATABASE anagrams; CREATE DATABASE anagrams WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252'; ALTER DATABASE anagrams OWNER TO postgres;
SET
statement_timeout = 0;
SET
lock_timeout = 0;
SET
idle_in_transaction_session_timeout = 0;
SET
client_encoding = 'UTF8';
SET
standard_conforming_strings = on;
SET
check_function_bodies = false;
SET
xmloption = content;
SET
client_min_messages = warning;
SET
row_security = off;
SET
default_table_access_method = heap; CREATE TABLE anagram ( anagram_text character varying(100) NOT NULL,
anagram_source_id bigint NOT NULL,
anagram_id bigint NOT NULL ); ALTER TABLE anagram OWNER TO postgres; CREATE SEQUENCE anagram_anagram_id_seq START WITH 1 INCREMENT
BY
1 NO MINVALUE NO MAXVALUE CACHE 1; ALTER TABLE anagram_anagram_id_seq OWNER TO postgres; ALTER SEQUENCE anagram_anagram_id_seq OWNED
BY
anagram.anagram_id; CREATE TABLE anagram_source ( id bigint NOT NULL,
anagram_source_text character varying NOT NULL ); ALTER TABLE public.anagram_source OWNER TO postgres; CREATE SEQUENCE anagram_source_id_seq START WITH 1 INCREMENT
BY
1 NO MINVALUE NO MAXVALUE CACHE 1; ALTER TABLE anagram_source_id_seq OWNER TO postgres; ALTER SEQUENCE anagram_source_id_seq OWNED
BY
anagram_source.id; ALTER TABLE ONLY anagram ALTER COLUMN anagram_id
SET
DEFAULT nextval('anagram_anagram_id_seq'::regclass); ALTER TABLE ONLY anagram_source ALTER COLUMN id
SET
DEFAULT nextval('anagram_source_id_seq'::regclass); ALTER TABLE ONLY anagram_source ADD CONSTRAINT anagram_source_pkey PRIMARY KEY (id); ALTER TABLE ONLY anagram -- ADD CONSTRAINT unique_anagram UNIQUE (anagram_text) INCLUDE (anagram_source_id); CREATE INDEX fki_anagram_source_fk
    ON anagram USING btree (anagram_source_id); ALTER TABLE ONLY anagram ADD CONSTRAINT anagram_source_fk FOREIGN KEY (anagram_source_id) REFERENCES anagram_source(id) NOT VALID;