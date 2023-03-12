package Model;

import BussinessLogic.SimulationManager;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Server implements Runnable{
   private BlockingQueue<Task> tasks;
   private int waitingPeriod;
   SimulationManager simulationManager;

    public Server(SimulationManager simulationManager) {
        tasks = new ArrayBlockingQueue<Task>(1000);
        this.waitingPeriod = 0;
        this.simulationManager=simulationManager;
    }

    public BlockingQueue<Task> getTasks() {
        return tasks;
    }

    public int getwaitingPeriod() {
        return waitingPeriod;
    }


    public void addTask(Task t) {
        tasks.add(t);
        this.waitingPeriod = waitingPeriod + t.getServiceTime();
    }

    @Override
    public void run() {
        while (true) {
            if (tasks.isEmpty() == false) {
                try {
                    int serviceTime = tasks.peek().getServiceTime();
                    for (int i = 0; i < serviceTime; i++) {
                        Thread.sleep(1000);
                        if (this.waitingPeriod > 0) {
                            this.waitingPeriod--;
                        }
                    }
                    tasks.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
