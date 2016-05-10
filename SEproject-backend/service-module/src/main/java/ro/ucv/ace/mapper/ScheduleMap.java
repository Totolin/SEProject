package ro.ucv.ace.mapper;

import org.modelmapper.PropertyMap;
import ro.ucv.ace.dto.SaveScheduleDto;
import ro.ucv.ace.model.Schedule;

/**
 * Created by Geo on 06.05.2016.
 */
public class ScheduleMap extends PropertyMap<SaveScheduleDto, Schedule> {

    @Override
    protected void configure() {
        map(source.getSubjectId(), destination.getSubject().getId());
        map(source.getDay(), destination.getDay());
        map(source.getHour(), destination.getHour());
        map(source.getRoom(), destination.getRoom());
        map(source.getProfessorSsn(), destination.getProfessor().getSsn());
    }
}
