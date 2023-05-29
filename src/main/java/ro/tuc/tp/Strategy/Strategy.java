package ro.tuc.tp.Strategy;

import ro.tuc.tp.Model.Server;
import ro.tuc.tp.Model.Task;

import java.util.List;

public interface Strategy {
    public void addTask(List<Server> servers, Task t);
}

