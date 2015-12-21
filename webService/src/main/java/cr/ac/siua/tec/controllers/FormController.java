package cr.ac.siua.tec.controllers;

import cr.ac.siua.tec.model.Constancy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FormController {

    @RequestMapping(value = "/constancy", method = RequestMethod.POST)
    public ResponseEntity<Constancy> update(@RequestBody Constancy constancy) {
        return new ResponseEntity<Constancy>(constancy, HttpStatus.OK);
    }
}