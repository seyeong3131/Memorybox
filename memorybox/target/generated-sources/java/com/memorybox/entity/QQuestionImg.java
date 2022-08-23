package com.memorybox.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestionImg is a Querydsl query type for QuestionImg
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestionImg extends EntityPathBase<QuestionImg> {

    private static final long serialVersionUID = -722646813L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestionImg questionImg = new QQuestionImg("questionImg");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgName = createString("imgName");

    public final StringPath imgUrl = createString("imgUrl");

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public final StringPath oriImgName = createString("oriImgName");

    public final QQuestion question;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regTime = _super.regTime;

    public final StringPath repImgYn = createString("repImgYn");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    public QQuestionImg(String variable) {
        this(QuestionImg.class, forVariable(variable), INITS);
    }

    public QQuestionImg(Path<? extends QuestionImg> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestionImg(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestionImg(PathMetadata metadata, PathInits inits) {
        this(QuestionImg.class, metadata, inits);
    }

    public QQuestionImg(Class<? extends QuestionImg> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.question = inits.isInitialized("question") ? new QQuestion(forProperty("question"), inits.get("question")) : null;
    }

}

