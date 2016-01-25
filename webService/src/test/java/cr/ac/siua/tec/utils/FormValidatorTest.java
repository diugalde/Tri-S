package cr.ac.siua.tec.utils;

import org.junit.Test;

import javax.validation.constraints.AssertTrue;
import java.util.HashMap;

import static org.junit.Assert.*;

public class FormValidatorTest {

    private FormValidator formValidator = new FormValidator();

    @Test
    public void testIsValidEmailAddress() throws Exception {
        boolean result = formValidator.isValidEmailAddress("test@email.com");
        assertTrue(result);
    }

    @Test
    public void testIsInvalidEmailAddress() throws Exception {
        boolean result = formValidator.isValidEmailAddress("test.email.com");
        assertFalse(result);
    }

    @Test
    public void testIsValidStringField() throws Exception {
        boolean result = formValidator.isValidStringField("Esto es una prueba.");
        assertTrue(result);
    }

    @Test
    public void testIsInvalidStringField() throws Exception {
        boolean result = formValidator.isValidStringField("2013");
        assertFalse(result);
    }

    @Test
    public void testIsValidAnyField() throws Exception {
        boolean result = formValidator.isValidAnyField("Esto es una prueba... 123");
        assertTrue(result);
    }

    @Test
    public void testIsInvalidAnyField() throws Exception {
        boolean result = formValidator.isValidAnyField("");
        assertFalse(result);
    }

    @Test
    public void testIsValidDateField() throws Exception {
        boolean result = formValidator.isValidDateField("08/01/2016");
        assertTrue(result);
    }

    @Test
    public void testIsInvalidDateField() throws Exception {
        boolean result = formValidator.isValidDateField("8/Ene/2016");
        assertFalse(result);
    }

    @Test
    public void testIsValidTimeField() throws Exception {
        boolean result = formValidator.isValidTimeField("05:10");
        assertTrue(result);
    }

    @Test
    public void testIsInvalidTimeField() throws Exception {
        boolean result = formValidator.isValidTimeField("64:64");
        assertFalse(result);
    }

    @Test
    public void testIsValidAlphanumericField() throws Exception {
        boolean result = formValidator.isValidAlphanumericField("CA0213");
        assertTrue(result);
    }

    @Test
    public void testIsInvalidAlphanumericField() throws Exception {
        boolean result = formValidator.isValidAlphanumericField("abc-123");
        assertFalse(result);
    }

    @Test
    public void testIsValidNumericField() throws Exception {
        boolean result = formValidator.isValidNumericField("1234567890");
        assertTrue(result);
    }

    @Test
    public void testIsInvalidNumericField() throws Exception {
        boolean result = formValidator.isValidNumericField("ACDG56");
        assertFalse(result);
    }

    @Test
    public void testIsValidBooleanField() throws Exception {
        boolean result = formValidator.isValidBooleanField("Si");
        assertTrue(result);
    }

    @Test
    public void testIsInvalidBooleanField() throws Exception {
        boolean result = formValidator.isValidBooleanField("prueba");
        assertFalse(result);
    }
}