package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapSchemaTest {

    @Test
    void testMapSchemaWithoutRequired() {
        var validator = new Validator();
        var schema = validator.map();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
    }

    @Test
    void testRequiredRule() {
        var validator = new Validator();
        var schema = validator.map().required();
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
        assertTrue(schema.isValid(data));
    }

    @Test
    void testSizeofRule() {
        var validator = new Validator();
        var schema = validator.map().sizeof(2);
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid(data));

        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }

    @Test
    void testCombinedRules() {
        var validator = new Validator();
        var schema = validator.map().required().sizeof(2);
        Map<String, String> data = new HashMap<>();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(data));

        data.put("key1", "value1");
        assertFalse(schema.isValid(data));

        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }

    @Test
    void testShapeWithStringSchemas() {
        var validator = new Validator();
        var schema = validator.map();
        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));
        schema.shape(schemas);

        Map<String, String> validData = new HashMap<>();
        validData.put("firstName", "John");
        validData.put("lastName", "Smith");

        Map<String, String> nullLastName = new HashMap<>();
        nullLastName.put("firstName", "John");
        nullLastName.put("lastName", null);

        Map<String, String> shortLastName = new HashMap<>();
        shortLastName.put("firstName", "Anna");
        shortLastName.put("lastName", "B");

        assertTrue(schema.isValid(validData));
        assertFalse(schema.isValid(nullLastName));
        assertFalse(schema.isValid(shortLastName));
    }

    @Test
    void testShapeWithMixedSchemas() {
        var validator = new Validator();
        var schema = validator.map();
        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("name", validator.string().required().minLength(3));
        schemas.put("age", validator.number().required().positive().range(18, 60));
        schema.shape(schemas);

        Map<String, Object> validData = new HashMap<>();
        validData.put("name", "Helen");
        validData.put("age", 32);

        Map<String, Object> invalidAge = new HashMap<>();
        invalidAge.put("name", "Helen");
        invalidAge.put("age", 12);

        Map<String, Object> missingName = new HashMap<>();
        missingName.put("age", 32);

        Map<String, Object> wrongType = new HashMap<>();
        wrongType.put("name", "Helen");
        wrongType.put("age", "32");

        assertTrue(schema.isValid(validData));
        assertFalse(schema.isValid(invalidAge));
        assertFalse(schema.isValid(missingName));
        assertFalse(schema.isValid(wrongType));
    }
}
