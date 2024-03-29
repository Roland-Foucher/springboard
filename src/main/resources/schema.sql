-- database to make test

--
-- drop tables before create
--

DROP TABLE IF EXISTS tracks;
DROP TABLE IF EXISTS socialNetwork;
DROP TABLE IF EXISTS pro;
DROP TABLE IF EXISTS favoritsArtists;
DROP TABLE IF EXISTS artistsShows;
DROP TABLE IF EXISTS shows;
DROP TABLE IF EXISTS upVotes;
DROP TABLE IF EXISTS artists;
DROP TABLE IF EXISTS musicalStyle;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS test;

-- Create all tables


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
  coverURL VARCHAR(100)NOT NULL,
  contact VARCHAR(100) NOT NULL,
  webSite VARCHAR(100),
  city VARCHAR(50) NOT NULL,
  voteCount INT NOT NULL,
  bio TEXT NOT NULL,
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
  userId INT NOT NULL UNIQUE
);
CREATE Table favoritsArtists(
  userId INT NOT NULL, 
  artistId INT NOT NULL,
  PRIMARY KEY (userId, artistId)
  );
CREATE Table upVotes(
  userId INT NOT NULL, 
  artistId INT NOT NULL,
  PRIMARY KEY (userId, artistId)
  );
CREATE Table artistsShows(
  artistId INT NOT NULL,
   showId INT NOT NULL,
   PRIMARY KEY (artistId, showId)
   );
--
-- Take foreign keys
--
ALTER TABLE
  artists
ADD
  FOREIGN KEY (userId) REFERENCES users (id) ON DELETE CASCADE;;
ALTER TABLE
  artists
ADD
  FOREIGN KEY (musicalStyleId) REFERENCES musicalStyle (id);
ALTER TABLE
  pro
ADD
  FOREIGN KEY (userId) REFERENCES users (id) ON DELETE CASCADE;;
ALTER TABLE
  socialNetwork
ADD
  FOREIGN KEY (artistId) REFERENCES artists (id) ON DELETE CASCADE;;
ALTER TABLE
  tracks
ADD
  FOREIGN KEY (artistId) REFERENCES artists (id) ON DELETE CASCADE;;
ALTER TABLE
  artistsShows
ADD
  FOREIGN KEY (showId) REFERENCES shows (id) ON DELETE CASCADE;
ALTER TABLE
  artistsShows
ADD
  FOREIGN KEY (artistId) REFERENCES artists (id) ON DELETE CASCADE;
ALTER TABLE
  favoritsArtists
ADD
  FOREIGN KEY (userId) REFERENCES users (id) ON DELETE CASCADE;
ALTER TABLE
  favoritsArtists
ADD
  FOREIGN KEY (artistId) REFERENCES artists (id) ON DELETE CASCADE;
ALTER TABLE
  upVotes
ADD
  FOREIGN KEY (userId) REFERENCES users (id) ON DELETE CASCADE;
  ALTER TABLE
  upVotes
ADD
  FOREIGN KEY (artistId) REFERENCES artists (id) ON DELETE CASCADE;
--
-- Insert values to musicalStyle
--