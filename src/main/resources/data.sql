insert into users values(1, 'namiq14', '{bcrypt}$2a$10$rTmDTD5MN58l.VYIyQfmOOQnsNnojVWmX5T5CqO0/puyyRNqLxyWO', 'Namiq', 'Bayramov', 'namiqbayramov14@gmail.com', true);
insert into users values(2, 'aldous666','{noop}aldous_1234','Aldous', 'Huxley','aldous_h@gmail.com',true);
insert into users values(3, 'rayBradburry', '{noop}martian_13','Ray', 'Bradburry','brad_burry@gmail.com',true);
insert into users values(4, 'darkWriter','{noop}black_cat','Edgar', 'Po','edgar_allan_po@gmail.com', true);

insert into authorities values (1,'namiq14', 'ROLE_USER');
insert into authorities values (2,'namiq14', 'ROLE_EDITOR');
insert into authorities values (3,'namiq14', 'ROLE_ADMIN');
insert into authorities values (4,'aldous666', 'ROLE_USER');
insert into authorities values (5,'aldous666', 'ROLE_EDITOR');
insert into authorities values (6,'rayBradburry', 'ROLE_USER');
insert into authorities values (7,'darkWriter', 'ROLE_USER');


insert into t_articles(id, article_name, category, about, url, img_url, author_img_url,bg_img, hit, article_body, user_id)  
values(1L, 'RSA - Açıq açarlı kriptosistem', 'CRYPTOGRAPHY', 'Rsa alqoritminin iş prinsipləri. Şifrələmə və deşifrələmə üsulları.', 'rsa', 'image_1.jpg', 'author_n.jpeg', 'rsa_bg.jpg', 123, 'body', 1);

insert into t_articles(id, article_name, category, about, url, img_url, author_img_url,bg_img, hit, article_body, user_id)  
values(2L, 'İkilik Ağaclar (Binary Tree)', 'DATA STRUCTURES', 'Ağac strukturları və İkilik ağaclar', 'binaryTree', 'binary.png', 'author_n.jpeg','btree_bg.jpg', 9, 'test2',  1);


insert into t_articles(id, article_name, category, about, url, img_url, author_img_url,bg_img, hit, article_body, user_id)  
values(3L, 'Deffi-Helman açar dəyişmə protokolu', 'CRYPTOGRAPHY', 'Parolların generasiyası və açıq kanallarla ötürülməsi.', 'deffi_helman', 'keys.jpeg', 'author_n.jpeg','rsa_bg.jpg', 121, 'test3', 1);


insert into t_articles(id, article_name, category, about, url, img_url, author_img_url,bg_img, hit, article_body, user_id)  
values(4L, 'Kriptoanaliz - "Tezlik analizi" və "Kasiski üsulu"', 'CRYPTOGRAPHY', 'Gizli şifrəni bilmədən gizli məlumatın deşifrələnməsi', 'cryptanalisis', 'encrpt.jpeg', 'author_n.jpeg','rsa_bg.jpg', 91, 'test4', 1);


insert into t_comments(id, name, email, web_site, comment, article_id) 
values(1,'John', 'john1234@gmail.com', 'www.jhon.com', 'this is test comment1234567 12345678 234567897654321234567654', 1);

insert into t_comments(id, name, email, web_site, comment, article_id) 
values(2,'John2', 'johndfdf1234@gmail.com', 'www.jhonf.com', 'this is test comment1234567 12345678 234567897654321234567fdfd654', 1);

insert into t_comments(id, name, email, web_site, comment, article_id) 
values(3,'John3', 'johnfdf1234@gmail.com', 'www.jhone.com', 'this is test comment1234567 12345678 234567fdfdf897654321234567654', 2);

