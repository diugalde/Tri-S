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

    //@CrossOrigin(origins = "http://localhost:8686")
    @RequestMapping(value="/request", method = RequestMethod.POST)
    public ResponseEntity<HashMap<String, String>> createTicket(@RequestBody HashMap<String, String> map) {
        HashMap<String, String> responseMap;
        if(!recaptchaService.isResponseValid("", map.get("g-recaptcha-response"))) {
            responseMap = (HashMap<String, String>) NotificationManager.getInvalidCaptchaMsg();
        }else {
            List<String> wrongFields = validator.getFormWrongFields(map);
            if(!wrongFields.isEmpty()) {
                responseMap = (HashMap<String, String>) NotificationManager.getInvalidFormMsg(wrongFields);
            }else{
                int status = rtService.createTicket(map);
                if(status == 1) responseMap = (HashMap<String, String>) NotificationManager.getValidFormMsg();
                else responseMap = (HashMap<String, String>) NotificationManager.getRTCrashedMsg();
            }
        }
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }
}