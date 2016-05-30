package ro.ucv.ace.dao;

import ro.ucv.ace.model.EducationPlan;

import java.util.List;

/**
 * This interfaces provides methods for working with EducationPlan entity explicitly (and EDUCATION_PLAN database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface EducationPlanDao extends JpaDao<EducationPlan, Integer> {

    List<EducationPlan> findByGroup(Integer groupId);

    List<EducationPlan> findByProfessor(Integer professorId);

    List<EducationPlan> findByProfessorAndSubject(Integer professorId, Integer subjectId);
}
