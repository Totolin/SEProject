package ro.ucv.ace.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ucv.ace.dao.StudentDao;
import ro.ucv.ace.dao.UserDao;
import ro.ucv.ace.dto.student.SaveStudentDto;
import ro.ucv.ace.dto.student.StudentGradeDto;
import ro.ucv.ace.dto.student.StudentInfoDto;
import ro.ucv.ace.dto.student.UpdateStudentDto;
import ro.ucv.ace.exception.*;
import ro.ucv.ace.model.Student;
import ro.ucv.ace.model.StudentSubject;
import ro.ucv.ace.model.User;
import ro.ucv.ace.service.StudentService;
import ro.ucv.ace.utils.mail.MailSender;
import ro.ucv.ace.utils.user.UserCreator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geo on 28.04.2016.
 */
@Service
@Transactional(rollbackFor = ServiceForeignKeyNotFoundException.class)
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserCreator userCreator;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailSender mailSender;

    @Override
    public List<StudentGradeDto> getAllGrades(Integer id) throws ServiceEntityNotFoundException {
        List<StudentGradeDto> studentGrades = new ArrayList<>();
        try {
            Student student = studentDao.findOne(id);
            for (StudentSubject studentSubject : student.getStudentSubjects()) {
                StudentGradeDto studentGrade = modelMapper.map(studentSubject, StudentGradeDto.class);
                studentGrades.add(studentGrade);
            }

        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }

        return studentGrades;
    }

    @Override
    public StudentInfoDto getStudentInfo(Integer id) throws ServiceEntityNotFoundException {
        try {
            Student student = studentDao.findOne(id);

            return modelMapper.map(student, StudentInfoDto.class);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public List<StudentInfoDto> getAll() {
        List<Student> students = studentDao.findAll();

        return modelMapper.map(students, new TypeToken<List<StudentInfoDto>>() {
        }.getType());
    }

    @Override
    public void save(SaveStudentDto saveStudentDto) throws ServiceEntityAlreadyExistsException, ServiceForeignKeyNotFoundException {
        Student student = modelMapper.map(saveStudentDto, Student.class);
        String password;
        try {
            studentDao.save(student);

            Student saved = studentDao.findBySsn(student.getSsn());

            User user = userCreator.createUser(saved.getId(), student.getSsn(), "STUDENT");
            password = user.getPassword();
            user.setPassword(passwordEncoder.encode(password));

            userDao.save(user);

            mailSender.sendPasswordMail(student.getEmail(), password, user.getUsername());
        } catch (DaoEntityAlreadyExistsException e) {
            throw new ServiceEntityAlreadyExistsException(e);
        } catch (DaoForeignKeyNotFoundException e) {
            throw new ServiceForeignKeyNotFoundException(e);
        } catch (DaoEntityNotFoundException e) {
            // ignored
        }
    }

    @Override
    public void update(UpdateStudentDto updateStudentDto, Integer id) throws ServiceEntityNotFoundException, ServiceForeignKeyNotFoundException {
        Student student = modelMapper.map(updateStudentDto, Student.class);

        try {
            studentDao.update(id, student);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        } catch (DaoForeignKeyNotFoundException e) {
            throw new ServiceForeignKeyNotFoundException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceEntityNotFoundException {
        userDao.deleteByPersonId(id);

        try {
            studentDao.delete(id);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }
}
