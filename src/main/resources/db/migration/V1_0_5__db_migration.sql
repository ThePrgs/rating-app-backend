CREATE TABLE emoji_config (id bigint auto_increment, num_of_emoticons int, emoji_id bigint, PRIMARY KEY (id),
constraint fk_emojiSettings foreign key(emoji_id) references emoji(id) )