package ro.ucv.ace.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ucv.ace.dao.StudentDao;
import ro.ucv.ace.dto.student.SaveStudentDto;
import ro.ucv.ace.dto.student.StudentGrade;
import ro.ucv.ace.dto.student.StudentInfoDto;
import ro.ucv.ace.dto.student.UpdateStudentDto;
import ro.ucv.ace.exception.*;
import ro.ucv.ace.model.Student;
import ro.ucv.ace.model.StudentSubject;
import ro.ucv.ace.service.StudentService;

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
    private ModelMapper modelMapper;

    @Override
    public List<StudentGrade> getAllGrades(Integer id) throws ServiceEntityNotFoundException {
        List<StudentGrade> studentGrades = new ArrayList<>();
        try {
            Student student = studentDao.findOne(id);
            for (StudentSubject studentSubject : student.getStudentSubjects()) {
                StudentGrade studentGrade = modelMapper.map(studentSubject, StudentGrade.class);
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

        try {
            studentDao.save(student);
        } catch (DaoEntityAlreadyExistsException e) {
            throw new ServiceEntityAlreadyExistsException(e);
        } catch (DaoForeignKeyNotFoundException e) {
            throw new ServiceForeignKeyNotFoundException(e);
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
        try {
            studentDao.delete(id);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }
}
