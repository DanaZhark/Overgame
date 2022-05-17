create table if not exists role
(
    id          bigserial primary key,
    code        varchar(50)  not null,
    name        varchar(200) not null,
    description varchar(200) not null
);

insert into role (code, name, description)
VALUES ('OVERGAME_ADMIN_ROLE',
        'Супер администратор',
        'Пользователь со стороны Zhandabo, который добавляет и удаляет игры'),
       ('OVERGAME_USER_ROLE',
        'Пользователь',
        'Пользователь, который может играть в игры и предлагать свои игры');