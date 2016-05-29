package ro.ucv.ace.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ucv.ace.dao.GroupDao;
import ro.ucv.ace.dto.group.PreviewGroupDto;
import ro.ucv.ace.dto.group.SaveGroupDto;
import ro.ucv.ace.dto.group.UpdateGroupDto;
import ro.ucv.ace.exception.*;
import ro.ucv.ace.model.Group;
import ro.ucv.ace.service.GroupService;

import java.util.List;

/**
 * Created by Geo on 28.05.2016.
 */
@Service
@Transactional(rollbackFor = ServiceForeignKeyNotFoundException.class)
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PreviewGroupDto> getAllGroups() {
        List<Group> groups = groupDao.findAll();

        return modelMapper.map(groups, new TypeToken<List<PreviewGroupDto>>() {
        }.getType());
    }

    @Override
    public PreviewGroupDto getById(int id) throws ServiceEntityNotFoundException {
        try {
            Group group = groupDao.findOne(id);

            return modelMapper.map(group, PreviewGroupDto.class);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceEntityNotFoundException {
        try {
            groupDao.delete(id);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public void save(SaveGroupDto saveGroupDto) throws ServiceEntityAlreadyExistsException, ServiceForeignKeyNotFoundException {
        Group group = modelMapper.map(saveGroupDto, Group.class);

        try {
            groupDao.save(group);
        } catch (DaoEntityAlreadyExistsException e) {
            throw new ServiceEntityAlreadyExistsException(e);
        } catch (DaoForeignKeyNotFoundException e) {
            throw new ServiceForeignKeyNotFoundException(e);
        }
    }

    @Override
    public void update(Integer id, UpdateGroupDto updateGroupDto) throws ServiceEntityNotFoundException, ServiceForeignKeyNotFoundException {
        Group group = modelMapper.map(updateGroupDto, Group.class);

        try {
            groupDao.update(id, group);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        } catch (DaoForeignKeyNotFoundException e) {
            throw new ServiceForeignKeyNotFoundException(e);
        }
    }
}
