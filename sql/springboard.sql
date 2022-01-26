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
  bio VARCHAR(1024),
  listenCount INT NOT NULL,
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
CREATE Table favoritsArtists(userId INT NOT NULL, artistId INT NOT NULL);
CREATE Table upVotes(userId INT NOT NULL, artistId INT NOT NULL);
CREATE Table artistsShows(artistId INT NOT NULL, showId INT NOT NULL);
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
  FOREIGN KEY (showId) REFERENCES shows (id),
ADD
  FOREIGN KEY (artistId) REFERENCES artists (id);
ALTER TABLE
  favoritsArtists
ADD
  FOREIGN KEY (userId) REFERENCES users (id),
ADD
  FOREIGN KEY (artistId) REFERENCES artists (id);
ALTER TABLE
  upVotes
ADD
  FOREIGN KEY (userId) REFERENCES users (id),
ADD
  FOREIGN KEY (artistId) REFERENCES artists (id);
INSERT INTO
  musicalstyle
VALUES
  (1, 'Rock'),(2, 'Pop'),(3, 'Electronic'),(4, 'Alternative'),(5, 'Ambiant'),(6, 'Metal'),(7, 'Hip-Hop/Rap'),(8, 'Experimental'),(9, 'Punk');
INSERT INTO
  users
VALUES
  (
    1,
    "firsname1",
    "lastName1",
    "email1",
    "password1",
    "artist"
  );
INSERT INTO
  users
VALUES
  (
    2,
    "firsname2",
    "lastName2",
    "email2",
    "password2",
    "artist"
  );
INSERT INTO
  users
VALUES
  (
    3,
    "firsname3",
    "lastName3",
    "email3",
    "password3",
    "artist"
  );
INSERT INTO
  users
VALUES
  (
    4,
    "firsname4",
    "lastName4",
    "email4",
    "password4",
    "artist"
  );
INSERT INTO
  users
VALUES
  (
    5,
    "firsname5",
    "lastName5",
    "email5",
    "password5",
    "artist"
  );
INSERT INTO
  users
VALUES
  (
    6,
    "firsname6",
    "lastName6",
    "email6",
    "password6",
    "artist"
  );
INSERT INTO
  users
VALUES
  (
    7,
    "firsname7",
    "lastName7",
    "email7",
    "password7",
    "artist"
  );
INSERT INTO
  users
VALUES
  (
    8,
    "firsname8",
    "lastName8",
    "email8",
    "password8",
    "artist"
  );
INSERT INTO
  users
VALUES
  (
    9,
    "firsname9",
    "lastName9",
    "email9",
    "password9",
    "artist"
  );
INSERT INTO
  users
VALUES
  (
    10,
    "firsname10",
    "lastName10",
    "email10",
    "password10",
    "artist"
  );
INSERT INTO
  users
VALUES
  (
    11,
    "firsname11",
    "lastName11",
    "email11",
    "password11",
    "artist"
  );
INSERT INTO
  users
VALUES
  (
    12,
    "firsname12",
    "lastName12",
    "email12",
    "password12",
    "artist"
  );
-- IMPORT artists
INSERT INTO
  artists
VALUES
  (
    1,
    "ASIWYFA",
    "/img/covers/asiwyfa.jpeg",
    "asiwyfa@asiwyfa.com",
    "https://asiwyfa.com",
    "UK",
    223,
    "And So I Watch You from Afar is a Northern Irish instrumental rock band from Belfast, composed of guitarists Rory Friers and Niall Kennedy, bassist Johnny Adger and drummer Chris Wee. The band released two albums with former member Tony Wright, who was replaced by Niall Kennedy in 2011. The band signed with Richter Collective, and in October 2011 announced a deal in North America with management and record label Sargent House. They released their self-titled debut studio album on April 13, 2009. Their second studio album, Gangs (2011), was met with favorable reviews. The band released their third studio album, All Hail Bright Futures, on March 15, 2013 on Sargent House. Their fourth studio album, Heirs (2015). Their fifth studio album, The Endless Shimmering, was released on October 20, 2017. ",
    412,
    1,
    1
  );
INSERT INTO
  artists
VALUES
  (
    2,
    "Amen Ra",
    "/img/covers/amenra.jpeg",
    "amenra@gmail.com",
    "https://amenra.bandcamp.com",
    "Belgique",
    153,
    "Amenra est un groupe belge de doom metal, originaire de Courtrai, en Flandre-Occidentale. Il est formé en 1999 par le chanteur Colin H. van Eeckhout et le guitariste Mathieu Vandekerckhove, et plus tard rejoint par le batteur Bjorn Lebon, le guitariste Lennart Bossu et le bassiste Levy Seynaeve. Le groupe compte notamment une série de six albums studio intitulée Mass. En 2005, Church of Ra s'est développée autour de musiciens et d'artistes proches du groupe dont les œuvres font l'objet de présentations en France et en Belgique.",
    326,
    6,
    2
  );
INSERT INTO
  artists
VALUES
  (
    3,
    "Dinausor Jr.",
    "/img/covers/DinausorJR.jpeg",
    "dino@gmail.com",
    "https://www.dinosaurjr.com",
    "USA",
    298,
    "Dinosaur Jr. est un groupe de rock indépendant américain, originaire d'Amherst, dans le Massachusetts.",
    403,
    4,
    3
  );
INSERT INTO
  artists
