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
        String sectionId = educationPlan.getSection().getId();
        String subjectName = educationPlan.getSubject().getName();

        return streamAll()
                .where(e -> e.getSection().getId().equals(sectionId) && e.getSubject().getName().equals(subjectName))
                .findAny();
    }
}
