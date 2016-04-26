package ro.ucv.ace.dao.impl;

import org.springframework.stereotype.Repository;
import ro.ucv.ace.dao.UserDao;
import ro.ucv.ace.exception.DaoEntityNotFoundException;
import ro.ucv.ace.model.User;

import java.util.Optional;

/**
 * Created by ctotolin on 24-Apr-16.
 */
@Repository
public class UserDaoImpl extends JpaDao<User, Integer> implements UserDao {

    @Override
    public User exists(User user) throws DaoEntityNotFoundException {
        Optional<User> userOptional = streamAll()
                .where(u -> u.getUsername().equals(user.getUsername()))
                .findAny();

        if (userOptional.isPresent()) {
            return userOptional.get();
        }

        throw new DaoEntityNotFoundException();
    }

    @Override
    public User findByUsername(String username) throws DaoEntityNotFoundException {
        Optional<User> userOptional = streamAll()
                .where(u -> u.getUsername().equals(username))
                .findAny();

        if (userOptional.isPresent()) {
            return userOptional.get();
        }

        throw new DaoEntityNotFoundException();
    }
}
