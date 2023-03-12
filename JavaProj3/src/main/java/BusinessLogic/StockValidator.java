package BusinessLogic;

import Model.Product;

/**
 * Checks if the stock of a product is greater than 0
 */

public class StockValidator implements Validator<Product>{
    @Override
    public void validate(Product product) throws IllegalArgumentException {
        if (product.getStock() <= 0)
            throw new IllegalArgumentException("Invalid stock");
    }
}
