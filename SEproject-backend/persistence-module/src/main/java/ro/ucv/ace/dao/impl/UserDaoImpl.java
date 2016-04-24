package ro.ucv.ace.dao.impl;

import org.springframework.stereotype.Repository;
import ro.ucv.ace.dao.JpaDao;
import ro.ucv.ace.dao.UserDao;
import ro.ucv.ace.exception.DaoEntityNotFoundException;
import ro.ucv.ace.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Created by ctotolin on 24-Apr-16.
 */
@Repository
public class UserDaoImpl extends JpaDao<User> implements UserDao{

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getBySsn(String ssn) throws DaoEntityNotFoundException {
        return null;
    }

    @Override
    public User getByUsername(String username) throws DaoEntityNotFoundException {
        Optional<User> user = getJinqSource().streamAll(getEntityManager(), User.class)
                .where(u -> u.getUsername().equals(username)).findAny();
        if (user.isPresent()) {
            return user.get();
        }

        throw new DaoEntityNotFoundException();
    }

    @Override
    public void persist(User person) {

    }

    @Override
    public void delete(String ssn) {

    }

    @Override
    public void update(User person) {

    }
}
