package ro.ucv.ace.dto.schedule;

import lombok.Getter;
import lombok.Setter;
import ro.ucv.ace.dto.group.PreviewGroupDto;
import ro.ucv.ace.dto.professor.PreviewProfessorDto;
import ro.ucv.ace.dto.subject.PreviewSubjectDto;

/**
 * This is a data transfer object.
 *
 * @author Georgian Vladutu
 */
@Getter
@Setter
public class PreviewScheduleDto {

    private int id;

    private PreviewProfessorDto professor;

    private PreviewSubjectDto subject;

    private PreviewGroupDto group;

    private int day;

    private int hour;

    private String room;
}
