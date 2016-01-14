package cr.ac.siua.tec.utils.impl;

import cr.ac.siua.tec.utils.PDFGenerator;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;

@Component("CEInclusion")
@PropertySource(value={"classpath:application-data.properties"})
public class CEInclusionPDFGenerator extends PDFGenerator{

    @Value("${coordinator.name}")
    private String coordinatorName;

    @Override
    public String generate(HashMap<String, String> formValues) {
        String templateName = "cons.pdf";
        String pdfPath = PDFGenerator.RESOURCES_PATH + templateName;
        return populateAndCopy(pdfPath);
    }


    private String populateAndCopy(String originalPdf) {
        try {
            System.out.println("\n\n" + new String(coordinatorName.getBytes("ISO-8859-1"), "UTF-8") + "\n\n");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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
