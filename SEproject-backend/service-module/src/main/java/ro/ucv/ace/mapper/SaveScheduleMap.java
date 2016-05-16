package ro.ucv.ace.mapper;

import org.modelmapper.PropertyMap;
import ro.ucv.ace.dto.schedule.SaveScheduleDto;
import ro.ucv.ace.model.Schedule;

/**
 * Created by Geo on 06.05.2016.
 */
public class SaveScheduleMap extends PropertyMap<SaveScheduleDto, Schedule> {

    @Override
    protected void configure() {
        map().setId(0);
        map().getSubject().setId(source.getSubjectId());
        map().getProfessor().setId(source.getProfessorId());
        map().setDay(source.getDay());
        map().setHour(source.getHour());
        map().setRoom(source.getRoom());
    }
}
