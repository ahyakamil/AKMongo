package com.ahyakamil.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@NoRepositoryBean
public interface AKMongo<T, ID> extends MongoRepository<T, ID> {
    <S extends T> S akUpsert(S entity);
    <S extends T> List<S> akUpsertAll(Iterable<S> entities);
}