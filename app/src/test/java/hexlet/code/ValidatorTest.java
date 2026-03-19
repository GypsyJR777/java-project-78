package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidatorTest {

    @Test
    void testStringSchemaWithoutRequired() {
        var validator = new Validator();
        var schema = validator.string();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid("hexlet"));
    }

    @Test
    void testRequiredRule() {
        var validator = new Validator();
        var schema = validator.string().required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("what does the fox say"));
        assertTrue(schema.isValid("hexlet"));
    }

    @Test
    void testMinLengthRule() {
        var validator = new Validator();
        var schema = validator.string().minLength(5);

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
        assertFalse(schema.isValid("java"));
        assertTrue(schema.isValid("hello"));
        assertTrue(schema.isValid("hexlet"));
    }

    @Test
    void testContainsRule() {
        var validator = new Validator();
        var schema = validator.string().contains("what");

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid("what does the fox say"));
        assertFalse(schema.isValid("hexlet"));
    }

    @Test
    void testCombinedRules() {
        var validator = new Validator();
        var schema = validator.string().required().minLength(5).contains("hex");

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid("java"));
        assertFalse(schema.isValid("hello"));
        assertTrue(schema.isValid("hexlet"));
    }

    @Test
    void testContainsAccumulatesForSchemaState() {
        var validator = new Validator();
        var schema = validator.string().required();

        assertTrue(schema.contains("wh").isValid("what does the fox say"));
        assertTrue(schema.contains("what").isValid("what does the fox say"));
        assertFalse(schema.contains("whatthe").isValid("what does the fox say"));
        assertFalse(schema.isValid("what does the fox say"));
    }

    @Test
    void testLastMinLengthHasPriority() {
        var validator = new Validator();
        var schema = validator.string();

        assertTrue(schema.minLength(10).minLength(4).isValid("Hexlet"));
    }

    @Test
    void testLastContainsHasPriority() {
        var validator = new Validator();
        var schema = validator.string();

        assertFalse(schema.contains("what").contains("foxes").isValid("what does the fox say"));
    }
}
