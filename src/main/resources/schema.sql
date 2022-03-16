DROP TABLE IF EXISTS public.exercise, public.user, public.workout, public.address, public.profile, public.set, public.program, public.goal, public.program_workout, public.goal_workout CASCADE;


CREATE TABLE public.exercise (
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    description VARCHAR(200) NOT NULL DEFAULT (''),
    image VARCHAR(255),
    name VARCHAR(50) NOT NULL,
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
    name VARCHAR(50) NOT NULL,
    type VARCHAR NOT NULL,
    set_id BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE public.address (
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    address_line_1 VARCHAR NOT NULL,
    address_line_2 VARCHAR,
    address_line_3 VARCHAR,
    city VARCHAR(70) NOT NULL,
    country VARCHAR(70) NOT NULL,
    postal_code VARCHAR(12) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.profile (
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    disabilities VARCHAR,
    height INTEGER NOT NULL,
    medical_conditions VARCHAR,
    weight INTEGER NOT NULL,
    set_id BIGINT,
    user_id BIGINT,
    goal_id BIGINT,
    address_id BIGINT,
    program_id BIGINT,
    workout_id BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE public.set (
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    exercise_repetition INTEGER NOT NULL,
    exercise_id BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE public.program (
     id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
     name VARCHAR(50) NOT NULL,
     category VARCHAR(100),
     PRIMARY KEY (id)
);

CREATE TABLE public.goal (
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    end_date TIMESTAMP DEFAULT now(),
    achieved BOOLEAN NOT NULL,
    program_id BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE public.program_workout (
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    workout_id BIGINT,
    program_id BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE public.goal_workout (
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    end_date timestamp NOT NULL,
    workout_id BIGINT,
    goal_id BIGINT,
    PRIMARY KEY (id)
);
