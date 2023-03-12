package controller;
import java.util.Arrays;
import java.util.regex.*;

import model.Monome;
import view.Calc;

public class Main {

    public static void main(String[] args) {
        Calc calc = new Calc();
        Controller controller = new Controller(calc);
        calc.setVisible(true);
    }
}
