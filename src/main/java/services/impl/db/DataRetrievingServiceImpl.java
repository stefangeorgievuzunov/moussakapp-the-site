package services.impl.db;

import org.modelmapper.ModelMapper;
import services.DataRetrievingService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.lang.reflect.Field;
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
    public <T,V> V getEntityById(Class<T> passedType, Class<V> returnedType, Integer id) {

        for (Field field : passedType.getDeclaredFields()) {
            if (field.getName().toLowerCase().equals("id")) {

                String query = String.format("select entity from %s entity where entity.id=%d", passedType.getName(), id);

                List<T> entities = entityManager.createQuery(query, passedType)
                        .getResultList();

                if (!entities.isEmpty()) {
                    T entity = entities.get(0);
                    return modelMapper.map(entity, returnedType);
                } else
                    break;
            }
        }
        return null;
    }
}
