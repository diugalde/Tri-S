package cr.ac.siua.tec.controllers;

import cr.ac.siua.tec.model.*;
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

    @RequestMapping(value = "/inclusion", method = RequestMethod.POST)
    public ResponseEntity<Inclusion> update(@RequestBody Inclusion inclusion) {
        return new ResponseEntity<Inclusion>(inclusion, HttpStatus.OK);
    }

    @RequestMapping(value = "/assistance", method = RequestMethod.POST)
    public ResponseEntity<Assistance> update(@RequestBody Assistance assistance) {
        return new ResponseEntity<Assistance>(assistance, HttpStatus.OK);
    }

    @RequestMapping(value = "/damageReport", method = RequestMethod.POST)
    public ResponseEntity<DamageReport> update(@RequestBody DamageReport damageReport) {
        return new ResponseEntity<DamageReport>(damageReport, HttpStatus.OK);
    }

    @RequestMapping(value = "/equipment", method = RequestMethod.POST)
    public ResponseEntity<Equipment> update(@RequestBody Equipment equipment) {
        return new ResponseEntity<Equipment>(equipment, HttpStatus.OK);
    }

    @RequestMapping(value = "/proficiency", method = RequestMethod.POST)
    public ResponseEntity<Proficiency> update(@RequestBody Proficiency proficiency) {
        return new ResponseEntity<Proficiency>(proficiency, HttpStatus.OK);
    }

    @RequestMapping(value = "/purchase", method = RequestMethod.POST)
    public ResponseEntity<Purchase> update(@RequestBody Purchase purchase) {
        return new ResponseEntity<Purchase>(purchase, HttpStatus.OK);
    }

    @RequestMapping(value = "/requirements", method = RequestMethod.POST)
    public ResponseEntity<Requirements> update(@RequestBody Requirements requirements) {
        return new ResponseEntity<Requirements>(requirements, HttpStatus.OK);
    }

    @RequestMapping(value = "/softwareInstallation", method = RequestMethod.POST)
    public ResponseEntity<SoftwareInstallation> update(@RequestBody SoftwareInstallation softwareInstallation) {
        return new ResponseEntity<SoftwareInstallation>(softwareInstallation, HttpStatus.OK);
    }
    @RequestMapping(value = "/workstation", method = RequestMethod.POST)
    public ResponseEntity<Workstation> update(@RequestBody Workstation workstation) {
        return new ResponseEntity<Workstation>(workstation, HttpStatus.OK);
    }

}