package BussinessLogic;

import GUI.MainWindow;
import Model.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulationManager implements Runnable{
    private int arrivalMin;
    private int serviceTimeMin;
    private int noServers;
    private int noTasks;
    private int time;
    private int arrivalMax;
    private int serviceTimeMax;
    private List<Task> taskList;
    public Scheduler scheduler;
    private MainWindow mainWindow;

    public SimulationManager(int arrivalMin, int serviceTimeMin, int noServers, int noTasks, int time, int arrivalMax, int serviceTimeMax,MainWindow mainWindow) {
        this.arrivalMin = arrivalMin;
        this.serviceTimeMin = serviceTimeMin;
        this.noServers = noServers;
        this.noTasks = noTasks;
        this.time = time;
        this.arrivalMax = arrivalMax;
        this.serviceTimeMax = serviceTimeMax;
        this.scheduler=new Scheduler(noServers,noTasks,this);
        this.mainWindow = mainWindow;
        this.taskList = new ArrayList<>();

    }

    public int randomSelector(int min, int max){
        Random random = new Random();
        return random.nextInt(max-min)+min;
    }

    public int getTime() {
        return time;
    }

    private void generateNRandomTasks(){
        try {
            for(int i=0;i<noTasks;i++){
                Task task = new Task(i,randomSelector(arrivalMin,arrivalMax),randomSelector(serviceTimeMin,serviceTimeMax));
                this.taskList.add(task);
            }
            System.out.println("Tasks generated");
        }
        catch (Exception e) {

        }

    }
    public void createLog() {
        try {
            File myObj = new File("Log.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter("Log.txt");
            mainWindow.gettClients().selectAll();
            myWriter.write(mainWindow.gettClients().getSelectedText());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }
    @Override
    public void run() {
        generateNRandomTasks();
        int peakWaitTime=0;
        int currentTime=0;
        ArrayList<Task> doneTasks = new ArrayList<>();
        while (currentTime<time){
            doneTasks.clear();
            for(Task task:taskList){
                if(task.getArrivalTime()==currentTime){
                    scheduler.dispatchTask(task);
                    doneTasks.add(task);
                }
            }
            mainWindow.writeToArea(mainWindow.gettClients(),doneTasks,currentTime, scheduler.getServerArrayList());
            taskList.removeAll(doneTasks);
            mainWindow.writeRemoved(mainWindow.gettRemoved(),doneTasks);
            currentTime++;
            mainWindow.settCurrentTime(currentTime);
            mainWindow.setfAvgWaitingOut(scheduler.getAvgWaitingTime());
            if(scheduler.getAvgWaitingTime()>peakWaitTime){
                mainWindow.setfPeak(currentTime);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(currentTime==time)
            createLog();
    }
}
