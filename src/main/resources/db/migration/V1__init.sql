DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users
(
    id bigserial PRIMARY KEY,
    login        varchar(50)  not null unique,
    password     varchar(255) not null,
    first_name   varchar(150),
    last_name    varchar(150),
    middle_names varchar(150),
    email        varchar(100) not null unique,
    phone        varchar(100),
    created_at   timestamp default current_timestamp,
    updated_at   timestamp default current_timestamp
);

DROP TABLE IF EXISTS roles;
CREATE TABLE IF NOT EXISTS roles
(
    id bigserial PRIMARY KEY,
    name       varchar(100),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

DROP TABLE IF EXISTS user_roles;
CREATE TABLE IF NOT EXISTS user_roles

(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

DROP TABLE IF EXISTS categories;
CREATE TABLE IF NOT EXISTS categories
(
    id bigserial PRIMARY KEY,
    name              varchar(255) not null,
    image             varchar(255) not null,
    description       varchar(255) not null,
    url_path          varchar(255) not null,
    alt_text          varchar(255) not null,
    img_width         int          not null,
    img_height        int          not null,
    brief_description text         not null,
    full_description  text         not null,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);

DROP TABLE IF EXISTS brands;
CREATE TABLE IF NOT EXISTS brands
(
    id bigserial PRIMARY KEY,
    name       varchar(255) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp

);

DROP TABLE IF EXISTS models;
CREATE TABLE IF NOT EXISTS models
(
    id bigserial PRIMARY KEY,
    category_id bigint       not null,
    brand_id    bigint       not null,
    name        varchar(255) not null,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp,
    foreign key (category_id) references categories (id),
    foreign key (brand_id) references brands (id)

);

DROP TABLE IF EXISTS spares;
CREATE TABLE IF NOT EXISTS spares
(
    id bigserial PRIMARY KEY,
    name       varchar(255) not null,
    price      decimal(5, 2),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

DROP TABLE IF EXISTS compatibility;
CREATE TABLE IF NOT EXISTS compatibility
(
    model_id   bigint not null,
    spare_id   bigint not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    primary key (model_id, spare_id),
    foreign key (model_id) references models (id),
    foreign key (spare_id) references spares (id)
);

DROP TABLE IF EXISTS stock;
CREATE TABLE IF NOT EXISTS stock
(
    id bigserial PRIMARY KEY,
    spare_id   bigint not null,
    quantity   int,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    foreign key (spare_id) references spares (id)
);

DROP TABLE IF EXISTS statuses;
CREATE TABLE IF NOT EXISTS statuses
(
    id bigserial PRIMARY KEY,
    name       varchar(255) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

DROP TABLE IF EXISTS comments;
CREATE TABLE IF NOT EXISTS comments
(
    id bigserial PRIMARY KEY,
    order_id   bigint not null,
    text       text   not null,
    created_at timestamp default current_timestamp,
    user_id    bigint not null
);

DROP TABLE IF EXISTS warranty;
CREATE TABLE IF NOT EXISTS warranty
(
    id bigserial PRIMARY KEY,
    name       varchar(50),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

DROP TABLE IF EXISTS orders;
CREATE TABLE IF NOT EXISTS orders
(
    id                         serial PRIMARY KEY,
    created_at                 timestamp default current_timestamp,
    customer_id                bigint,
    applicant_name             varchar(100),
    applicant_phone            varchar(100),
    applicant_email            varchar(100),
    executor_id                bigint,
    model_id                   bigint,
--    brand_id        bigint,
--    category_id     bigint,
    client_subject             varchar(255),
    client_description         text,
    product_preview_comment    text,
    se_diagnostics_comment     text,
    se_provisional_repair_cost decimal(8, 2),
    se_repair_comment          text,
    deadline                   timestamp,
    status_id                  int,
    warranty_id                int,
    serial_number              varchar(100),
    total_price                decimal(8, 2),
    warranty_price             decimal(8, 2),
    foreign key (customer_id) references users (id),
    foreign key (executor_id) references users (id),
    foreign key (model_id) references models (id),
    foreign key (status_id) references statuses (id)
--    foreign key (category_id) references categories (id),
--    foreign key (brand_id) references brands (id)

);


-- filling user and roles in tables

insert into users(login, password, email)
VALUES ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@domain.su'),           -- 100
       ('master', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'master@domain.su'),       -- 100
       ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'admin@domain.su'),         -- 100
       ('superuser', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'superuser@domain.su'), -- 100
       ('superadmin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'superadmin@domain.su'); -- 100;

insert into roles(name)
values ('ROLE_USER'),
       ('ROLE_MASTER'),
       ('ROLE_ADMIN'),
       ('ROLE_SEPERUSER'),
       ('ROLE_SUPERADMIN');

insert into user_roles(user_id, role_id)
values (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5);


insert into categories(name, image, description, url_path, alt_text, img_width, img_height, brief_description,
                       full_description)
values ('Стиральные машины',
        'category_washing_machine_edit2.jpg',
        'Ремонт стиральных машин всех типов',
        '#!/promo_page',
        'ремонт стиральных машин',
        150,
        150,
        'Наш сервис ремонтирует стиральные машины всех моделей и брендов, представленных на российском рынке.

         Мы гарантируем оперативное выполнение работы, качество, и ответственный подход.
         Цены Вас так же приятно удивят.

         Обращайтесь в наш сервис и наш опытный мастер грамотно произведет комплексную диагностику и качественно устранит неисправности вашей стиральной машины.',
        'Если вы обнаружили, что ваша домашняя помощница начала подтекать, или белье не отжимается, или автоматика некорректно работает, не спешите списывать свою стиралку в утиль.
         Наши мастера выполнят качественный срочный ремонт стиральной машины прямо у вас на дому.

        Основная причина поломки стиральной машинки - неправильная её установка или подключение, повреждение механизмов при установке.

        Также причиной для ремонта стиралки является износ деталей вследствие длительной эксплуатации стиральной машины.
        Современные машины с электроникой выходят из строя из-за скачков напряжения.

        Не пытайтесь отремонтировать свою стиральную машинку самостоятельно, даже если вам кажется, что проблема незначительная.
        Без профессиональной диагностики можно легко просмотреть скрытую неисправность, которая позже приведёт к более серьезным проблемам.

        Обращайтесь в наш сервис и наш опытный мастер грамотно произведет комплексную диагностику и качественно устранит неисправности вашей стиральной машины.
        Наш сервис ремонтирует стиральные машины всех моделей и брендов, представленных на российском рынке. Мы гарантируем оперативное выполнение работы, качество, и ответственный подход.
        Цены Вас так же приятно удивят.'),

       ('Холодильники',
        'category_refrigerator_edit2.jpg',
        'Качественный ремонт холодильников',
        '#!/promo_page',
        'ремонт холодильников',
        150,
        150,
        'Трудно найти недорогой и вместе с этим еще и качественный ремонт холодильника.
         Обратившись к нам, Вы ощутите эти преимущества.

         Мы произведем комплекс необходимых мероприятий по устранению неисправностей в холодильнике.

         Наши мастера имеют необходимый опыт и практические навыки, гарантирующие квалифицированный подход к ремонту.

         В работе используются только качественные комплектующие, что обеспечивает высокую надежность ремонта холодильников.',
        'Ремонт холодильника может потребоваться вследствие воздействия внешних факторов, например, скачок напряжения в электросети.
         Часто некорректное функционирование холодильника связано с человеческим фактором и систематическим нарушением владельцами холодильника правил эксплуатации этого бытового прибора.

          Самостоятельный ремонт холодильника без навыков и знания всех тонкостей или же обращение к частному мастеру без наличия лицензии может привести к ещё худшим последствиям и большим расходам.
          Лучше примите правильное решение и обратитесь к услугам нашего сервиса с большим практическим опытом работы в сфере ремонта.

          Наши мастера имеют необходимый опыт и практические навыки, гарантирующие квалифицированный подход к ремонту.
          В работе используют только качественные комплектующие, что обеспечивает высокую надежность ремонта холодильников.

          Трудно найти недорогой и вместе с этим еще и качественный ремонт холодильника.
          Обратившись к нам, Вы ощутите эти преимущества.
          Наши опытные мастера произведут комплекс необходимых мероприятий по устранению неисправностей в холодильнике.'),

       ('Пылесосы',
        'category_vacuum_cleaner_edit2.jpg',
        'Используем только оригинальные запчасти',
        '#!/promo_page',
        'ремонт пылесосов',
        150,
        150,
        'Наши специалисты готовы оказать поддержку в обслуживании всех современных моделей: для сухой чистки, моющих, автомобильных и пр.

         Своевременная профессиональная помощь застрахует вас от дорогостоящего ремонта или необходимости новой покупки!

         Независимо от модели и бренда-производителя цена на ремонт пылесоса в нашем сервисе остается на доступном уровне.

         Наша компания работает с техникой всех торговых марок, включая LG, Zelmer, Gorenje, Zanussi, Tefal, Bosch, Redmond и др.',
        'Перепады напряжения в электросети, несоблюдение правил эксплуатации, нарушения в функционировании системы охлаждения могут повлечь за собой необходимость срочного ремонта пылесоса.

Даже техника из самого дорогого сегмента не застрахована от возникновения поломок.

Если ваш пылесос вдруг стал плохо собирать пыль, появился посторонний шум или запах, то выключайте прибор и обращайтесь в наш сервис.

Наши специалисты готовы оказать поддержку в обслуживании всех современных моделей: для сухой чистки, моющих, автомобильных и пр.

Своевременная профессиональная помощь застрахует вас от дорогостоящего ремонта или необходимости новой покупки!

Оригинальные запчасти и аксессуары для пылесоса, диагностика неисправностей, чистка, гарантийное и послегарантийное обслуживание.

Полный спектр услуг по ремонту и замене вышедших из строя деталей.

Независимо от модели и бренда-производителя цена на ремонт пылесоса в нашем сервисе остается на доступном уровне.

Наша компания работает с техникой всех торговых марок, включая LG, Zelmer, Gorenje, Zanussi, Tefal, Bosch, Redmond и др.

Заручитесь профессиональной помощью мастеров нашего сервиса, которые гарантируют качественный ремонт!'),

       ('Смартфоны',
        'category_smartphone_edit2.jpg',
        'Ремонтируем смартфоны любых марок',
        '#!/promo_page',
        'ремонт смартфонов',
        150,
        150,
        'Наш сервис по ремонту смартфонов оснащен собственным складом запчастей.

Большинство нужных деталей всегда в наличии, поэтому простые ремонтные работы занимают не более часа.

Современное диагностическое и паяльное оборудование позволяет нашим мастерам точно обнаружить проблему и быстро устранить ее.

За многолетний опыт работы мы восстановили десятки тысяч устройств различной модели и сложности.

Мы не только чиним, а также предоставляем клиентам полную безопасность и гарантии качества.',
        'Если бы 20 лет назад Вам сказали, что телефоны заменят нам все, то Вы наверняка сочли этого человека за какого-нибудь ненормального.

Но подумать только: как мало потребовалось времени, чтобы гаджет стал для нас незаменимой палочкой-выручалочкой.

От банальных звонков и смс до сложнейших программ и приложений.

Заказ такси, оплата счетов и других мероприятий можно выполнить по щелчку пальца одной руки.

Один клик - и Вы выполнили весь план дел по работе, отправили годовой отчет или купили билеты в отпуск.

Поэтому так важно следить за тем, в каком состоянии находится Ваш гаджет.

Если Вы обнаружили любую неисправность, то за ремонтом любых смартфонов смело можете обратиться к нашим специалистам сервиса.

Наш сервис по ремонту смартфонов оснащен собственным складом запчастей.

Большинство нужных деталей всегда в наличии, поэтому простые ремонтные работы занимают не более часа.

Современное диагностическое и паяльное оборудование позволяет нашим мастерам точно обнаружить проблему и быстро устранить ее.

За многолетний опыт работы мы восстановили десятки тысяч устройств различной модели и сложности.

Мы не только чиним, а также предоставляем клиентам полную безопасность и гарантии качества.'),

       ('Телевизоры',
        'category_tv_edit2.jpg',
        'Ремонт телевизоров любой сложности',
        '#!/promo_page/',
        'ремонт телевизоров',
        150,
        150,
        'Наши специалисты возьмутся за любую задачу, замена матрицы или любые электронные неисправности не являются проблемой, любой ремонт телевизоров является для них мелочью.

В дополнение к высокому качеству и гарантийному обслуживанию, услуги нашего сервиса — это чрезвычайно короткое время ожидания для ремонта и, конечно же, низкие цены.

Индивидуальный подход к каждому вопросу позволяет согласовать сроки ремонта так, чтобы каждая из сторон была довольна.',
        'Телевизор в наши дни — это не просто инструмент для просмотра телевидения, он позволяет нам учиться, использовать Интернет и многое другое.

Многие люди не могут представить себе жизнь без телевизора.

Так что же делать, когда происходит поломка?

Лучше всего воспользоваться услугами профессионалов, услуги нашего сервиса решат все ваши проблемы.

Наши специалисты возьмутся за любую задачу, замена матрицы или любые электронные неисправности не являются проблемой, любой ремонт телевизоров является для них мелочью.

Все благодаря тому, что у нас работают лучшие специалисты, которые, помимо высокой квалификации, также имеют многолетний опыт, а это, в свою очередь, значительно облегчает поиск причины неисправности.

В дополнение к высокому качеству и гарантийному обслуживанию, услуги нашего сервиса — это чрезвычайно короткое время ожидания для ремонта и, конечно же, низкие цены.

Индивидуальный подход к каждому вопросу позволяет согласовать сроки ремонта так, чтобы каждая из сторон была довольна.

Это также сокращает время ожидания ремонта телевизора.');

insert into brands(name)
values ('LG'),
       ('Bosch'),
       ('BBK'),
       ('Xiaomi'),
       ('Stinol'),
       ('Atlant'),
       ('Ariston'),
       ('Beko'),
       ('ZTE'),
       ('Samsung'),
       ('Realme'),
       ('Huawei');

insert into models(category_id, brand_id, name)
values (1, 1, 'F2M5WS4W'),                  -- стиральная машина LG
       (1, 2, 'WGA142X6OE'),                -- стиральная машина Bosch
       (1, 7, 'BWSD 61051 1'),              -- стиральная машина Ariston
       (1, 8, 'WRE6512BWW'),                -- стиральная машина Beko
       (2, 1, 'GA-B379SLUL'),               -- холодильник LG
       (2, 2, 'KGN39VK25R'),                -- холодильник Bosch
       (2, 5, 'STS 185 S'),                 -- холодильник Stinol
       (2, 6, 'XM-6025-031'),               -- холодильник Atlant
       (2, 7, 'ITR 4180 W'),                -- холодильник Indesit
       (2, 8, 'RCNK335K00W'),               -- холодильник Beko
       (3, 1, 'VK76A09NTCR'),               -- пылесос cleaner LG
       (3, 2, 'BGL35MOV27'),                -- пылесос cleaner Bosch
       (3, 3, 'BV1507'),                    -- пылесос cleaner BBK
       (3, 4, 'Mi Robot Vacuum Mop P'),     -- пылесос cleaner Xiaomi
       (4, 4, 'Redmi 9A 32Gb'),             -- смартфон Xiaomi
       (4, 9, 'Blade A3 2020 NFC 32Gb'),    -- смартфон ZTE
       (4, 10, 'Galaxy A32 64Gb SM-A325F'), -- смартфон Samsung
       (4, 11, 'C21Y 4/64Gb'),              -- смартфон Realme
       (5, 1, '32LM6350PLA'),               -- телевизор LG
       (5, 1, '48LM6350PLA'),               -- телевизор LG
       (5, 3, '32LEX-7162/TS2C'),           -- телевизор BBK
       (5, 4, 'MI TV 32 P1'),               -- телевизор Xiaomi
       (5, 12, 'Vision S, 55'); -- телевизор Huawei

insert into spares(name)
values ('Помпа СМ'), -- стиральная машина
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
values ('Зарегистрирован'),
       ('Принят в работу'),
       ('Диагностика'),
       ('В процессе ремонта'),
       ('Готов к выдаче');

insert into warranty(name)
values ('Гарантийный'),
       ('Не гарантийный');


insert into orders(created_at, customer_id, applicant_name, applicant_phone, applicant_email,
                   executor_id, model_id, client_subject, client_description, product_preview_comment,
                   se_diagnostics_comment, se_provisional_repair_cost, se_repair_comment, deadline,
                   status_id, warranty_id, serial_number, total_price)
values ('2022-05-05T00:00:00', 1, 'Иван', '123-345-56-67', 'ivan@domain.su',
        1, 1, 'Ремонт', 'Стучит бак при отжиме', 'Клиент нервный. У бака есть люфт.',
        'Сломан опорный подшипник. Бак неразборный. Замена бака.',
        60, 'Бак неразборный. Замена бака на аналог. Надежная. Гарантия 1 год',
        '2022-05-25T00:00:00', 1, 1, 'TD19562T', 45),

       ('2022-05-06T00:00:00', 1, 'Петр', '456-234-77-88', 'peter@domain.su',
        2, 2, 'Ремонт', 'Не откачивает воду', 'Ремонт не гарантийный. Клиент согласен',
        'Помпа не вращается. Возможен посторонний предмет. Необходимо снимать помпу и разбирать.',
        120, 'Замена помпы на оригинал. Гарантия 1 год', '2022-05-27T00:00:00', 2, 2, 'CN578303T', 80),

       ('2022-05-07T00:00:00', 2, 'Дмитрий', '923-567-12-89', 'dmitry@domain.su',
        3, 5, 'Ремонт', 'Не морозит', 'Гарантийный ремонт. Чек и гарантийный талон имеются',
        'Компрессов, вентилятор, плата управления в норме. Проверка уровня хладагента и утечки.',
        150,
        'Утечка хладагента. Утечка устранена. Компрессор не меняли. Гарантия 6 месяцев', '2022-05-28T00:00:00', 3, 1,
        'FT78G53K', 99),

       ('2022-05-07T00:00:00', 2, 'Александр', '357-146-66-34', 'alexander@domain.su',
        4, 12, 'Ремонт', 'Не всасывает пыль', 'Не гарантийный ремонт. Замены комплектующих.',
        'Грязный HEPA фильтр. Замена.',
        60,
        'Выходной HEPA-Фильтр заменен. Входной и моторный фильтр почищен', '2022-05-29T00:00:00', 4, 2, 'DT7832L98RU',
        120),

       ('2022-05-07T00:00:00', 1, 'Григорий', '676-278-23-78', 'grigory@domain.su',
        5, 16, 'Ремонт', 'Экран черный и не показывает',
        'Не гарантийный ремонт. Скол на углу телефона. Вероятно падение углом на твердую поверхность.',
        'Экран поврежден. Восстановление невозможно. Замена.', 400,
        'Экран треснул от ударного физического воздействия. Не гарантия. Замена экрана.', '2022-06-12T00:00:00', 5, 1,
        'KV538F934T', 200);


