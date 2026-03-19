package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberSchemaTest {

    @Test
    void testNumberSchemaWithoutRequired() {
        var validator = new Validator();
        var schema = validator.number();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(5));
        assertTrue(schema.positive().isValid(null));
    }

    @Test
    void testRequiredRule() {
        var validator = new Validator();
        var schema = validator.number().required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(10));
        assertTrue(schema.isValid(-10));
        assertTrue(schema.isValid(0));
    }

    @Test
    void testPositiveRule() {
        var validator = new Validator();
        var schema = validator.number().positive();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));
    }

    @Test
    void testRangeRule() {
        var validator = new Validator();
        var schema = validator.number().range(5, 10);

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }

    @Test
    void testCombinedRules() {
        var validator = new Validator();
        var schema = validator.number().required().positive().range(5, 10);

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(4));
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(11));
    }
}
