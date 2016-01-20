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
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Component("Assistance")
public class AssistancePDFGenerator extends PDFGenerator {

    /**
     * Fills the PDF file (asistente.pdf) with the ticket values and returns base64 encoded string.
     */
    @Override
    public String generate(HashMap<String, String> formValues) {
        String originalPdf = PDFGenerator.RESOURCES_PATH + "asistente.pdf";
        try {
            PDDocument _pdfDocument = PDDocument.load(originalPdf);
            PDDocumentCatalog docCatalog = _pdfDocument.getDocumentCatalog();
            PDAcroForm acroForm = docCatalog.getAcroForm();

            //Set some fields manually.
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH)+1;
            if(month > 10 || month < 5) acroForm.getField("Semestre").setValue("PRIMER");
            else acroForm.getField("Semestre").setValue("SEGUNDO");

            if(month > 10 && acroForm.getField("Semestre").getValue().equals("PRIMER")) year++;

            acroForm.getField("Año").setValue(String.valueOf(year));
            formValues.remove("Queue");
            acroForm.getField(formValues.get("Banco")).setValue("x");
            formValues.remove("Banco");
            acroForm.getField(formValues.get("Tipo de asistencia")).setValue("x");
            formValues.remove("Tipo de asistencia");

            //Iterates through remaining custom fields.
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
