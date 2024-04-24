--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2
-- Dumped by pg_dump version 16.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: hotel; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.hotel (
    id bigint NOT NULL,
    name character varying NOT NULL,
    address character varying NOT NULL,
    city character varying(255) NOT NULL,
    mail character varying NOT NULL,
    phone character varying NOT NULL,
    star character varying,
    car_park boolean,
    wifi boolean,
    pool boolean,
    fitness boolean,
    concierge boolean,
    spa boolean,
    room_service boolean
);


ALTER TABLE public.hotel OWNER TO postgres;

--
-- Name: hotel_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.hotel ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hotel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 789746567864
    CACHE 1
);


--
-- Name: hotel_pension; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.hotel_pension (
    id bigint NOT NULL,
    hotel_id integer NOT NULL,
    pension_type character varying NOT NULL
);


ALTER TABLE public.hotel_pension OWNER TO postgres;

--
-- Name: hotel_pension_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.hotel_pension ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hotel_pension_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: hotel_season; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.hotel_season (
    id bigint NOT NULL,
    hotel_id integer NOT NULL,
    start_date date NOT NULL,
    finish_date date NOT NULL,
    price_parameter double precision NOT NULL
);


ALTER TABLE public.hotel_season OWNER TO postgres;

--
-- Name: hotel_season_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.hotel_season ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hotel_season_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: reservation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reservation (
    id bigint NOT NULL,
    room_id integer,
    check_in_date date,
    total_price double precision,
    guest_count integer,
    guest_name character varying,
    guest_citizen_id character varying,
    guest_mail character varying,
    guest_phone character varying,
    check_out_date date
);


ALTER TABLE public.reservation OWNER TO postgres;

--
-- Name: reservation_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.reservation ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.reservation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: room; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.room (
    id bigint NOT NULL,
    hotel_id integer NOT NULL,
    pension_id integer NOT NULL,
    season_id integer NOT NULL,
    type character varying NOT NULL,
    stock integer NOT NULL,
    adult_price double precision NOT NULL,
    child_price double precision NOT NULL,
    bed_capacity integer NOT NULL,
    square_meter integer NOT NULL,
    television boolean NOT NULL,
    minibar boolean NOT NULL,
    game_console boolean NOT NULL,
    cash_box boolean NOT NULL,
    projection boolean NOT NULL
);


ALTER TABLE public.room OWNER TO postgres;

--
-- Name: room_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.room ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    user_id bigint NOT NULL,
    user_name text,
    user_pass text,
    user_role text
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: user_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.users ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: hotel; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.hotel (id, name, address, city, mail, phone, star, car_park, wifi, pool, fitness, concierge, spa, room_service) FROM stdin;
2	otelAdi2	otelAdres2	otelSehri2	otelMail2@mail.com	+9055555	****	t	t	t	f	f	f	f
5	otelAdi3	otelAdres3	otelSehir3	otelMail3	+903333	***	t	t	f	f	t	t	t
7	bilgi	bilgi mah bilgi sok	istanbul	bilgi@gmail.com	4565625656	****	t	t	t	t	f	f	f
9	test	test	test	test	+55555	****	t	t	t	t	t	f	f
\.


--
-- Data for Name: hotel_pension; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.hotel_pension (id, hotel_id, pension_type) FROM stdin;
25	2	+ HALF PENSION
27	2	+ ONLY BED
28	5	+ ULTRA ALL-IN-ONE
29	2	+ FULL PENSION
30	5	+ HALF PENSION
31	2	+ ULTRA ALL-IN-ONE
\.


--
-- Data for Name: hotel_season; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.hotel_season (id, hotel_id, start_date, finish_date, price_parameter) FROM stdin;
33	2	2024-06-01	2024-12-31	1
34	2	2025-06-01	2025-12-31	1
36	5	2026-06-01	2026-12-31	1
37	2	2023-01-01	2023-06-01	1
35	2	2026-06-01	2026-12-31	2
38	5	2024-06-01	2024-12-31	1
39	2	2024-06-01	2024-12-31	1
41	2	2024-06-01	2024-12-31	2
42	2	2024-06-01	2024-12-31	2.3
47	9	2024-06-01	2024-12-31	1
\.


