INSERT INTO usr(id, name, password)
VALUES (0, 'admin', '1234');

INSERT INTO area(id, label)
VALUES (0, 'Arizona');
INSERT INTO area(id, label)
VALUES (1, 'California');
INSERT INTO area(id, label)
VALUES (2, 'Ohio');
INSERT INTO area(id, label)
VALUES (3, 'Texas');
INSERT INTO area(id, label)
VALUES (4, 'Virginia');
INSERT INTO area(id, label)
VALUES (5, 'Kentucky');

INSERT INTO area_version(id, created, edited, label, locked, area_id, usr_id)
VALUES (0, '2011-05-16 15:36:38', '2011-05-16 15:36:38', 'first_edition', false, 0, 0);

INSERT INTO point(id, x, y)
VALUES (0, 20, 20);
INSERT INTO point(id, x, y)
VALUES (1, 20, 120);
INSERT INTO road(id, forward, reverse, area_version_id, end_id, start_id)
VALUES (0, 2, 2, 0, 0, 1); -- ROAD 1

INSERT INTO point(id, x, y)
VALUES (2, 100, 120);
INSERT INTO road(id, forward, reverse, area_version_id, end_id, start_id)
VALUES (1, 2, 2, 0, 0, 2); -- ROAD 2

INSERT INTO road(id, forward, reverse, area_version_id, end_id, start_id)
VALUES (2, 2, 2, 0, 1, 2); -- ROAD 3

INSERT INTO point(id, x, y)
VALUES (3, 20, 200);
INSERT INTO road(id, forward, reverse, area_version_id, end_id, start_id)
VALUES (3, 1, 1, 0, 1, 3); -- ROAD 4

INSERT INTO point(id, x, y)
VALUES (4, 100, 200);
INSERT INTO road(id, forward, reverse, area_version_id, end_id, start_id)
VALUES (4, 1, 1, 0, 3, 4); -- ROAD 5

INSERT INTO point(id, x, y)
VALUES (5, 100, 250);
INSERT INTO road(id, forward, reverse, area_version_id, end_id, start_id)
VALUES (5, 2, 2, 0, 2, 4); -- ROAD 6

INSERT INTO road(id, forward, reverse, area_version_id, end_id, start_id)
VALUES (6, 1, 1, 0, 4, 5); -- ROAD 7

INSERT INTO point(id, x, y)
VALUES (6, 50, 340);
INSERT INTO road(id, forward, reverse, area_version_id, end_id, start_id)
VALUES (7, 1, 1, 0, 5, 6); -- ROAD 8

INSERT INTO point(id, x, y)
VALUES (7, 160, 340);
INSERT INTO road(id, forward, reverse, area_version_id, end_id, start_id)
VALUES (8, 1, 1, 0, 5, 7); -- ROAD 9

INSERT INTO point(id, x, y)
VALUES (8, 200, 120);
INSERT INTO road(id, forward, reverse, area_version_id, end_id, start_id)
VALUES (9, 2, 2, 0, 2, 8); -- ROAD 10

INSERT INTO point(id, x, y)
VALUES (9, 250, 110);
INSERT INTO road(id, forward, reverse, area_version_id, end_id, start_id)
VALUES (10, 2, 2, 0, 8, 9); -- ROAD 11

INSERT INTO point(id, x, y)
VALUES (10, 310, 100);
INSERT INTO road(id, forward, reverse, area_version_id, end_id, start_id)
VALUES (11, 2, 2, 0, 9, 10); -- ROAD 12

INSERT INTO point(id, x, y)
VALUES (11, 200, 170);
INSERT INTO road(id, forward, reverse, area_version_id, end_id, start_id)
VALUES (12, 2, 2, 0, 8, 11); -- ROAD 13

INSERT INTO point(id, x, y)
VALUES (12, 250, 160);
INSERT INTO road(id, forward, reverse, area_version_id, end_id, start_id)
VALUES (13, 3, 3, 0, 11, 12); -- ROAD 14
INSERT INTO road(id, forward, reverse, area_version_id, end_id, start_id)
VALUES (14, 3, 3, 0, 9, 12); -- ROAD 15

