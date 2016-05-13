package ro.ucv.ace.dao;

import ro.ucv.ace.exception.DaoEntityAlreadyExistsException;
import ro.ucv.ace.exception.DaoEntityNotFoundException;
import ro.ucv.ace.exception.DaoForeignKeyNotFoundException;

import java.util.List;

/**
 * This interface contains the basic operations avaiable on all DAO repositories.
 *
 * @param <T>  type of the entity
 * @param <ID> type of the entity's primary key
 * @author Georgian Vladutu
 */
public interface JpaDao<T, ID> {

    /**
     * Returns all the entities of this type from the database
     *
     * @return list of entities
     */
    List<T> findAll();

    /**
     * Returns the entity whose id is the same as the method parameter.
     *
     * @param id entity's primary key
     * @return T
     * @throws DaoEntityNotFoundException if the entity is not found
     */
    T findOne(ID id) throws DaoEntityNotFoundException;

    /**
     * Saves the entity into the database.
     *
     * @param t entity to be saved
     * @throws DaoEntityAlreadyExistsException if the entity already exists
     * @throws DaoForeignKeyNotFoundException  if the entity's foreign key does not exist
     */
    void save(T t) throws DaoEntityAlreadyExistsException, DaoForeignKeyNotFoundException;

    /**
     * Deletes the entity whose id is the same as the method parameter
     *
     * @param id entity's primary key
     * @throws DaoEntityNotFoundException if the entity is not found
     */
    void delete(ID id) throws DaoEntityNotFoundException;

    /**
     * Updates the entity whose id is id into the database.
     *
     * @param id id of the entity
     * @param t  entity to be updated
     * @throws DaoEntityNotFoundException     if the entity is not found
     * @throws DaoForeignKeyNotFoundException if the entity's foreign key does not exist
     */
    void update(ID id, T t) throws DaoEntityNotFoundException, DaoForeignKeyNotFoundException;
}
