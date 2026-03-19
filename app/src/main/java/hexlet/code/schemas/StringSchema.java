package hexlet.code.schemas;

/**
 * Schema for validating string values.
 */
public final class StringSchema extends BaseSchema<String> {

    /**
     * Makes a string required.
     *
     * @return current schema
     */
    public StringSchema required() {
        addRule("required", value -> value != null && !value.isEmpty());
        return this;
    }

    /**
     * Sets the minimum allowed string length.
     *
     * @param length minimum length
     * @return current schema
     */
    public StringSchema minLength(final int length) {
        addRule("minLength", value -> value == null || value.isEmpty() || value.length() >= length);
        return this;
    }

    /**
     * Requires a string to contain the specified substring.
     *
     * @param substring required substring
     * @return current schema
     */
    public StringSchema contains(final String substring) {
        addRule("contains", value -> value == null || value.isEmpty() || value.contains(substring));
        return this;
    }
}
