package GUI;

import Model.Server;
import Model.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainWindow extends JFrame{
    private JPanel panel1;
    private JFormattedTextField fArrivalMin;
    private JFormattedTextField fServiceTimeMin;
    private JFormattedTextField fServers;
    private JFormattedTextField fTasks;
    private JTextArea tClients;
    private JFormattedTextField fTime;
    private JFormattedTextField fArrivalMax;
    private JFormattedTextField fServiceTimeMax;
    private JFormattedTextField fAvgWaitingOut;
    private JFormattedTextField fAvgServiceOut;
    private JFormattedTextField fPeak;
    private JButton startButton;
    private JTextArea tRemoved;
    private JTextField tCurrentTime;



    public MainWindow(){
        setContentPane(panel1);
        setVisible(true);
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public int getArrivalMin() {
        return Integer.parseInt(fArrivalMin.getText());
    }

    public int getServiceTimeMin() {
        return Integer.parseInt(fServiceTimeMin.getText());
    }

    public int getNoServers() {
        return Integer.parseInt(fServers.getText());
    }

    public int getNoTasks() {
        return Integer.parseInt(fTasks.getText());
    }

    public int getTime() {
        return Integer.parseInt(fTime.getText());
    }

    public int getArrivalMax() {
        return Integer.parseInt(fArrivalMax.getText());
    }

    public int getServiceTimeMax() {
        return Integer.parseInt(fServiceTimeMax.getText());
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public void setfTime(JFormattedTextField fTime) {
        this.fTime = fTime;
    }

    public void settCurrentTime(int time) {
        tCurrentTime.setText(String.valueOf(time));
    }

    public JTextArea gettClients() {
        return tClients;
    }

    public JTextArea gettRemoved() {
        return tRemoved;
    }

    public void setfAvgWaitingOut(int avgWaiting) {
        this.fAvgWaitingOut.setText(String.valueOf(avgWaiting));
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public JFormattedTextField getfPeak() {
        return fPeak;
    }

    public void setfPeak(int peak) {
        this.fPeak.setText(String.valueOf(peak));
    }

    public void setfAvgServiceOut(int avgService) {
        this.fAvgServiceOut.setText(String.valueOf(avgService));
    }

    public void startListener(ActionListener a){
        startButton.addActionListener(a);
    }
   public void writeToArea(JTextArea jTextArea, ArrayList<Task> tasks, int time, ArrayList<Server> serverArrayList){
        int sCount=1;
        jTextArea.append("Time: "+time+"\nWaiting clients: ");
        for(Task task:tasks){
            jTextArea.append("("+task.getId()+","+task.getArrivalTime()+","+task.getServiceTime()+"); ");
        }
        jTextArea.append("\n");
        for(Server server:serverArrayList){
            jTextArea.append("queue"+sCount+": ");
            if(!server.getTasks().isEmpty()){
                for(Task task:server.getTasks()){
                    jTextArea.append("("+task.getId()+","+task.getArrivalTime()+","+task.getServiceTime()+"), ");
                }
                jTextArea.append("\n");
            }
            else{
                jTextArea.append("closed\n");
            }
            sCount++;
        }
        jTextArea.append("\n\n");
    }

    public void writeRemoved(JTextArea jTextArea, ArrayList<Task> tasks){

        for(Task task:tasks){
            jTextArea.append("("+task.getId()+","+task.getArrivalTime()+","+task.getServiceTime()+"), ");
        }
        jTextArea.append("\n");
    }
}



