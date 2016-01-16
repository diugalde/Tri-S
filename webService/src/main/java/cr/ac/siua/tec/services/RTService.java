package cr.ac.siua.tec.services;

import java.util.HashMap;

public interface RTService {

    int createTicket(HashMap<String, String> formValues);

    HashMap<String, String> getTicket(String ticketId);

}
