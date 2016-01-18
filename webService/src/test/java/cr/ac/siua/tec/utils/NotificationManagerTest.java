package cr.ac.siua.tec.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static cr.ac.siua.tec.utils.NotificationManager.getInvalidFormMsg;
import static cr.ac.siua.tec.utils.NotificationManager.getValidFormMsg;
import static cr.ac.siua.tec.utils.NotificationManager.getInvalidCaptchaMsg;
import static cr.ac.siua.tec.utils.NotificationManager.getRTCrashedMsg;
import static org.junit.Assert.*;

public class NotificationManagerTest {

    @Test
    public void testGetValidFormMsg() throws Exception {
        Map<String, String> result = getValidFormMsg();
        assertEquals(result.get("type"), "success");
        assertEquals(result.get("msg"), "El formulario ha sido enviado exitosamente. Se envió un correo a su cuenta.");
    }

    @Test
    public void testGetInvalidFormMsg() throws Exception {
        List<String> list = new ArrayList<>();
        Map<String, String> result = getInvalidFormMsg(list);
        assertEquals(result.get("type"), "error");
        assertEquals(result.get("msg"), "Error al procesar el formulario. Revise los campos llenados.");
    }

    @Test
    public void testGetInvalidCaptchaMsg() throws Exception {
        Map<String, String> result = getInvalidCaptchaMsg();
        assertEquals(result.get("type"), "error");
        assertEquals(result.get("msg"), "El captcha es inválido.");
    }

    @Test
    public void testGetRTCrashedMsg() throws Exception {
        Map<String, String> result = getRTCrashedMsg();
        assertEquals(result.get("type"), "error");
        assertEquals(result.get("msg"), "Hubo un problema con el servidor. Inténtelo más tarde.");
    }
}