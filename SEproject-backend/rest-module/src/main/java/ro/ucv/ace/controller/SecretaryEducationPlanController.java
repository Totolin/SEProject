package ro.ucv.ace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.ucv.ace.dto.educationPlan.PreviewEducationPlanDto;
import ro.ucv.ace.dto.educationPlan.SaveEducationPlanDto;
import ro.ucv.ace.exception.*;
import ro.ucv.ace.misc.ExceptionMessageManager;
import ro.ucv.ace.service.EducationPlanService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Geo on 22.05.2016.
 */
@RestController
@RequestMapping("/secretaries")
public class SecretaryEducationPlanController {

    @Autowired
    private ExceptionMessageManager eMM;

    @Autowired
    private EducationPlanService educationPlanService;

    @RequestMapping(value = "/educationPlans", method = RequestMethod.GET)
    public ResponseEntity<List<PreviewEducationPlanDto>> getAll() {
        List<PreviewEducationPlanDto> previewEducationPlanDtos = educationPlanService.getAll();

        return new ResponseEntity<List<PreviewEducationPlanDto>>(previewEducationPlanDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/educationPlans", method = RequestMethod.POST)
    public ResponseEntity<Void> save(@Valid @RequestBody SaveEducationPlanDto saveEducationPlanDto, BindingResult bindingResult) throws RestEntityBindingException, RestEntityAlreadyExistsException, RestForeignKeyNotFoundException {
        if (bindingResult.hasErrors()) {
            throw new RestEntityBindingException(bindingResult.getFieldErrors(), eMM.get("educationPlan.binding"));
        }

        try {
            educationPlanService.save(saveEducationPlanDto);
        } catch (ServiceEntityAlreadyExistsException e) {
            throw new RestEntityAlreadyExistsException(eMM.get("educationPlan.alreadyExists"));
        } catch (ServiceForeignKeyNotFoundException e) {
            throw new RestForeignKeyNotFoundException(e.getMessage());
        }

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/educationPlans/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws RestEntityNotFoundException {
        try {
            educationPlanService.delete(id);
        } catch (ServiceEntityNotFoundException e) {
            throw new RestEntityNotFoundException(eMM.get("educationPlan.notFound"));
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
