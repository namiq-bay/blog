create table public.users(
	id bigint not null AUTO_INCREMENT,
	username varchar(128) not null,
	password varchar(512) not null,
	first_name varchar(255),
	last_name varchar(255),
	email varchar(255),
	enabled boolean not null default true
);

create table authorities(
	id bigint not null,
	username varchar(128) not null ,
	authority varchar(128) not null 
);

create unique index idx_aut_username on authorities(username, authority);

create table public.t_articles(
	id bigint not null,
	article_name varchar(255),
	category varchar(255),
	about varchar(255),
	url varchar(255),
	img_url varchar(255),
	author_img_url varchar(255),
	bg_img varchar(255),
	hit bigint,
	article_body text,
	user_id bigint
);


create table public.t_comments(
	id bigint not null, 
	accept tinyint(1) default 0,
	name varchar(255),
	email varchar(255),
	web_site varchar(255),
	comment text,
	comment_date date,
	article_id bigint
);



alter table public.users add constraint public.constraint_1 primary key(username);

alter table public.t_articles add constraint public.constraint_2 primary key(id);

alter table public.t_comments add constraint public.constraint_3 primary key(id);

alter table public.t_articles add constraint public.constraint_4 foreign key(user_id) references public.users(id);

alter table public.t_comments add constraint public.constraint_5 foreign key(article_id) references public.t_articles(id);

alter table public.authorities add constraint public.constraint_6 primary key(id);

create sequence public.blog_sequence start with 100;
