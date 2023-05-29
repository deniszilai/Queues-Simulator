package ro.tuc.tp.Strategy;

import ro.tuc.tp.Model.Task;
import ro.tuc.tp.Model.Server;
import ro.tuc.tp.GUI.View.View2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SimulationManager implements  Runnable{
    private int timeLimit;
    private int maxProcessingTime;
    private int minProcessingTime;
    private int numberOfServers;
    private int numberOfClients;
    private int minArrival;
    private int maxArrival;
    private float maxT;
    private float max;
    private SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;
    private Scheduler scheduler;
    private View2 view2;
    private List<Task> generatedTasks;
    private int maxNoOfTasksPerServer = 1000;
    private float avgProcessingTime;
    public SimulationManager(int timeLimit, int maxProcessingTime, int minProcessingTime, int numberOfServers, int numberOfClients, int maxArrival, int minArrival){
        this.timeLimit = timeLimit;
        this.maxProcessingTime = maxProcessingTime;
        this.minProcessingTime = minProcessingTime;
        this.numberOfServers = numberOfServers;
        this.numberOfClients = numberOfClients;
        this.maxArrival = maxArrival;
        this.minArrival = minArrival;
        view2 = new View2(numberOfServers);
        view2.setVisible(true);
        scheduler = new Scheduler(numberOfServers, maxNoOfTasksPerServer);
        this.scheduler.changeStrategy(selectionPolicy);
        this.generateNRandomTasks();
        calcAverage();
        max = 0;
    }
    public void generateNRandomTasks(){
        generatedTasks = new ArrayList<Task>(numberOfClients);
        Random rand = new Random();
        for(int i = 0; i < numberOfClients; i ++){
            Task t = new Task(rand.nextInt(maxArrival - minArrival) + minArrival, new AtomicInteger(rand.nextInt(maxProcessingTime - minProcessingTime) + minProcessingTime));
            generatedTasks.add(t);
        }
        Collections.sort(generatedTasks);
    }
    @Override
    public void run(){
        PrintStream out = null;
        try {
            out = new PrintStream(new FileOutputStream("output.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.setOut(out);
        int currentTime = 0;
        try {
            while(currentTime <= timeLimit){//se opreste cand s-a depasit timpul
                int sum = 0;
                System.out.println("Time: " + currentTime + "\n");
                view2.setSimTime(String.valueOf(currentTime));
                String s = "";
                for (int i = 0; i < generatedTasks.size(); i++) {
                    if (generatedTasks.get(i).getArrivalTime() == currentTime) {
                        scheduler.dispatchTask(generatedTasks.get(i));// se trimite task-ul in queue
                        generatedTasks.remove(i);
                        i--;
                    }
                }
                for(int i = 0; i < generatedTasks.size(); i ++){
                    s = s + generatedTasks.get(i).toString() + " ";
                }
                System.out.println("Waiting clients: " + s + "\n");
                view2.setClWait(s);
                for(int i = 0; i < scheduler.getServers().size(); i ++){
                    String str = "";
                    for(int j = 0; j < scheduler.getServers().get(i).getNumberOfTasks(); j ++){
                        str = str + scheduler.getServers().get(i).getTasks()[j].toString();//coada generata
                    }
                    int nr = i + 1;
                    System.out.println("Line " + nr + ": "+ str);
                    System.out.println("\n");
                    view2.setCl(str, i);
                }
                System.out.println("\n");
                for(int i = 0; i < scheduler.getServers().size(); i ++) {
                    if (scheduler.getServers().get(i).getNumberOfTasks() != 0) {
                        if (scheduler.getServers().get(i).getTasks()[0].getProcessingPeriod().get() != 0) {
                            scheduler.getServers().get(i).getTasks()[0].dec();
                        }
                    }
                }
                for(int i = 0; i < scheduler.getServers().size(); i ++){//timpul de servire al clientilor
                    sum += scheduler.getServers().get(i).getWaitingPeriod();
                }
                if(max < sum) {
                    max = sum;
                    maxT = currentTime;//ora de varf
                }

                if(scheduler.isEmpty() == true && generatedTasks.size() == 0){
                    break;
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                currentTime++;//creste timpul curent
            }
            System.out.println("Average processing time: " + avgProcessingTime);
            System.out.println("\n");
            System.out.println("Waiting time: " + Server.getWaitingTime() / numberOfClients);
            System.out.println("\n");
            System.out.println("Peak hour: " + maxT);
            System.out.println("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void calcAverage(){//timpul mediu de procesare
        float sum = 0;
        for(int i = 0; i < generatedTasks.size(); i ++){
            sum += generatedTasks.get(i).getProcessingPeriod().get();
            this.avgProcessingTime = sum / generatedTasks.size();
        }
    }
}

