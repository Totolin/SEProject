package ro.ucv.ace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ucv.ace.dto.subject.PreviewSubjectDto;
import ro.ucv.ace.service.SubjectService;

import java.util.List;

/**
 * Created by Geo on 27.05.2016.
 */
@RestController
@RequestMapping(value = "/secretaries")
public class SecretarySubjectController {

    @Autowired
    private SubjectService subjectService;

    @RequestMapping(value = "/subjects", method = RequestMethod.GET)
    public ResponseEntity<List<PreviewSubjectDto>> getAll() {
        List<PreviewSubjectDto> previewSubjectDtos = subjectService.getAllSubjects();

        return new ResponseEntity<List<PreviewSubjectDto>>(previewSubjectDtos, HttpStatus.OK);
    }
}