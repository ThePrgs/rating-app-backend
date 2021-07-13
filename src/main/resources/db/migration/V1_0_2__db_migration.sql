CREATE TABLE emoji (id bigint PRIMARY KEY , name varchar(25), color varchar(25), image varchar(50));
INSERT INTO emoji VALUES (5, 'VERY_HAPPY', 'rgb(0, 168, 107)', 'emoticon-outline.svg'),(4, 'HAPPY', 'rgb(65, 179, 233)', 'emoticon-happy-outline.svg'),
                         (3, 'MEH', 'rgb(122, 122, 122)', 'emoticon-neutral-outline.svg'), (2, 'SAD', 'rgb(255, 186, 19)', 'emoticon-sad-outline.svg'),
                         (1, 'VERY_SAD', 'rgb(249, 88, 90)', 'emoticon-angry-outline.svg');