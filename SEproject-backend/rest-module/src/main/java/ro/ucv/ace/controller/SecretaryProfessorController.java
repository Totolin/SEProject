package ro.ucv.ace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ucv.ace.dto.professor.ProfessorDto;
import ro.ucv.ace.exception.RestEntityNotFoundException;
import ro.ucv.ace.exception.ServiceEntityNotFoundException;
import ro.ucv.ace.misc.ExceptionMessageManager;
import ro.ucv.ace.service.ProfessorService;

import java.util.List;

/**
 * Created by Geo on 15.05.2016.
 */
@RestController
@RequestMapping("/secretaries")
public class SecretaryProfessorController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private ExceptionMessageManager eMM;

    @RequestMapping(value = "/professors", method = RequestMethod.GET)
    public ResponseEntity<List<ProfessorDto>> getAll() {
        List<ProfessorDto> professors = professorService.getAll();

        return new ResponseEntity<List<ProfessorDto>>(professors, HttpStatus.OK);
    }

    @RequestMapping(value = "/professors/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProfessorDto> getById(@PathVariable Integer id) throws RestEntityNotFoundException {
        ProfessorDto professor;
        try {
            professor = professorService.getById(id);
        } catch (ServiceEntityNotFoundException e) {
            throw new RestEntityNotFoundException(eMM.get("professor.notFound"));
        }

        return new ResponseEntity<ProfessorDto>(professor, HttpStatus.OK);
    }
}
