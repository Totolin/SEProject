package ro.ucv.ace.dao.impl;

import org.springframework.stereotype.Repository;
import ro.ucv.ace.dao.UserDao;
import ro.ucv.ace.exception.DaoEntityNotFoundException;
import ro.ucv.ace.model.User;

import java.util.Optional;

/**
 * This class implements UserDao interface.
 *
 * @author Georgian Vladutu
 */
@Repository
public class UserDaoImpl extends JpaDaoImpl<User, Integer> implements UserDao {

    @Override
    public Optional<User> existenceCondition(User user) {
        String username = user.getUsername();
        return streamAll()
                .where(u -> u.getUsername().equals(username))
                .findAny();
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
