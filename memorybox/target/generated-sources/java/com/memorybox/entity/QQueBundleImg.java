package com.memorybox.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQueBundleImg is a Querydsl query type for QueBundleImg
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQueBundleImg extends EntityPathBase<QueBundleImg> {

    private static final long serialVersionUID = -986865670L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQueBundleImg queBundleImg = new QQueBundleImg("queBundleImg");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgName = createString("imgName");

    public final StringPath imgUrl = createString("imgUrl");

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public final StringPath oriImgName = createString("oriImgName");

    public final QQueBundle queBundle;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regTime = _super.regTime;

    public final StringPath repImgYn = createString("repImgYn");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    public QQueBundleImg(String variable) {
        this(QueBundleImg.class, forVariable(variable), INITS);
    }

    public QQueBundleImg(Path<? extends QueBundleImg> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQueBundleImg(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQueBundleImg(PathMetadata metadata, PathInits inits) {
        this(QueBundleImg.class, metadata, inits);
    }

    public QQueBundleImg(Class<? extends QueBundleImg> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.queBundle = inits.isInitialized("queBundle") ? new QQueBundle(forProperty("queBundle")) : null;
    }

}

