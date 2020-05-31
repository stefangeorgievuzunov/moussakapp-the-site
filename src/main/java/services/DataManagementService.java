package services;

import services.impl.db.DataManagementServiceImpl;

import java.util.List;

public interface DataManagementService {
    <T, V> V getEntityById(Class<T> passedType, Class<V> returnedType, Integer id);
    <T,V> List<V> select(DataManagementServiceImpl.Specification<T,V> specification);
    <T, V> V selectSingleResult(DataManagementServiceImpl.Specification<T, V> specification);
    <T> void persist(T passedObject);
}
