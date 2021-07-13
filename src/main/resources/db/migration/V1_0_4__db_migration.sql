CREATE SEQUENCE rating_sequence;
CREATE TABLE rating (id bigint AUTO_INCREMENT, emoji_id bigint ,date DateTime, PRIMARY KEY (id), constraint fk_emoji foreign key(emoji_id) references emoji(id));

