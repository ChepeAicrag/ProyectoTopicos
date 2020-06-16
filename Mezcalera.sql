--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.26
-- Dumped by pg_dump version 9.4.26
-- Started on 2020-06-16 18:12:13

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;

--
-- TOC entry 8 (class 2615 OID 22319)
-- Name: mezcal; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA mezcal;


ALTER SCHEMA mezcal OWNER TO postgres;

--
-- TOC entry 1 (class 3079 OID 11855)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2127 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 192 (class 1259 OID 22389)
-- Name: cliente; Type: TABLE; Schema: mezcal; Owner: postgres; Tablespace: 
--

CREATE TABLE mezcal.cliente (
    id_cliente integer NOT NULL,
    nombre character varying(30),
    razon_social character varying(30)
);


ALTER TABLE mezcal.cliente OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 22387)
-- Name: cliente_id_cliente_seq; Type: SEQUENCE; Schema: mezcal; Owner: postgres
--

CREATE SEQUENCE mezcal.cliente_id_cliente_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mezcal.cliente_id_cliente_seq OWNER TO postgres;

--
-- TOC entry 2128 (class 0 OID 0)
-- Dependencies: 191
-- Name: cliente_id_cliente_seq; Type: SEQUENCE OWNED BY; Schema: mezcal; Owner: postgres
--

ALTER SEQUENCE mezcal.cliente_id_cliente_seq OWNED BY mezcal.cliente.id_cliente;


--
-- TOC entry 178 (class 1259 OID 22333)
-- Name: cortador; Type: TABLE; Schema: mezcal; Owner: postgres; Tablespace: 
--

CREATE TABLE mezcal.cortador (
    id_cortador integer NOT NULL,
    nombre character varying(30),
    estado character varying(30)
);


ALTER TABLE mezcal.cortador OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 22331)
-- Name: cortador_id_cortador_seq; Type: SEQUENCE; Schema: mezcal; Owner: postgres
--

CREATE SEQUENCE mezcal.cortador_id_cortador_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mezcal.cortador_id_cortador_seq OWNER TO postgres;

--
-- TOC entry 2129 (class 0 OID 0)
-- Dependencies: 177
-- Name: cortador_id_cortador_seq; Type: SEQUENCE OWNED BY; Schema: mezcal; Owner: postgres
--

ALTER SEQUENCE mezcal.cortador_id_cortador_seq OWNED BY mezcal.cortador.id_cortador;


--
-- TOC entry 186 (class 1259 OID 22365)
-- Name: destilador; Type: TABLE; Schema: mezcal; Owner: postgres; Tablespace: 
--

CREATE TABLE mezcal.destilador (
    id_destilador integer NOT NULL,
    nombre character varying(30),
    estado character varying(30)
);


ALTER TABLE mezcal.destilador OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 22363)
-- Name: destilador_id_destilador_seq; Type: SEQUENCE; Schema: mezcal; Owner: postgres
--

CREATE SEQUENCE mezcal.destilador_id_destilador_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mezcal.destilador_id_destilador_seq OWNER TO postgres;

--
-- TOC entry 2130 (class 0 OID 0)
-- Dependencies: 185
-- Name: destilador_id_destilador_seq; Type: SEQUENCE OWNED BY; Schema: mezcal; Owner: postgres
--

ALTER SEQUENCE mezcal.destilador_id_destilador_seq OWNED BY mezcal.destilador.id_destilador;


--
-- TOC entry 188 (class 1259 OID 22373)
-- Name: enbotelladora; Type: TABLE; Schema: mezcal; Owner: postgres; Tablespace: 
--

CREATE TABLE mezcal.enbotelladora (
    id_enbotelladora integer NOT NULL,
    nombre character varying(30),
    estado character varying(30)
);


ALTER TABLE mezcal.enbotelladora OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 22371)
-- Name: enbotelladora_id_enbotelladora_seq; Type: SEQUENCE; Schema: mezcal; Owner: postgres
--

CREATE SEQUENCE mezcal.enbotelladora_id_enbotelladora_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mezcal.enbotelladora_id_enbotelladora_seq OWNER TO postgres;

--
-- TOC entry 2131 (class 0 OID 0)
-- Dependencies: 187
-- Name: enbotelladora_id_enbotelladora_seq; Type: SEQUENCE OWNED BY; Schema: mezcal; Owner: postgres
--

