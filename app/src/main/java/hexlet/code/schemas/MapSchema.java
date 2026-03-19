package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Schema for validating map values.
 */
public final class MapSchema extends BaseSchema<Map<?, ?>> {
    private Map<String, BaseSchema<?>> schemaByKey = new LinkedHashMap<>();

    /**
     * Makes a map required.
     *
     * @return current schema
     */
    public MapSchema required() {
        addRule("required", Objects::nonNull);
        return this;
    }

    /**
     * Restricts a map to the specified size.
     *
     * @param size expected number of entries
     * @return current schema
     */
    public MapSchema sizeof(final int size) {
        addRule("sizeof", value -> value == null || value.size() == size);
        return this;
    }

    /**
     * Defines nested validation schemas for map keys.
     *
     * @param schemas schemas for map values by key
     * @return current schema
     */
    public MapSchema shape(final Map<String, ? extends BaseSchema<?>> schemas) {
        schemaByKey = new LinkedHashMap<>(schemas);
        addRule("shape", value -> value == null || schemaByKey.entrySet().stream()
                .allMatch(entry -> isValidBySchema(entry.getValue(), value.get(entry.getKey()))));
        return this;
    }

    private static boolean isValidBySchema(final BaseSchema<?> schema, final Object value) {
        return isValidBySchemaTyped(schema, value);
    }

    private static <T> boolean isValidBySchemaTyped(final BaseSchema<T> schema, final Object value) {
        try {
            T typedValue = (T) value;
            return schema.isValid(typedValue);
        } catch (ClassCastException e) {
            return false;
        }
    }
}
