package cr.ac.siua.tec.services.impl;

import cr.ac.siua.tec.services.RTService;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RTServiceImpl implements RTService {

    @Value("${rt.pw}")
    private String rtPw;

    @Value("${rt.username}")
    private String rtUser;

    @Value("${rt.rest-base-url}")
    private String baseUrl;

    public HashMap<String, String> getTicket(String ticketId) {
        HashMap<String, String> ticketContent = null;
        String url = this.baseUrl + "/ticket/" + ticketId + "/show";

        try(CloseableHttpClient client = HttpClientBuilder.create().build()) {
            List<NameValuePair> urlParameters = new ArrayList<>();
            urlParameters.add(new BasicNameValuePair("user", this.rtUser));
            urlParameters.add(new BasicNameValuePair("pass", this.rtPw));
            StringBuilder requestUrl = new StringBuilder(url);
            String queryString = URLEncodedUtils.format(urlParameters, "utf-8");
            requestUrl.append("?");
            requestUrl.append(queryString);

            HttpGet get = new HttpGet(requestUrl.toString());
            HttpResponse response = client.execute(get);
            String responseString = getResponseString(response);
            ticketContent = ticketStringToHashMap(responseString);
            return ticketContent;
        }catch (IOException e) {
            System.out.println("Exception while trying to get RT Ticket using GET.");
            e.printStackTrace();
            return null;
        }
    }

    public HashMap<String, String> ticketStringToHashMap(String ticket) {
        HashMap<String, String> result = new HashMap<>();
        String[] pairs = ticket.split("\n(?!\\s{4})");
        // Adding queue value to HashMap
        String[] queue = pairs[3].split(": ");
        result.put(queue[0], queue[1]);
        // Adding requestor value to HashMap
        String[] requestor = pairs[11].split(": ");
        result.put(requestor[0], requestor[1]);
        // Adding custom fields to HashMap
        for(int i = 24; i < pairs.length; i++) {
            String[] keyValue = pairs[i].split(": ");
            if(keyValue.length == 2) result.put(keyValue[0].substring(4, keyValue[0].length()-1), keyValue[1]);
            else if(!keyValue[0].equals("")) result.put(keyValue[0].substring(4, keyValue[0].length()-1), "");
        }
        return result;
    }

    public int createTicket(HashMap<String, String> formValues) {
        String url = this.baseUrl + "/ticket/new";

        try(CloseableHttpClient client = HttpClientBuilder.create().build()) {
            HttpPost post = new HttpPost(url);
            List<NameValuePair> urlParameters = new ArrayList<>();
            urlParameters.add(new BasicNameValuePair("user", this.rtUser));
            urlParameters.add(new BasicNameValuePair("pass", this.rtPw));
            urlParameters.add(new BasicNameValuePair("content", getTicketParamsString(formValues)));

            post.setEntity(new UrlEncodedFormEntity(urlParameters, StandardCharsets.UTF_8));
            HttpResponse response = client.execute(post);
            System.out.println(getResponseString(response));
            return 1;
        }catch (IOException e) {
            System.out.println("Exception while trying to create RT Ticket using POST.");
            e.printStackTrace();
            return -1;
        }
    }

    private String getResponseString(HttpResponse response) throws IOException {
        System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line + "\n");
        }
        return result.toString();
    }

    public String getTicketParamsString(HashMap<String, String> formValues) {
        formValues.remove("g-recaptcha-response");
        StringBuilder sb = new StringBuilder();

        sb.append("Queue: " + formValues.get("Queue") + "\n");
        sb.append("Subject: Solicitado por " + formValues.get("RequestorName") + "\n");
        sb.append("Requestor: " + formValues.get("Requestor") + "\n");
        formValues.remove("Queue");
        formValues.remove("Requestor");
        formValues.remove("RequestorName");

        String fieldName, fieldValue;
        for(Map.Entry<String, String> entry : formValues.entrySet()) {
            fieldName = entry.getKey();
            fieldValue = entry.getValue();
            String lines[] = fieldValue.split("\n");
            if(lines.length > 1) {
                fieldValue = "";
                for(String line : lines) fieldValue += " " + line + "\n";
            }
            sb.append("CF-" + fieldName + ": " + fieldValue + "\n");
        }
        return sb.toString();
    }
}
