create table public.users(
id BIGINT NOT NULL,
first_name VARCHAR(255),
last_name VARCHAR(255),
age BIGINT
);

ALTER TABLE public.users ADD CONSTRAINT public.constraints_1 PRIMARY KEY(id);

CREATE SEQUENCE public.restapi_sequence START WITH 100;