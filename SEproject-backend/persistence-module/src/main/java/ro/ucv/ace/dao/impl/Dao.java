package ro.ucv.ace.dao.impl;

import ro.ucv.ace.exception.DaoEntityAlreadyExistsException;
import ro.ucv.ace.exception.DaoEntityNotFoundException;

import java.util.List;

/**
 * This class is an abstract generic data access object class that implements the CRUD operations for all entities that
 * extend this class.
 *
 * @param <T>  the type of the entity
 * @param <ID> the type of the entity primary key
 * @author Georgian Vladutu
 */
public abstract class Dao<T, ID> {

    public Dao() {
    }

    public abstract List<T> findAll();

    public abstract T findOne(ID id) throws DaoEntityNotFoundException;

    public abstract void save(T t) throws DaoEntityAlreadyExistsException;

    public abstract void delete(ID id) throws DaoEntityNotFoundException;

    public abstract void update(ID id, T t) throws DaoEntityNotFoundException;

    public abstract T exists(T t) throws DaoEntityNotFoundException;

}
