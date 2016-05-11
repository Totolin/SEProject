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
public class UpdateScheduleDto {

    private int id;

    private int professorId;

    private int subjectId;

    private int day;

    private int hour;

    private String room;

}
