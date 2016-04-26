package ro.ucv.ace.dao;

import ro.ucv.ace.exception.DaoEntityAlreadyExistsException;
import ro.ucv.ace.exception.DaoEntityNotFoundException;
import ro.ucv.ace.model.User;

import java.util.List;

/**
 * Created by ctotolin on 24-Apr-16.
 */
public interface UserDao {

    List<User> findAll();

    User findOne(Integer id) throws DaoEntityNotFoundException;

    User findByUsername(String username) throws DaoEntityNotFoundException;

    void save(User user) throws DaoEntityAlreadyExistsException;

    void delete(Integer Integer) throws DaoEntityNotFoundException;

    void update(Integer Integer, User user) throws DaoEntityNotFoundException;


}
