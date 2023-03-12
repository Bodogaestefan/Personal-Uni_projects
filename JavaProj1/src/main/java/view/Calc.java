package view;

import model.ConvPoli;
import model.Operations;
import model.Polynome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calc extends JFrame{
    private JPanel mainForm;
    public JFormattedTextField fP1;
    public JFormattedTextField fP2;
    private JLabel lP1;
    private JLabel lP2;
    private JButton bMulti;
    private JButton bSubstract;
    private JButton bDivide;
    private JButton bDer;
    private JButton bAdd;
    private JButton bIntegrate;

    public Calc(){
        setContentPane(mainForm);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(600,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        bAdd.setPreferredSize(new Dimension(100,30));
        bSubstract.setPreferredSize(new Dimension(100,30));
        bMulti.setPreferredSize(new Dimension(100,30));
        bDer.setPreferredSize(new Dimension(100,30));
        bIntegrate.setPreferredSize(new Dimension(100,30));
        bDivide.setPreferredSize(new Dimension(100,30));

     /*   bAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Polynome polynome1;
                Polynome polynome2;
                Polynome r;
                polynome1 = ConvPoli.convertP(fP1.getText());
                polynome2 = ConvPoli.convertP(fP2.getText());
                r = Operations.add(polynome1,polynome2);
                fP1.setText(Operations.printP(r));


            }
        });
        bSubstract.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polynome polynome1;
                Polynome polynome2;
                Polynome r;
                polynome1 = ConvPoli.convertP(fP1.getText());
                polynome2 = ConvPoli.convertP(fP2.getText());
                r = Operations.sub(polynome1,polynome2);
                fP1.setText(Operations.printP(r));
            }
        });
        bIntegrate.addActionListener(new ActionListener() { //Integraton
            @Override
            public void actionPerformed(ActionEvent e) {
                Polynome polynome1;
                Polynome r;
                polynome1 = ConvPoli.convertP(fP1.getText());
                r = Operations.Integrate(polynome1);
                fP1.setText(Operations.printP(r));
            }
        });
        bMulti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polynome polynome1;
                Polynome polynome2;
                Polynome r;
                polynome1 = ConvPoli.convertP(fP1.getText());
                polynome2 = ConvPoli.convertP(fP2.getText());
                r = Operations.Multiply(polynome1,polynome2);
                fP1.setText(Operations.printP(r));
            }
        });
        bDer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polynome polynome1;
                Polynome r;
                polynome1 = ConvPoli.convertP(fP1.getText());
                r = Operations.Derivate(polynome1);
                fP1.setText(Operations.printP(r));
            }
        });
        bDivide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });*/
    }

    public void addAddListener(ActionListener e){
        bAdd.addActionListener(e);
    }
    public void addSubistener(ActionListener e){
        bSubstract.addActionListener(e);
    }
    public void addMulListener(ActionListener e){bMulti.addActionListener(e);}
    public void addDivListener(ActionListener e){
        bDivide.addActionListener(e);
    }
    public void addIntListener(ActionListener e){
        bIntegrate.addActionListener(e);
    }
    public void addDerListener(ActionListener e){
        bDer.addActionListener(e);
    }


}
