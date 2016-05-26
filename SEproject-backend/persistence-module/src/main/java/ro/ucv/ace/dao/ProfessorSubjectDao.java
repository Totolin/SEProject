package ro.ucv.ace.dao;

import ro.ucv.ace.model.ProfessorSubject;

import java.util.List;

/**
 * This interfaces provides methods for working with ProfessorSubject entity explicitly (and PROFESSOR_SUBJECT database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface ProfessorSubjectDao extends JpaDao<ProfessorSubject, Integer> {

    List<ProfessorSubject> findByProfessorId(Integer id);

    List<ProfessorSubject> findByProfessorIdAndSubjectId(Integer professorId, Integer subjectId);

}
