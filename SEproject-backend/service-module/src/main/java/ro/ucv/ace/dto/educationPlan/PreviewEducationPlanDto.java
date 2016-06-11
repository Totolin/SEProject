package ro.ucv.ace.dto.educationPlan;

import lombok.Getter;
import lombok.Setter;
import ro.ucv.ace.dto.group.PreviewGroupDto;
import ro.ucv.ace.dto.professor.PreviewProfessorDto;
import ro.ucv.ace.dto.subject.PreviewSubjectDto;

/**
 * This class represents a data transfer object.
 *
 * @author Cristian Totolin
 */
@Getter
@Setter
public class PreviewEducationPlanDto {

    private int id;

    private PreviewGroupDto group;

    private PreviewSubjectDto subject;

    private PreviewProfessorDto professor;

    private String evaluationMethod;
}
