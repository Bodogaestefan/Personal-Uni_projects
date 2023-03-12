import model.Monome;
import model.Operations;
import model.Polynome;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import model.Operations;
import model.Monome;
import model.Polynome;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class DerivateOperationTest {
    @Test
    public void integrationTest() {
        ArrayList<Monome> monomes = new ArrayList<Monome>();
        monomes.add(new Monome(2,2,'-'));
        monomes.add(new Monome(3,1,'-'));
        monomes.add(new Monome(2,0,'-'));
        Polynome p1 = new Polynome(monomes);
        Polynome p3;
        p3 = Operations.Derivate(p1);
        System.out.println(Operations.printP(p3));
    }


}
