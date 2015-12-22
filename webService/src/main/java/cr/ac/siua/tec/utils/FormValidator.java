package cr.ac.siua.tec.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class FormValidator {

    private Map<String, String> fieldTypesMap;
    private Map<String, Function> methodsMap;

    public FormValidator() {
        initMethodsMap();
        initFieldTypesMap();
    }

    private void initFieldTypesMap() {
        fieldTypesMap = new HashMap<>();
        HashMap<String, String[]> validationCategoryMap = (HashMap) getValidationTypeMap();
        for(Map.Entry<String, String[]> entry : validationCategoryMap.entrySet()) {
            String key = entry.getKey();
            String[] fields = entry.getValue();
            for(String field : fields) fieldTypesMap.put(field, key);
        }
    }

    private void initMethodsMap() {
        methodsMap = new HashMap<>();
        methodsMap.put("email", (field) -> isValidEmailAddress((String)field));
        methodsMap.put("boolean", (field) -> isValidBooleanField((String)field));
        methodsMap.put("alphanumeric", (field) -> isValidAlphanumericField((String)field));
        methodsMap.put("numeric", (field) -> isValidNumericField((String)field));
        methodsMap.put("string", (field) -> isValidStringField((String) field));
        methodsMap.put("date", (field) -> isValidDateField((String) field));
        methodsMap.put("time", (field) -> isValidTimeField((String)field));
    }


    public boolean isValidForm(HashMap<String, String> map) {
        boolean validationResult = true;
        boolean fieldValidation;
        String fieldName, fieldValue;
        for(Map.Entry<String, String> entry : map.entrySet()) {
            fieldName = entry.getKey();
            fieldValue = entry.getValue();
            fieldValidation = (Boolean) methodsMap.get(fieldTypesMap.get(fieldName)).apply(fieldValue);
            System.out.println("Validacion para " + fieldName + ":" + fieldValue + "  ->  " + fieldValidation);
            validationResult &= fieldValidation;
        }
        return validationResult;
    }

    private boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    private boolean isValidStringField(String field) {
        String pattern = "^[\\p{L} .'-]+$";
        return field.matches(pattern);
    }

    private boolean isValidDateField(String field) {
        String pattern1 = "([0-9]{2})-([0-9]{2})-([0-9]{4})";
        String pattern2 = "([0-9]{2})/([0-9]{2})/([0-9]{4})";
        return field.matches(pattern1) || field.matches(pattern2);
    }

    private boolean isValidTimeField(String field) {
        String pattern1 = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
        String pattern2 = "(1[012]|0[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)";
        return field.matches(pattern1) || field.matches(pattern2);
    }

    private boolean isValidAlphanumericField(String field) {
        String pattern = "^[a-zA-Z0-9]*$";
        return field.matches(pattern);
    }

    private boolean isValidNumericField(String field) {
        String pattern = "[0-9]+";
        return field.matches(pattern);
    }

    private boolean isValidBooleanField(String field) {
        return field.equalsIgnoreCase("true") || field.equalsIgnoreCase("false");
    }

    private Map<String, String[]> getValidationTypeMap() {
        Map<String, String[]> map = new HashMap<>();
        map.put("email", new String[] {"email"});
        map.put("boolean", new String[] {"fulfillRequisites", "hasTimeConflict"});
        map.put("alphanumeric", new String[] {"labId", "deviceId", "courseCode", "workstationId", "justification",
                "detail", "motive", "assistanceActivities"});
        map.put("numeric", new String[] {"studentId", "telephone", "cellphone", "courseGroup", "rn", "id",
                "courseScore", "studentAverageScore", "assistanceTotalHours", "clientAccountNumber"});
        map.put("string", new String[] {"studentName", "career", "enrollDay", "enrollTime", "courseName", "professorName",
                "enrolledCourses", "campus", "requisiteCourses", "requisiteType", "assistanceType", "officialName", "bankName"});
        map.put("date", new String[] {"enrollDay", "useDate"});
        map.put("time", new String[] {"enrollTime", "initialUseTime", "finalUseTime"});
        return map;
    }
}
