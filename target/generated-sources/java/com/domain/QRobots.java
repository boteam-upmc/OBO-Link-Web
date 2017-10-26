package com.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QRobots is a Querydsl query type for Robots
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRobots extends EntityPathBase<Robots> {

    private static final long serialVersionUID = -720829914L;

    public static final QRobots robots = new QRobots("robots");

    public final NumberPath<Integer> idRobot = createNumber("idRobot", Integer.class);

    public final StringPath numSerie = createString("numSerie");

    public QRobots(String variable) {
        super(Robots.class, forVariable(variable));
    }

    public QRobots(Path<? extends Robots> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRobots(PathMetadata metadata) {
        super(Robots.class, metadata);
    }

}

