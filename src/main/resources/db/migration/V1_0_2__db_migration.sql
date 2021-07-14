CREATE TABLE emoji (id bigint PRIMARY KEY , name varchar(25), color varchar(25), image varchar(50));
INSERT INTO emoji VALUES (1, 'VERY_HAPPY', 'rgb(0, 168, 107)', 'very_happy.svg'),(2, 'HAPPY', 'rgb(65, 179, 233)', 'happy.svg'),
                         (3, 'MEH', 'rgb(122, 122, 122)', 'meh.svg'), (4, 'SAD', 'rgb(255, 186, 19)', 'sad.svg'),
                         (5, 'VERY_SAD', 'rgb(249, 88, 90)', 'very_sad.svg');