
create table usuario
(
    id        serial
        primary key,
    avatar    varchar(500)
        unique,
    username  varchar(45) not null,
    email     varchar(300),
    tipo_pago smallint,
    rol       smallint    not null,
    password  char(90)    not null,
    eliminado boolean default false
);

alter table usuario
    owner to postgres;

create unique index username_users
    on usuario (username)
    where (eliminado = false);

create unique index email_users
    on usuario (email)
    where (eliminado = false);

create index users_deleted
    on usuario (eliminado);

create index users_password
    on usuario (password);

create index user_password
    on usuario (password);

create table datos_personales
(
    id               integer not null
        primary key
        constraint users_datos_personales
            references usuario,
    nombre           varchar(45),
    primer_apellido  varchar(45),
    segundo_apellido varchar(45),
    fecha_nacimiento timestamp(6),
    telefono         char(12),
    direccion        varchar(150)
);

create table cuenta
(
    id           serial
        primary key,
    nombre       varchar(20)  not null,
    descripcion  varchar(400),
    imagen       varchar(200) not null,
    imagen_fondo varchar(200)
);

create table amigo
(
    usuario1  integer               not null
        constraint users_usuario1
            references usuario,
    usuario2  integer               not null
        constraint users_usuario2
            references usuario,
    confimado boolean default false not null,
    id        serial
        primary key
);

create table user_cuenta
(
    id        serial
        primary key,
    id_user   integer not null
        constraint user_user_cuentas
            references usuario
            on update cascade on delete cascade,
    id_cuenta integer not null
        constraint cuentas_user_cuentas
            references cuenta
            on update cascade on delete cascade,
    is_admin  boolean not null
);

create table producto
(
    id          serial
        primary key,
    nombre      varchar(100) not null,
    precio      numeric(8, 2),
    descripcion varchar(400),
    imagen      varchar(200),
    id_cuenta   integer      not null
        constraint cuenta_producto
            references cuenta
            on update cascade on delete cascade,
    id_users    integer      not null
        constraint user_producto
            references usuario
            on update cascade on delete cascade,
    fecha       timestamp default now(),
    facturas    varchar(200)
);

create table historial_pago
(
    id        serial
        primary key,
    tipo_pago smallint not null,
    id_users  integer  not null
        constraint users_historial_pagos
            references usuario,
    id_cuenta integer
        references cuenta,
    monton    double precision
);

create table notificacion
(
    id       serial
        primary key,
    fecha    timestamp(6),
    mensaje  varchar(200) not null,
    visto    boolean,
    users_id integer      not null
        constraint users_notificaciones
            references usuario
);


create table chat
(
    id          serial
        primary key,
    id_usuario1 integer                             not null
        constraint fk_user1
            references usuario,
    id_usuario2 integer                             not null
        constraint fk_user2
            references usuario,
    hora        timestamp default CURRENT_TIMESTAMP not null,
    mensaje     text
);

-- Inserts for table usuario
insert into usuario (avatar, username, email, tipo_pago, rol, password) values
                                                                            ('https://images.unsplash.com/photo-1517423440428-a5a00ad493e8', 'user1', 'user1@example.com', 1, 1, 'password1'),
                                                                            ('https://images.unsplash.com/photo-1503023345310-bd7c1de61c7d', 'user2', 'user2@example.com', 2, 2, 'password2'),
                                                                            ('https://images.unsplash.com/photo-1544005313-94ddf0286df2', 'user3', 'user3@example.com', 3, 3, 'password3');

-- Inserts for table datos_personales
insert into datos_personales (id, nombre, primer_apellido, segundo_apellido, fecha_nacimiento, telefono, direccion) values
                                                                                                                        (1, 'Carlos', 'González', 'Martínez', '1990-01-01', '1234567890', 'Calle 1, Ciudad A'),
                                                                                                                        (2, 'Ana', 'López', 'Pérez', '1991-01-01', '1234567891', 'Calle 2, Ciudad B'),
                                                                                                                        (3, 'María', 'Hernández', 'Ruiz', '1992-01-01', '1234567892', 'Calle 3, Ciudad C');

-- Inserts for table cuenta
insert into cuenta (nombre, descripcion, imagen, imagen_fondo) values
                                                                   ('Grupo de Deportes', 'Intercambio sobre deportes y actividades físicas.', 'https://images.unsplash.com/photo-1594737625785-cb09f5b546c6', 'https://images.unsplash.com/photo-1596496058185-0b6b5d1b93f6'),
                                                                   ('Grupo de Cocina', 'Recetas y tips de cocina para compartir.', 'https://images.unsplash.com/photo-1566843977413-5a6986a6911c', 'https://images.unsplash.com/photo-1532635249-37d1c9b23e2e'),
                                                                   ('Grupo de Tecnología', 'Novedades y discusiones sobre tecnología.', 'https://images.unsplash.com/photo-1517430816045-df4b7deaa7b2', 'https://images.unsplash.com/photo-1498050108023-c5249f4df085');

-- Inserts for table amigo
insert into amigo (usuario1, usuario2, confimado) values
                                                      (1, 2, true),
                                                      (2, 3, false),
                                                      (3, 1, true);

-- Inserts for table user_cuenta
insert into user_cuenta (id_user, id_cuenta, is_admin) values
                                                           (1, 1, true),
                                                           (1, 2, false),
                                                           (2, 1, false),
                                                           (2, 2, true),
                                                           (3, 3, true),
                                                           (3, 1, false);

-- Inserts for table producto
insert into producto (nombre, precio, descripcion, imagen, id_cuenta, id_users, fecha, facturas) values
                                                                                                     ('Balón de Fútbol', 25.00, 'Balón de fútbol para césped artificial.', 'https://images.unsplash.com/photo-1565061827141-e9d85f1ddb51', 1, 1, now(), 'Factura1'),
                                                                                                     ('Libro de Cocina', 30.00, 'Libro con recetas para principiantes.', 'https://images.unsplash.com/photo-1515378791036-0648a3ef77b1', 2, 2, now(), 'Factura2'),
                                                                                                     ('Audífonos Bluetooth', 50.00, 'Audífonos de alta calidad con cancelación de ruido.', 'https://images.unsplash.com/photo-1517336714731-489689fd1ca8', 3, 3, now(), 'Factura3');

-- Inserts for table historial_pago
insert into historial_pago (tipo_pago, id_users, id_cuenta, monton) values
                                                                        (1, 1, 1, 100.00),
                                                                        (2, 2, 2, 200.00),
                                                                        (3, 3, 3, 300.00);

-- Inserts for table notificacion
insert into notificacion (fecha, mensaje, visto, users_id) values
                                                               (now(), 'Nueva actividad en tu grupo.', false, 1),
                                                               (now(), 'Tu compra ha sido confirmada.', true, 2),
                                                               (now(), 'Tienes un nuevo mensaje.', false, 3);

-- Inserts for table chat
insert into chat (id_usuario1, id_usuario2, hora, mensaje) values
                                                               (1, 2, now(), 'Hola Ana, ¿cómo estás?'),
                                                               (2, 3, now(), 'Hola María, ¿puedes revisar esto?'),
                                                               (3, 1, now(), 'Carlos, ¿listo para la reunión?');






