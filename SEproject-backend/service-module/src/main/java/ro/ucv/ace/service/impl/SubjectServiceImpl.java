package ro.ucv.ace.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ucv.ace.dao.SubjectDao;
import ro.ucv.ace.dto.subject.PreviewSubjectDto;
import ro.ucv.ace.exception.ServiceForeignKeyNotFoundException;
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
}
