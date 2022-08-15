package com.memorybox.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QQueBundle is a Querydsl query type for QueBundle
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQueBundle extends EntityPathBase<QueBundle> {

    private static final long serialVersionUID = 81278729L;

    public static final QQueBundle queBundle = new QQueBundle("queBundle");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public final EnumPath<com.memorybox.constant.QCategory> qCategory = createEnum("qCategory", com.memorybox.constant.QCategory.class);

    public final StringPath queBundleNm = createString("queBundleNm");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regTime = _super.regTime;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    public QQueBundle(String variable) {
        super(QueBundle.class, forVariable(variable));
    }

    public QQueBundle(Path<? extends QueBundle> path) {
        super(path.getType(), path.getMetadata());
    }

    public QQueBundle(PathMetadata metadata) {
        super(QueBundle.class, metadata);
    }

}

