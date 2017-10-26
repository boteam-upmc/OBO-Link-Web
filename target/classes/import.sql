insert into users(idUsers, nom, prenom, mail, alpha) values
        (1001, 'Lijuan', 'LALA','lala@gmail.com', 1),
        (1002, 'user', 'userNom', 'user@gmail.com', '1');

insert into Robots(idRobot, numSerie) values
        (2001, 123456789),
        (2002, 900000001);

insert into AssociationUsersRobots(id, idUser, idRobot) VALUES
        (100001, 1001, 2001);


