package services;

import java.lang.reflect.Field;
import java.util.List;

public interface DataRetrievingService {
    <T, V> V getEntityById(Class<T> passedType, Class<V> returnedType, Integer id);
    <T, V> List<V> selectAll(Class<T> passedType, Class<V> returnedType,Boolean desc,Field orderBy);
}
