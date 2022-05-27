alter table favourite_games
    add constraint favourite_game_user_unique unique (user_id, game_id);

alter table favourite_developers
    add constraint favourite_developer_user_unique unique (user_id, developer_id);