ALTER SEQUENCE mezcal.enbotelladora_id_enbotelladora_seq OWNED BY mezcal.enbotelladora.id_enbotelladora;


--
-- TOC entry 184 (class 1259 OID 22357)
-- Name: fermentador; Type: TABLE; Schema: mezcal; Owner: postgres; Tablespace: 
--

CREATE TABLE mezcal.fermentador (
    id_fermentador integer NOT NULL,
    nombre character varying(30),
    estado character varying(30)
);


ALTER TABLE mezcal.fermentador OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 22355)
-- Name: fermentador_id_fermentador_seq; Type: SEQUENCE; Schema: mezcal; Owner: postgres
--

CREATE SEQUENCE mezcal.fermentador_id_fermentador_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mezcal.fermentador_id_fermentador_seq OWNER TO postgres;

--
-- TOC entry 2132 (class 0 OID 0)
-- Dependencies: 183
-- Name: fermentador_id_fermentador_seq; Type: SEQUENCE OWNED BY; Schema: mezcal; Owner: postgres
--

ALTER SEQUENCE mezcal.fermentador_id_fermentador_seq OWNED BY mezcal.fermentador.id_fermentador;


--
-- TOC entry 194 (class 1259 OID 22397)
-- Name: gradoalcohol; Type: TABLE; Schema: mezcal; Owner: postgres; Tablespace: 
--

CREATE TABLE mezcal.gradoalcohol (
    id_grado integer NOT NULL,
    valor double precision
);


ALTER TABLE mezcal.gradoalcohol OWNER TO postgres;

--
-- TOC entry 193 (class 1259 OID 22395)
-- Name: gradoalcohol_id_grado_seq; Type: SEQUENCE; Schema: mezcal; Owner: postgres
--

CREATE SEQUENCE mezcal.gradoalcohol_id_grado_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mezcal.gradoalcohol_id_grado_seq OWNER TO postgres;

--
-- TOC entry 2133 (class 0 OID 0)
-- Dependencies: 193
-- Name: gradoalcohol_id_grado_seq; Type: SEQUENCE OWNED BY; Schema: mezcal; Owner: postgres
--

ALTER SEQUENCE mezcal.gradoalcohol_id_grado_seq OWNED BY mezcal.gradoalcohol.id_grado;


--
-- TOC entry 180 (class 1259 OID 22341)
-- Name: horno; Type: TABLE; Schema: mezcal; Owner: postgres; Tablespace: 
--

CREATE TABLE mezcal.horno (
    id_horno integer NOT NULL,
    nombre character varying(30),
    estado character varying(30)
);


ALTER TABLE mezcal.horno OWNER TO postgres;

--
-- TOC entry 179 (class 1259 OID 22339)
-- Name: horno_id_horno_seq; Type: SEQUENCE; Schema: mezcal; Owner: postgres
--

CREATE SEQUENCE mezcal.horno_id_horno_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mezcal.horno_id_horno_seq OWNER TO postgres;

--
-- TOC entry 2134 (class 0 OID 0)
-- Dependencies: 179
-- Name: horno_id_horno_seq; Type: SEQUENCE OWNED BY; Schema: mezcal; Owner: postgres
--

ALTER SEQUENCE mezcal.horno_id_horno_seq OWNED BY mezcal.horno.id_horno;


--
-- TOC entry 176 (class 1259 OID 22324)
-- Name: maguey; Type: TABLE; Schema: mezcal; Owner: postgres; Tablespace: 
--

CREATE TABLE mezcal.maguey (
    id_maguey integer NOT NULL,
    nombre character varying(30),
    cantidadpinia integer NOT NULL
);


ALTER TABLE mezcal.maguey OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 22322)
-- Name: maguey_cantidadpinia_seq; Type: SEQUENCE; Schema: mezcal; Owner: postgres
--

CREATE SEQUENCE mezcal.maguey_cantidadpinia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mezcal.maguey_cantidadpinia_seq OWNER TO postgres;

--
-- TOC entry 2135 (class 0 OID 0)
-- Dependencies: 175
-- Name: maguey_cantidadpinia_seq; Type: SEQUENCE OWNED BY; Schema: mezcal; Owner: postgres
--

ALTER SEQUENCE mezcal.maguey_cantidadpinia_seq OWNED BY mezcal.maguey.cantidadpinia;


