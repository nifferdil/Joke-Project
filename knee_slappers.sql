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
-- Name: categories; Type: TABLE; Schema: public; Owner: Guest; Tablespace:
--

CREATE TABLE categories (
    id integer NOT NULL,
    type character varying,
    location character varying
);


ALTER TABLE categories OWNER TO "Guest";

--
-- Name: categories_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE categories_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE categories_id_seq OWNER TO "Guest";

--
-- Name: categories_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE categories_id_seq OWNED BY categories.id;


--
-- Name: jokes; Type: TABLE; Schema: public; Owner: Guest; Tablespace:
--

CREATE TABLE jokes (
    id integer NOT NULL,
    questions character varying,
    answers character varying,
    hilarity integer
);


ALTER TABLE jokes OWNER TO "Guest";

--
-- Name: jokes_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE jokes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE jokes_id_seq OWNER TO "Guest";

--
-- Name: jokes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE jokes_id_seq OWNED BY jokes.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY categories ALTER COLUMN id SET DEFAULT nextval('categories_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY jokes ALTER COLUMN id SET DEFAULT nextval('jokes_id_seq'::regclass);


--
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY categories (id, type, location) FROM stdin;
\.


--
-- Name: categories_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('categories_id_seq', 1, false);


--
-- Data for Name: jokes; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY jokes (id, questions, answers, hilarity) FROM stdin;
\.


--
-- Name: jokes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('jokes_id_seq', 1, false);


--
-- Name: categories_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace:
--

ALTER TABLE ONLY categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (id);


--
-- Name: jokes_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace:
--

ALTER TABLE ONLY jokes
    ADD CONSTRAINT jokes_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--
