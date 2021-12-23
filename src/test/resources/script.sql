INSERT INTO directors(id, first_name, last_name) VALUES ('66241ef1-c725-4e0a-afcc-02658be40afc', 'Calinh', 'Corp');

INSERT INTO directors(id, first_name, last_name) VALUES ('2c4b1c4e-79ff-4dae-8b45-3ae6798da01d', 'Direc', 'Tor');
INSERT INTO directors(id, first_name, last_name) VALUES ('e4338f1f-f498-40f2-8559-4bcafaf8e031', 'Aaron', 'Verachtert');

INSERT INTO divisions(id, name, original_name, director) VALUES
(
 'ef1be64d-dc95-4dcc-89e4-e3b9866dbc52',
 'Calinh Corporation',
 'Apple',
 '66241ef1-c725-4e0a-afcc-02658be40afc'
);

INSERT INTO divisions(id, name, original_name, director) VALUES
(
    'fd68cfa3-2dd7-4082-8ac4-b734b667b82f',
    'Switchbelly',
    'Switchfully',
    'e4338f1f-f498-40f2-8559-4bcafaf8e031'
);

INSERT INTO divisions(id, name, original_name, director) VALUES
(
    'e0e25604-2c5e-4688-b3eb-3c8fc45980c1',
    'Cegeka',
    '',
    'e4338f1f-f498-40f2-8559-4bcafaf8e031'
);

