drop table if exists students;
create table if not exists students (
    id bigserial primary key,
    name varchar(255) not null,
    mark real not null
    );

insert into students
    (id, name, mark)
values
    (1, 'g', 7.0),
    (2, 'f', 6.0),
    (3, 'e', 5.0),
    (4, 'd', 4.0),
    (5, 'c', 3.0),
    (6, 'b', 2.0),
    (7, 'a', 1.0);

select * from students;
	