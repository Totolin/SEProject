package ro.ucv.ace.dao.impl;

import org.springframework.stereotype.Repository;
import ro.ucv.ace.dao.UserDao;
import ro.ucv.ace.exception.DaoEntityNotFoundException;
import ro.ucv.ace.model.User;

import javax.persistence.Query;
import java.util.List;
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

    @Override
    public List<User> findByType(String type) {
        return streamAll()
                .where(u -> u.getType().equals(type))
                .toList();
    }

    @Override
    public void uploadImage(int id, String image) throws DaoEntityNotFoundException {
        Query query = getEntityManager().createQuery("Update User u SET u.image = :image WHERE u.id = :id")
                .setParameter("id", id)
                .setParameter("image", image);

        int updated = query.executeUpdate();

        if (updated == 0) {
            throw new DaoEntityNotFoundException();
        }
    }
}
