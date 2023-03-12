package BusinessLogic;

import DataAccess.ClientDAO;
import Model.Client;

/**
 * Applies the Age validator and calls the insert method from the ClientDAO class
 */

public class ClientBLL {
    private ClientDAO clientDAO;
    public ClientBLL(){
        clientDAO = new ClientDAO();
    }
    public Client insert(Client c) throws Exception {
        AgeValidator agev = new AgeValidator();
        agev.validate(c);
        clientDAO.insert(c);
        return c;
    }
}
