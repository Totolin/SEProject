package ro.ucv.ace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ucv.ace.dto.group.PreviewGroupDto;
import ro.ucv.ace.service.SecretaryService;

import java.util.List;

/**
 * Created by Geo on 27.05.2016.
 */
@RestController
@RequestMapping(value = "/secretaries")
public class SecretaryGroupController {

    @Autowired
    private SecretaryService secretaryService;

    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public ResponseEntity<List<PreviewGroupDto>> getAll() {
        List<PreviewGroupDto> previewGroupDtos = secretaryService.getAllGroups();

        return new ResponseEntity<List<PreviewGroupDto>>(previewGroupDtos, HttpStatus.OK);
    }
}
