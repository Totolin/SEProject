package ro.ucv.ace.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ucv.ace.dao.SubjectDao;
import ro.ucv.ace.dto.subject.PreviewSubjectDto;
import ro.ucv.ace.dto.subject.SaveSubjectDto;
import ro.ucv.ace.dto.subject.UpdateSubjectDto;
import ro.ucv.ace.exception.*;
import ro.ucv.ace.model.Subject;
import ro.ucv.ace.service.SubjectService;

import java.util.List;

/**
 * Created by Geo on 28.05.2016.
 */
@Service
@Transactional(rollbackFor = ServiceForeignKeyNotFoundException.class)
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectDao subjectDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PreviewSubjectDto> getAllSubjects() {
        List<Subject> subjects = subjectDao.findAll();

        return modelMapper.map(subjects, new TypeToken<List<PreviewSubjectDto>>() {
        }.getType());
    }

    @Override
    public PreviewSubjectDto getById(int id) throws ServiceEntityNotFoundException {
        try {
            Subject subject = subjectDao.findOne(id);

            return modelMapper.map(subject, PreviewSubjectDto.class);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceEntityNotFoundException {
        try {
            subjectDao.delete(id);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public void save(SaveSubjectDto saveSubjectDto) throws ServiceEntityAlreadyExistsException, ServiceForeignKeyNotFoundException {
        Subject subject = modelMapper.map(saveSubjectDto, Subject.class);

        try {
            subjectDao.save(subject);
        } catch (DaoEntityAlreadyExistsException e) {
            throw new ServiceEntityAlreadyExistsException(e);
        } catch (DaoForeignKeyNotFoundException e) {
            throw new ServiceForeignKeyNotFoundException(e);
        }
    }

    @Override
    public void update(Integer id, UpdateSubjectDto updateSubjectDto) throws ServiceEntityNotFoundException, ServiceForeignKeyNotFoundException {
        Subject subject = modelMapper.map(updateSubjectDto, Subject.class);

        try {
            subjectDao.update(id, subject);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        } catch (DaoForeignKeyNotFoundException e) {
            throw new ServiceForeignKeyNotFoundException(e);
        }
    }
}
