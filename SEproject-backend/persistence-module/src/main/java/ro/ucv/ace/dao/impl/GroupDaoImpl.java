package ro.ucv.ace.dao.impl;

import org.springframework.stereotype.Repository;
import ro.ucv.ace.dao.GroupDao;
import ro.ucv.ace.model.Group;

import java.util.Optional;

/**
 * This class implements GroupDao interface.
 *
 * @author Georgian Vladutu
 */
@Repository
public class GroupDaoImpl extends JpaDaoImpl<Group, Integer> implements GroupDao {

    @Override
    public Optional<Group> existenceCondition(Group group) {
        String name = group.getName();

        return streamAll()
                .where(s -> s.getName().equals(name))
                .findAny();
    }
}
