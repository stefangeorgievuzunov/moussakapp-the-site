package services;

import services.impl.db.DbServiceImpl;

import java.util.List;

public interface DbService {
    <T> T getEntityById(Class<T> entity,Integer id);
    <T,V> List<V> createQuery(DbServiceImpl.Query<T,V> query);
    <T> void persist(T passedObject);
}
