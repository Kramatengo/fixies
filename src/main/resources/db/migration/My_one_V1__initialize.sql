DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users(
    id              serial PRIMARY KEY,
    login           varchar(50)  not null unique,
    password        varchar(255) not null,
    first_name      varchar(150),
    last_name       varchar(150),
    middle_names    varchar(150),
    email           varchar(100) not null unique,
    phone           varchar(100),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

DROP TABLE IF EXISTS roles;
CREATE TABLE IF NOT EXISTS roles
(
    id         serial PRIMARY KEY,
    name       varchar(100),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

DROP TABLE IF EXISTS user_roles;
CREATE TABLE IF NOT EXISTS user_roles

(
    user_id bigint not null,
    role_id int    not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

DROP TABLE IF EXISTS categories;
CREATE TABLE IF NOT EXISTS categories
(
    id      serial PRIMARY KEY,
    name    varchar(255) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

DROP TABLE IF EXISTS brands;
CREATE TABLE IF NOT EXISTS brands
(
    id      serial PRIMARY KEY,
    name    varchar(255) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp

);

DROP TABLE IF EXISTS models;
CREATE TABLE IF NOT EXISTS models
(
    id              serial PRIMARY KEY,
    category_id     bigint not null,
    brand_id        bigint not null,
    name    varchar(255) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    foreign key (category_id) references categories (id),
    foreign key (brand_id) references brands (id)

);

DROP TABLE IF EXISTS spares;
CREATE TABLE IF NOT EXISTS spares
(
    id      serial PRIMARY KEY,
    name    varchar(255) not null,
    price   decimal(5, 2),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

DROP TABLE IF EXISTS compatibility;
CREATE TABLE IF NOT EXISTS compatibility
(
    model_id bigint not null,
    spare_id bigint not null,
    primary key (model_id, spare_id),
    foreign key (model_id) references models (id),
    foreign key (spare_id) references spares (id)
);

DROP TABLE IF EXISTS stock;
CREATE TABLE IF NOT EXISTS stock
(
    id          serial PRIMARY KEY,
    spare_id    bigint not null,
    quantity    int,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    foreign key (spare_id) references spares (id)
);

DROP TABLE IF EXISTS statuses;
CREATE TABLE IF NOT EXISTS statuses
(
    id      serial PRIMARY KEY,
    name    varchar(255) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

DROP TABLE IF EXISTS orders;
CREATE TABLE IF NOT EXISTS orders
(
    id              serial PRIMARY KEY,
    created_at      timestamp default current_timestamp,
    customer_id     bigint not null,
    executor_id     bigint not null,
    model_id        bigint not null,
    subject         varchar(255) not null,
    description     text not null,
    deadline        timestamp,
    status_id       int not null,
    serial_number   varchar(100),
    total_price   decimal(5, 2),
    foreign key (customer_id) references users (id),
    foreign key (executor_id) references users (id),
    foreign key (model_id) references models (id),
    foreign key (status_id) references statuses (id)
);

-- filling user and roles in tables

insert into users(login, password, email)
VALUES ('master', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'master@domain.su'),   -- 100
       ('admin', '$2y$04$to.PaR/wcSn/b0ieuGw3ZOq2sy9BPFhjS5c3nDLUCY8yzvEW7/9K.', 'admin@domain.su'), -- 200
       ('superadmin', '$2y$04$r76P41tjVWWBI8dAspu7AuqHpym86Brl1tlSJkX9eOdz.5Den4J.2', 'superadmin@domain.su'); -- 111;

insert into roles(name)
values ('ROLE_USER'),
       ('ROLE_MASTER'),
       ('ROLE_ADMIN'),
       ('ROLE_SEPERUSER'),
       ('ROLE_SUPERADMIN');

insert into user_roles(user_id, role_id)
values (1, 2),
       (2, 3),
       (3, 4);

insert into categories(name)
values ('Стиральные машины'), ('Холодильники'), ('Пылесосы'), ('Смартфоны'), ('Телевизоры');

insert into brands(name)
values ('LG'), ('Bosch'),
       ('BBK'), ('Xiaomi'), ('Stinol'),
       ('Atlant'), ('Ariston'), ('Beko'),
       ('ZTE'), ('Samsung'), ('Realme'),
       ('Huawei');

insert into models(category_id, brand_id, name)
values (1, 1, 'F2M5WS4W'),  -- стиральная машина LG
       (1, 2, 'WGA142X6OE'),  -- стиральная машина Bosch
       (1, 7, 'BWSD 61051 1'),  -- стиральная машина Ariston
       (1, 8, 'WRE6512BWW'),  -- стиральная машина Beko
       (2, 1, 'GA-B379SLUL'),  -- холодильник LG
       (2, 2, 'KGN39VK25R'),  -- холодильник Bosch
       (2, 5, 'STS 185 S'),  -- холодильник Stinol
       (2, 6, 'XM-6025-031'),  -- холодильник Atlant
       (2, 7, 'ITR 4180 W'),  -- холодильник Indesit
       (2, 8, 'RCNK335K00W'),  -- холодильник Beko
       (3, 1, 'VK76A09NTCR'),  -- пылесос cleaner LG
       (3, 2, 'BGL35MOV27'),  -- пылесос cleaner Bosch
       (3, 3, 'BV1507'),  -- пылесос cleaner BBK
       (3, 4, 'Mi Robot Vacuum Mop P'),  -- пылесос cleaner Xiaomi
       (4, 4, 'Redmi 9A 32Gb'),  -- смартфон Xiaomi
       (4, 9, 'Blade A3 2020 NFC 32Gb'), -- смартфон ZTE
       (4, 10, 'Galaxy A32 64Gb SM-A325F'),  -- смартфон Samsung
       (4, 11, 'C21Y 4/64Gb'),  -- смартфон Realme
       (5, 1, '32LM6350PLA'),  -- телевизор LG
       (5, 3, '32LEX-7162/TS2C'),  -- телевизор BBK
       (5, 4, 'MI TV 32 P1'),  -- телевизор Xiaomi
       (5, 12, 'Vision S, 55');  -- телевизор Huawei

insert into spares(name)
values ('Помпа СМ'),  -- стиральная машина
       ('Плата управления СМ'),
       ('Компрессор холодильника'),
       ('Плата управления холодильника'),
       ('Мотор пылесоса'),
       ('Фильтр выходной пылесоса'),
       ('Экран смартфона'),
       ('Главная плата смартфона'),
       ('Динамик смартфона'),
       ('Панель телевизора'),
       ('Главная плата телевизора'),
       ('Плата питания телевизора');


insert into compatibility(model_id, spare_id)
values (1, 1),
       (1, 2),
       (2, 1),
       (2, 2),
       (3, 1),
       (3, 2),
       (4, 1),
       (4, 2),
       (5, 3),
       (5, 4),
       (6, 3),
       (6, 4),
       (7, 3),
       (7, 4),
       (8, 3),
       (8, 4),
       (9, 3),
       (9, 4),
       (10, 3),
       (10, 4),
       (11, 5),
       (11, 6),
       (12, 5),
       (12, 6),
       (13, 5),
       (13, 6),
       (14, 5),
       (14, 6),
       (15, 7),
       (15, 8),
       (15, 9),
       (16, 7),
       (16, 8),
       (16, 9),
       (17, 7),
       (17, 8),
       (17, 9),
       (18, 7),
       (18, 8),
       (18, 9),
       (19, 10),
       (19, 11),
       (19, 12),
       (20, 10),
       (20, 11),
       (20, 12),
       (21, 10),
       (21, 11),
       (21, 12),
       (22, 10),
       (22, 11),
       (22, 12);

insert into stock(spare_id, quantity)
values (1, 100),
       (2, 100),
       (3, 100),
       (4, 100),
       (5, 100),
       (6, 100),
       (7, 100),
       (8, 100),
       (9, 100),
       (10, 100),
       (11, 100),
       (12, 100);

insert into statuses(name)
values('Зарегистрирован'),
       ('Принят в работу'),
       ('Диагностика'),
       ('В процессе ремонта'),
       ('Готов к выдаче');

