package cr.ac.siua.tec.utils.impl;

import cr.ac.siua.tec.utils.PDFGenerator;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

@Component("CEInclusion")
public class CEInclusionPDFGenerator extends PDFGenerator{

    @Override
    public String generate(HashMap<String, String> formValues) {
        String templateName = "cons.pdf";
        String pdfPath = PDFGenerator.RESOURCES_PATH + templateName;
        return populateAndCopy(pdfPath);
    }


    private String populateAndCopy(String originalPdf) {
        try {
            PDDocument _pdfDocument = PDDocument.load(originalPdf);
            PDDocumentCatalog docCatalog = _pdfDocument.getDocumentCatalog();
            PDAcroForm acroForm = docCatalog.getAcroForm();
            acroForm.getField("coordinator1").setValue("EDDY RAMÍREZ ELIZOASDAÁÁÁÁ");
            return encodePDF(_pdfDocument);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "1";
    }

}
