package ro.ucv.ace.mapper;

import org.modelmapper.PropertyMap;
import ro.ucv.ace.dto.SaveStudentDto;
import ro.ucv.ace.model.Student;

/**
 * Created by Geo on 15.05.2016.
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
