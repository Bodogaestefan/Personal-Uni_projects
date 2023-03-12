package BusinessLogic;

import Model.Client;

/**
 * Makes sure that the age of an inserted client is greater than 0
 */

public class AgeValidator implements BusinessLogic.Validator<Client> {

    @Override
    public void validate(Client client) throws Exception {
        if (client.getAge() <= 0)
            throw new Exception("Please provide a valid age");
    }
}
