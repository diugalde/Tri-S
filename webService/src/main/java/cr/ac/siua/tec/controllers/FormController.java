/*
	TRI-S - Web Service
	Developed by: Luis E. Ugalde Barrantes - Diego Ugalde Ávila. 2016.
	This code is licensed under the GNU GENERAL PUBLIC LICENSE (GPL) V3. See LICENSE file for details.
*/


package cr.ac.siua.tec.controllers;

import cr.ac.siua.tec.services.RTService;
import cr.ac.siua.tec.services.RecaptchaService;
import cr.ac.siua.tec.utils.FormValidator;
import cr.ac.siua.tec.utils.NotificationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;

@RestController
public class FormController {

    private FormValidator validator = new FormValidator();

    @Autowired
    private RecaptchaService recaptchaService;

    @Autowired
    private RTService rtService;

    /**
     * In charge of receiving web requests (forms) and validating them.
     */
    @CrossOrigin(origins = "http://tec.siua.ac.cr")
    @RequestMapping(value="/request", method = RequestMethod.POST)
    public ResponseEntity<HashMap<String, String>> createTicket(@RequestBody HashMap<String, String> map) {
        HashMap<String, String> responseMap;
        // Verifies recaptcha response with GoogleService.
        if(!recaptchaService.isResponseValid("", map.get("g-recaptcha-response"))) {
            responseMap = (HashMap<String, String>) NotificationManager.getInvalidCaptchaMsg();
        }else {
            responseMap = getRequestResponseMap(map);
        }
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

    /**
     * In charge of receiving mobile requests (forms) and validating them. No captcha response required.
     */
    @RequestMapping(value="/mobileRequest", method = RequestMethod.POST)
    public ResponseEntity<HashMap<String, String>> createMobileTicket(@RequestBody HashMap<String, String> map) {
        return new ResponseEntity<>(getRequestResponseMap(map), HttpStatus.OK);
    }


    /**
     * Validates form fields. If the validation was succesful, a ticket is created in RT.
     * Returns HashMap with the corresponding notification.
     */
    private HashMap<String, String> getRequestResponseMap(HashMap<String, String> formMap) {
        HashMap<String, String> responseMap;
        List<String> wrongFields = validator.getFormWrongFields(formMap);
        if(!wrongFields.isEmpty()) {
            responseMap = (HashMap<String, String>) NotificationManager.getInvalidFormMsg(wrongFields);
        }else{
            int status = rtService.createTicket(formMap);
            if(status == 1) responseMap = (HashMap<String, String>) NotificationManager.getValidFormMsg();
            else responseMap = (HashMap<String, String>) NotificationManager.getRTCrashedMsg();
        }
        return responseMap;
    }
}