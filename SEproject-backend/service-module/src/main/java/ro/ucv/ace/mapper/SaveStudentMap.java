package ro.ucv.ace.mapper;

import org.modelmapper.PropertyMap;
import ro.ucv.ace.dto.student.SaveStudentDto;
import ro.ucv.ace.model.Student;

/**
 * This class is used by ModelMapper to map from SaveStudentDto class to Student class.
 *
 * @author Georgian Vladutu
 */
public class SaveStudentMap extends PropertyMap<SaveStudentDto, Student> {

    @Override
    protected void configure() {
        map().setId(0);
        map().setAddress(source.getAddress());
        map().setEmail(source.getEmail());
        map().setFirstName(source.getFirstName());
        map().setLastName(source.getLastName());
        map().setPhoneNumber(source.getPhoneNumber());
        map().setSsn(source.getSsn());
        map().setSubgroup(source.getSubgroup());
        map().getGroup().setId(source.getGroupId());
    }
}
