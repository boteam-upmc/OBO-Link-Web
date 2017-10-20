insert into users(idUsers, nom, prenom, mail, alpha) values
        (1001, 'Lijuan', 'LALA','lala@gmail.com', 1);

insert into Robots(idRobot, numSerie) values
        (1002, 123456789);


insert into Users_Robots(idUsers, idRobots) values
        (1001, 1002);