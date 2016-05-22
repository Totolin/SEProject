package ro.ucv.ace.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ucv.ace.dao.DepartmentDao;
import ro.ucv.ace.dao.ProfessorDao;
import ro.ucv.ace.dao.StudentSubjectDao;
import ro.ucv.ace.dto.professor.ProfessorDto;
import ro.ucv.ace.dto.professor.StudentGradeDto;
import ro.ucv.ace.dto.professor.SaveProfessorDto;
import ro.ucv.ace.dto.professor.UpdateProfessorDto;
import ro.ucv.ace.dto.student.StudentGrade;
import ro.ucv.ace.exception.*;
import ro.ucv.ace.model.Professor;
import ro.ucv.ace.model.StudentSubject;
import ro.ucv.ace.service.ProfessorService;

import java.util.List;

/**
 * Created by Geo on 15.05.2016.
 */
@Service
@Transactional(rollbackFor = ServiceForeignKeyNotFoundException.class)
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProfessorDao professorDao;

    @Autowired
    private StudentSubjectDao studentSubjectDao;

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public List<ProfessorDto> getAll() {
        List<Professor> professors = professorDao.findAll();

        return modelMapper.map(professors, new TypeToken<List<ProfessorDto>>() {
        }.getType());
    }

    @Override
    public ProfessorDto getById(Integer id) throws ServiceEntityNotFoundException {
        try {
            Professor professor = professorDao.findOne(id);

            return modelMapper.map(professor, ProfessorDto.class);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public void save(SaveProfessorDto saveProfessorDto) throws ServiceEntityAlreadyExistsException, ServiceForeignKeyNotFoundException {
        Professor professor = modelMapper.map(saveProfessorDto, Professor.class);

        try {
            professorDao.save(professor);
        } catch (DaoEntityAlreadyExistsException e) {
            throw new ServiceEntityAlreadyExistsException(e);
        } catch (DaoForeignKeyNotFoundException e) {
            throw new ServiceForeignKeyNotFoundException(e);
        }
    }

    @Override
    public void update(UpdateProfessorDto updateProfessorDto, Integer id) throws ServiceEntityNotFoundException, ServiceForeignKeyNotFoundException {
        Professor professor = modelMapper.map(updateProfessorDto, Professor.class);

        try {
            professorDao.update(id, professor);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        } catch (DaoForeignKeyNotFoundException e) {
            throw new ServiceForeignKeyNotFoundException(e);
        }
    }

    @Override
    public void grade(StudentGradeDto studentGradeDto) throws ServiceEntityNotFoundException, ServiceForeignKeyNotFoundException {
        StudentSubject studentGrade = modelMapper.map(studentGradeDto, StudentSubject.class);

        try{
            studentSubjectDao.save(studentGrade);
        } catch (DaoEntityAlreadyExistsException e) {
            throw new ServiceEntityNotFoundException(e);
        } catch (DaoForeignKeyNotFoundException e) {
            throw new ServiceForeignKeyNotFoundException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceEntityNotFoundException {
        departmentDao.removeDirector(id);

        try {
            professorDao.delete(id);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }
}
