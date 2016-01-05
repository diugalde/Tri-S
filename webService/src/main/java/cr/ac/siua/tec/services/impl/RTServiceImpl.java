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

            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            urlParameters.add(new BasicNameValuePair("user", this.rtUser));
            urlParameters.add(new BasicNameValuePair("pass", this.rtPw));
            urlParameters.add(new BasicNameValuePair("content", "Queue: Reporte de daños\nSubject: Computadora mala\nCF-Carné: 2323232232321111\n"));

            post.setEntity(new UrlEncodedFormEntity(urlParameters, HTTP.UTF_8));

            HttpResponse response = client.execute(post);

            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + post.getEntity());
            System.out.println("Response Code : " +
                    response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            System.out.println(result.toString());

        }catch (IOException e) {
            System.out.println("Excepcion al hacer el POST a RT.");
            e.printStackTrace();
            return -1;
        }
        return 1;
    }
}