VALUES
  (
    4,
    "Bonobo",
    "/img/covers/bonobo.jpeg",
    "bonobo@gmail.com",
    "https://bonobomusic.com",
    "UK",
    2000,
    "Simon Green (born 30 March 1976), known by his stage name Bonobo, is a British musician, producer, and DJ based in Los Angeles.[1] He debuted with a trip hop aesthetic, and has since explored more upbeat approaches as well as jazz and world music influences. His tranquil electronic sound incorporates the use of organic instrumentation, and is recreated by a full band in live performances. Green's work has attained a cult following, and he has collaborated with a variety of other artists.",
    4300,
    3,
    4
  );
INSERT INTO
  artists
VALUES
  (
    5,
    "Burial",
    "/img/covers/burial.jpeg",
    "burial@gmail.com",
    "https://https://burial.bandcamp.com",
    "UK",
    1000,
    "Burial est le nom de scène de William Emmanuel Bevan, un musicien britannique produisant une musique électronique empruntant des éléments à l'UK Garage, au dubstep (avec lequel il est généralement associé), au trip hop et à l'ambient. William Bevan est londonien, de South London",
    2500,
    5,
    5
  );
INSERT INTO
  artists
VALUES
  (
    6,
    "Dead Cross",
    "/img/covers/deadcross.jpeg",
    "deadcross@deadcross.com",
    "https://deadcross.bandcamp.com",
    "USA",
    4322,
    "Dead Cross are an American hardcore punk supergroup formed in Southern California. The band consists of guitarist Michael Crain (Retox), bassist Justin Pearson (the Locust, Head Wound City and Retox), drummer Dave Lombardo (Slayer, Mr. Bungle and Fantômas) and vocalist Mike Patton (currently of Faith No More, Mr Bungle and Fantômas)",
    5343,
    6,
    6
  );
INSERT INTO
  artists
VALUES
  (
    7,
    "DoseOne",
    "/img/covers/doseone.jpeg",
    "doseone@anticon.com",
    "https://doseone.bandcamp.com",
    "USA",
    2345,
    "Adam Drucker (born April 21, 1977), better known by his stage name Doseone, is an American rapper, producer, poet and artist. He is a co-founder of the indie hip hop record label Anticon.[1] He has also been a member of numerous groups including Deep Puddle Dynamics, Greenthink, Clouddead, Themselves, Subtle, 13 & God, Go Dark, Nevermen, and A7pha.",
    4322,
    7,
    7
  );
INSERT INTO
  artists
VALUES
  (
    8,
    "Fugazi",
    "/img/covers/fugazi.jpeg",
    "fugazi@yahoo.com",
    "https://fugazi.bandcamp.com",
    "USA",
    4325,
    "Fugazi is an American post-hardcore band that formed in Washington, D.C. in 1986. The band consists of guitarists and vocalists Ian MacKaye and Guy Picciotto, bassist Joe Lally, and drummer Brendan Canty. They are noted for their style-transcending music, DIY ethical stance, manner of business practice, and contempt for the music industry.",
    8787,
    9,
    8
  );
INSERT INTO
  artists
VALUES
  (
    9,
    "Marvin",
    "/img/covers/marvin.jpeg",
    "marvin@gmail.com",
    "https://marvin.bandcamp.com",
    "France",
    4325,
    "Marvin est un groupe de rock instrumental francais",
    8787,
    1,
    9
  );
INSERT INTO
  artists
VALUES
  (
    10,
    "Metronomy",
    "/img/covers/metronomy.jpeg",
    "metronomy@gmail.com",
    "http://metronomy.co.uk",
    "UK",
    5304,
    "Metronomy are an English electronic music group formed in 1999. As of 2021, the band consists of Joseph Mount (vocals, keyboards and guitar), Oscar Cash (keyboards, backing vocals, guitar and saxophone), Anna Prior (drums and vocals), Olugbenga Adelekan (bass guitar and vocals) and Michael Lovett (keyboards and guitars).",
    3199,
    3,
    10
  );
INSERT INTO
  artists
VALUES
  (
    11,
    "Mondo Cane",
    "/img/covers/mondocane.jpeg",
    "mikepatton@gmail.com",
    "http://metronomy.co.uk",
    "USA",
    5304,
    "Mondo Cane is a 2010 album by Mike Patton. Featuring a forty-member orchestra and fifteen-piece backing band, the album contains a series of cover versions of 1950s and 1960s Italian pop music. Patton conceived of the album while living in Bologna, and became attracted to music he heard on the radio featuring pop singers backed by orchestras.",
    3199,
    4,
    11
  );
INSERT INTO
  artists
VALUES
  (
    12,
    "Sonic Youth",
    "/img/covers/sonicYouth.jpeg",
    "sonicyouth@gmail.com",
    "http://www.sonicyouth.com",
    "USA",
    5403,
    "Sonic Youth emerged from the experimental no wave art and music scene in New York before evolving into a more conventional rock band and becoming a prominent member of the American noise rock scene. Sonic Youth have been praised for having 'redefined what rock guitar could do' using a wide variety of unorthodox guitar tunings while preparing guitars with objects like drum sticks and screwdrivers to alter the instruments' timbre. The band was a pivotal influence on the alternative and indie rock movements",
    1223,
    8,
    12
  );
  INSERT INTO
    tracks
  VALUES
  (
    1, "BEAUTIFUL UNIVERSE MASTER CHAMPION", "/sound/01_BEAUTIFULUNIVERSEMASTERCHAMPION.mp3", 1
  );
  INSERT INTO
    tracks
  VALUES
  (
    2, "Gangs", "/sound/02_Gang.mp3", 1
  );
  INSERT INTO
    tracks
  VALUES
  (
    3, "Search Party Animal", "/sound/03_Search_Party_Animal.mp3", 1
  );
  INSERT INTO
    tracks
  VALUES
  (
    4, "7 Billion People all Alive at Once.mp3", "/sound/04_7_Billion_People_all_Alive_at_Once.mp3", 1
  );