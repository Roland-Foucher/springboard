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
  (
    1,
    'ASIWYFA',
    '/img/covers/asiwyfa.jpeg',
    'asiwyfa@asiwyfa.com',
    'https://asiwyfa.com',
    'UK',
    223,
    'And So I Watch You from Afar is a Northern Irish instrumental rock band from Belfast, composed of guitarists Rory Friers and Niall Kennedy, bassist Johnny Adger and drummer Chris Wee. The band released two albums with former member Tony Wright, who was replaced by Niall Kennedy in 2011. The band signed with Richter Collective, and in October 2011 announced a deal in North America with management and record label Sargent House. They released their self-titled debut studio album on April 13, 2009. Their second studio album, Gangs (2011), was met with favorable reviews. The band released their third studio album, All Hail Bright Futures, on March 15, 2013 on Sargent House. Their fourth studio album, Heirs (2015). Their fifth studio album, The Endless Shimmering, was released on October 20, 2017. ',
    412,
    true,
    1,
    1
  );
  INSERT INTO
`artists`
  VALUES
  (2,
    'Amen Ra',
    '/img/covers/amenra.jpeg',
    'amenra@gmail.com',
    'https://amenra.bandcamp.com',
    'Belgique',
    153,
    'Amenra est un groupe belge de doom metal, originaire de Courtrai, en Flandre-Occidentale. Il est formé en 1999 par le chanteur Colin H. van Eeckhout et le guitariste Mathieu Vandekerckhove, et plus tard rejoint par le batteur Bjorn Lebon, le guitariste Lennart Bossu et le bassiste Levy Seynaeve. Le groupe compte notamment une série de six albums studio intitulée Mass. En 2005, Church of Ra sest développée autour de musiciens et dartistes proches du groupe dont les œuvres font lobjet de présentations en France et en Belgique.',
    326,
    true,
    6,
    2
  );

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