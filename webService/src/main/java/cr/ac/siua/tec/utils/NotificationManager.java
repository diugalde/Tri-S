package cr.ac.siua.tec.utils;


import java.util.HashMap;
import java.util.Map;

public final class NotificationManager {

    public static Map<String, String> getValidFormMsg() {
        Map<String, String> map = new HashMap<>();
        map.put("type", "success");
        map.put("msg", "El formulario ha sido enviado exitosamente. Se envió un correo a su cuenta.");
        return map;
    }

    public static Map<String, String> getInvalidFormMsg() {
        Map<String, String> map = new HashMap<>();
        map.put("type", "error");
        map.put("msg", "Error al procesar el formulario. Revise los campos llenados.");
        return map;
    }

}
