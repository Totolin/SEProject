package ro.ucv.ace.dao.impl;

import org.springframework.stereotype.Repository;
import ro.ucv.ace.dao.EducationPlanDao;
import ro.ucv.ace.model.EducationPlan;

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
        Integer sectionId = educationPlan.getGroup().getId();
        String subjectName = educationPlan.getSubject().getName();

        return streamAll()
                .where(e -> e.getGroup().getId().equals(sectionId) && e.getSubject().getName().equals(subjectName))
                .findAny();
    }
}
