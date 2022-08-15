package com.memorybox.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSaveQueBook is a Querydsl query type for SaveQueBook
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSaveQueBook extends EntityPathBase<SaveQueBook> {

    private static final long serialVersionUID = -342592525L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSaveQueBook saveQueBook = new QSaveQueBook("saveQueBook");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public QSaveQueBook(String variable) {
        this(SaveQueBook.class, forVariable(variable), INITS);
    }

    public QSaveQueBook(Path<? extends SaveQueBook> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSaveQueBook(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSaveQueBook(PathMetadata metadata, PathInits inits) {
        this(SaveQueBook.class, metadata, inits);
    }

    public QSaveQueBook(Class<? extends SaveQueBook> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

