package services;

import services.impl.db.DataManagementServiceImpl;

import java.util.List;

public interface DataManagementService {
    <T, V> V getEntityById(Class<T> passedType, Class<V> returnedType, Integer id);
    <T> List<T> select(Class<T> passedType, DataManagementServiceImpl.Specification specification);
    <T> void persist(T passedObject);
}
