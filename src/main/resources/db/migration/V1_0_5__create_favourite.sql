create table if not exists favourite_games
(
    id      bigserial primary key,
    game_id bigint references game (id),
    user_id bigint references users (id)
);