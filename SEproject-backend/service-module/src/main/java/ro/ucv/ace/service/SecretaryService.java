package ro.ucv.ace.service;

import ro.ucv.ace.dto.secretary.PreviewSecretaryDto;
import ro.ucv.ace.dto.secretary.SaveSecretaryDto;
import ro.ucv.ace.exception.ServiceEntityAlreadyExistsException;
import ro.ucv.ace.exception.ServiceEntityNotFoundException;
import ro.ucv.ace.exception.ServiceForeignKeyNotFoundException;

import java.util.List;

/**
 * Created by Geo on 23.05.2016.
 */
public interface SecretaryService {
    List<PreviewSecretaryDto> getAll();

    PreviewSecretaryDto getById(Integer id) throws ServiceEntityNotFoundException;

    void save(SaveSecretaryDto saveSecretaryDto) throws ServiceEntityAlreadyExistsException, ServiceForeignKeyNotFoundException;

    void delete(Integer id) throws ServiceEntityNotFoundException;
}
