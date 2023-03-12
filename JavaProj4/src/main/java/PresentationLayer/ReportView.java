package PresentationLayer;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ReportView extends JFrame{
    private JPanel jpanel;
    private JTextField tday;
    private JTextField tStartHour;
    private JTextField tProducts;
    private JTextField tOrders;
    private JButton btnGenerate;
    private JTextField tNo;
    private JTextField tEndHour;

    public ReportView(){
        setContentPane(jpanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,300);


    }
    public void newGenerateListener(ActionListener a) {
        btnGenerate.addActionListener(a);
    }

    public JPanel getJpanel() {
        return jpanel;
    }

    public String getTday() {
        return tday.getText();
    }

    public String gettStartHour() {
        return tStartHour.getText();
    }

    public String gettProducts() {
        return tProducts.getText();
    }

    public String gettOrders() {
        return tOrders.getText();
    }

    public JButton getBtnGenerate() {
        return btnGenerate;
    }

    public String gettNo() {
        return tNo.getText();
    }

    public String gettEndHour() {
        return tEndHour.getText();
    }
}
