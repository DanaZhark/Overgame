create table if not exists banner
(
    id          bigserial primary key,
    name        varchar(255) not null,
    description varchar(255) not null,
    img         varchar(255) not null,
    code        varchar(20)  not null,
    is_active   boolean default false
);

create table if not exists comments
(
    id           bigserial primary key,
    text         text not null,
    date_created timestamp default now(),
    game_id      bigint references game (id),
    user_id      bigint references users (id)
);

create table if not exists gamer_action
(
    id   bigserial primary key,
    code varchar(255) not null,
    name varchar(255) not null
);

create table if not exists gamer_action_history
(
    id              bigserial primary key,
    date_created    timestamp default now(),
    game_id         bigint references game (id),
    user_id         bigint references users (id),
    gamer_action_id bigint references gamer_action (id)
);

create table if not exists played_game
(
    id           bigserial primary key,
    date_created timestamp default now(),
    duration     bigint not null,
    game_id      bigint references game (id),
    user_id      bigint references users (id)
);