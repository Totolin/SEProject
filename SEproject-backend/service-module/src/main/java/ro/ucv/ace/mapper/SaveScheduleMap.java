package ro.ucv.ace.mapper;

import org.modelmapper.PropertyMap;
import ro.ucv.ace.dto.schedule.SaveScheduleDto;
import ro.ucv.ace.model.Schedule;

/**
 * This class is used by ModelMapper to map from SaveScheduleDto class to Schedule class.
 *
 * @author Georgian Vladutu
 */
public class SaveScheduleMap extends PropertyMap<SaveScheduleDto, Schedule> {

    @Override
    protected void configure() {
        map().setId(0);
        map().getSubject().setId(source.getSubjectId());
        map().getProfessor().setId(source.getProfessorId());
        map().getGroup().setId(source.getGroupId());
        map().setDay(source.getDay());
        map().setHour(source.getHour());
        map().setRoom(source.getRoom());
    }
}
