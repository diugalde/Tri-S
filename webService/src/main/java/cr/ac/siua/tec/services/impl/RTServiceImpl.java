package cr.ac.siua.tec.services.impl;


import cr.ac.siua.tec.services.RTService;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public int createTicket(HashMap<String, String> formValues) {

        String url = this.baseUrl + "/ticket/new";

        try(CloseableHttpClient client = HttpClientBuilder.create().build()) {

            HttpPost post = new HttpPost(url);

            List<NameValuePair> urlParameters = new ArrayList<>();
            urlParameters.add(new BasicNameValuePair("user", this.rtUser));
            urlParameters.add(new BasicNameValuePair("pass", this.rtPw));
            urlParameters.add(new BasicNameValuePair("content", getTicketParamsString(formValues)));

            post.setEntity(new UrlEncodedFormEntity(urlParameters, HTTP.UTF_8));

            HttpResponse response = client.execute(post);

            printResponse(response);

            return 1;

        }catch (IOException e) {
            System.out.println("Excepcion al hacer el POST a RT.");
            e.printStackTrace();
            return -1;
        }
    }

    private void printResponse(HttpResponse response) throws IOException {
        System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        System.out.println(result.toString());
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
            sb.append("CF-" + fieldName + ": " + fieldValue + "\n");
        }
        return sb.toString();
    }
}
