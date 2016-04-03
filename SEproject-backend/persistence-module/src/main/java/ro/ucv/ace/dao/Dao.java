package ro.ucv.ace.dao;

import ro.ucv.ace.exception.DaoEntityNotFoundException;

import java.util.List;

/**
 * This class is an abstract generic data access object class that implements the CRUD operations for all entities that
 * extend this class.
 *
 * @param <T> the type of the entity
 * @author Georgian Vladutu
 */
public abstract class Dao<T> {

    public Dao() {
    }

    protected abstract List<T> getAllEntities();

    protected abstract T getEntityById(int id) throws DaoEntityNotFoundException;

    protected abstract void persistEntity(T t);

    protected abstract void deleteEntity(T t);

    protected abstract void updateEntity(T t);

}
