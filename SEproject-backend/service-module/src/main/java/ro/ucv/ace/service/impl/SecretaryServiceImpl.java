package ro.ucv.ace.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ucv.ace.dao.*;
import ro.ucv.ace.dto.department.DepartmentDto;
import ro.ucv.ace.dto.group.PreviewGroupDto;
import ro.ucv.ace.dto.secretary.PreviewSecretaryDto;
import ro.ucv.ace.dto.secretary.SaveSecretaryDto;
import ro.ucv.ace.dto.secretary.UpdateSecretaryDto;
import ro.ucv.ace.dto.subject.PreviewSubjectDto;
import ro.ucv.ace.dto.user.PreviewAccountDto;
import ro.ucv.ace.enums.UserType;
import ro.ucv.ace.exception.*;
import ro.ucv.ace.model.*;
import ro.ucv.ace.service.SecretaryService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geo on 23.05.2016.
 */
@Service
@Transactional(rollbackFor = ServiceForeignKeyNotFoundException.class)
public class SecretaryServiceImpl implements SecretaryService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PersonDao personDao;

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private SubjectDao subjectDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PreviewSecretaryDto> getAllSecretaries() {
        List<User> users = userDao.findByType(UserType.SECRETARY.getType());
        List<PreviewSecretaryDto> previewSecretaryDtos = new ArrayList<>();

        for (User user : users) {
            try {
                Person person = personDao.findOne(user.getId());

                PreviewSecretaryDto previewSecretaryDto = modelMapper.map(person, PreviewSecretaryDto.class);
                previewSecretaryDto.setAccount(modelMapper.map(user, PreviewAccountDto.class));

                previewSecretaryDtos.add(previewSecretaryDto);
            } catch (DaoEntityNotFoundException e) {
                e.printStackTrace();
                // ignore; secretaries must be registered as a Person
            }
        }

        return previewSecretaryDtos;
    }

    @Override
    public PreviewSecretaryDto getById(Integer id) throws ServiceEntityNotFoundException {
        try {
            User user = userDao.findOne(id);
            Person person = personDao.findOne(user.getId());

            PreviewSecretaryDto previewSecretaryDto = modelMapper.map(person, PreviewSecretaryDto.class);
            previewSecretaryDto.setAccount(modelMapper.map(user, PreviewAccountDto.class));

            return previewSecretaryDto;
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public void save(SaveSecretaryDto saveSecretaryDto) throws ServiceEntityAlreadyExistsException, ServiceForeignKeyNotFoundException {
        Person person = modelMapper.map(saveSecretaryDto, Person.class);
        User user = modelMapper.map(saveSecretaryDto.getAccount(), User.class);

        try {
            Person saved = personDao.save(person);

            user.setId(saved.getId());
            userDao.save(user);
        } catch (DaoEntityAlreadyExistsException e) {
            throw new ServiceEntityAlreadyExistsException(e);
        } catch (DaoForeignKeyNotFoundException e) {
            throw new ServiceForeignKeyNotFoundException(e);
        }
    }


    @Override
    public void delete(Integer id) throws ServiceEntityNotFoundException {
        try {
            userDao.delete(id);
            personDao.delete(id);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public void update(UpdateSecretaryDto updateSecretaryDto, Integer id) throws ServiceEntityNotFoundException, ServiceForeignKeyNotFoundException {
        Person person = modelMapper.map(updateSecretaryDto, Person.class);
        User user = modelMapper.map(updateSecretaryDto.getAccount(), User.class);
        user.setId(person.getId());

        try {
            personDao.update(id, person);
            userDao.update(id, user);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        } catch (DaoForeignKeyNotFoundException e) {
            throw new ServiceForeignKeyNotFoundException(e);
        }
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentDao.findAll();

        return modelMapper.map(departments, new TypeToken<List<DepartmentDto>>() {
        }.getType());
    }

    @Override
    public List<PreviewGroupDto> getAllGroups() {
        List<Group> groups = groupDao.findAll();

        return modelMapper.map(groups, new TypeToken<List<PreviewGroupDto>>() {
        }.getType());
    }

    @Override
    public List<PreviewSubjectDto> getAllSubjects() {
        List<Subject> subjects = subjectDao.findAll();

        return modelMapper.map(subjects, new TypeToken<List<PreviewSubjectDto>>() {
        }.getType());
    }
}
