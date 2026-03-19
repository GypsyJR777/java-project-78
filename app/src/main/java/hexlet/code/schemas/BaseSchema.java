package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Base class for validation schemas.
 *
 * @param <T> type of value being validated
 */
public abstract class BaseSchema<T> {
    private final Map<String, Predicate<T>> rules = new LinkedHashMap<>();

    /**
     * Checks whether a value matches all configured rules.
     *
     * @param value value to validate
     * @return validation result
     */
    public final boolean isValid(final T value) {
        return rules.values().stream().allMatch(rule -> rule.test(value));
    }

    /**
     * Registers or replaces a validation rule by name.
     *
     * @param name rule identifier
     * @param rule validation predicate
     */
    protected final void addRule(final String name, final Predicate<T> rule) {
        rules.put(name, rule);
    }
}
