create table role (
    id bigserial primary key,
    code varchar(255) not null unique,
    name varchar(255),
    description varchar(255)
);

create table users_roles (
    user_id bigint references users(id) not null,
    role_id bigint references role(id) not null,
    constraint user_id_role_id_unique unique(user_id, role_id)
);

insert into role (code, name, description)
values ('OVERGAME_ADMIN_ROLE', 'Супер администратор',
        'Пользователь со стороны Zhandabo, который добавляет и удаляет игры');


insert into role (code, name, description)
values ('OVERGAME_USER_ROLE', 'Пользователь',
        'Пользователь, который может играть в игры и предлагать свои игры');