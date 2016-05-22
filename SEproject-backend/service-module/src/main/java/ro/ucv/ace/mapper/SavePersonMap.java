package ro.ucv.ace.mapper;

import org.modelmapper.PropertyMap;
import ro.ucv.ace.dto.person.SavePersonDto;
import ro.ucv.ace.model.Person;

/**
 * Created by Geo on 22.05.2016.
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
