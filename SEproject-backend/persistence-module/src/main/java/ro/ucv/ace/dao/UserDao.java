package ro.ucv.ace.dao;

import ro.ucv.ace.exception.DaoEntityNotFoundException;
import ro.ucv.ace.model.User;

/**
 * Created by ctotolin on 24-Apr-16.
 */
public interface UserDao extends JpaDao<User, Integer> {

    User findByUsername(String username) throws DaoEntityNotFoundException;

}
