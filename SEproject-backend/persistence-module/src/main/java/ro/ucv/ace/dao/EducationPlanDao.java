package ro.ucv.ace.dao;

import ro.ucv.ace.exception.DaoEntityNotFoundException;
import ro.ucv.ace.model.EducationPlan;

import java.util.List;

/**
 * This interfaces provides methods for working with EducationPlan entity explicitly (and EDUCATION_PLAN database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface EducationPlanDao extends JpaDao<EducationPlan, Integer> {

    /**
     * Returns all EducationPlan entities which contain the group whose id is groupId.
     *
     * @param groupId id of the group
     * @return list of EducationPlan
     */
    List<EducationPlan> findByGroup(Integer groupId);

    /**
     * Returns all EducationPlan entities which contain the professor whose id is professorId.
     *
     * @param professorId id of the professor
     * @return list of EducationPlan
     */
    List<EducationPlan> findByProfessor(Integer professorId);

    /**
     * Returns all EducationPlan entities which contain the subject whose id is subjectId and the professor whose id is professorId.
     *
     * @param professorId id of the professor
     * @param subjectId   id of the subject
     * @return list of EducationPlan
     */
    List<EducationPlan> findByProfessorAndSubject(Integer professorId, Integer subjectId);

    /**
     * Returns the EducationPlan which contain the group whose id is groupId, the professor whose id is professorId and the subject
     * whose id is subjectId.
     *
     * @param groupId     id of the group
     * @param professorId id of the professor
     * @param subjectId   id of the subject
     * @return EducationPlan
     * @throws DaoEntityNotFoundException if the EducationPlan is not found
     */
    EducationPlan findByGroupAndProfessorAndSubject(Integer groupId, Integer professorId, Integer subjectId) throws DaoEntityNotFoundException;
}
