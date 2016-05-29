package ro.ucv.ace.service;

import ro.ucv.ace.dto.group.PreviewGroupDto;
import ro.ucv.ace.dto.group.SaveGroupDto;
import ro.ucv.ace.dto.group.UpdateGroupDto;
import ro.ucv.ace.dto.subject.PreviewSubjectDto;
import ro.ucv.ace.dto.subject.SaveSubjectDto;
import ro.ucv.ace.dto.subject.UpdateSubjectDto;
import ro.ucv.ace.exception.ServiceEntityAlreadyExistsException;
import ro.ucv.ace.exception.ServiceEntityNotFoundException;
import ro.ucv.ace.exception.ServiceForeignKeyNotFoundException;

import java.util.List;

/**
 * Created by Geo on 28.05.2016.
 */
public interface GroupService {

    List<PreviewGroupDto> getAllGroups();

    PreviewGroupDto getById(int id) throws ServiceEntityNotFoundException;

    void delete(Integer id) throws ServiceEntityNotFoundException;

    void save(SaveGroupDto saveGroupDto) throws ServiceEntityAlreadyExistsException, ServiceForeignKeyNotFoundException;

    void update(Integer id,  UpdateGroupDto updateGroupDto) throws ServiceEntityNotFoundException, ServiceForeignKeyNotFoundException;
}
