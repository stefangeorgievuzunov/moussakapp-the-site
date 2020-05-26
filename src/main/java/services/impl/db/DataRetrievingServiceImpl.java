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
    public <T1,T2> T2 getEntityById(Class<T1> passedType, Class<T2> returnedType, Integer id) {

        for (Field field : passedType.getDeclaredFields()) {
            if (field.getName().toLowerCase().equals("id")) {

                String query = String.format("select entity from %s entity where entity.id=%d", passedType.getName(), id);

                List<T1> entities = entityManager.createQuery(query, passedType)
                        .getResultList();

                if (!entities.isEmpty()) {
                    T1 entity = entities.get(0);
                    return modelMapper.map(entity, returnedType);
                } else
                    break;
            }
        }
        return null;
    }
}
