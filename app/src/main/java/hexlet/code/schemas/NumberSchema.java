package hexlet.code.schemas;

import java.util.Objects;

/**
 * Schema for validating integer values.
 */
public final class NumberSchema extends BaseSchema<Integer> {

    /**
     * Makes a number required.
     *
     * @return current schema
     */
    public NumberSchema required() {
        addRule("required", Objects::nonNull);
        return this;
    }

    /**
     * Requires a number to be positive.
     *
     * @return current schema
     */
    public NumberSchema positive() {
        addRule("positive", value -> value == null || value > 0);
        return this;
    }

    /**
     * Restricts a number to an inclusive range.
     *
     * @param min minimum value
     * @param max maximum value
     * @return current schema
     */
    public NumberSchema range(final int min, final int max) {
        addRule("range", value -> value == null || value >= min && value <= max);
        return this;
    }
}
