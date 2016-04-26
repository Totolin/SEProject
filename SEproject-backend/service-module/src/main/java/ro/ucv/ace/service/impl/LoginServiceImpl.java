package ro.ucv.ace.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ucv.ace.dao.UserDao;
import ro.ucv.ace.dto.UserDto;
import ro.ucv.ace.exception.DaoEntityNotFoundException;
import ro.ucv.ace.exception.ServiceEntityNotFoundException;
import ro.ucv.ace.model.User;
import ro.ucv.ace.service.LoginService;

/**
 * Created by ctotolin on 24-Apr-16.
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto getByUsername(String username) throws ServiceEntityNotFoundException {
        try {
            User user = userDao.findByUsername(username);
            return modelMapper.map(user, UserDto.class);

        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }
}
