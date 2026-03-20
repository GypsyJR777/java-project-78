package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Schema for validating map values.
 */
public final class MapSchema extends BaseSchema<Map<?, ?>> {
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
        var shapeSchemas = new LinkedHashMap<>(schemas);
        addRule("shape", value -> value == null || shapeSchemas.entrySet().stream().allMatch(entry -> {
            try {
                return ((BaseSchema) entry.getValue()).isValid(value.get(entry.getKey()));
            } catch (ClassCastException e) {
                return false;
            }
        }));
        return this;
    }
}
