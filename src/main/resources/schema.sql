drop table post if exists cascade;
create table post (
 post_id integer AUTO_INCREMENT,
 author varchar(10) not null,
 title varchar(10) not null,
 content varchar(255) not null,
 primary key (post_id)
);