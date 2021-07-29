CREATE TABLE emoji (id bigint PRIMARY KEY , name varchar(25), color varchar(25), image varchar(50));
INSERT INTO emoji VALUES (1, 'VERY_HAPPY', 'rgb(0, 168, 107)', 'https://res.cloudinary.com/dxlyytkww/image/upload/v1627541878/RatingApp/very_happy_ujm04f.svg'),
						 (2, 'HAPPY', 'rgb(65, 179, 233)', 'https://res.cloudinary.com/dxlyytkww/image/upload/v1627541876/RatingApp/happy_mlvvqt.svg'),
						 (3, 'MEH', 'rgb(122, 122, 122)', 'https://res.cloudinary.com/dxlyytkww/image/upload/v1627541877/RatingApp/meh_owc1iz.svg'),
						 (4, 'SAD', 'rgb(255, 186, 19)', 'https://res.cloudinary.com/dxlyytkww/image/upload/v1627541878/RatingApp/sad_vokr45.svg'),
						 (5, 'VERY_SAD', 'rgb(249, 88, 90)', 'https://res.cloudinary.com/dxlyytkww/image/upload/v1627541878/RatingApp/very_sad_rwkai6.svg');