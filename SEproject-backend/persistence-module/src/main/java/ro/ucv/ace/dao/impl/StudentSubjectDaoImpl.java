package ro.ucv.ace.dao.impl;

import org.springframework.stereotype.Repository;
import ro.ucv.ace.dao.StudentSubjectDao;
import ro.ucv.ace.exception.DaoEntityNotFoundException;
import ro.ucv.ace.model.StudentSubject;

import javax.persistence.Query;
import java.util.Optional;

/**
 * This class implements StudentSubjectDao interface.
 *
 * @author Georgian Vladutu
 */
@Repository
public class StudentSubjectDaoImpl extends JpaDaoImpl<StudentSubject, Integer> implements StudentSubjectDao {

    @Override
    public Optional<StudentSubject> existenceCondition(StudentSubject studentSubject) {
        Integer studentId = studentSubject.getStudent().getId();
        Integer subjectId = studentSubject.getSubject().getId();

        return streamAll()
                .where(ss -> ss.getStudent().getId().equals(studentId) && ss.getSubject().getId().equals(subjectId))
                .findAny();
    }

    @Override
    public StudentSubject findByStudentAndSubject(Integer studentId, Integer subjectId) throws DaoEntityNotFoundException {
        Optional<StudentSubject> studentSubjectOptional = streamAll()
                .where(ss -> ss.getSubject().getId().equals(subjectId) && ss.getStudent().getId().equals(studentId))
                .findAny();

        if (studentSubjectOptional.isPresent()) {
            return studentSubjectOptional.get();
        }

        throw new DaoEntityNotFoundException();
    }

    @Override
    public void updateGrade(int studentId, int subjectId, int grade) throws DaoEntityNotFoundException {
        Query query = getEntityManager()
                .createQuery("UPDATE StudentSubject ss SET ss.grade = :grade WHERE ss.student.id = :studentId AND ss.subject.id = :subjectId")
                .setParameter("grade", grade)
                .setParameter("studentId", studentId)
                .setParameter("subjectId", subjectId);

        int updatedRows = query.executeUpdate();

        if (updatedRows == 0) {
            throw new DaoEntityNotFoundException();
        }
    }
}
