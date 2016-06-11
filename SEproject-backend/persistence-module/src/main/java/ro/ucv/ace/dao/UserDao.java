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

    /**
     * Returns the user whose username is the same as the method parameter.
     *
     * @param username username of the user
     * @return User
     * @throws DaoEntityNotFoundException if the user is not found
     */
    User findByUsername(String username) throws DaoEntityNotFoundException;

    /**
     * Returns all User entities which type is the same as the method parameter.
     *
     * @param type type of the user
     * @return list of User
     */
    List<User> findByType(String type);

    /**
     * Uploads the profile image of the user whose id is id.
     *
     * @param id    id of the user
     * @param image image encoded in Base64
     * @throws DaoEntityNotFoundException if the user is not found
     */
    void uploadImage(int id, String image) throws DaoEntityNotFoundException;
}
