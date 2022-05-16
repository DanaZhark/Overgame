create table if not exists users
(
    id            bigserial primary key,
    keycloak_id   varchar(500) unique,
    username      varchar(200)             not null,
    email         varchar(200)             not null,
    birthday_date timestamp,
    created_date  timestamp with time zone not null default now()
);

create table if not exists genre
(
    id          bigserial primary key,
    code        varchar(50) not null,
    name        jsonb       not null,
    description jsonb       not null,
    img_link    text        not null
);

create index genre_id on genre (id);

create table if not exists game
(
    id           bigserial primary key,
    name         jsonb not null,
    description  jsonb not null,
    rating       double precision default 0,
    status       varchar(25),
    game_link    text  not null,
    img_link     text  not null,
    price        double precision default 0,
    date_created timestamp        default now(),
    creator_id   bigint references users (id),
    moderator_id bigint references users (id)
);

create index game_id on game (id);

create table if not exists favorites
(
    id           bigserial primary key,
    date_created timestamp default now(),
    game_id      bigint references game (id),
    user_id      bigint references users (id)
);

create table if not exists rating
(
    id           bigserial primary key,
    grade        double precision default 0,
    date_created timestamp        default now(),
    game_id      bigint references game (id),
    user_id      bigint references users (id)
);

create index rating_game_id on rating (game_id);
