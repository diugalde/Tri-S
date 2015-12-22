package cr.ac.siua.tec.controllers;

import cr.ac.siua.tec.utils.FormValidator;
import cr.ac.siua.tec.utils.NotificationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;

@RestController
public class FormController {

    private FormValidator validator = new FormValidator();

    @RequestMapping(value="/request", method = RequestMethod.POST)
    public ResponseEntity<HashMap<String, String>> createTicket(@RequestBody HashMap<String, String> map) {
        HashMap<String, String> responseMap;
        if(validator.isValidForm(map)) {
            responseMap = (HashMap) NotificationManager.getValidFormMsg();
        }else {
            responseMap = (HashMap) NotificationManager.getInvalidFormMsg();
        }
        System.out.println("___________________________________________________");
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

}