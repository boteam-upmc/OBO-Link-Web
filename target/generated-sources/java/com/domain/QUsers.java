package com.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUsers is a Querydsl query type for Users
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUsers extends EntityPathBase<Users> {

    private static final long serialVersionUID = -1544380533L;

    public static final QUsers users = new QUsers("users");

    public final NumberPath<Integer> alpha = createNumber("alpha", Integer.class);

    public final NumberPath<Integer> idUsers = createNumber("idUsers", Integer.class);

    public final StringPath mail = createString("mail");

    public final StringPath nom = createString("nom");

    public final StringPath prenom = createString("prenom");

    public QUsers(String variable) {
        super(Users.class, forVariable(variable));
    }

    public QUsers(Path<? extends Users> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUsers(PathMetadata metadata) {
        super(Users.class, metadata);
    }

}

