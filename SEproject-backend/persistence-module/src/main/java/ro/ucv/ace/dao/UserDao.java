package ro.ucv.ace.dao;

import ro.ucv.ace.model.User;

/**
 * This interfaces provides methods for working with User entity explicitly (and USER database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface UserDao extends JpaDao<User, String> {
}
