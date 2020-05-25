package core;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerFactory {
    @Produces
    public EntityManager getEntityManager(){
        return Persistence.createEntityManagerFactory("moussakapp_site_db").createEntityManager();
    }
}
