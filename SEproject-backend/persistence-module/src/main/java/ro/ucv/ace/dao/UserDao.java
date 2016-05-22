package ro.ucv.ace.dao;

import ro.ucv.ace.exception.DaoEntityNotFoundException;
import ro.ucv.ace.model.User;

/**
 * This interfaces provides methods for working with User entity explicitly (and USER database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface UserDao extends JpaDao<User, Integer> {

    User findByUsername(String username) throws DaoEntityNotFoundException;

    void deleteByPersonId(Integer personId);
}
