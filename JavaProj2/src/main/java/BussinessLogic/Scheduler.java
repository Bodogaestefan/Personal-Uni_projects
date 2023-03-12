package BussinessLogic;

import Model.Server;
import Model.Task;

import java.util.ArrayList;

public class Scheduler {
    private ArrayList<Server> serverArrayList=new ArrayList<>();
    private int maxNoServers;
    private int maxTasksPerServer;
    private TimeStrategy timeStrategy;
    SimulationManager simulationManager;

    public Scheduler(int maxNoServers, int maxTasksPerServer,SimulationManager simulationManager){
        this.maxNoServers = maxNoServers;
        this.simulationManager = simulationManager;
        this.maxTasksPerServer=maxTasksPerServer;
        this.timeStrategy = new TimeStrategy(simulationManager);

        for(int i = 0;i<maxNoServers;i++){
            Server server = new Server(simulationManager);
            Thread thread = new Thread(server);
            thread.start();
            this.serverArrayList.add(server);

        }
    }

    public int getAvgWaitingTime(){
        int waitingTime=0;
        for(Server server:serverArrayList){
            waitingTime = waitingTime+server.getwaitingPeriod();
        }
        if(!serverArrayList.isEmpty()){
            waitingTime = waitingTime/serverArrayList.size();
            return waitingTime;
        }
        return 0;
    }

    public void dispatchTask(Task task){
        timeStrategy.addTask(serverArrayList,task);
    }

    public ArrayList<Server> getServerArrayList() {
        return serverArrayList;
    }
}
