/*
	TRI-S - Web Service
	Developed by: Luis E. Ugalde Barrantes - Diego Ugalde Ávila. 2016.
	This code is licensed under the GNU GENERAL PUBLIC LICENSE (GPL) V3. See LICENSE file for details.
*/

package cr.ac.siua.tec.utils.impl;

import cr.ac.siua.tec.utils.PDFGenerator;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component("Proficiency")
public class ProficiencyPDFGenerator extends PDFGenerator{

    /**
     * Fills the PDF file (suficiencia.pdf) with the ticket values and returns base64 encoded string.
     */
    @Override
    public String generate(HashMap<String, String> formValues) {
        String originalPdf = PDFGenerator.RESOURCES_PATH + "suficiencia.pdf";
        try {
            PDDocument _pdfDocument = PDDocument.load(originalPdf);
            PDDocumentCatalog docCatalog = _pdfDocument.getDocumentCatalog();
            PDAcroForm acroForm = docCatalog.getAcroForm();
            formValues.remove("Queue");

            //Iterates through all custom field values.
            for(Map.Entry<String, String> entry : formValues.entrySet()) {
                acroForm.getField(entry.getKey()).setValue(entry.getValue());
            }
            return encodePDF(_pdfDocument);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Excepción al llenar el PDF.");
            return null;
        }
    }
}
