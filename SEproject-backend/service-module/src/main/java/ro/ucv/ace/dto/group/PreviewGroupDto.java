package ro.ucv.ace.dto.group;

import lombok.Getter;
import lombok.Setter;

/**
 * This class represents a data transfer object.
 *
 * @author Georgian Vladutu
 */
@Getter
@Setter
public class PreviewGroupDto {

    private int id;

    private String name;

    private int year;

    private String specialization;
}
