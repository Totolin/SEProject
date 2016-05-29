package ro.ucv.ace.dto.group;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by ctotolin on 29-May-16.
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
