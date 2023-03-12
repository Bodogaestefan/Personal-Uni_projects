package BusinessLogic;
import Model.Product;

import java.util.regex.Pattern;

/**
 * Makes sure that the product to be inserted has a price greater than 0
 */

public class PriceValidator implements Validator<Product>{
    private static final String PHONE_PATTERN = "\\d{10}";
    @Override
    public void validate(Product product) throws IllegalArgumentException {
        if (product.getPrice() <= 0)
            throw new IllegalArgumentException("Invalid price");
    }
}
