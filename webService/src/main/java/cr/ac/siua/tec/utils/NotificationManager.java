/*
	TRI-S - Web Service
	Developed by: Luis E. Ugalde Barrantes - Diego Ugalde Ávila. 2016.
	This code is licensed under the GNU GENERAL PUBLIC LICENSE (GPL) V3. See LICENSE file for details.
*/

package cr.ac.siua.tec.utils;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class NotificationManager {

    /**
     * Returns hashmap with notification message when the ticket was succesfully created.
     */
    public static Map<String, String> getValidFormMsg() {
        Map<String, String> map = new HashMap<>();
        map.put("type", "success");
        map.put("msg", "El formulario ha sido enviado exitosamente. Se envió un correo a su cuenta.");
        return map;
    }

    /**
     * Returns hashmap with notification message and wrong fields when the form was not correctly filled.
     */
    public static Map<String, String> getInvalidFormMsg(List<String> wrongFields) {
        Map<String, String> map = new HashMap<>();
        map.put("type", "error");
        String wrongFieldsString = "";
        for(String field : wrongFields) wrongFieldsString += field + ",";
        map.put("wrongFields", wrongFieldsString);
        map.put("msg", "Error al procesar el formulario. Revise los campos llenados.");
        return map;
    }

    /**
     * Returns hashmap with notification message when the recaptcha response was invalid.
     */
    public static Map<String, String> getInvalidCaptchaMsg() {
        Map<String, String> map = new HashMap<>();
        map.put("type", "error");
        map.put("msg", "El captcha es inválido.");
        return map;
    }

    /**
     * Returns hashmap with notification message when communicating with RT was unsuccesful.
     */
    public static Map<String, String> getRTCrashedMsg() {
        Map<String, String> map = new HashMap<>();
        map.put("type", "error");
        map.put("msg", "Hubo un problema con el servidor. Inténtelo más tarde.");
        return map;
    }
}
