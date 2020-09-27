package com.ahyakamil.mongo.impl;

import com.ahyakamil.mongo.AKMongo;
import org.bson.Document;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.Assert;

import java.util.List;

public class AKMongoImpl<T, ID> implements AKMongo<T, ID> {
    private final MongoOperations mongoOperations;

    public AKMongoImpl(MongoOperations mongoOperations) {
        Assert.notNull(mongoOperations, "MongoOperations must not be null!");
        this.mongoOperations = mongoOperations;
    }

    public <S extends T> S upsert(S entity) {
        Document dbObject = new Document();
        MongoConverter mongoConverter = mongoOperations.getConverter();
        mongoConverter.write(entity, dbObject);
        mongoOperations.upsert(
                new Query(Criteria.where("_id").is(dbObject.get("_id"))),
                Update.fromDocument(new Document("$set", dbObject)),
                entity.getClass()
        );
        return entity;
    }

    public <S extends T> List<S> upsertAll(Iterable<S> entities) {
        S documentClass = entities.iterator().next();
        BulkOperations bulkOperations = mongoOperations.bulkOps(BulkOperations.BulkMode.ORDERED, documentClass.getClass());

        for(S entity: entities) {
            Document dbObject = new Document();
            MongoConverter mongoConverter = mongoOperations.getConverter();
            mongoConverter.write(entity, dbObject);
            bulkOperations.upsert(
                    new Query(Criteria.where("_id").is(dbObject.get("_id"))),
                    Update.fromDocument(new Document("$set", dbObject))
            );
        }

        bulkOperations.execute();
        return (List) entities;
    }
}
