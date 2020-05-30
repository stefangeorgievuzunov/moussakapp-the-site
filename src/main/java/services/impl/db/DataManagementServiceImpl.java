package services.impl.db;

import org.modelmapper.ModelMapper;
import services.DataManagementService;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class DataManagementServiceImpl implements DataManagementService {
    private final EntityManager entityManager;
    private final ModelMapper modelMapper;

    @Inject
    DataManagementServiceImpl(EntityManager entityManager, ModelMapper modelMapper) {
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
    public <T> List<T> select(Class<T> passedType, Specification specification) {
        if (passedType.getAnnotation(Entity.class) != null) {
            try {
                CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

                CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(passedType);
                Root<T> root = criteriaQuery.from(passedType);

                criteriaQuery.select(specification.select(root, criteriaBuilder));
                criteriaQuery.where(specification.where(root, criteriaBuilder));

                return entityManager.createQuery(criteriaQuery).getResultList();

            } catch (Exception exception) {
                exception.printStackTrace();
                return new ArrayList<>();
            }
        }
        return new ArrayList<>();
    }

    public abstract static class Specification {
        protected abstract <T> Selection<? extends T> select(Root<T> root, CriteriaBuilder builder);
        protected abstract <T> Predicate where(Root<T> root, CriteriaBuilder builder);
    }

    @Override
    public <T> void persist(T passedObject) {
        try {
            if (passedObject.getClass().getAnnotation(Entity.class) != null) {
                entityManager.getTransaction().begin();
                entityManager.persist(passedObject);
                entityManager.getTransaction().commit();
            }
        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
            exception.printStackTrace();
        }
    }
}
