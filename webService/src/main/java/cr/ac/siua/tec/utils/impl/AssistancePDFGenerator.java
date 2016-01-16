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

    @Override
    public String generate(HashMap<String, String> formValues) {
        String templateName = "asistente.pdf";
        String pdfPath = PDFGenerator.RESOURCES_PATH + templateName;
        return populateAndCopy(pdfPath, formValues);
    }

    private String populateAndCopy(String originalPdf, HashMap<String, String> formValues) {
        try {
            PDDocument _pdfDocument = PDDocument.load(originalPdf);
            PDDocumentCatalog docCatalog = _pdfDocument.getDocumentCatalog();
            PDAcroForm acroForm = docCatalog.getAcroForm();

            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH)+1;
            if(month > 10 || month < 5) {
                acroForm.getField("Semestre").setValue("PRIMER");
            } else {
                acroForm.getField("Semestre").setValue("SEGUNDO");
            }
            if(month > 10 && acroForm.getField("Semestre").getValue().equals("PRIMER")) {
                year++;
            }
            acroForm.getField("Año").setValue(String.valueOf(year));

            formValues.remove("Queue");
            acroForm.getField(formValues.get("Banco")).setValue("x");
            formValues.remove("Banco");
            acroForm.getField(formValues.get("Tipo de asistencia")).setValue("x");
            formValues.remove("Tipo de asistencia");

            for(Map.Entry<String, String> entry : formValues.entrySet()) {
                acroForm.getField(entry.getKey()).setValue(entry.getValue());
            }
            return encodePDF(_pdfDocument);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "1";
    }
}
