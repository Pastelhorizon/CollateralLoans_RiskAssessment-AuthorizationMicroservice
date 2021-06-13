create table if not exists USER (
    user_id varchar(255) Primary Key,
    password varchar(255),
    user_name varchar(255) unique not null
);