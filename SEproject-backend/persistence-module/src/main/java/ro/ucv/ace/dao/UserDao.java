package ro.ucv.ace.dao;

import ro.ucv.ace.exception.DaoEntityNotFoundException;
import ro.ucv.ace.model.User;

import java.util.List;

/**
 * This interfaces provides methods for working with User entity explicitly (and USER database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface UserDao extends JpaDao<User, Integer> {

    User findByUsername(String username) throws DaoEntityNotFoundException;

    List<User> findByType(String type);

    void uploadImage(int id, String image) throws DaoEntityNotFoundException;
}
