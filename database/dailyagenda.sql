--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.5
-- Started on 2014-11-05 10:20:58

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 177 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1983 (class 0 OID 0)
-- Dependencies: 177
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 172 (class 1259 OID 16405)
-- Name: Category; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE "Category" (
    "ID" integer NOT NULL,
    "CategoryName" character varying(20),
    "Description" text
);


ALTER TABLE public."Category" OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 16408)
-- Name: Category_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "Category_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Category_ID_seq" OWNER TO postgres;

--
-- TOC entry 1984 (class 0 OID 0)
-- Dependencies: 173
-- Name: Category_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "Category_ID_seq" OWNED BY "Category"."ID";


--
-- TOC entry 176 (class 1259 OID 16435)
-- Name: Recurrence; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE "Recurrence" (
    "RecurrenceType" integer,
    "RecurrenceDayOfTheWeek" integer,
    "StartDate" date,
    "TaskID" integer NOT NULL
);


ALTER TABLE public."Recurrence" OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 16423)
-- Name: Task; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE "Task" (
    "ID" integer NOT NULL,
    "CategoryID" integer,
    "UserID" integer,
    "ParentID" integer,
    "TaskType" integer,
    "ScheduledAtHours" character varying(15),
    "Description" text,
    "Priority" integer,
    "CompletionGrade" integer,
    "StatusComment" text
);


ALTER TABLE public."Task" OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 16426)
-- Name: Task_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "Task_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Task_ID_seq" OWNER TO postgres;

--
-- TOC entry 1985 (class 0 OID 0)
-- Dependencies: 175
-- Name: Task_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "Task_ID_seq" OWNED BY "Task"."ID";


--
-- TOC entry 170 (class 1259 OID 16394)
-- Name: User; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE "User" (
    "ID" integer NOT NULL,
    "Username" character(12) NOT NULL,
    "Password" character(32),
    "FullName" character varying(50)
);


ALTER TABLE public."User" OWNER TO postgres;

--
-- TOC entry 171 (class 1259 OID 16397)
-- Name: User_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "User_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."User_ID_seq" OWNER TO postgres;

--
-- TOC entry 1986 (class 0 OID 0)
-- Dependencies: 171
-- Name: User_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "User_ID_seq" OWNED BY "User"."ID";


--
-- TOC entry 1842 (class 2604 OID 16410)
-- Name: ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "Category" ALTER COLUMN "ID" SET DEFAULT nextval('"Category_ID_seq"'::regclass);


--
-- TOC entry 1843 (class 2604 OID 16428)
-- Name: ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "Task" ALTER COLUMN "ID" SET DEFAULT nextval('"Task_ID_seq"'::regclass);


--
-- TOC entry 1841 (class 2604 OID 16399)
-- Name: ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "User" ALTER COLUMN "ID" SET DEFAULT nextval('"User_ID_seq"'::regclass);


--
-- TOC entry 1971 (class 0 OID 16405)
-- Dependencies: 172
-- Data for Name: Category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "Category" ("ID", "CategoryName", "Description") FROM stdin;
1	Work	Tasks to be done for work
2	Personal	Personal tasks
\.


--
-- TOC entry 1987 (class 0 OID 0)
-- Dependencies: 173
-- Name: Category_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"Category_ID_seq"', 1, false);


--
-- TOC entry 1975 (class 0 OID 16435)
-- Dependencies: 176
-- Data for Name: Recurrence; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "Recurrence" ("RecurrenceType", "RecurrenceDayOfTheWeek", "StartDate", "TaskID") FROM stdin;
0	\N	2014-08-10	2
0	\N	2014-09-10	3
0	\N	2014-11-10	16
\N	\N	2014-10-31	174
\N	\N	2014-11-10	175
\N	\N	2014-10-31	176
\.


