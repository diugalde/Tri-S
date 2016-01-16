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

@Component("CEInclusion")
public class CEInclusionPDFGenerator extends PDFGenerator{

    @Override
    public String generate(HashMap<String, String> formValues) {
        String originalPdf = PDFGenerator.RESOURCES_PATH + "inclusion.pdf";
        try {
            PDDocument _pdfDocument = PDDocument.load(originalPdf);
            PDDocumentCatalog docCatalog = _pdfDocument.getDocumentCatalog();
            PDAcroForm acroForm = docCatalog.getAcroForm();
            formValues.remove("Queue");
            formValues.remove("Justificación");

            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH)+1;
            if(month > 10 || month < 5) acroForm.getField("Semestre").setValue("I");
            else acroForm.getField("Semestre").setValue("II");
            if(month > 10 && acroForm.getField("Semestre").getValue().equals("I")) year++;
            acroForm.getField("Año").setValue(String.valueOf(year));

            String enrolledCourses[] = formValues.get("Cursos matriculados").split("\n");
            for(int i = 0; i < enrolledCourses.length; i++) {
                String courseRow[] = enrolledCourses[i].split("-");
                acroForm.getField("Código" + Integer.toString(i)).setValue(courseRow[0]);
                acroForm.getField("Curso" + Integer.toString(i)).setValue(courseRow[1]);
                acroForm.getField("Grupo" + Integer.toString(i)).setValue(courseRow[2]);
            }
            formValues.remove("Cursos matriculados");

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
