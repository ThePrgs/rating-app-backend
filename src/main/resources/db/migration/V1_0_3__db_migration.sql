CREATE TABLE rating_settings (id bigint PRIMARY KEY, num_of_emoticons int, timeout int, msg varchar(120));
INSERT INTO rating_settings VALUES (1, 3, 5, 'Thank you for your rating');