package ro.ucv.ace.dao;

import ro.ucv.ace.exception.DaoEntityAlreadyExistsException;
import ro.ucv.ace.exception.DaoEntityNotFoundException;
import ro.ucv.ace.model.User;

import java.util.List;

/**
 * Created by ctotolin on 24-Apr-16.
 */
public interface UserDao {

    List<User> getAll();

    User getBySsn(String ssn) throws DaoEntityNotFoundException;

    User getByUsername(String username) throws DaoEntityNotFoundException;

    void persist(User user) throws DaoEntityAlreadyExistsException;

    void delete(String username) throws DaoEntityNotFoundException;

    void update(User user) throws DaoEntityNotFoundException;


}