--
-- Data for Name: reservation; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.reservation (id, room_id, check_in_date, total_price, guest_count, guest_name, guest_citizen_id, guest_mail, guest_phone, check_out_date) FROM stdin;
21	27	2026-10-01	220	3	deneme	deneme	deneme	5555	2026-10-02
\.


--
-- Data for Name: room; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.room (id, hotel_id, pension_id, season_id, type, stock, adult_price, child_price, bed_capacity, square_meter, television, minibar, game_console, cash_box, projection) FROM stdin;
39	5	28	36	Single Room	2	2	2	2	2	f	t	t	t	t
36	2	25	33	Single Room	30	30	30	30	30	f	f	f	t	f
33	2	25	33	Single Room	30	30	30	30	30	f	f	f	t	f
41	2	25	33	Single Room	50	555	222	4	40	f	f	t	f	t
38	5	28	36	Junior Suite Room	20	20	20	20	20	t	t	t	t	t
27	2	25	35	Double Room	29	30	40	30	30	f	f	f	f	f
34	2	25	33	Single Room	30	40	30	30	30	f	f	f	f	f
37	2	25	33	Single Room	30	30	30	30	30	t	t	t	t	f
31	2	25	33	Single Room	30	30	30	30	30	t	t	t	t	t
28	2	25	33	Junior Suite Room	50	50	50	50	50	t	t	t	t	t
35	2	25	33	Single Room	30	30	30	30	30	t	t	t	t	t
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (user_id, user_name, user_pass, user_role) FROM stdin;
29	admin	admin	admin
30	burcak	1234	employee
33	deneme1	denem	admin
\.


--
-- Name: hotel_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hotel_id_seq', 13, true);


--
-- Name: hotel_pension_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hotel_pension_id_seq', 35, true);


--
-- Name: hotel_season_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hotel_season_id_seq', 47, true);


--
-- Name: reservation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.reservation_id_seq', 23, true);


--
-- Name: room_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.room_id_seq', 44, true);


--
-- Name: user_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_user_id_seq', 43, true);


--
-- Name: hotel_pension hotel_pension_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.hotel_pension
    ADD CONSTRAINT hotel_pension_pkey PRIMARY KEY (id);


--
-- Name: hotel hotel_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (id);


--
-- Name: hotel_season hotel_season_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.hotel_season
    ADD CONSTRAINT hotel_season_pkey PRIMARY KEY (id);


--
-- Name: reservation reservation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (id);


--
-- Name: room room_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (id);


--
-- Name: users user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);


--
-- Name: hotel_pension fk_hotel_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.hotel_pension
    ADD CONSTRAINT fk_hotel_id FOREIGN KEY (hotel_id) REFERENCES public.hotel(id) ON DELETE CASCADE;


--
-- Name: hotel_season fk_hotel_id2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.hotel_season
    ADD CONSTRAINT fk_hotel_id2 FOREIGN KEY (hotel_id) REFERENCES public.hotel(id) ON DELETE CASCADE;


--
-- Name: room fk_hotel_id3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.room
    ADD CONSTRAINT fk_hotel_id3 FOREIGN KEY (hotel_id) REFERENCES public.hotel(id) ON DELETE CASCADE;


--
-- Name: room fk_pension_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.room
    ADD CONSTRAINT fk_pension_id FOREIGN KEY (pension_id) REFERENCES public.hotel_pension(id) ON DELETE CASCADE;


--
-- Name: room fk_season_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.room
    ADD CONSTRAINT fk_season_id FOREIGN KEY (season_id) REFERENCES public.hotel_season(id) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

