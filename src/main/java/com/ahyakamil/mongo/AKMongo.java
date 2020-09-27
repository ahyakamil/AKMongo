package com.ahyakamil.mongo;

import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface AKMongo<T, ID> {
    <S extends T> S upsert(S entity);
    <S extends T> List<S> upsertAll(Iterable<S> entities);
}