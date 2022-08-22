package com.memorybox.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSaveQue is a Querydsl query type for SaveQue
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSaveQue extends EntityPathBase<SaveQue> {

    private static final long serialVersionUID = -1247819286L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSaveQue saveQue = new QSaveQue("saveQue");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public final QQuestion question;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regTime = _super.regTime;

    public final QSaveQueBook saveQueBook;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    public QSaveQue(String variable) {
        this(SaveQue.class, forVariable(variable), INITS);
    }

    public QSaveQue(Path<? extends SaveQue> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSaveQue(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSaveQue(PathMetadata metadata, PathInits inits) {
        this(SaveQue.class, metadata, inits);
    }

    public QSaveQue(Class<? extends SaveQue> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
        this.question = inits.isInitialized("question") ? new QQuestion(forProperty("question"), inits.get("question")) : null;
        this.saveQueBook = inits.isInitialized("saveQueBook") ? new QSaveQueBook(forProperty("saveQueBook"), inits.get("saveQueBook")) : null;
    }

}

