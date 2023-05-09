INSERT INTO usr(id, name, password) VALUES (0, 'admin', '1234');

INSERT INTO area(id, label) VALUES (0, 'Arizona');
INSERT INTO area(id, label) VALUES (1, 'California');
INSERT INTO area(id, label) VALUES (2, 'Ohio');
INSERT INTO area(id, label) VALUES (3, 'Texas');
INSERT INTO area(id, label) VALUES (4, 'Virginia');
INSERT INTO area(id, label) VALUES (5, 'Kentucky');

INSERT INTO area_version(id, created, edited, label, locked, area_id, usr_id) VALUES (0, '2011-05-16 15:36:38', '2011-05-16 15:36:38', 'first_edition', false, 2, 0);