package ro.ucv.ace.configuration;

import org.jinq.jpa.JPAJinqStream;
import org.jinq.jpa.JinqJPAStreamProvider;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * This bean is a Jinq entity used to query into the database using Java 8 streams.
 *
 * @author Georgian Vladutu
 */
@Component
public class JinqSource {

    private JinqJPAStreamProvider streams;

    @PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.streams = new JinqJPAStreamProvider(emf);
    }

    /**
     * Returns the Jinq stream of the entity.
     *
     * @param em     entityManager
     * @param entity the class of the entity
     * @param <U>    generic type
     * @return JPAJinqStream
     */
    public <U> JPAJinqStream<U> streamAll(EntityManager em, Class<U> entity) {
        return streams.streamAll(em, entity);
    }
}
