create table if not exists authors
(
    id          serial primary key,
    name        varchar(255)
);

create table if not exists books
(
    id          serial primary key,
    title       varchar(255),
    author_id   int,
    constraint author_constraint foreign key (author_id) references authors(id) on delete cascade
);
