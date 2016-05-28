package ro.ucv.ace.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ucv.ace.dao.GroupDao;
import ro.ucv.ace.dto.group.PreviewGroupDto;
import ro.ucv.ace.exception.ServiceForeignKeyNotFoundException;
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
}
