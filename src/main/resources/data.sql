INSERT INTO character (name,alias, gender,picture_url)
VALUES ('Tony Stark', 'Iron Man','Male','https://upload.wikimedia.org/wikipedia/en/thumb/4/47/Iron_Man_%28circa_2018%29.png/220px-Iron_Man_%28circa_2018%29.png'),
       ('Bruce Banner', 'The Incredible Hulk', 'Male', 'https://upload.wikimedia.org/wikipedia/en/a/aa/Hulk_%28circa_2019%29.png'),
       ('Smeagol', 'Gollum', 'Male', 'https://i.pinimg.com/236x/72/ea/e6/72eae6624ac90ff82b1b002d4102bb71.jpg');


INSERT INTO franchise (name)
VALUES ('Marvel Cinematic Universe'),
       ('The Lord of the Rings Trilogy');

INSERT INTO movie (movie_title, genre, director, picture, release_year, trailer, franchise_id)
values ('Iron Man','Action', 'Jon Favreau', 'https://upload.wikimedia.org/wikipedia/en/0/02/Iron_Man_%282008_film%29_poster.jpg', 2008, 'https://www.youtube.com/watch?v=8ugaeA-nMTc',1),
       ('Avengers: Endgame', 'Action', 'Anthony Russo', 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/ce/Avengers_Endgame_Logo_Black.svg/250px-Avengers_Endgame_Logo_Black.svg.png', '2019','https://www.youtube.com/watch?v=TcMBFSGVi1c',1),
       ('The Lord of the Rings: The Fellowship of the Ring', 'Fantasy', 'Peter Jackson', 'https://upload.wikimedia.org/wikipedia/en/8/8a/The_Lord_of_the_Rings_The_Fellowship_of_the_Ring_%282001%29.jpg', 2001,'https://www.youtube.com/watch?v=V75dMMIW2B4',2);
INSERT INTO movie_character
values (1,1),
       (2,1),
       (2,2),
       (3,3);



