package ro.ucv.ace.dao.impl;

import org.springframework.stereotype.Repository;
import ro.ucv.ace.dao.UserDao;
import ro.ucv.ace.model.User;

import java.util.Optional;

/**
 * This class implements UserDao interface.
 *
 * @author Georgian Vladutu
 */
@Repository
public class UserDaoImpl extends JpaDaoImpl<User, String> implements UserDao {

    @Override
    public Optional<User> existenceCondition(User user) {
        return streamAll()
                .where(u -> u.getUsername().equals(user.getUsername()))
                .findAny();
    }
}
