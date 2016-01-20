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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Component("Constancy")
@PropertySource(value={"classpath:application-data.properties"})
public class ConstancyPDFGenerator extends PDFGenerator {

    @Value("${coordinator.name}")
    private String coordinatorName;

    private Map<Integer, String> monthsMap = new HashMap<>();

    public ConstancyPDFGenerator() {
        initMonthsMap();
    }

    /**
     * Fills the PDF file (constancia.pdf) with the ticket values and returns base64 encoded string.
     */
    @Override
    public String generate(HashMap<String, String> formValues) {
        String originalPdf = PDFGenerator.RESOURCES_PATH + "constancia.pdf";
        try {
            PDDocument _pdfDocument = PDDocument.load(originalPdf);
            PDDocumentCatalog docCatalog = _pdfDocument.getDocumentCatalog();
            PDAcroForm acroForm = docCatalog.getAcroForm();

            //Set coordinator name using application-data.properties info.
            String coordinator = new String(coordinatorName.getBytes("ISO-8859-1"), "UTF-8");
            acroForm.getField("Coordinador1").setValue(coordinator);
            acroForm.getField("Coordinador2").setValue(coordinator);

            //Set some fields manually.
            Calendar cal = Calendar.getInstance();
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            String date = String.valueOf(day) + " de " + monthsMap.get(month) + " del año " + String.valueOf(year) + ".";
            acroForm.getField("Fecha").setValue(date);

            formValues.remove("Queue");
            formValues.remove("Motivo");
            formValues.remove("Requestors");

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

    /**
     * Initializes map for translating months from integer to spanish words.
     */
    private void initMonthsMap() {
        monthsMap.put(0, "enero");
        monthsMap.put(1, "febrero");
        monthsMap.put(2, "marzo");
        monthsMap.put(3, "abril");
        monthsMap.put(4, "mayo");
        monthsMap.put(5, "junio");
        monthsMap.put(6, "julio");
        monthsMap.put(7, "agosto");
        monthsMap.put(8, "setiembre");
        monthsMap.put(9, "octubre");
        monthsMap.put(10, "noviembre");
        monthsMap.put(11, "diciembre");
    }
}
