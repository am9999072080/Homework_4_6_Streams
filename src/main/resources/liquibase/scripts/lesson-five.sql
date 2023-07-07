--liquibase formatted sql

--changeset AM:1

create index students_name_index ON students(name);

--changeset AM:2
create index faculties_name_color_index ON faculties(name, color);