--
-- TOC entry 174 (class 1259 OID 22320)
-- Name: maguey_id_maguey_seq; Type: SEQUENCE; Schema: mezcal; Owner: postgres
--

CREATE SEQUENCE mezcal.maguey_id_maguey_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mezcal.maguey_id_maguey_seq OWNER TO postgres;

--
-- TOC entry 2136 (class 0 OID 0)
-- Dependencies: 174
-- Name: maguey_id_maguey_seq; Type: SEQUENCE OWNED BY; Schema: mezcal; Owner: postgres
--

ALTER SEQUENCE mezcal.maguey_id_maguey_seq OWNED BY mezcal.maguey.id_maguey;


--
-- TOC entry 182 (class 1259 OID 22349)
-- Name: molino; Type: TABLE; Schema: mezcal; Owner: postgres; Tablespace: 
--

CREATE TABLE mezcal.molino (
    id_molino integer NOT NULL,
    nombre character varying(30),
    estado character varying(30)
);


ALTER TABLE mezcal.molino OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 22347)
-- Name: molino_id_molino_seq; Type: SEQUENCE; Schema: mezcal; Owner: postgres
--

CREATE SEQUENCE mezcal.molino_id_molino_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mezcal.molino_id_molino_seq OWNER TO postgres;

--
-- TOC entry 2137 (class 0 OID 0)
-- Dependencies: 181
-- Name: molino_id_molino_seq; Type: SEQUENCE OWNED BY; Schema: mezcal; Owner: postgres
--

ALTER SEQUENCE mezcal.molino_id_molino_seq OWNED BY mezcal.molino.id_molino;


--
-- TOC entry 202 (class 1259 OID 22421)
-- Name: tanda; Type: TABLE; Schema: mezcal; Owner: postgres; Tablespace: 
--

CREATE TABLE mezcal.tanda (
    id_tanda integer NOT NULL,
    tipomaguey integer NOT NULL,
    gradoalcohol integer NOT NULL,
    tipomezcal integer NOT NULL,
    cantidadpinias integer NOT NULL,
    status character varying(20),
    id_cortador integer,
    id_horno integer,
    id_molino integer,
    id_fermentador integer,
    id_destilador integer,
    id_enbotelladora integer,
    id_cliente integer,
    fecha_inicio timestamp with time zone,
    fecha_final timestamp with time zone,
    id_transportista integer
);


ALTER TABLE mezcal.tanda OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 22419)
-- Name: tanda_cantidadpinias_seq; Type: SEQUENCE; Schema: mezcal; Owner: postgres
--

CREATE SEQUENCE mezcal.tanda_cantidadpinias_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mezcal.tanda_cantidadpinias_seq OWNER TO postgres;

--
-- TOC entry 2138 (class 0 OID 0)
-- Dependencies: 201
-- Name: tanda_cantidadpinias_seq; Type: SEQUENCE OWNED BY; Schema: mezcal; Owner: postgres
--

ALTER SEQUENCE mezcal.tanda_cantidadpinias_seq OWNED BY mezcal.tanda.cantidadpinias;


--
-- TOC entry 199 (class 1259 OID 22415)
-- Name: tanda_gradoalcohol_seq; Type: SEQUENCE; Schema: mezcal; Owner: postgres
--

CREATE SEQUENCE mezcal.tanda_gradoalcohol_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mezcal.tanda_gradoalcohol_seq OWNER TO postgres;

--
-- TOC entry 2139 (class 0 OID 0)
-- Dependencies: 199
-- Name: tanda_gradoalcohol_seq; Type: SEQUENCE OWNED BY; Schema: mezcal; Owner: postgres
--

ALTER SEQUENCE mezcal.tanda_gradoalcohol_seq OWNED BY mezcal.tanda.gradoalcohol;


--
-- TOC entry 197 (class 1259 OID 22411)
-- Name: tanda_id_tanda_seq; Type: SEQUENCE; Schema: mezcal; Owner: postgres
--

CREATE SEQUENCE mezcal.tanda_id_tanda_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mezcal.tanda_id_tanda_seq OWNER TO postgres;

--
-- TOC entry 2140 (class 0 OID 0)
-- Dependencies: 197
-- Name: tanda_id_tanda_seq; Type: SEQUENCE OWNED BY; Schema: mezcal; Owner: postgres
--

