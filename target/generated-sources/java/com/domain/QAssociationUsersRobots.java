package com.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAssociationUsersRobots is a Querydsl query type for AssociationUsersRobots
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAssociationUsersRobots extends EntityPathBase<AssociationUsersRobots> {

    private static final long serialVersionUID = 831528205L;

    public static final QAssociationUsersRobots associationUsersRobots = new QAssociationUsersRobots("associationUsersRobots");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> idRobot = createNumber("idRobot", Integer.class);

    public final NumberPath<Integer> idUser = createNumber("idUser", Integer.class);

    public QAssociationUsersRobots(String variable) {
        super(AssociationUsersRobots.class, forVariable(variable));
    }

    public QAssociationUsersRobots(Path<? extends AssociationUsersRobots> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAssociationUsersRobots(PathMetadata metadata) {
        super(AssociationUsersRobots.class, metadata);
    }

}

