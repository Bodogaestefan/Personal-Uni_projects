package PresentationLayer;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Login extends JFrame{
    private JPasswordField pPassword;
    private JPanel panel1;
    private JFormattedTextField fUsername;
    private JLabel Login;
    private JLabel Username;
    private JButton bLogin;
    private JButton bReg;
    private JComboBox cRole;

    public Login(){
        setContentPane(panel1);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,300);
        setVisible(true);

        cRole.addItem("Admin");
        cRole.addItem("Employee");
        cRole.addItem("Client");
    }
    public int getRole(){
        if(cRole.getSelectedItem().equals("Admin"))
            return 1;
        else if(cRole.getSelectedItem().equals("Employee"))
            return 2;
        else
            return 3;
    }

    public void newLoginListener(ActionListener a) {
        bLogin.addActionListener(a);
    }
    public void newRegListener(ActionListener a) {
        bReg.addActionListener(a);
    }

    public String getpPassword() {
        return String.valueOf(pPassword.getPassword());
    }

    public void setpPassword(JPasswordField pPassword) {
        this.pPassword = pPassword;
    }

    public JFormattedTextField getfUsername() {
        return fUsername;
    }

    public void setfUsername(JFormattedTextField fUsername) {
        this.fUsername = fUsername;
    }
}
