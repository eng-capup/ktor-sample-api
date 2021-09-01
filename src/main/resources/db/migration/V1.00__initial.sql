drop table if exists Memos;

create table Memos(id int primary key AUTO_INCREMENT, body varchar(256));

insert into Memos(body) values('sample1'), ('sample2'), ('sample3');
