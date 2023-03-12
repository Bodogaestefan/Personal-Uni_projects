package PresentationLayer;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class Employee extends JFrame implements Observer {
    JTextArea textArea;
    JScrollPane scrollPane;
    String rep;

    @Override
    public void update(Observable o, Object arg) {
        textArea.append(rep);
    }

    public void updateE(String rep1){
        rep=rep1;
        update(new Observable(),new Object());
    }

    public Employee(){
        this.setTitle("Employee");
        this.setBounds(100, 100, 470, 568);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 30, 434, 488);
        this.getContentPane().add(scrollPane);

        textArea = new JTextArea();
        scrollPane.setViewportView(textArea);
    }
}
