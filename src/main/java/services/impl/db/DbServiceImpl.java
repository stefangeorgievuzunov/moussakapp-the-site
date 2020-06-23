package services.impl.db;

import org.modelmapper.ModelMapper;
import services.DbService;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class DbServiceImpl implements DbService {
    private final EntityManager entityManager;
    private final ModelMapper modelMapper;

    @Inject
    DbServiceImpl(EntityManager entityManager, ModelMapper modelMapper) {
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
    public <T, V> List<V> select(Query<T, V> query) {
        try {
            if (query != null && query.entityType.getAnnotation(Entity.class) != null) {
                CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

                CriteriaQuery<V> criteriaQuery = criteriaBuilder.createQuery(query.returnType);

                query.root = criteriaQuery.from(query.entityType);
                query.builder = criteriaBuilder;

                Selection<? extends V> selection = query.select();
                Predicate predicate = query.where();

                if (predicate != null) {
                    criteriaQuery.where(predicate);
                }

                criteriaQuery.select(selection);

                return entityManager.createQuery(criteriaQuery).getResultList();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ArrayList<>();
    }


    public abstract static class Query<T, V> {
        private final Class<T> entityType;
        private final Class<V> returnType;

        private Root<T> root;
        private CriteriaBuilder builder;

        public Query(Class<T> entityType, Class<V> returnType) {
            this.entityType = entityType;
            this.returnType = returnType;
        }

        protected abstract Selection<? extends V> select();

        protected abstract Predicate where();

        protected Root<T> root() {
            return root;
        }

        protected CriteriaBuilder builder() {
            return builder;
        }
    }

    @Override
    public <T> void persist(T object) {
        try {
            if (object.getClass().getAnnotation(Entity.class) != null) {
                entityManager.getTransaction().begin();
                entityManager.persist(object);
                entityManager.getTransaction().commit();
            }
        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
            exception.printStackTrace();
        }
    }
}
