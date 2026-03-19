package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

/**
 * Entry point for creating validation schemas.
 */
public final class Validator {

    /**
     * Creates a schema for validating strings.
     *
     * @return string schema
     */
    public StringSchema string() {
        return new StringSchema();
    }

    /**
     * Creates a schema for validating numbers.
     *
     * @return number schema
     */
    public NumberSchema number() {
        return new NumberSchema();
    }

    /**
     * Creates a schema for validating maps.
     *
     * @return map schema
     */
    public MapSchema map() {
        return new MapSchema();
    }
}
