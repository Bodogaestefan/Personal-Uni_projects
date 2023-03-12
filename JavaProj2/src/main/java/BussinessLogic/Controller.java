package BussinessLogic;

import GUI.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    SimulationManager simulationManager;
    MainWindow mainWindow;
    private int avgWaiting;
    private int avgService;

    public Controller() {
        this.mainWindow = new MainWindow();
        mainWindow.startListener(new Start());

    }


    private class Start implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                int arrivalMin = mainWindow.getArrivalMin();
                int arrivalMax = mainWindow.getArrivalMax();
                int serviceTimeMin = mainWindow.getServiceTimeMin();
                int serviceTimeMax = mainWindow.getServiceTimeMax();
                int noServers = mainWindow.getNoServers();
                int noTasks = mainWindow.getNoTasks();
                int time = mainWindow.getTime();
                simulationManager = new SimulationManager(arrivalMin, serviceTimeMin, noServers, noTasks, time, arrivalMax, serviceTimeMax,mainWindow);
                Thread thread = new Thread(simulationManager);
                thread.start();
              //  simulationManager.createLog();
            }
            catch (Exception ex){
                System.out.println("Wrong data");
            }

        }
    }
}
