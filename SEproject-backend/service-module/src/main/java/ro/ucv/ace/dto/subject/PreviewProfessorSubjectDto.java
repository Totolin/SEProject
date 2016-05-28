package ro.ucv.ace.dto.subject;

import lombok.Getter;
import lombok.Setter;
import ro.ucv.ace.dto.group.PreviewGroupDto;
import ro.ucv.ace.dto.professor.PreviewProfessorDto;

/**
 * Created by Geo on 28.05.2016.
 */
@Getter
@Setter
public class PreviewProfessorSubjectDto {

    private int id;

    private String evaluationMethod;

    private PreviewGroupDto group;

    private PreviewSubjectDto subject;

    private PreviewProfessorDto professor;
}
