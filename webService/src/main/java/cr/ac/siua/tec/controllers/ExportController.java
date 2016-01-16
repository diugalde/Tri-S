package cr.ac.siua.tec.controllers;

import cr.ac.siua.tec.services.ExportService;
import cr.ac.siua.tec.services.RTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class ExportController {

    @Autowired
    private ExportService exportService;

    @Autowired
    private RTService rtService;

    @CrossOrigin(origins = "http://localhost:8484")
    @RequestMapping(value="/export", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, String>> export(@RequestParam("ticketId") String ticketId) {
        HashMap<String, String> ticketContent = rtService.getTicket(ticketId);
        HashMap<String, String> responseMap = new HashMap<>();
        if(ticketContent != null) {
            String pdfContent = exportService.getPDF(ticketContent);
            responseMap.put("content", pdfContent);
        }else {
            responseMap.put("content", "RT Server is down.");
        }
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }
}