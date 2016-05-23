package ro.ucv.ace.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ucv.ace.dao.PersonDao;
import ro.ucv.ace.dao.UserDao;
import ro.ucv.ace.dto.user.AuthorizationAccountDto;
import ro.ucv.ace.dto.user.UserDto;
import ro.ucv.ace.exception.DaoEntityNotFoundException;
import ro.ucv.ace.exception.ServiceEntityNotFoundException;
import ro.ucv.ace.exception.ServiceForeignKeyNotFoundException;
import ro.ucv.ace.model.Person;
import ro.ucv.ace.model.User;
import ro.ucv.ace.service.LoginService;

/**
 * Created by ctotolin on 24-Apr-16.
 */
@Service
@Transactional(rollbackFor = ServiceForeignKeyNotFoundException.class)
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PersonDao personDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto getByUsername(String username) throws ServiceEntityNotFoundException {
        try {
            User user = userDao.findByUsername(username);
            Person person = personDao.findOne(user.getId());

            UserDto userDto = modelMapper.map(person, UserDto.class);
            userDto.setAccount(modelMapper.map(user, AuthorizationAccountDto.class));

            return userDto;

        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }
}
