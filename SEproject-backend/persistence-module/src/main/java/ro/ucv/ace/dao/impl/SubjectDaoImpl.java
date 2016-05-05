package ro.ucv.ace.dao.impl;

import org.springframework.stereotype.Repository;
import ro.ucv.ace.dao.SubjectDao;
import ro.ucv.ace.model.Subject;

import java.util.Optional;

/**
 * This class implements SubjectDao interface.
 *
 * @author Georgian Vladutu
 */
@Repository
public class SubjectDaoImpl extends JpaDaoImpl<Subject, Integer> implements SubjectDao {

    @Override
    public Optional<Subject> existenceCondition(Subject subject) {
        String name = subject.getName();
        return streamAll()
                .where(s -> s.getName().equals(name))
                .findAny();
    }
}
