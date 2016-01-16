package cr.ac.siua.tec.services;


import java.util.HashMap;

public interface ExportService {

    String getPDF(HashMap<String, String> ticketContent);

}
