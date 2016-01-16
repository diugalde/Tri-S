package cr.ac.siua.tec.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public abstract class PDFGenerator {

    public static final String RESOURCES_PATH = "src/main/resources/pdfTemplates/";

    public abstract String generate(HashMap<String, String> formValues);

    public String encodePDF(PDDocument document) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            document.save(out);
            document.close();
        } catch (COSVisitorException | IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = out.toByteArray();
        byte[] encoded = Base64.encodeBase64(bytes);
        return new String(encoded);
    }

}
