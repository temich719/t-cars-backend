CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE IF NOT EXISTS cars
(
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name        text           NOT NULL,
    price_in_usd  NUMERIC(10, 2) NOT NULL,
    description text,
    image_paths  text
);

CREATE TABLE IF NOT EXISTS users
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name       varchar(25) NOT NULL,
    surname    varchar(25) NOT NULL,
    avatar_path text,
    login      text UNIQUE NOT NULL,
    password   text        NOT NULL,
    phone      varchar(13) NOT NULL
);

CREATE TABLE IF NOT EXISTS users_favorite_cars
(
    user_id UUID REFERENCES users (id) ON DELETE CASCADE,
    car_id  UUID REFERENCES cars (id),
    CONSTRAINT user_car_pkey PRIMARY KEY (user_id, car_id)
);