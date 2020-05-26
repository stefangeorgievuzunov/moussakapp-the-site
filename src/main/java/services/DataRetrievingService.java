package services;

import java.lang.reflect.Field;

public interface DataRetrievingService{
    <T1,T2> T2 getEntityById(Class<T1> passedType, Class<T2> returnedType, Integer id);
}