--
-- TOC entry 1973 (class 0 OID 16423)
-- Dependencies: 174
-- Data for Name: Task; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "Task" ("ID", "CategoryID", "UserID", "ParentID", "TaskType", "ScheduledAtHours", "Description", "Priority", "CompletionGrade", "StatusComment") FROM stdin;
174	1	1	\N	1	\N	test	2	101	sample commment
2	1	1	\N	2	\N	Container Task	1	71	test
16	2	1	\N	1	\N	Another personal task	3	20	started doing this
3	2	1	\N	1	\N	Standalone Task	2	45	a standalone task
175	1	1	2	3	\N	example subtask	2	1	\N
176	1	1	2	3	\N	subtask	2	41	am facut o parte din el
\.


--
-- TOC entry 1988 (class 0 OID 0)
-- Dependencies: 175
-- Name: Task_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"Task_ID_seq"', 177, true);


--
-- TOC entry 1969 (class 0 OID 16394)
-- Dependencies: 170
-- Data for Name: User; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "User" ("ID", "Username", "Password", "FullName") FROM stdin;
1	ageacar     	202cb962ac59075b964b07152d234b70	Andrei Geacar
\.


--
-- TOC entry 1989 (class 0 OID 0)
-- Dependencies: 171
-- Name: User_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"User_ID_seq"', 1, true);


--
-- TOC entry 1849 (class 2606 OID 16415)
-- Name: PKCategory; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "Category"
    ADD CONSTRAINT "PKCategory" PRIMARY KEY ("ID");


--
-- TOC entry 1851 (class 2606 OID 16447)
-- Name: PKTask; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "Task"
    ADD CONSTRAINT "PKTask" PRIMARY KEY ("ID");


--
-- TOC entry 1856 (class 2606 OID 32790)
-- Name: PKTaskID; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "Recurrence"
    ADD CONSTRAINT "PKTaskID" PRIMARY KEY ("TaskID");


--
-- TOC entry 1845 (class 2606 OID 16404)
-- Name: PKUser; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "User"
    ADD CONSTRAINT "PKUser" PRIMARY KEY ("ID");


--
-- TOC entry 1847 (class 2606 OID 40961)
-- Name: UniqueUsername; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "User"
    ADD CONSTRAINT "UniqueUsername" UNIQUE ("Username");


--
-- TOC entry 1852 (class 1259 OID 16453)
-- Name: fki_FKCategory; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "fki_FKCategory" ON "Task" USING btree ("CategoryID");


--
-- TOC entry 1853 (class 1259 OID 16465)
-- Name: fki_FKParent; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "fki_FKParent" ON "Task" USING btree ("ParentID");


--
-- TOC entry 1857 (class 1259 OID 32778)
-- Name: fki_FKTaskID; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "fki_FKTaskID" ON "Recurrence" USING btree ("TaskID");


--
-- TOC entry 1854 (class 1259 OID 16459)
-- Name: fki_FKUser; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "fki_FKUser" ON "Task" USING btree ("UserID");


--
-- TOC entry 1858 (class 2606 OID 16448)
-- Name: FKCategory; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "Task"
    ADD CONSTRAINT "FKCategory" FOREIGN KEY ("CategoryID") REFERENCES "Category"("ID");


--
-- TOC entry 1860 (class 2606 OID 24576)
-- Name: FKParent; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "Task"
    ADD CONSTRAINT "FKParent" FOREIGN KEY ("ParentID") REFERENCES "Task"("ID") ON DELETE CASCADE;


--
-- TOC entry 1861 (class 2606 OID 32784)
-- Name: FKTaskID; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "Recurrence"
    ADD CONSTRAINT "FKTaskID" FOREIGN KEY ("TaskID") REFERENCES "Task"("ID") ON DELETE CASCADE;


--
-- TOC entry 1859 (class 2606 OID 16454)
-- Name: FKUser; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "Task"
    ADD CONSTRAINT "FKUser" FOREIGN KEY ("UserID") REFERENCES "User"("ID");


--
-- TOC entry 1982 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2014-11-05 10:20:58

--
-- PostgreSQL database dump complete
--

