package DataAccess;

import Model.Client;
import Model.Product;
import Presentation.TableView;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

/**
 *  Extends the AbstractDAO class and overides its methods to make them usable specific for the Product type objects
 */

public class ProductDAO extends AbstractDAO<Product>{
    public ProductDAO() {
        super();
    }

   /* @Override
    public List<Product> findAll() {
        return super.findAll();
    }

    @Override
    public Product findById(int id) {
        return super.findById(id);
    }

    /*@Override
    public Product insert(Product product) {
        return super.insert(product);
    }*/

   /* @Override
    public Product update(Product product) {
        return super.update(product);
    }

    @Override
    public void delete(Product product) {
        super.delete(product);
    }
*/
    /**
     * Creates a JTable filled with a list of all the products in the DB
     * @param tableView
     * @throws SQLException
     */
    public void viewAllProducts(TableView tableView) throws SQLException {

        List<Product> productList = findAll();

        JScrollPane myScrollPane = new JScrollPane();
        myScrollPane.setBounds(20, 80, 600, 400);

        JTable clientTable = new JTable();
        clientTable=createTable(productList);
        clientTable.setEnabled(true);
        clientTable.setVisible(true);

        myScrollPane.setViewportView(clientTable);
        try {
            tableView.getTableView().add(myScrollPane);
        }catch(Exception e){}
    }
}
