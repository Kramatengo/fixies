create table users
(
    id           bigserial,
    login        varchar(50)  not null unique,
    password     varchar(255) not null,
    first_name   varchar(150),
    last_name    varchar(150),
    middle_names varchar(150),
    email        varchar(100) not null unique,
    phone        varchar(100),
    created_at   timestamp default current_timestamp,
    updated_at   timestamp default current_timestamp,
    primary key (id)
);

create table roles
(
    id         serial,
    name       varchar(100) not null unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    primary key (id)
);

create table user_roles
(
    user_id bigint not null,
    role_id int    not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

create table categories
(
    id   bigserial,
    name varchar(255) not null,
    primary key (id)
);

create table brands
(
    id   bigserial,
    name varchar(255) not null,
    primary key (id)
);

create table models
(
    id          bigserial,
    category_id bigint       not null,
    brand_id    bigint       not null,
    name        varchar(255) not null,
    primary key (id),
    foreign key (category_id) references categories (id),
    foreign key (brand_id) references brands (id)
);

create table spares
(
    id    bigserial,
    name  varchar(255) not null,
    price money,
    primary key (id)
);

create table compatibility
(
    model_id        bigint not null,
    spare_id        bigint not null,
    used_by_default boolean default false,
    primary key (model_id, spare_id),
    foreign key (model_id) references models (id),
    foreign key (spare_id) references spares (id)
);

create table stock
(
    id       bigserial,
    spare_id bigint not null,
    quantity int,
    primary key (id),
    foreign key (spare_id) references spares (id)
);

create table statuses
(
    id   serial,
    name varchar(255) not null,
    primary key (id)
);

create table orders
(
    id            bigserial,
    created_at    timestamp default current_timestamp,
    customer_id   bigint       not null,
    executor_id   bigint       not null,
    model_id      bigint       not null,
    subject       varchar(255) not null,
    description   text         not null,
    deadline      timestamp,
    status_id     int          not null,
    serial_number varchar(100),
    total_price   money,
    primary key (id),
    foreign key (customer_id) references users (id),
    foreign key (executor_id) references users (id),
    foreign key (model_id) references models (id),
    foreign key (status_id) references statuses (id)
);

create table comments
(
    id         bigserial,
    created_at timestamp default current_timestamp,
    text       text,
    order_id   bigint not null,
    user_id    bigint not null,
    primary key (id),
    foreign key (order_id) references orders (id),
    foreign key (user_id) references users (id)
);

-- filling user and roles in tables

insert into users(login, password, email)
VALUES ('master', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'master@domain.su'), --100
       ('admin', '$2y$04$to.PaR/wcSn/b0ieuGw3ZOq2sy9BPFhjS5c3nDLUCY8yzvEW7/9K.', 'admin@domain.su'),   --200
       ('user', '$2y$04$r76P41tjVWWBI8dAspu7AuqHpym86Brl1tlSJkX9eOdz.5Den4J.2', 'superadmin@domain.su'); -- 111;

insert into roles(name)
values ('ROLE_USER'),
       ('ROLE_MASTER'),
       ('ROLE_ADMIN');

insert into user_roles(user_id, role_id)
values (1, 2),
       (2, 3),
       (3, 1);

insert into categories(name)
values ('Холодильник'),
       ('Стиральная машина'),
       ('Мобильный телефон'),
       ('Морозильная камера'),
       ('Микроволновая печь');

insert into brands(name)
values ('LG'),
       ('Panasonic'),
       ('Haier'),
       ('Sony'),
       ('Apple'),
       ('Honor');

insert into models(category_id, brand_id, name)
values (4, 3, 'HF-136A'),
       (5, 1, 'MG6349LMS');

insert into spares(name, price)
values ('ТЭН', 100.8),
       ('Сенсорная панель', 500.24);

insert into compatibility(model_id, spare_id, used_by_default)
values (2, 1, false),
       (2, 2, true);
