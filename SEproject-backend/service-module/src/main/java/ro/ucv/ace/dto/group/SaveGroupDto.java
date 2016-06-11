package ro.ucv.ace.dto.group;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This class represents a data transfer object.
 *
 * @author Georgian Vladutu
 */
@Getter
@Setter
public class SaveGroupDto {

    @NotNull
    @Size(min = 3, max = 100)
    private String name;

    @Min(1)
    @Max(4)
    @NotNull
    private int year;

    @NotNull
    @Size(min = 3, max = 100)
    private String specialization;
}
