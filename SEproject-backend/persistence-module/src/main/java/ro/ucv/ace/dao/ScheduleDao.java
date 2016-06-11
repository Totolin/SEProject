package ro.ucv.ace.dao;

import ro.ucv.ace.model.Schedule;

import java.util.List;

/**
 * This interfaces provides methods for working with Schedule entity explicitly (and SCHEDULE database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface ScheduleDao extends JpaDao<Schedule, Integer> {

    /**
     * Returns all Schedule entities which contain the group whose id is groupId.
     *
     * @param groupId id of the group
     * @return list of Schedule
     */
    List<Schedule> findByGroup(Integer groupId);

}