ALTER SEQUENCE mezcal.tanda_id_tanda_seq OWNED BY mezcal.tanda.id_tanda;


--
-- TOC entry 198 (class 1259 OID 22413)
-- Name: tanda_tipomaguey_seq; Type: SEQUENCE; Schema: mezcal; Owner: postgres
--

CREATE SEQUENCE mezcal.tanda_tipomaguey_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mezcal.tanda_tipomaguey_seq OWNER TO postgres;

--
-- TOC entry 2141 (class 0 OID 0)
-- Dependencies: 198
-- Name: tanda_tipomaguey_seq; Type: SEQUENCE OWNED BY; Schema: mezcal; Owner: postgres
--

ALTER SEQUENCE mezcal.tanda_tipomaguey_seq OWNED BY mezcal.tanda.tipomaguey;


--
-- TOC entry 200 (class 1259 OID 22417)
-- Name: tanda_tipomezcal_seq; Type: SEQUENCE; Schema: mezcal; Owner: postgres
--

CREATE SEQUENCE mezcal.tanda_tipomezcal_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mezcal.tanda_tipomezcal_seq OWNER TO postgres;

--
-- TOC entry 2142 (class 0 OID 0)
-- Dependencies: 200
-- Name: tanda_tipomezcal_seq; Type: SEQUENCE OWNED BY; Schema: mezcal; Owner: postgres
--

ALTER SEQUENCE mezcal.tanda_tipomezcal_seq OWNED BY mezcal.tanda.tipomezcal;


--
-- TOC entry 196 (class 1259 OID 22405)
-- Name: tipomezcal; Type: TABLE; Schema: mezcal; Owner: postgres; Tablespace: 
--

CREATE TABLE mezcal.tipomezcal (
    id_tipo integer NOT NULL,
    nombre character varying(20)
);


ALTER TABLE mezcal.tipomezcal OWNER TO postgres;

--
-- TOC entry 195 (class 1259 OID 22403)
-- Name: tipomezcal_id_tipo_seq; Type: SEQUENCE; Schema: mezcal; Owner: postgres
--

CREATE SEQUENCE mezcal.tipomezcal_id_tipo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mezcal.tipomezcal_id_tipo_seq OWNER TO postgres;

--
-- TOC entry 2143 (class 0 OID 0)
-- Dependencies: 195
-- Name: tipomezcal_id_tipo_seq; Type: SEQUENCE OWNED BY; Schema: mezcal; Owner: postgres
--

ALTER SEQUENCE mezcal.tipomezcal_id_tipo_seq OWNED BY mezcal.tipomezcal.id_tipo;


--
-- TOC entry 190 (class 1259 OID 22381)
-- Name: transportista; Type: TABLE; Schema: mezcal; Owner: postgres; Tablespace: 
--

CREATE TABLE mezcal.transportista (
    id_transportista integer NOT NULL,
    nombre character varying(30),
    modelo_trailer character varying(30)
);


ALTER TABLE mezcal.transportista OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 22379)
-- Name: transportista_id_transportista_seq; Type: SEQUENCE; Schema: mezcal; Owner: postgres
--

CREATE SEQUENCE mezcal.transportista_id_transportista_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mezcal.transportista_id_transportista_seq OWNER TO postgres;

--
-- TOC entry 2144 (class 0 OID 0)
-- Dependencies: 189
-- Name: transportista_id_transportista_seq; Type: SEQUENCE OWNED BY; Schema: mezcal; Owner: postgres
--

ALTER SEQUENCE mezcal.transportista_id_transportista_seq OWNED BY mezcal.transportista.id_transportista;


--
-- TOC entry 1967 (class 2604 OID 22392)
-- Name: id_cliente; Type: DEFAULT; Schema: mezcal; Owner: postgres
--

ALTER TABLE ONLY mezcal.cliente ALTER COLUMN id_cliente SET DEFAULT nextval('mezcal.cliente_id_cliente_seq'::regclass);


--
-- TOC entry 1960 (class 2604 OID 22336)
-- Name: id_cortador; Type: DEFAULT; Schema: mezcal; Owner: postgres
--

ALTER TABLE ONLY mezcal.cortador ALTER COLUMN id_cortador SET DEFAULT nextval('mezcal.cortador_id_cortador_seq'::regclass);


--
-- TOC entry 1964 (class 2604 OID 22368)
-- Name: id_destilador; Type: DEFAULT; Schema: mezcal; Owner: postgres
--

