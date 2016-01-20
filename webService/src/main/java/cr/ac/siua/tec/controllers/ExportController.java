/*
	TRI-S - Web Service
	Developed by: Luis E. Ugalde Barrantes - Diego Ugalde Ávila. 2016.
	This code is licensed under the GNU GENERAL PUBLIC LICENSE (GPL) V3. See LICENSE file for details.
*/

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

    /**
     * Method for incoming RT export requests.
     * It takes ticket Id as a parameter and returns JSON with a base64 encoded PDF.
     */
    @CrossOrigin(origins = "http://rt.tec.siua.ac.cr")
    @RequestMapping(value="/export", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, String>> export(@RequestParam("ticketId") String ticketId) {
        HashMap<String, String> ticketContent = rtService.getTicket(ticketId); //Fills hashmap with ticket customFields.
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