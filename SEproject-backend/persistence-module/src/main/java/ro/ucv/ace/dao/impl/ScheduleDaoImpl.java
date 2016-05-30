package ro.ucv.ace.dao.impl;

import org.springframework.stereotype.Repository;
import ro.ucv.ace.dao.ScheduleDao;
import ro.ucv.ace.model.Schedule;

import java.util.List;
import java.util.Optional;

/**
 * This class implements ScheduleDao interface.
 *
 * @author Georgian Vladutu
 */
@Repository
public class ScheduleDaoImpl extends JpaDaoImpl<Schedule, Integer> implements ScheduleDao {

    @Override
    public Optional<Schedule> existenceCondition(Schedule schedule) {
        Integer day = schedule.getDay();
        Integer hour = schedule.getHour();
        String room = schedule.getRoom();

        return streamAll()
                .where(s -> s.getRoom().equals(room) && s.getDay().equals(day) && s.getHour().equals(hour))
                .findAny();
    }

    @Override
    public List<Schedule> findByGroup(Integer groupId) {
        return streamAll()
                .where(s -> s.getGroup().getId().equals(groupId))
                .toList();
    }
}
