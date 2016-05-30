package ro.ucv.ace.dao.impl;

import org.springframework.stereotype.Repository;
import ro.ucv.ace.dao.EducationPlanDao;
import ro.ucv.ace.model.EducationPlan;

import java.util.List;
import java.util.Optional;

/**
 * This class implements EducationPlanDao interface.
 *
 * @author Georgian Vladutu
 */
@Repository
public class EducationPlanDaoImpl extends JpaDaoImpl<EducationPlan, Integer> implements EducationPlanDao {

    @Override
    public Optional<EducationPlan> existenceCondition(EducationPlan educationPlan) {
        Integer groupId = educationPlan.getGroup().getId();
        Integer subjectId = educationPlan.getSubject().getId();
        Integer professorId = educationPlan.getProfessor().getId();

        return streamAll()
                .where(e -> e.getGroup().getId().equals(groupId) &&
                        e.getSubject().getId().equals(subjectId) &&
                        e.getProfessor().getId().equals(professorId))
                .findAny();
    }

    @Override
    public List<EducationPlan> findByGroup(Integer groupId) {
        return streamAll()
                .where(e -> e.getGroup().getId().equals(groupId))
                .toList();
    }

    @Override
    public List<EducationPlan> findByProfessor(Integer professorId) {
        return streamAll()
                .where(e -> e.getProfessor().getId().equals(professorId))
                .toList();
    }

    @Override
    public List<EducationPlan> findByProfessorAndSubject(Integer professorId, Integer subjectId) {
        return streamAll()
                .where(e -> e.getProfessor().getId().equals(professorId) && e.getSubject().getId().equals(subjectId))
                .toList();
    }
}
