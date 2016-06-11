package ro.ucv.ace.mapper;

import org.modelmapper.PropertyMap;
import ro.ucv.ace.dto.person.SavePersonDto;
import ro.ucv.ace.model.Person;

/**
 * This class is used by ModelMapper to map from SavePersonDto class to Person class.
 *
 * @author Georgian Vladutu
 */
public class SavePersonMap extends PropertyMap<SavePersonDto, Person> {

    @Override
    protected void configure() {
        map().setId(0);
        map().setAddress(source.getAddress());
        map().setEmail(source.getEmail());
        map().setFirstName(source.getFirstName());
        map().setLastName(source.getLastName());
        map().setPhoneNumber(source.getPhoneNumber());
        map().setSsn(source.getSsn());
    }
}
