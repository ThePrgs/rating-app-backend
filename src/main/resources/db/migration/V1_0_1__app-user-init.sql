CREATE SEQUENCE app_user_sequence;
CREATE TABLE app_user (id bigint primary key auto_increment, email varchar(50) UNIQUE , app_user_role varchar(15));
INSERT INTO app_user(id, email, app_user_role) VALUES (DEFAULT, 'josipprga@gmail.com', 'ADMIN'),
                                                      (DEFAULT, 'matej.rakic96@gmail.com', 'ADMIN'),
                                                      (DEFAULT, 'ivanand34@gmail.com', 'ADMIN'),
                                                      (DEFAULT, 'brzicamona@gmail.com', 'ADMIN');
