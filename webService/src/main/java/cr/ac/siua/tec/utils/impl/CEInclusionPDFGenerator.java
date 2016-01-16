package cr.ac.siua.tec.utils.impl;

import cr.ac.siua.tec.utils.PDFGenerator;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component("CEInclusion")
public class CEInclusionPDFGenerator extends PDFGenerator{

    @Override
    public String generate(HashMap<String, String> formValues) {
        return null;
    }
}
