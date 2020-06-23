package services;

import services.impl.db.DbServiceImpl;

import java.util.List;

public interface DbService {
    <T, V> V getEntityById(Class<T> passedType, Class<V> returnedType, Integer id);
    <T,V> List<V> select(DbServiceImpl.Query<T,V> query);
    <T> void persist(T passedObject);
}
