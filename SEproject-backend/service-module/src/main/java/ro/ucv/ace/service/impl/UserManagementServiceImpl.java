package ro.ucv.ace.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ucv.ace.dao.PersonDao;
import ro.ucv.ace.dao.UserDao;
import ro.ucv.ace.dto.user.UserCreateDto;
import ro.ucv.ace.dto.user.UserDto;
import ro.ucv.ace.exception.*;
import ro.ucv.ace.model.Person;
import ro.ucv.ace.model.User;
import ro.ucv.ace.service.UserManagementService;

import java.util.List;

/**
 * Created by ctotolin on 24-Apr-16.
 */
@Service
@Transactional(rollbackFor = ServiceForeignKeyNotFoundException.class)
public class UserManagementServiceImpl implements UserManagementService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PersonDao personDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userDao.findAll();

        return modelMapper.map(users, new TypeToken<List<UserDto>>() {
        }.getType());
    }

    @Override
    public UserDto getById(Integer id) throws ServiceEntityNotFoundException {
        try {
            User user = userDao.findOne(id);
            return modelMapper.map(user, UserDto.class);

        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public void addUser(UserCreateDto user) throws ServiceEntityAlreadyExistsException, ServiceForeignKeyNotFoundException {
        Person person = modelMapper.map(user.getPerson(), Person.class);
        User userU = modelMapper.map(user, User.class);

        try {
            personDao.save(person);
            Person saved = personDao.findBySsn(person.getSsn());
            userU.getPerson().setId(saved.getId());
            userDao.save(userU);
        } catch (DaoEntityAlreadyExistsException e) {
            throw new ServiceEntityAlreadyExistsException(e);
        } catch (DaoForeignKeyNotFoundException e) {
            throw new ServiceForeignKeyNotFoundException(e);
        } catch (DaoEntityNotFoundException e) {
            // ignore
        }
    }

    @Override
    public void deleteUser(Integer id) throws ServiceEntityNotFoundException {
        try {
            userDao.delete(id);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }
}
