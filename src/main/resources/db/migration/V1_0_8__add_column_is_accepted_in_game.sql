create table if not exists favourite_developers
(
    id           bigserial primary key,
    user_id      bigint references users (id),
    developer_id bigint references users (id)
);