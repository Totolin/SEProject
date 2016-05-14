package ro.ucv.ace.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ucv.ace.dao.UserDao;
import ro.ucv.ace.dto.UserCreateDto;
import ro.ucv.ace.dto.UserDto;
import ro.ucv.ace.exception.*;
import ro.ucv.ace.model.User;
import ro.ucv.ace.service.UserManagementService;

import java.lang.reflect.Type;
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
    private ModelMapper modelMapper;

    @Override
    public List<UserDto> getAllUsers() {
        Type targetListType = new TypeToken<List<User>>() {
        }.getType();
        List<User> users = userDao.findAll();
        return modelMapper.map(users, targetListType);
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
        User userU = modelMapper.map(user, User.class);
        try {
            userDao.save(userU);
        } catch (DaoEntityAlreadyExistsException e) {
            throw new ServiceEntityAlreadyExistsException(e);
        } catch (DaoForeignKeyNotFoundException e) {
            throw new ServiceForeignKeyNotFoundException(e);
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
