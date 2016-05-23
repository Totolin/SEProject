package ro.ucv.ace.dao.impl;

import org.springframework.stereotype.Repository;
import ro.ucv.ace.dao.ProfessorDao;
import ro.ucv.ace.exception.DaoEntityNotFoundException;
import ro.ucv.ace.model.Professor;

import java.util.Optional;

/**
 * This class implements ProfessorDao interface.
 *
 * @author Georgian Vladutu
 */
@Repository
public class ProfessorDaoImpl extends JpaDaoImpl<Professor, Integer> implements ProfessorDao {

    @Override
    public Optional<Professor> existenceCondition(Professor professor) {
        String ssn = professor.getSsn();

        return streamAll()
                .where(p -> p.getSsn().equals(ssn))
                .findAny();
    }

    @Override
    public Professor findBySsn(String ssn) throws DaoEntityNotFoundException {
        Optional<Professor> professorOptional = streamAll()
                .where(p -> p.getSsn().equals(ssn))
                .findAny();

        if (professorOptional.isPresent()) {
            return professorOptional.get();
        }

        throw new DaoEntityNotFoundException();
    }
}
