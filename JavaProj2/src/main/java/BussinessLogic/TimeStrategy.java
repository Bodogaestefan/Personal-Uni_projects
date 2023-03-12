package BussinessLogic;

import Model.Server;
import Model.Task;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TimeStrategy {
    SimulationManager simulationManager;
    public TimeStrategy(SimulationManager simulationManager){
        this.simulationManager = simulationManager;
    }
    public void addTask(List<Server> servers, Task t) {
        Server bestServer = new Server(simulationManager);
        int minWaiting=99999999;
        //servers.get(servers.size() - 1).addTask(t);
        for(Server server: servers){
            if(server.getwaitingPeriod()<minWaiting){
                minWaiting = server.getwaitingPeriod();
                bestServer = server;
            }
        }
        bestServer.addTask(t);
        //Collections.sort(servers, new SortByWaitingTime().reversed());
    }

    public static class SortByWaitingTime implements Comparator<Server> {
        public int compare(Server s1, Server s2) {
            return s1.getwaitingPeriod() - s2.getwaitingPeriod();
        }
    }
}
