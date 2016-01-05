package cr.ac.siua.tec.controllers;

import cr.ac.siua.tec.services.RTService;
import cr.ac.siua.tec.services.RecaptchaService;
import cr.ac.siua.tec.utils.FormValidator;
import cr.ac.siua.tec.utils.NotificationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class FormController {

    private FormValidator validator = new FormValidator();

    @Autowired
    private RecaptchaService recaptchaService;

    @Autowired
    private RTService rtService;

    @RequestMapping(value="/request", method = RequestMethod.POST)
    public ResponseEntity<HashMap<String, String>> getRequest(@RequestBody HashMap<String, String> map) {
        HashMap<String, String> responseMap;
        if(!recaptchaService.isResponseValid("", map.get("recaptchaResponse"))) {
            responseMap = (HashMap) NotificationManager.getInvalidCaptchaMsg();
        }
        else if(!validator.isValidForm(map)) {
            responseMap = (HashMap) NotificationManager.getInvalidFormMsg();
        }else {
            responseMap = (HashMap) NotificationManager.getValidFormMsg();
        }
        System.out.println("___________________________________________________");
        rtService.createTicket(map);
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

}