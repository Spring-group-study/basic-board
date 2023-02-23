drop table post if exists cascade;
create table post (
 post_id integer AUTO_INCREMENT,
 author varchar(10) not null,
 title varchar(10) not null,
 content varchar(255) not null,
 primary key (post_id)
);

insert into post(author,title,content) values('author1','title1','content1');
insert into post(author,title,content) values('author2','title2','content2');
insert into post(author,title,content) values('author3','title3','content3');
insert into post(author,title,content) values('author4','title4','content4');
insert into post(author,title,content) values('author5','title5','content5');
insert into post(author,title,content) values('author6','title6','content6');
insert into post(author,title,content) values('author7','title7','content7');
insert into post(author,title,content) values('author8','title8','content8');
insert into post(author,title,content) values('author9','title9','content9');
insert into post(author,title,content) values('author10','title10','content10');