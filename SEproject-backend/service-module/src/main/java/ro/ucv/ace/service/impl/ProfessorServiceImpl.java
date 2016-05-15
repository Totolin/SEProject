package ro.ucv.ace.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ucv.ace.dao.ProfessorDao;
import ro.ucv.ace.dto.professor.ProfessorDto;
import ro.ucv.ace.exception.DaoEntityNotFoundException;
import ro.ucv.ace.exception.ServiceEntityNotFoundException;
import ro.ucv.ace.exception.ServiceForeignKeyNotFoundException;
import ro.ucv.ace.model.Professor;
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
}
