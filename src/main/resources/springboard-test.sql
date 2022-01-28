-- database to make test
--
-- drop tables before create
--
DROP TABLE IF EXISTS tracks;
DROP TABLE IF EXISTS musicalStyle;
DROP TABLE IF EXISTS socialNetwork;
DROP TABLE IF EXISTS pro;
DROP TABLE IF EXISTS favoritsArtists;
DROP TABLE IF EXISTS artistsShows;
DROP TABLE IF EXISTS shows;
DROP TABLE IF EXISTS upVotes;
DROP TABLE IF EXISTS artists;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS test;
--
-- Create tables
--
CREATE Table users (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  firstName VARCHAR(50) NOT NULL,
  lastName VARCHAR(50) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(50) NOT NULL
);
CREATE Table artists(
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  artistName VARCHAR(50) NOT NULL,
  coverURL VARCHAR(100),
  contact VARCHAR(100),
  webSite VARCHAR(100),
  city VARCHAR(50) NOT NULL,
  voteCount INT NOT NULL,
  bio VARCHAR(255),
  listenCount INT NOT NULL,
  isOnArtistList BOOLEAN NOT NULL,
  musicalStyleId INT NOT NULL,
  userId INT UNIQUE NOT NULL
);
CREATE Table tracks(
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  url VARCHAR(100) NOT NULL,
  artistId INT NOT NULL
);
CREATE Table musicalStyle(
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  styleName VARCHAR(50) NOT NULL
);
CREATE Table socialNetwork(
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  url VARCHAR(100) NOT NULL,
  name VARCHAR(50) NOT NULL,
  artistId INT NOT NULL
);
CREATE Table shows(
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  date DATE NOT NULL,
  venue VARCHAR(50) NOT NULL,
  adress VARCHAR(100) NOT NULL
);
CREATE Table pro(
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  companyName VARCHAR(50) NOT NULL,
  activity VARCHAR(50) NOT NULL,
  contact VARCHAR(100) NOT NULL,
  city VARCHAR(50) NOT NULL,
  siret VARCHAR(100) NOT NULL,
  userId INT UNIQUE NOT NULL
);
CREATE Table favoritsArtists(userId INT NOT NULL, artistId INT NOT NULL);
CREATE Table upVotes(userId INT NOT NULL, artistId INT NOT NULL);
CREATE Table artistsShows(artistId INT NOT NULL, showId INT NOT NULL);
--
-- Take foreign keys
--
ALTER TABLE
  artists
ADD
  FOREIGN KEY (userId) REFERENCES users (id);
ALTER TABLE
  pro
ADD
  FOREIGN KEY (userId) REFERENCES users (id);
ALTER TABLE
  socialNetwork
ADD
  FOREIGN KEY (artistId) REFERENCES artists (id);
ALTER TABLE
  tracks
ADD
  FOREIGN KEY (artistId) REFERENCES artists (id);
ALTER TABLE
  artistsShows
ADD
  FOREIGN KEY (showId) REFERENCES shows (id);
ALTER TABLE
  artistsShows
ADD
  FOREIGN KEY (artistId) REFERENCES artists (id);
ALTER TABLE
  favoritsArtists
ADD
  FOREIGN KEY (userId) REFERENCES users (id);
ALTER TABLE
  favoritsArtists
ADD
  FOREIGN KEY (artistId) REFERENCES artists (id);
ALTER TABLE
  upVotes
ADD
  FOREIGN KEY (userId) REFERENCES users (id);
  ALTER TABLE
  upVotes
ADD
  FOREIGN KEY (artistId) REFERENCES artists (id);
--
-- Insert values to musicalStyle
--
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