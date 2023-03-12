package BusinessLogic;

/**
 * The interface for client and product validators
 * @param <T>
 */

public interface Validator<T> {

    void validate(T t) throws Exception;
}
