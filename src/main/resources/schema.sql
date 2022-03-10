DROP TABLE IF EXISTS public.exercise, public.user, public.workout CASCADE;

CREATE TABLE public.exercise (
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    description VARCHAR(200) NOT NULL DEFAULT (''),
    image VARCHAR(255),
    name VARCHAR(50) NOT NULL DEFAULT (''),
    target_muscle_group VARCHAR(255),
    vid_link VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE public.user (
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    admin BOOLEAN NOT NULL DEFAULT FALSE,
    contributor BOOLEAN NOT NULL DEFAULT FALSE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50),
    password VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.workout (
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    complete BOOLEAN NOT NULL DEFAULT FALSE,
    name VARCHAR(50) NOT NULL DEFAULT (''),
    type INT4 NOT NULL DEFAULT -1,
    PRIMARY KEY (id)
);
