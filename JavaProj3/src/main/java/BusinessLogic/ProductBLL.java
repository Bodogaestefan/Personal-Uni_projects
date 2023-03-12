package BusinessLogic;

import DataAccess.ProductDAO;
import Model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * This Class contains the methods for applying validators and calling DAO methods on products
 * <p>
 *     This class has methods for updating and inserting products and applying validators
 * </p>
 */

public class ProductBLL {
    private List<Validator<Product>> validators;
    private ProductDAO productDAO;

    public ProductBLL() {
        validators = new ArrayList<Validator<Product>>();
        validators.add(new PriceValidator());
        validators.add(new StockValidator());

        productDAO = new ProductDAO();
    }

    public Product insert(Product c) throws Exception {
        for (Validator<Product> validator : validators) {
            validator.validate(c);
        }
        productDAO.insert(c);

        return c;
    }

    public Product update(Product c) throws Exception {
        for (Validator<Product> validator : validators) {
            validator.validate(c);
        }
        productDAO.update(c);

        return c;
    }
}
