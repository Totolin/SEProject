package ro.ucv.ace.dao.impl;

import org.jinq.jpa.JPAJinqStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ro.ucv.ace.configuration.JinqSource;
import ro.ucv.ace.exception.DaoEntityAlreadyExistsException;
import ro.ucv.ace.exception.DaoEntityNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * This class is an abstract generic data access object class that implements the CRUD operations for all entities that
 * extend this class. It uses Java Persistence Api to persist objects.
 *
 * @param <T>  the type of the entity
 * @param <ID> the type of the entity primary key
 * @author Georgian Vladutu
 */
public abstract class JpaDao<T, ID> extends Dao<T, ID> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JpaDao.class);

    private final Class<T> persistentClass;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JinqSource jinqSource;

    public JpaDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    protected JinqSource getJinqSource() {
        return this.jinqSource;
    }

    protected JPAJinqStream<T> streamAll() {
        return jinqSource.streamAll(entityManager, (Class<T>) persistentClass);
    }

    protected Class<T> getPersistentClass() {
        return persistentClass;
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(getPersistentClass());
        Root<T> rootEntry = cq.from(getPersistentClass());
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> allQuery = getEntityManager().createQuery(all);

        return allQuery.getResultList();
    }

    @Override
    public T findOne(ID id) throws DaoEntityNotFoundException {
        T t = getEntityManager().find(getPersistentClass(), id);

        if (t == null) {
            LOGGER.debug("Null entity");
            throw new DaoEntityNotFoundException();
        }

        return t;
    }

    @Override
    public void save(T t) throws DaoEntityAlreadyExistsException {
        try {
            T newT = exists(t);
        } catch (DaoEntityNotFoundException e) {
            getEntityManager().persist(t);

            return;
        }

        throw new DaoEntityAlreadyExistsException();
    }

    @Override
    public void delete(ID id) throws DaoEntityNotFoundException {
        T t = findOne(id);

        getEntityManager().remove(t);
    }

    @Override
    public void update(ID id, T t) throws DaoEntityNotFoundException {
        T newT = findOne(id);

        getEntityManager().merge(t);
    }

}
