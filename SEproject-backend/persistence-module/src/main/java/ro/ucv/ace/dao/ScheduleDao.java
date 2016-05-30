package ro.ucv.ace.dao;

import ro.ucv.ace.model.Schedule;

import java.util.List;

/**
 * This interfaces provides methods for working with Schedule entity explicitly (and SCHEDULE database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface ScheduleDao extends JpaDao<Schedule, Integer> {

    List<Schedule> findByGroup(Integer groupId);

}
