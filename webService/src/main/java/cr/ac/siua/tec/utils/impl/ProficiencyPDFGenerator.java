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

    @Override
    public String generate(HashMap<String, String> formValues) {
        String templateName = "suficiencia.pdf";
        String pdfPath = PDFGenerator.RESOURCES_PATH + templateName;
        return populateAndCopy(pdfPath, formValues);
    }

    private String populateAndCopy(String originalPdf, HashMap<String, String> formValues) {
        try {
            PDDocument _pdfDocument = PDDocument.load(originalPdf);
            PDDocumentCatalog docCatalog = _pdfDocument.getDocumentCatalog();
            PDAcroForm acroForm = docCatalog.getAcroForm();
            formValues.remove("Queue");

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
