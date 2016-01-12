package cr.ac.siua.tec.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class FormValidator {

    private Map<String, String> fieldTypesMap;
    private Map<String, Function<String, Boolean>> methodsMap;

    public FormValidator() {
        initMethodsMap();
        initFieldTypesMap();
    }

    private void initFieldTypesMap() {
        fieldTypesMap = new HashMap<>();
        HashMap<String, String[]> validationCategoryMap = (HashMap<String, String[]>) getValidationTypeMap();
        for(Map.Entry<String, String[]> entry : validationCategoryMap.entrySet()) {
            String key = entry.getKey();
            String[] fields = entry.getValue();
            for(String field : fields) fieldTypesMap.put(field, key);
        }
    }

    private void initMethodsMap() {
        methodsMap = new HashMap<>();
        methodsMap.put("email", (field) -> isValidEmailAddress((String)field));
        methodsMap.put("boolean", (field) -> isValidBooleanField((String) field));
        methodsMap.put("alphanumeric", (field) -> isValidAlphanumericField((String) field));
        methodsMap.put("numeric", (field) -> isValidNumericField((String) field));
        methodsMap.put("string", (field) -> isValidStringField((String) field));
        methodsMap.put("date", (field) -> isValidDateField((String) field));
        methodsMap.put("time", (field) -> isValidTimeField((String) field));
        methodsMap.put("any", (field) -> isValidAnyField((String) field));
        methodsMap.put("phone", (field) -> isValidPhoneField((String) field));
    }


    public boolean isValidForm(HashMap<String, String> map) {
        boolean validationResult = true;
        boolean fieldValidation;
        String fieldName, fieldValue, fieldType;
        for(Map.Entry<String, String> entry : map.entrySet()) {
            fieldName = entry.getKey();
            fieldValue = entry.getValue();
            fieldType = fieldTypesMap.get(fieldName);
            fieldValidation = true;
            if(fieldType != null) {
                fieldValidation = (Boolean) methodsMap.get(fieldType).apply(fieldValue);
                System.out.println("Validacion para " + fieldName + ":" + fieldValue + "  ->  " + fieldValidation);
            }
            validationResult = validationResult && fieldValidation;
        }
        System.out.println("____________________________________________\n");
        return validationResult;
    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public boolean isValidStringField(String field) {
        String pattern = "^[\\p{L} .'-]+$";
        return field.matches(pattern);
    }

    public boolean isValidAnyField(String field) {
        return !field.equals("");
    }

    public boolean isValidDateField(String field) {
        String pattern1 = "([0-9]{2})-([0-9]{2})-([0-9]{4})";
        String pattern2 = "([0-9]{2})/([0-9]{2})/([0-9]{4})";
        return field.matches(pattern1) || field.matches(pattern2);
    }

    public boolean isValidTimeField(String field) {
        String pattern1 = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
        String pattern2 = "(1[012]|0[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)";
        return field.matches(pattern1) || field.matches(pattern2);
    }

    public boolean isValidAlphanumericField(String field) {
        String pattern = "^[a-zA-Z0-9]*$";
        return field.matches(pattern);
    }

    public boolean isValidNumericField(String field) {
        String pattern = "[0-9]+";
        return field.matches(pattern) || field.equals("");
    }

    public boolean isValidPhoneField(String field) {
        String pattern = "[+]?[0-9-]+";
        return field.matches(pattern) || field.equals("");
    }

    public boolean isValidBooleanField(String field) {
        return field.equalsIgnoreCase("true") || field.equalsIgnoreCase("false")
                || field.equalsIgnoreCase("Si") || field.equalsIgnoreCase("No") || field.equalsIgnoreCase("Sí");
    }

    private Map<String, String[]> getValidationTypeMap() {
        Map<String, String[]> map = new HashMap<>();
        map.put("email", new String[] {"Requestor"});
        map.put("boolean", new String[] {"Cumple con los requisitos", "Tiene choque de horario"});
        map.put("alphanumeric", new String[] {"Identificador del laboratorio", "Identificador del equipo",
                "Código del curso", "Identificador del espacio de trabajo"});
        map.put("numeric", new String[] {"Carné", "Número de grupo", "RN", "Cédula",
                "Nota del curso", "Promedio ponderado", "Total de horas", "Número de cuenta cliente"});
        map.put("string", new String[] {"Nombre del estudiante", "Carrera", "Nombre del curso",
                "Nombre del profesor", "Sede", "Tipo de levantamiento", "Tipo de asistencia", "Nombre del funcionario", "Banco"});
        map.put("date", new String[] {"Día de matrícula", "Fecha de uso"});
        map.put("time", new String[] {"Hora de matrícula", "Hora inicial", "Hora final"});
        map.put("any", new String[] {"Justificación", "Detalle", "Motivo", "Actividades que realizará el asistente",
                "Cursos requisito", "Cursos matriculados"});
        map.put("phone", new String[] {"Número de celular", "Número de teléfono"});
        return map;
    }
}
