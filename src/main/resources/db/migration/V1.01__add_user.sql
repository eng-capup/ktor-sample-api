create table Users(
email varchar(256),
password varchar(256),
PRIMARY KEY(email, password));

insert into Users(email, password) values('a@a.com', "123456"), ('b@b.com', "098765"), ('c@c.com', "asdfgh");
