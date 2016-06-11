package ro.ucv.ace.dto.subject;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This class represents a data transfer object.
 *
 * @author Cristian Totolin
 */
@Getter
@Setter
public class UpdateSubjectDto {

    @Min(1)
    private int id;

    @NotNull
    @Size(min = 3, max = 100)
    private String name;

    @NotNull
    @Min(0)
    private int credits;
}
