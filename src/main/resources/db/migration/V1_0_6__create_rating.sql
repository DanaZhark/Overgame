create table if not exists rating
(
    id      bigserial primary key,
    game_id bigint references game (id),
    user_id bigint references users (id),
    grade   double precision not null
);