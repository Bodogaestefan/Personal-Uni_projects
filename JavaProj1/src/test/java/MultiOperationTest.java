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

public class MultiOperationTest {
    @Test
    public void multiTest() {
        ArrayList<Monome> monomes1 = new ArrayList<Monome>();
        ArrayList<Monome> monomes2 = new ArrayList<Monome>();
        monomes1.add(new Monome(2,2,'-'));
        monomes1.add(new Monome(3,1,'-'));
        monomes1.add(new Monome(2,0,'-'));
        monomes2.add(new Monome(3,3,'-'));
        Polynome p1 = new Polynome(monomes1);
        Polynome p2 = new Polynome(monomes2);
        Polynome p3;
        p3 = Operations.Multiply(p1,p2);
        System.out.println(Operations.printP(p3));
    }


}
