
insert into "user" ("login", "hash") values ('admin','$2a$10$6mf3CesQx9eRGB4B3sjr8e1eSr5cYO/zt87bwYVdA4O8rmjDMDdHO');

insert into "author" ("full_name") values ('Goethe');
insert into "author" ("full_name") values ('Orwell');
insert into "author" ("full_name") values ('Ashot');

insert into "book" ("title") values ('1984');
insert into "book" ("title") values ('Animal Farm');
insert into "book" ("title") values ('A Hanging');
insert into "book" ("title") values ('Faust');
insert into "book" ("title") values ('Test');

insert into "publisher" ("publisher_name") values ('Mahaon');
insert into "publisher" ("publisher_name") values ('Parka');
insert into "publisher" ("publisher_name") values ('Piter');

insert into "book_author" ("author_id", "book_id", "publisher_id") values (1, 1, 1);
insert into "book_author" ("author_id", "book_id", "publisher_id") values (2, 1, 1);
insert into "book_author" ("author_id", "book_id", "publisher_id") values (2, 2, 1);
insert into "book_author" ("author_id", "book_id", "publisher_id") values (2, 3, 1);



