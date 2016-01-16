package cr.ac.siua.tec.controllers;

import cr.ac.siua.tec.services.ExportService;
import cr.ac.siua.tec.services.RTService;
import cr.ac.siua.tec.services.RecaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class ExportController {

    @Autowired
    private ExportService exportService;

    @Autowired
    private RTService rtService;

    //Falta el @RequestBody HashMap<String, String> map como parametro de export() para que reciba el json.
    @RequestMapping(value="/export", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, String>> export() {

        //El parametro debería ser map.get("ticketId") que lo recibe del JSON.
        HashMap<String, String> ticketContent = rtService.getTicket("3");

        String pdfContent = exportService.getPDF(ticketContent);

        HashMap<String, String> responseMap = new HashMap<>();
        responseMap.put("content", pdfContent);

        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }
}