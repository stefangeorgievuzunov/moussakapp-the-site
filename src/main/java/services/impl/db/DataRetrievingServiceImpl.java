package services.impl.db;

import org.modelmapper.ModelMapper;
import services.DataRetrievingService;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class DataRetrievingServiceImpl implements DataRetrievingService {
    private final EntityManager entityManager;
    private final ModelMapper modelMapper;

    @Inject
    DataRetrievingServiceImpl(EntityManager entityManager, ModelMapper modelMapper) {
        this.entityManager = entityManager;
        this.modelMapper = modelMapper;
    }

    @Override
    public <T, V> V getEntityById(Class<T> passedType, Class<V> returnedType, Integer id) {
        if (passedType.getAnnotation(Entity.class) != null) {
            entityManager.getTransaction().begin();

            T entity = entityManager.find(passedType, id);

            entityManager.getTransaction().commit();

            return modelMapper.map(entity, returnedType);
        }
        return null;
    }

    @Override
    public <T, V> List<V> selectAll(Class<T> passedType, Class<V> returnedType, Boolean desc, Field orderBy) {
        if (passedType.getAnnotation(Entity.class) != null) {

            List<Field> declaredFields = Arrays.asList(passedType.getDeclaredFields());
            declaredFields.retainAll(Arrays.asList(orderBy));

        }
        return null;
    }

}
