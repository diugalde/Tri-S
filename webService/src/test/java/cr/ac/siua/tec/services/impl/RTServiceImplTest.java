package cr.ac.siua.tec.services.impl;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashMap;


public class RTServiceImplTest extends TestCase {

    private RTServiceImpl rtService = new RTServiceImpl();

    @Test
    public void testCreateTicket() throws Exception {
        HashMap <String, String> form = new HashMap<>();
        form.put("g-recaptcha-response","");
        form.put("Carné", "2013");
        form.put("Nombre del estudiante", "Luis");
        form.put("Motivo", "prueba");
        form.put("Cédula", "2020");
        form.put("Requestor", "luis@gmail.com");
        form.put("RequestorName", "Luis");
        form.put("Queue", "Constancias de estudio");
        int result = rtService.createTicket(form);

        assertEquals(-1, result);
    }

    @Test
    public void testGetTicketParamsString() throws Exception {
        HashMap <String, String> form = new HashMap<>();
        form.put("g-recaptcha-response","");
        form.put("Carné", "2013");
        form.put("Nombre del estudiante", "Luis");
        form.put("Motivo", "prueba");
        form.put("Cédula", "2020");
        form.put("Requestor", "luis@gmail.com");
        form.put("RequestorName", "Luis");
        form.put("Queue", "Constancias de estudio");

        String expectedResult = "Queue: Constancias de estudio\nSubject: Solicitado por Luis\nRequestor: luis@gmail.com" +
                "\nCF-Carné: 2013\nCF-Nombre del estudiante: Luis\nCF-Motivo: prueba\nCF-Cédula: 2020";
        String result = rtService.getTicketParamsString(form);
        assertTrue(result.trim().equals(expectedResult));
    }
}