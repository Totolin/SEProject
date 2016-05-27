package ro.ucv.ace.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ucv.ace.dao.ProfessorSubjectDao;
import ro.ucv.ace.dao.StudentDao;
import ro.ucv.ace.dao.StudentSubjectDao;
import ro.ucv.ace.dao.UserDao;
import ro.ucv.ace.dto.student.SaveStudentDto;
import ro.ucv.ace.dto.student.StudentGradeDto;
import ro.ucv.ace.dto.student.StudentInfoDto;
import ro.ucv.ace.dto.student.UpdateStudentDto;
import ro.ucv.ace.dto.user.PreviewAccountDto;
import ro.ucv.ace.enums.UserType;
import ro.ucv.ace.exception.*;
import ro.ucv.ace.model.ProfessorSubject;
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
    private ProfessorSubjectDao professorSubjectDao;

    @Autowired
    private StudentSubjectDao studentSubjectDao;

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
            User user = userDao.findOne(id);
            Student student = studentDao.findOne(id);

            PreviewAccountDto accountDto = modelMapper.map(user, PreviewAccountDto.class);
            StudentInfoDto studentInfoDto = modelMapper.map(student, StudentInfoDto.class);
            studentInfoDto.setAccount(accountDto);

            return studentInfoDto;
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public List<StudentInfoDto> getAllStudents() {
        List<Student> students = studentDao.findAll();
        List<StudentInfoDto> returnList = new ArrayList<>();

        for (Student student : students) {
            try {
                User user = userDao.findOne(student.getId());

                PreviewAccountDto accountDto = modelMapper.map(user, PreviewAccountDto.class);
                StudentInfoDto studentInfoDto = modelMapper.map(student, StudentInfoDto.class);
                studentInfoDto.setAccount(accountDto);

                returnList.add(studentInfoDto);
            } catch (DaoEntityNotFoundException e) {
                // ignore; students must have an account
            }
        }

        return returnList;
    }

    @Override
    public void save(SaveStudentDto saveStudentDto) throws ServiceEntityAlreadyExistsException, ServiceForeignKeyNotFoundException {
        Student student = modelMapper.map(saveStudentDto, Student.class);
        String password;
        try {
            Student saved = studentDao.save(student);

            List<ProfessorSubject> professorSubjects = professorSubjectDao.findByGroup(saved.getGroup().getId());
            for (ProfessorSubject professorSubject : professorSubjects) {
                StudentSubject studentSubject = new StudentSubject(saved.getId(), professorSubject.getSubject().getId());
                studentSubjectDao.save(studentSubject);
            }

            User user = userCreator.createUser(saved, UserType.STUDENT.getType());
            password = user.getPassword();
            user.setPassword(passwordEncoder.encode(password));

            userDao.save(user);

            mailSender.sendCreateAccountMail(student.getEmail(), password, user.getUsername());
        } catch (DaoEntityAlreadyExistsException e) {
            throw new ServiceEntityAlreadyExistsException(e);
        } catch (DaoForeignKeyNotFoundException e) {
            throw new ServiceForeignKeyNotFoundException(e);
        }
    }

    @Override
    public void update(UpdateStudentDto updateStudentDto, Integer id) throws ServiceEntityNotFoundException, ServiceForeignKeyNotFoundException {
        Student student = modelMapper.map(updateStudentDto, Student.class);
        String password;

        try {
            Student updated = studentDao.update(id, student);

            userDao.delete(updated.getId());

            User user = userCreator.createUser(updated, UserType.STUDENT.getType());

            password = user.getPassword();
            user.setPassword(passwordEncoder.encode(password));

            userDao.save(user);

            mailSender.sendUpdateAccountMail(student.getEmail(), password, user.getUsername());

        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        } catch (DaoForeignKeyNotFoundException e) {
            throw new ServiceForeignKeyNotFoundException(e);
        } catch (DaoEntityAlreadyExistsException e) {
            // ignored; account was just deleted
        }
    }

    @Override
    public void delete(Integer id) throws ServiceEntityNotFoundException {

        try {
            userDao.delete(id);
            studentDao.delete(id);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }
}
