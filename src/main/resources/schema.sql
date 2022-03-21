DROP TABLE IF EXISTS
    public.exercise, public.user, public.workout,
    public.address, public.set, public.profile,
    public.program, public.goal,

    public.program_workout,
    public.program_goal,

    public.goal_workout,

    public.profile_goal, public.profile_program,
    public.profile_set, public.profile_workout,
    public.profile_user, public.profile_address,

    public.set_exercise,
    public.workout_set
    CASCADE;


CREATE TABLE public.exercise
(
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    description VARCHAR(200) NOT NULL DEFAULT (''),
    image VARCHAR(255),
    name VARCHAR(50) NOT NULL,
    target_muscle_group VARCHAR(255),
    vid_link VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE public.user
(
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    admin BOOLEAN NOT NULL DEFAULT FALSE,
    contributor BOOLEAN NOT NULL DEFAULT FALSE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50),
    password VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.workout
(
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    complete BOOLEAN NOT NULL DEFAULT FALSE,
    name VARCHAR(50) NOT NULL,
    type VARCHAR NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.address
(
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    address_line_1 VARCHAR NOT NULL,
    address_line_2 VARCHAR,
    address_line_3 VARCHAR,
    city VARCHAR(70) NOT NULL,
    country VARCHAR(70) NOT NULL,
    postal_code VARCHAR(12) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.profile
(
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    disabilities VARCHAR,
    height INTEGER NOT NULL,
    medical_conditions VARCHAR,
    weight INTEGER NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.set
(
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    exercise_repetition INTEGER NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.program
(
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(50) NOT NULL,
    category VARCHAR(100),
    PRIMARY KEY (id)
);

CREATE TABLE public.goal
(
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    end_date TIMESTAMP DEFAULT now(),
    achieved BOOLEAN NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.program_workout
(
    workout_id BIGINT,
    program_id BIGINT
);

CREATE TABLE public.goal_workout
(
    goal_id BIGINT,
    workout_id BIGINT
);

CREATE TABLE public.profile_user
(
    profile_id BIGINT,
    user_id BIGINT
);

CREATE TABLE public.profile_address
(
    profile_id BIGINT,
    address_id BIGINT
);

CREATE TABLE public.profile_program
(
    profile_id BIGINT,
    program_id BIGINT
);

CREATE TABLE public.profile_set
(
    profile_id BIGINT,
    set_id BIGINT
);

CREATE TABLE public.profile_workout
(
    profile_id BIGINT,
    workout_id BIGINT
);

CREATE TABLE public.profile_goal
(
    profile_id BIGINT,
    goal_id BIGINT
);

CREATE TABLE public.set_exercise
(
    set_id BIGINT,
    exercise_id BIGINT
);

CREATE TABLE public.program_goal
(
    program_id BIGINT,
    goal_id BIGINT
);

CREATE TABLE public.workout_set
(
    workout_id BIGINT,
    set_id BIGINT
);
