package ro.tuc.tp.Strategy;

import ro.tuc.tp.Model.Task;
import ro.tuc.tp.Model.Server;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;

    public Scheduler(int maxNoServers, int maxTasksPerServer) {
        this.maxNoServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
        this.servers = new ArrayList<Server>(maxTasksPerServer);
        for (int i = 0; i < this.maxNoServers; i++) {
            this.servers.add(new Server(maxTasksPerServer));
            Thread t = new Thread(this.servers.get(i));
            t.start();
        }
    }

    public void changeStrategy(SelectionPolicy policy) {
        if (policy == SelectionPolicy.SHORTEST_QUEUE) {
            strategy = new ConcreteStrategyQueue();
        }
        if (policy == SelectionPolicy.SHORTEST_TIME) {
            strategy = new ConcreteStrategyTime();
        }
    }

    public void dispatchTask(Task t) {
        strategy.addTask(servers, t);
    }

    public ArrayList<Server> getServers() {
        return (ArrayList<Server>) servers;
    }

    public boolean isEmpty() {//cand cozile sunt goale si ult client a ajuns la procesare 0, se intrerupe simularea
        for (int i = 0; i < servers.size(); i++) {
            if (servers.get(i).getNumberOfTasks() != 0) {
                return false;
            }
        }
        return true;
    }
}

