--
-- PostgreSQL database dump
--

-- Dumped from database version 17.0
-- Dumped by pg_dump version 17.0

-- Started on 2025-06-01 20:17:23

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 4826 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 862 (class 1247 OID 90140)
-- Name: statusordem; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.statusordem AS ENUM (
    'PENDENTE',
    'EM_EXECUCAO',
    'CONCLUIDO'
);


ALTER TYPE public.statusordem OWNER TO postgres;

--
-- TOC entry 859 (class 1247 OID 90134)
-- Name: tipomanutencao; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.tipomanutencao AS ENUM (
    'PREVENTIVA',
    'CORRETIVA'
);


ALTER TYPE public.tipomanutencao OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 218 (class 1259 OID 90120)
-- Name: aeronave; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.aeronave (
    id integer NOT NULL,
    modelo character varying(50),
    fabricante character varying(50),
    anofabricacao integer,
    registronacional character varying(10)
);


ALTER TABLE public.aeronave OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 90119)
-- Name: aeronave_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.aeronave_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.aeronave_id_seq OWNER TO postgres;

--
-- TOC entry 4827 (class 0 OID 0)
-- Dependencies: 217
-- Name: aeronave_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.aeronave_id_seq OWNED BY public.aeronave.id;


--
-- TOC entry 220 (class 1259 OID 90127)
-- Name: mecanico; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.mecanico (
    id integer NOT NULL,
    nome character varying(50),
    registroanac character varying(50),
    especialidade character varying(30),
    ativo boolean
);


ALTER TABLE public.mecanico OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 90126)
-- Name: mecanico_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.mecanico_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.mecanico_id_seq OWNER TO postgres;

--
-- TOC entry 4828 (class 0 OID 0)
-- Dependencies: 219
-- Name: mecanico_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.mecanico_id_seq OWNED BY public.mecanico.id;


--
-- TOC entry 222 (class 1259 OID 90157)
-- Name: ordemdeservico; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ordemdeservico (
    id integer NOT NULL,
    descricaoservico text,
    tipomanutencao public.tipomanutencao,
    datasolicitacao date,
    dataconclusao date,
    status public.statusordem,
    aeronave_id integer,
    mecanico_id integer
);


ALTER TABLE public.ordemdeservico OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 90156)
-- Name: ordemdeservico_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ordemdeservico_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.ordemdeservico_id_seq OWNER TO postgres;

--
-- TOC entry 4829 (class 0 OID 0)
-- Dependencies: 221
-- Name: ordemdeservico_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ordemdeservico_id_seq OWNED BY public.ordemdeservico.id;


--
-- TOC entry 224 (class 1259 OID 98311)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    id integer NOT NULL,
    email character varying(80),
    senha character varying(80),
    ativo boolean
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 98310)
-- Name: usuario_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuario_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.usuario_id_seq OWNER TO postgres;

--
-- TOC entry 4830 (class 0 OID 0)
-- Dependencies: 223
-- Name: usuario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuario_id_seq OWNED BY public.usuario.id;


--
-- TOC entry 4662 (class 2604 OID 90123)
-- Name: aeronave id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.aeronave ALTER COLUMN id SET DEFAULT nextval('public.aeronave_id_seq'::regclass);


--
-- TOC entry 4663 (class 2604 OID 90130)
-- Name: mecanico id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mecanico ALTER COLUMN id SET DEFAULT nextval('public.mecanico_id_seq'::regclass);


--
-- TOC entry 4664 (class 2604 OID 90160)
-- Name: ordemdeservico id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordemdeservico ALTER COLUMN id SET DEFAULT nextval('public.ordemdeservico_id_seq'::regclass);


--
-- TOC entry 4665 (class 2604 OID 98314)
-- Name: usuario id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario ALTER COLUMN id SET DEFAULT nextval('public.usuario_id_seq'::regclass);


--
-- TOC entry 4667 (class 2606 OID 90125)
-- Name: aeronave aeronave_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.aeronave
    ADD CONSTRAINT aeronave_pkey PRIMARY KEY (id);


--
-- TOC entry 4669 (class 2606 OID 90132)
-- Name: mecanico mecanico_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mecanico
    ADD CONSTRAINT mecanico_pkey PRIMARY KEY (id);


--
-- TOC entry 4671 (class 2606 OID 90164)
-- Name: ordemdeservico ordemdeservico_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordemdeservico
    ADD CONSTRAINT ordemdeservico_pkey PRIMARY KEY (id);


--
-- TOC entry 4673 (class 2606 OID 98316)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- TOC entry 4674 (class 2606 OID 90165)
-- Name: ordemdeservico ordemdeservico_aeronave_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordemdeservico
    ADD CONSTRAINT ordemdeservico_aeronave_id_fkey FOREIGN KEY (aeronave_id) REFERENCES public.aeronave(id);


--
-- TOC entry 4675 (class 2606 OID 90170)
-- Name: ordemdeservico ordemdeservico_mecanico_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordemdeservico
    ADD CONSTRAINT ordemdeservico_mecanico_id_fkey FOREIGN KEY (mecanico_id) REFERENCES public.mecanico(id);


-- Completed on 2025-06-01 20:17:23

--
-- PostgreSQL database dump complete
--

