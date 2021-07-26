CREATE SEQUENCE app_user_sequence;
CREATE TABLE app_user (id bigint, email varchar(50) UNIQUE , app_user_role varchar(15));
INSERT INTO app_user(id, email, app_user_role) VALUES (1, 'josipprga@gmail.com', 'ADMIN');

