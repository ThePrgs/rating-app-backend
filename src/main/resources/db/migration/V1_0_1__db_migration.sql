CREATE SEQUENCE app_user_sequence;
CREATE TABLE app_user (id bigint, name varchar(25), email varchar(50),image_url varchar(255),password varchar(255), app_user_role varchar(15),provider varchar(25), provider_id varchar(25));