ALTER TABLE ONLY mezcal.destilador ALTER COLUMN id_destilador SET DEFAULT nextval('mezcal.destilador_id_destilador_seq'::regclass);


--
-- TOC entry 1965 (class 2604 OID 22376)
-- Name: id_enbotelladora; Type: DEFAULT; Schema: mezcal; Owner: postgres
--

ALTER TABLE ONLY mezcal.enbotelladora ALTER COLUMN id_enbotelladora SET DEFAULT nextval('mezcal.enbotelladora_id_enbotelladora_seq'::regclass);


--
-- TOC entry 1963 (class 2604 OID 22360)
-- Name: id_fermentador; Type: DEFAULT; Schema: mezcal; Owner: postgres
--

ALTER TABLE ONLY mezcal.fermentador ALTER COLUMN id_fermentador SET DEFAULT nextval('mezcal.fermentador_id_fermentador_seq'::regclass);


--
-- TOC entry 1968 (class 2604 OID 22400)
-- Name: id_grado; Type: DEFAULT; Schema: mezcal; Owner: postgres
--

ALTER TABLE ONLY mezcal.gradoalcohol ALTER COLUMN id_grado SET DEFAULT nextval('mezcal.gradoalcohol_id_grado_seq'::regclass);


--
-- TOC entry 1961 (class 2604 OID 22344)
-- Name: id_horno; Type: DEFAULT; Schema: mezcal; Owner: postgres
--

ALTER TABLE ONLY mezcal.horno ALTER COLUMN id_horno SET DEFAULT nextval('mezcal.horno_id_horno_seq'::regclass);


--
-- TOC entry 1958 (class 2604 OID 22327)
-- Name: id_maguey; Type: DEFAULT; Schema: mezcal; Owner: postgres
--

ALTER TABLE ONLY mezcal.maguey ALTER COLUMN id_maguey SET DEFAULT nextval('mezcal.maguey_id_maguey_seq'::regclass);


--
-- TOC entry 1959 (class 2604 OID 22328)
-- Name: cantidadpinia; Type: DEFAULT; Schema: mezcal; Owner: postgres
--

ALTER TABLE ONLY mezcal.maguey ALTER COLUMN cantidadpinia SET DEFAULT nextval('mezcal.maguey_cantidadpinia_seq'::regclass);


--
-- TOC entry 1962 (class 2604 OID 22352)
-- Name: id_molino; Type: DEFAULT; Schema: mezcal; Owner: postgres
--

ALTER TABLE ONLY mezcal.molino ALTER COLUMN id_molino SET DEFAULT nextval('mezcal.molino_id_molino_seq'::regclass);


--
-- TOC entry 1970 (class 2604 OID 22424)
-- Name: id_tanda; Type: DEFAULT; Schema: mezcal; Owner: postgres
--

ALTER TABLE ONLY mezcal.tanda ALTER COLUMN id_tanda SET DEFAULT nextval('mezcal.tanda_id_tanda_seq'::regclass);


--
-- TOC entry 1971 (class 2604 OID 22425)
-- Name: tipomaguey; Type: DEFAULT; Schema: mezcal; Owner: postgres
--

ALTER TABLE ONLY mezcal.tanda ALTER COLUMN tipomaguey SET DEFAULT nextval('mezcal.tanda_tipomaguey_seq'::regclass);


--
-- TOC entry 1972 (class 2604 OID 22426)
-- Name: gradoalcohol; Type: DEFAULT; Schema: mezcal; Owner: postgres
--

ALTER TABLE ONLY mezcal.tanda ALTER COLUMN gradoalcohol SET DEFAULT nextval('mezcal.tanda_gradoalcohol_seq'::regclass);


--
-- TOC entry 1973 (class 2604 OID 22427)
-- Name: tipomezcal; Type: DEFAULT; Schema: mezcal; Owner: postgres
--

ALTER TABLE ONLY mezcal.tanda ALTER COLUMN tipomezcal SET DEFAULT nextval('mezcal.tanda_tipomezcal_seq'::regclass);


--
-- TOC entry 1974 (class 2604 OID 22428)
-- Name: cantidadpinias; Type: DEFAULT; Schema: mezcal; Owner: postgres
--

ALTER TABLE ONLY mezcal.tanda ALTER COLUMN cantidadpinias SET DEFAULT nextval('mezcal.tanda_cantidadpinias_seq'::regclass);


