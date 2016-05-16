package ro.ucv.ace.mapper;

import org.modelmapper.PropertyMap;
import ro.ucv.ace.dto.professor.SaveProfessorDto;
import ro.ucv.ace.model.Professor;

/**
 * Created by Geo on 16.05.2016.
 */
public class SaveProfessorMap extends PropertyMap<SaveProfessorDto, Professor> {

    @Override
    protected void configure() {
        map().setId(0);
        map().setAddress(source.getAddress());
        map().setEmail(source.getEmail());
        map().setFirstName(source.getFirstName());
        map().setLastName(source.getLastName());
        map().setPhoneNumber(source.getPhoneNumber());
        map().setSsn(source.getSsn());
        map().setOffice(source.getOffice());
        map().setPosition(source.getPosition());
        map().getDepartment().setId(source.getDepartmentId());
    }
}
