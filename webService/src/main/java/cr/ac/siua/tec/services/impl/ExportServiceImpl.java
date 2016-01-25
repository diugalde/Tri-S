/*
	TRI-S - Web Service
	Developed by: Luis E. Ugalde Barrantes - Diego Ugalde Ávila. 2016.
	This code is licensed under the GNU GENERAL PUBLIC LICENSE (GPL) V3. See LICENSE file for details.
*/

package cr.ac.siua.tec.services.impl;

import cr.ac.siua.tec.services.ExportService;
import cr.ac.siua.tec.utils.PDFGeneratorFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;

@Service
public class ExportServiceImpl implements ExportService {

    @Autowired
    private PDFGeneratorFactory pdfGeneratorFactory;

    /**
     * In charge of retreving the base64 String (encoded PDF) for the requested ticket.
     */
    public String getPDF(HashMap<String, String> ticketContent) {
        String formType = ticketContent.get("Queue");
        try {
            //It uses the factory pattern to invoke the form's corresponding export method.
            String pdfContent = pdfGeneratorFactory.getPDFGenerator(formType).generate(ticketContent);
            return pdfContent;
        }catch(NoSuchBeanDefinitionException e) {
            e.printStackTrace();
            System.out.println("This queue's ticket export method has not been implemented.");
            return "Not implemented yet.";
        }
    }
}
