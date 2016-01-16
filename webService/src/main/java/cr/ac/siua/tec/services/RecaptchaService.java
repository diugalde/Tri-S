package cr.ac.siua.tec.services;

public interface RecaptchaService {

    boolean isResponseValid(String remoteIp, String response);

}
