package cr.ac.siua.tec.utils.impl;

import cr.ac.siua.tec.utils.PDFGenerator;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component("Assistance")
public class AssistancePDFGenerator extends PDFGenerator {

    @Override
    public String generate(HashMap<String, String> formValues) {
        return "Assistance";
    }
}
