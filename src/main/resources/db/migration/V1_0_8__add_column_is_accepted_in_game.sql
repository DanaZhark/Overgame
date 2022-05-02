alter table game
    add column status varchar(25);

create table if not exists favouriteDevelopers
(
    id           bigserial primary key,
    user_id      bigint references users (id),
    developer_id bigint references users (id)
);

alter table favourite
    rename to favourite_games;