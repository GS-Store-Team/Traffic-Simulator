INSERT INTO usr(id, name, password) VALUES (0, 'admin', '1234');

INSERT INTO area(id, label) VALUES (0, 'Arizona');
INSERT INTO area(id, label) VALUES (1, 'California');
INSERT INTO area(id, label) VALUES (2, 'Ohio');
INSERT INTO area(id, label) VALUES (3, 'Texas');
INSERT INTO area(id, label) VALUES (4, 'Virginia');
INSERT INTO area(id, label) VALUES (5, 'Kentucky');

INSERT INTO area_version(id, created, edited, label, locked, area_id, usr_id) VALUES (0, '2011-05-16 15:36:38', '2011-05-16 15:36:38', 'first_edition', false, 0, 0);

INSERT INTO point(id, x, y) VALUES (0, 20, 20);                                                      -- ROAD
INSERT INTO point(id, x, y) VALUES (1, 120, 20);                                                     --
INSERT INTO road(id, forward, reverse, area_version_id, end_id, start_id) VALUES (0, 2, 3, 0, 0, 1); --

INSERT INTO point(id, x, y) VALUES (2, 80, 80);                                                                                                             -- BUILDING
INSERT INTO building(id, in_flow, label, out_flow, residents, type, area_version_id, location_id, parking_id) VALUES (0, 10, 'my home', 10, 50, 'LIVING', 0, 2, null); --
