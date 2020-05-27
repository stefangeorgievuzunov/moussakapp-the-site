package services;

import java.lang.reflect.Field;

public interface DataRetrievingService {
    <T, V> V getEntityById(Class<T> passedType, Class<V> returnedType, Integer id);
}