--
-- TOC entry 1969 (class 2604 OID 22408)
-- Name: id_tipo; Type: DEFAULT; Schema: mezcal; Owner: postgres
--

ALTER TABLE ONLY mezcal.tipomezcal ALTER COLUMN id_tipo SET DEFAULT nextval('mezcal.tipomezcal_id_tipo_seq'::regclass);


--
-- TOC entry 1966 (class 2604 OID 22384)
-- Name: id_transportista; Type: DEFAULT; Schema: mezcal; Owner: postgres
--

ALTER TABLE ONLY mezcal.transportista ALTER COLUMN id_transportista SET DEFAULT nextval('mezcal.transportista_id_transportista_seq'::regclass);


--
-- TOC entry 1992 (class 2606 OID 22394)
-- Name: cliente_pkey; Type: CONSTRAINT; Schema: mezcal; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY mezcal.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id_cliente);


--
-- TOC entry 1978 (class 2606 OID 22338)
-- Name: cortador_pkey; Type: CONSTRAINT; Schema: mezcal; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY mezcal.cortador
    ADD CONSTRAINT cortador_pkey PRIMARY KEY (id_cortador);


--
-- TOC entry 1986 (class 2606 OID 22370)
-- Name: destilador_pkey; Type: CONSTRAINT; Schema: mezcal; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY mezcal.destilador
    ADD CONSTRAINT destilador_pkey PRIMARY KEY (id_destilador);


--
-- TOC entry 1988 (class 2606 OID 22378)
-- Name: enbotelladora_pkey; Type: CONSTRAINT; Schema: mezcal; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY mezcal.enbotelladora
    ADD CONSTRAINT enbotelladora_pkey PRIMARY KEY (id_enbotelladora);


--
-- TOC entry 1984 (class 2606 OID 22362)
-- Name: fermentador_pkey; Type: CONSTRAINT; Schema: mezcal; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY mezcal.fermentador
    ADD CONSTRAINT fermentador_pkey PRIMARY KEY (id_fermentador);


--
-- TOC entry 1994 (class 2606 OID 22402)
-- Name: gradoalcohol_pkey; Type: CONSTRAINT; Schema: mezcal; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY mezcal.gradoalcohol
    ADD CONSTRAINT gradoalcohol_pkey PRIMARY KEY (id_grado);


--
-- TOC entry 1980 (class 2606 OID 22346)
-- Name: horno_pkey; Type: CONSTRAINT; Schema: mezcal; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY mezcal.horno
    ADD CONSTRAINT horno_pkey PRIMARY KEY (id_horno);


--
-- TOC entry 1976 (class 2606 OID 22330)
-- Name: maguey_pkey; Type: CONSTRAINT; Schema: mezcal; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY mezcal.maguey
    ADD CONSTRAINT maguey_pkey PRIMARY KEY (id_maguey);


--
-- TOC entry 1982 (class 2606 OID 22354)
-- Name: molino_pkey; Type: CONSTRAINT; Schema: mezcal; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY mezcal.molino
    ADD CONSTRAINT molino_pkey PRIMARY KEY (id_molino);


--
-- TOC entry 1998 (class 2606 OID 22430)
-- Name: tanda_pkey; Type: CONSTRAINT; Schema: mezcal; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY mezcal.tanda
    ADD CONSTRAINT tanda_pkey PRIMARY KEY (id_tanda);


--
-- TOC entry 1996 (class 2606 OID 22410)
-- Name: tipomezcal_pkey; Type: CONSTRAINT; Schema: mezcal; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY mezcal.tipomezcal
    ADD CONSTRAINT tipomezcal_pkey PRIMARY KEY (id_tipo);


--
-- TOC entry 1990 (class 2606 OID 22386)
-- Name: transportista_pkey; Type: CONSTRAINT; Schema: mezcal; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY mezcal.transportista
    ADD CONSTRAINT transportista_pkey PRIMARY KEY (id_transportista);


--
-- TOC entry 2000 (class 2606 OID 22436)
-- Name: tanda_gradoalcohol_fkey; Type: FK CONSTRAINT; Schema: mezcal; Owner: postgres
--

ALTER TABLE ONLY mezcal.tanda
    ADD CONSTRAINT tanda_gradoalcohol_fkey FOREIGN KEY (gradoalcohol) REFERENCES mezcal.gradoalcohol(id_grado);


