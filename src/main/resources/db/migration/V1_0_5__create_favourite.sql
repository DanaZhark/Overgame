create table if not exists favourite
(
    id      bigserial primary key,
    game_id bigint references game (id),
    user_id bigint references users (keycloak_id)
);