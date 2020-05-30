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
    public <T, V> List<V> select(Specification<T, V> specification) {
        try {
            if (specification.entityType.getAnnotation(Entity.class) != null) {
                CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

                CriteriaQuery<T> cqEntity = criteriaBuilder.createQuery(specification.entityType);
                Root<T> root = cqEntity.from(specification.entityType);

                CriteriaQuery<V> cqReturn = criteriaBuilder.createQuery(specification.returnType);

                cqReturn.select(specification.select(root, criteriaBuilder));
                cqReturn.where(specification.where(root, criteriaBuilder));

                return entityManager.createQuery(cqReturn).getResultList();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    public abstract static class Specification<T, V> {
        private final Class<T> entityType;
        private final Class<V> returnType;

        protected Specification(Class<T> entityType, Class<V> returnType) {
            this.entityType = entityType;
            this.returnType = returnType;
        }

        protected abstract Selection<? extends V> select(Root<T> root, CriteriaBuilder builder);

        protected abstract Predicate where(Root<T> root, CriteriaBuilder builder);
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
