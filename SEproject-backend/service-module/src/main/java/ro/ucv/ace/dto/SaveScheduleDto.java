package ro.ucv.ace.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * This is a data transfer object.
 *
 * @author Georgian Vladutu
 */
@Getter
@Setter
public class SaveScheduleDto {

    private int professorId;

    private int subjectId;

    @Min(1)
    @Max(5)
    private int day;

    @Min(8)
    @Max(18)
    private int hour;

    @Size(min = 2, max = 20)
    private String room;

}
