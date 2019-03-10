create table public.t_user(
	id bigint not null,
	first_name varchar(255),
	last_name varchar(255)
);

create table public.t_articles(
	id bigint not null,
	article_name varchar(255),
	category varchar(255),
	about varchar(255),
	url varchar(255),
	img_url varchar(255),
	author_img_url varchar(255),
	hit bigint,
	cre_date date,
	user_id bigint
);




alter table public.t_user add constraint public.constraint_1 primary key(id);

alter table public.t_articles add constraint public.constraint_2 primary key(id);

alter table public.t_articles add constraint public.constraint_3 foreign key(user_id) references public.t_articles(id);

create sequence public.blog_sequence start with 100;