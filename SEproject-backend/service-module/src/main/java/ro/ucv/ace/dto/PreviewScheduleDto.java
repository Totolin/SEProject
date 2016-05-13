package ro.ucv.ace.dto;

import lombok.Getter;
import lombok.Setter;

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

    private int day;

    private int hour;

    private String room;
}
