--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: categories; Type: TABLE; Schema: public; Owner: nataliyamiller; Tablespace: 
--

CREATE TABLE categories (
    id integer NOT NULL,
    type character varying
);


ALTER TABLE categories OWNER TO nataliyamiller;

--
-- Name: categories_id_seq; Type: SEQUENCE; Schema: public; Owner: nataliyamiller
--

CREATE SEQUENCE categories_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE categories_id_seq OWNER TO nataliyamiller;

--
-- Name: categories_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: nataliyamiller
--

ALTER SEQUENCE categories_id_seq OWNED BY categories.id;


--
-- Name: jokes; Type: TABLE; Schema: public; Owner: nataliyamiller; Tablespace: 
--

CREATE TABLE jokes (
    id integer NOT NULL,
    hilarity integer DEFAULT 0,
    question character varying,
    answer character varying
);


ALTER TABLE jokes OWNER TO nataliyamiller;

--
-- Name: jokes_categories; Type: TABLE; Schema: public; Owner: nataliyamiller; Tablespace: 
--

CREATE TABLE jokes_categories (
    id integer NOT NULL,
    joke_id integer,
    category_id integer
);


ALTER TABLE jokes_categories OWNER TO nataliyamiller;

--
-- Name: jokes_categories_id_seq; Type: SEQUENCE; Schema: public; Owner: nataliyamiller
--

CREATE SEQUENCE jokes_categories_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE jokes_categories_id_seq OWNER TO nataliyamiller;

--
-- Name: jokes_categories_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: nataliyamiller
--

ALTER SEQUENCE jokes_categories_id_seq OWNED BY jokes_categories.id;


--
-- Name: jokes_id_seq; Type: SEQUENCE; Schema: public; Owner: nataliyamiller
--

CREATE SEQUENCE jokes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE jokes_id_seq OWNER TO nataliyamiller;

--
-- Name: jokes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: nataliyamiller
--

ALTER SEQUENCE jokes_id_seq OWNED BY jokes.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: nataliyamiller
--

ALTER TABLE ONLY categories ALTER COLUMN id SET DEFAULT nextval('categories_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: nataliyamiller
--

ALTER TABLE ONLY jokes ALTER COLUMN id SET DEFAULT nextval('jokes_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: nataliyamiller
--

ALTER TABLE ONLY jokes_categories ALTER COLUMN id SET DEFAULT nextval('jokes_categories_id_seq'::regclass);


--
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: nataliyamiller
--

COPY categories (id, type) FROM stdin;
1	Animal Jokes
3	Food Jokes
4	Office Jokes
6	Sport Jokes
7	Technology Jokes
8	Holiday Jokes
9	Miscellaneous Jokes
10	Yo Momma Jokes
11	Dark Humor
12	Money
5	School Laughing
2	Family Teasers
13	Simply Silly!
\.


--
-- Name: categories_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nataliyamiller
--

SELECT pg_catalog.setval('categories_id_seq', 13, true);


--
-- Data for Name: jokes; Type: TABLE DATA; Schema: public; Owner: nataliyamiller
--

COPY jokes (id, hilarity, question, answer) FROM stdin;
\.


--
-- Data for Name: jokes_categories; Type: TABLE DATA; Schema: public; Owner: nataliyamiller
--

COPY jokes_categories (id, joke_id, category_id) FROM stdin;
\.


--
-- Name: jokes_categories_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nataliyamiller
--

SELECT pg_catalog.setval('jokes_categories_id_seq', 1, false);


--
-- Name: jokes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nataliyamiller
--

SELECT pg_catalog.setval('jokes_id_seq', 1, true);


--
-- Name: categories_pkey; Type: CONSTRAINT; Schema: public; Owner: nataliyamiller; Tablespace: 
--

ALTER TABLE ONLY categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (id);


--
-- Name: jokes_categories_pkey; Type: CONSTRAINT; Schema: public; Owner: nataliyamiller; Tablespace: 
--

ALTER TABLE ONLY jokes_categories
    ADD CONSTRAINT jokes_categories_pkey PRIMARY KEY (id);


--
-- Name: jokes_pkey; Type: CONSTRAINT; Schema: public; Owner: nataliyamiller; Tablespace: 
--

ALTER TABLE ONLY jokes
    ADD CONSTRAINT jokes_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: nataliyamiller
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM nataliyamiller;
GRANT ALL ON SCHEMA public TO nataliyamiller;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

