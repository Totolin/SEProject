package ro.ucv.ace.dao.impl;

import org.springframework.stereotype.Repository;
import ro.ucv.ace.dao.ProfessorSubjectDao;
import ro.ucv.ace.model.ProfessorSubject;

import java.util.List;
import java.util.Optional;

/**
 * This class implements ProfessorSubjectDao interface.
 *
 * @author Georgian Vladutu
 */
@Repository
public class ProfessorSubjectDaoImpl extends JpaDaoImpl<ProfessorSubject, Integer> implements ProfessorSubjectDao {

    @Override
    public Optional<ProfessorSubject> existenceCondition(ProfessorSubject professorSubject) {
        String professorSsn = professorSubject.getProfessor().getSsn();
        String subjectName = professorSubject.getSubject().getName();

        return streamAll()
                .where(ps -> ps.getProfessor().getSsn().equals(professorSsn) && ps.getSubject().getName().equals(subjectName))
                .findAny();
    }

    @Override
    public List<ProfessorSubject> findByProfessorId(Integer id) {
        return streamAll()
                .where(ps -> ps.getProfessor().getId().equals(id))
                .toList();
    }

    @Override
    public List<ProfessorSubject> findByProfessorIdAndSubjectId(Integer professorId, Integer subjectId) {
        return streamAll()
                .where(ps -> ps.getProfessor().getId().equals(professorId) && ps.getSubject().getId().equals(subjectId))
                .toList();
    }

    @Override
    public List<ProfessorSubject> findByGroup(Integer groupId) {
        return streamAll()
                .where(ps -> ps.getGroup().getId().equals(groupId))
                .toList();
    }
}
