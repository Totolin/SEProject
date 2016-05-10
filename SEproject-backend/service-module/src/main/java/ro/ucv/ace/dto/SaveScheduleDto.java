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
public class SaveScheduleDto {

    private String professorSsn;

    private int subjectId;

    private int day;

    private int hour;

    private String room;

}