INSERT INTO point(id, x, y)
VALUES (13, 310, 150);
INSERT INTO road(id, forward, reverse, area_version_id, end_id, start_id)
VALUES (15, 3, 3, 0, 12, 13); -- ROAD 16
INSERT INTO road(id, forward, reverse, area_version_id, end_id, start_id)
VALUES (16, 2, 2, 0, 10, 13); -- ROAD 17

INSERT INTO point(id, x, y)
VALUES (14, 200, 210);
INSERT INTO road(id, forward, reverse, area_version_id, end_id, start_id)
VALUES (17, 2, 2, 0, 11, 14); -- ROAD 18

INSERT INTO point(id, x, y)
VALUES (15, 250, 200);
INSERT INTO road(id, forward, reverse, area_version_id, end_id, start_id)
VALUES (18, 2, 2, 0, 14, 15); -- ROAD 19
INSERT INTO road(id, forward, reverse, area_version_id, end_id, start_id)
VALUES (19, 3, 3, 0, 12, 15); -- ROAD 20

INSERT INTO point(id, x, y)
VALUES (16, 310, 190);
INSERT INTO road(id, forward, reverse, area_version_id, end_id, start_id)
VALUES (20, 2, 2, 0, 15, 16); -- ROAD 21
INSERT INTO road(id, forward, reverse, area_version_id, end_id, start_id)
VALUES (21, 2, 2, 0, 16, 13); -- ROAD 22

INSERT INTO road(id, forward, reverse, area_version_id, end_id, start_id)
VALUES (22, 2, 2, 0, 14, 4); -- ROAD 23

INSERT INTO building(id, in_flow, label, out_flow, residents, type, area_version_id, location_id, parking_id)
VALUES (0, 10, 'my home', 10, 50, 'LIVING', 0, 0, null);
INSERT INTO building(id, in_flow, label, out_flow, residents, type, area_version_id, location_id, parking_id)
VALUES (1, 10, 'house 2', 10, 50, 'LIVING', 0, 0, null);

INSERT INTO building(id, in_flow, label, out_flow, residents, type, area_version_id, location_id, parking_id)
VALUES (2, 10, 'branch I house 1', 10, 50, 'LIVING', 0, 6, null);
INSERT INTO building(id, in_flow, label, out_flow, residents, type, area_version_id, location_id, parking_id)
VALUES (3, 10, 'branch I house 2', 10, 50, 'LIVING', 0, 6, null);

INSERT INTO building(id, in_flow, label, out_flow, residents, type, area_version_id, location_id, parking_id)
VALUES (4, 10, 'branch II house 1', 10, 50, 'LIVING', 0, 7, null);

INSERT INTO building(id, in_flow, label, out_flow, residents, type, area_version_id, location_id, parking_id)
VALUES (5, 10, 'work 1', 10, 50, 'WORKING', 0, 3, null);

INSERT INTO building(id, in_flow, label, out_flow, residents, type, area_version_id, location_id, parking_id)
VALUES (6, 10, 'work 2', 10, 50, 'WORKING', 0, 13, null);

INSERT INTO building(id, in_flow, label, out_flow, residents, type, area_version_id, location_id, parking_id)
VALUES (7, 10, 'work 3', 10, 50, 'WORKING', 0, 10, null);
INSERT INTO building(id, in_flow, label, out_flow, residents, type, area_version_id, location_id, parking_id)
VALUES (8, 10, 'work 4', 10, 50, 'WORKING', 0, 10, null);

INSERT INTO building(id, in_flow, label, out_flow, residents, type, area_version_id, location_id, parking_id)
VALUES (9, 10, 'work 5', 10, 50, 'WORKING', 0, 16, null);
INSERT INTO building(id, in_flow, label, out_flow, residents, type, area_version_id, location_id, parking_id)
VALUES (10, 10, 'work 6', 10, 50, 'WORKING', 0, 16, null);

