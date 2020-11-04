-- public.anagram_source definition

-- Drop table

-- DROP TABLE public.anagram_source;

CREATE TABLE public.anagram_source (
	id int8 NOT NULL,
	anagram_source_text varchar NOT NULL,
	CONSTRAINT anagram_source_pk PRIMARY KEY (id)
);
CREATE INDEX anagram_source_id_idx ON public.anagram_source USING btree (id);


-- public.anagram definition

-- Drop table

-- DROP TABLE public.anagram;

CREATE TABLE public.anagram (
	anagram_text varchar(100) NOT NULL,
	anagram_source_id int8 NOT NULL,
	anagram_id int8 NOT NULL,
	CONSTRAINT anagram_pk PRIMARY KEY (anagram_id),
	CONSTRAINT anagram_fk FOREIGN KEY (anagram_source_id) REFERENCES anagram_source(id)
);