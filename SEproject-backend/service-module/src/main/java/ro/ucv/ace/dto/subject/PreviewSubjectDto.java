package ro.ucv.ace.dto.subject;

import lombok.Getter;
import lombok.Setter;

/**
 * This is a data transfer object.
 *
 * @author Cristian Totolin
 */
@Getter
@Setter
public class PreviewSubjectDto {

    private int id;

    private String name;

    private Integer credits;
}