--
-- TOC entry 2008 (class 2606 OID 22476)
-- Name: tanda_id_cliente_fkey; Type: FK CONSTRAINT; Schema: mezcal; Owner: postgres
--

ALTER TABLE ONLY mezcal.tanda
    ADD CONSTRAINT tanda_id_cliente_fkey FOREIGN KEY (id_cliente) REFERENCES mezcal.cliente(id_cliente);


--
-- TOC entry 2002 (class 2606 OID 22446)
-- Name: tanda_id_cortador_fkey; Type: FK CONSTRAINT; Schema: mezcal; Owner: postgres
--

ALTER TABLE ONLY mezcal.tanda
    ADD CONSTRAINT tanda_id_cortador_fkey FOREIGN KEY (id_cortador) REFERENCES mezcal.cortador(id_cortador);


--
-- TOC entry 2006 (class 2606 OID 22466)
-- Name: tanda_id_destilador_fkey; Type: FK CONSTRAINT; Schema: mezcal; Owner: postgres
--

ALTER TABLE ONLY mezcal.tanda
    ADD CONSTRAINT tanda_id_destilador_fkey FOREIGN KEY (id_destilador) REFERENCES mezcal.destilador(id_destilador);


--
-- TOC entry 2007 (class 2606 OID 22471)
-- Name: tanda_id_enbotelladora_fkey; Type: FK CONSTRAINT; Schema: mezcal; Owner: postgres
--

ALTER TABLE ONLY mezcal.tanda
    ADD CONSTRAINT tanda_id_enbotelladora_fkey FOREIGN KEY (id_enbotelladora) REFERENCES mezcal.enbotelladora(id_enbotelladora);


--
-- TOC entry 2005 (class 2606 OID 22461)
-- Name: tanda_id_fermentador_fkey; Type: FK CONSTRAINT; Schema: mezcal; Owner: postgres
--

ALTER TABLE ONLY mezcal.tanda
    ADD CONSTRAINT tanda_id_fermentador_fkey FOREIGN KEY (id_fermentador) REFERENCES mezcal.fermentador(id_fermentador);


--
-- TOC entry 2003 (class 2606 OID 22451)
-- Name: tanda_id_horno_fkey; Type: FK CONSTRAINT; Schema: mezcal; Owner: postgres
--

ALTER TABLE ONLY mezcal.tanda
    ADD CONSTRAINT tanda_id_horno_fkey FOREIGN KEY (id_horno) REFERENCES mezcal.horno(id_horno);


--
-- TOC entry 2004 (class 2606 OID 22456)
-- Name: tanda_id_molino_fkey; Type: FK CONSTRAINT; Schema: mezcal; Owner: postgres
--

ALTER TABLE ONLY mezcal.tanda
    ADD CONSTRAINT tanda_id_molino_fkey FOREIGN KEY (id_molino) REFERENCES mezcal.molino(id_molino);


--
-- TOC entry 2009 (class 2606 OID 22481)
-- Name: tanda_id_transportista_fkey; Type: FK CONSTRAINT; Schema: mezcal; Owner: postgres
--

ALTER TABLE ONLY mezcal.tanda
    ADD CONSTRAINT tanda_id_transportista_fkey FOREIGN KEY (id_transportista) REFERENCES mezcal.transportista(id_transportista);


--
-- TOC entry 1999 (class 2606 OID 22431)
-- Name: tanda_tipomaguey_fkey; Type: FK CONSTRAINT; Schema: mezcal; Owner: postgres
--

ALTER TABLE ONLY mezcal.tanda
    ADD CONSTRAINT tanda_tipomaguey_fkey FOREIGN KEY (tipomaguey) REFERENCES mezcal.maguey(id_maguey);


--
-- TOC entry 2001 (class 2606 OID 22441)
-- Name: tanda_tipomezcal_fkey; Type: FK CONSTRAINT; Schema: mezcal; Owner: postgres
--

ALTER TABLE ONLY mezcal.tanda
    ADD CONSTRAINT tanda_tipomezcal_fkey FOREIGN KEY (tipomezcal) REFERENCES mezcal.tipomezcal(id_tipo);


--
-- TOC entry 2126 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2020-06-16 18:12:14

--
-- PostgreSQL database dump complete
--

