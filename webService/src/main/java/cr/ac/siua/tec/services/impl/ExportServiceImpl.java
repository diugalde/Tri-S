package cr.ac.siua.tec.services.impl;

import cr.ac.siua.tec.services.ExportService;
import cr.ac.siua.tec.utils.PDFGeneratorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ExportServiceImpl implements ExportService {

    @Autowired
    private PDFGeneratorFactory pdfGeneratorFactory;

    public String getPDF(HashMap<String, String> ticketContent) {

        String formType = ticketContent.get("Queue");
        String pdfContent = pdfGeneratorFactory.getPDFGenerator(formType).generate(ticketContent);
        return pdfContent;
    }


}
