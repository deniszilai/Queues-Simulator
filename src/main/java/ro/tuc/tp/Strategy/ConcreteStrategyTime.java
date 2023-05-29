package ro.tuc.tp.Strategy;

import ro.tuc.tp.Model.Server;
import ro.tuc.tp.Model.Task;

import java.util.List;

public class ConcreteStrategyTime implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task t) {
        int bestT = servers.get(0).getWaitingPeriod();//waiting period de la primul server

        for(int i = 1; i < servers.size(); i++){
            if(servers.get(i).getWaitingPeriod() < bestT){//afla timpul minim
                bestT = servers.get(i).getWaitingPeriod();
            }
        }

        for(Server s: servers) {//suma de waiting time
            if (s.getWaitingPeriod() == bestT) {
                int sum = 0;
                for(int i = 0; i < s.getNumberOfTasks(); i ++){
                    sum += s.getTasks()[i].getProcessingPeriod().get();
                }
                t.setWaitingTime(sum + t.getProcessingPeriod().get());
                s.addTask(t);
                break;
            }
        }

    }
}