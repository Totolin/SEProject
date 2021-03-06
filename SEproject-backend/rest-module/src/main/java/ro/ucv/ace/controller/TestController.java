package ro.ucv.ace.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is a test controller.
 *
 * @author Georgian Vladutu
 */
@RestController
@RequestMapping(value = "/")
public class TestController {

    /**
     * Returns a ping with 200 OK status.
     *
     * @return 200 OK status
     */
    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public ResponseEntity<String> getPint() {
        return new ResponseEntity<String>(StringUtils.repeat("Ping test!", "-", 4), HttpStatus.OK);
    }
}
