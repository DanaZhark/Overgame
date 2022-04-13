create table if not exists game_genre
(
    id       bigserial primary key,
    game_id  bigint references game (id),
    genre_id bigint references genre (id)
);