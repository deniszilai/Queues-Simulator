package ro.tuc.tp.Strategy;

import ro.tuc.tp.Model.Task;
import ro.tuc.tp.Model.Server;

import java.util.List;

public class ConcreteStrategyQueue implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task t) {
        int min = servers.get(0).getNumberOfTasks();
        for(Server s: servers){
            if(s.getNumberOfTasks() < min){
                min = s.getNumberOfTasks();
            }
        }
        for(Server s: servers) {
            if (s.getNumberOfTasks() == min) {
                s.addTask(t);
                break;
            }
        }
    }
}
