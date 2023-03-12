package controller;

import model.ConvPoli;
import model.Operations;
import model.Polynome;
import view.Calc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    Calc calc = new Calc();
    public Controller(Calc calc){
        this.calc = calc;
        calc.addAddListener(new addPolynomials());
        calc.addSubistener(new subPolynomials());
        calc.addMulListener(new multiPolynomials());
        calc.addDerListener(new DerivatePolynomials());
        calc.addIntListener(new IntegratePolynomials());
        calc.addDivListener(new DividePolynomials());

    }
    class addPolynomials implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            Polynome polynome1;
            Polynome polynome2;
            Polynome r;
            polynome1 = ConvPoli.convertP(calc.fP1.getText());
            polynome2 = ConvPoli.convertP(calc.fP2.getText());
            r = Operations.add(polynome1,polynome2);
            calc.fP1.setText(Operations.printP(r));
        }
    }
    class subPolynomials implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            Polynome polynome1;
            Polynome polynome2;
            Polynome r;
            polynome1 = ConvPoli.convertP(calc.fP1.getText());
            polynome2 = ConvPoli.convertP(calc.fP2.getText());
            r = Operations.sub(polynome1,polynome2);
            calc.fP1.setText(Operations.printP(r));
        }
    }
    class multiPolynomials implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            Polynome polynome1;
            Polynome polynome2;
            Polynome r;
            polynome1 = ConvPoli.convertP(calc.fP1.getText());
            polynome2 = ConvPoli.convertP(calc.fP2.getText());
            r = Operations.Multiply(polynome1,polynome2);
            calc.fP1.setText(Operations.printP(r));
        }
    }

    class DerivatePolynomials implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                Polynome polynome1;
                Polynome r;
                polynome1 = ConvPoli.convertP(calc.fP1.getText());
                r = Operations.Derivate(polynome1);
                calc.fP1.setText(Operations.printP(r));
            }
    }
    class IntegratePolynomials implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            Polynome polynome1;
            Polynome r;
            polynome1 = ConvPoli.convertP(calc.fP1.getText());
            r = Operations.Integrate(polynome1);
            calc.fP1.setText(Operations.printP(r));
        }
    }
    class DividePolynomials implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            Polynome polynome1;
            Polynome polynome2;
            Polynome r;
            polynome1 = ConvPoli.convertP(calc.fP1.getText());
            polynome2 = ConvPoli.convertP(calc.fP2.getText());
            r = Operations.Divide(polynome1,polynome2);
            calc.fP1.setText(Operations.printP(r));
        }
    }


}
