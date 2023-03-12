
import org.junit.jupiter.api.Test;

import model.Operations;
import model.Monome;
import model.Polynome;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class DivideOperationTest {
    @Test
    public void divisionTest() {
        ArrayList<Monome> monomes1 = new ArrayList<Monome>();
        ArrayList<Monome> monomes2 = new ArrayList<Monome>();
        monomes1.add(new Monome(2,2,'-'));
        monomes1.add(new Monome(2,1,'-'));
        monomes2.add(new Monome(1,2,'-'));
        monomes2.add(new Monome(1,1,'-'));
        Polynome p1 = new Polynome(monomes1);
        Polynome p2 = new Polynome(monomes2);
        Polynome p3;
        p3 = Operations.Divide(p1,p2);
        System.out.println(Operations.printP(p3));


    }


}
