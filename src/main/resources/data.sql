INSERT INTO
  musicalstyle
VALUES
  (1, 'Rock'),(2, 'Pop'),(3, 'Electronic'),(4, 'Alternative'),(5, 'Ambiant'),(6, 'Metal'),(7, 'Hip-Hop/Rap'),(8, 'Experimental'),(9, 'Punk');
--
-- Insert defaults values to make tests
--
INSERT INTO
  `users`
 VALUES
  (1, 'test', 'test', 'test', 'test', 'test');
INSERT INTO
  `users`
 VALUES
  (2, 'test', 'test', 'test2', 'test', 'test');
INSERT INTO
  `users`
 VALUES
  (3, 'test', 'test', 'test3', 'test', 'test');
INSERT INTO
  `artists`
VALUES
  (1, 'test', NULL, NULL, NULL, 'city', 0, NULL, 0,true, 3, 1);
INSERT INTO
  `artists`
VALUES
  (2, 'test2', NULL, NULL, NULL, 'city2', 100, NULL, 100, true, 2, 2);
INSERT INTO
  `tracks`
  VALUES
  (1, 'name', 'url', 1);
INSERT INTO
  `tracks`
  VALUES
  (2, 'name', 'url', 1);
INSERT INTO 
  `socialNetwork` 
  VALUES 
  (1,'url', 'name', 1);
INSERT INTO 
  `socialNetwork` 
  VALUES 
  (2,'url', 'name', 1);
INSERT INTO 
  `shows` 
  VALUES 
  (1,'2020-10-10', 'venue', 'test');
INSERT INTO 
  `shows` 
  VALUES 
  (2,'2021-10-10', 'venue', 'test');
INSERT INTO 
  `pro` 
  VALUES 
  (1,'test', 'test', 'test','test','test', 1);
INSERT INTO 
  `pro` 
  VALUES 
  (2,'test', 'test', 'test','test','test', 2);
INSERT INTO
  `upVotes`
    VALUES
    (1,1);
  
INSERT INTO
  `favoritsArtists`
    VALUES
    (1,1);
INSERT INTO
  `artistsShows`
    VALUES
    (1,1);
INSERT INTO
  `artistsShows`
    VALUES
    (2,1);
INSERT INTO
  `artistsShows`
    VALUES
    (1,2);