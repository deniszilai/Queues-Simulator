package ro.tuc.tp.Model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod = new AtomicInteger(0);
    private static float waitingTime;

    public int getNumberOfTasks(){
        return tasks.size();
    }
    public int getWaitingPeriod() {
        return waitingPeriod.get();
    }
    public static float getWaitingTime() {
        return waitingTime;
    }

    public Server(int maxNTasks){
        this.tasks = new ArrayBlockingQueue<Task>(maxNTasks);
    }

    public void addTask(Task newTask){
        tasks.add(newTask);
        waitingPeriod.addAndGet(newTask.getProcessingPeriod().get());
    }

    public  void run(){
        while(true){
            if(tasks.isEmpty() == false){
                AtomicInteger time = tasks.peek().getProcessingPeriod();//timpul taskului din varful stivei
                if(tasks.peek() != null){
                    waitingTime += tasks.peek().getWaitingTime();//calcularea timpului de asteptare
                }
                try {
                    Thread.sleep(time.get() * 1000);//sleeps timpul taskului*1 sec
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tasks.poll();//elimina taskul
                waitingPeriod.set(waitingPeriod.get() - time.get());//scade timpul de asteptare
            }
        }
    }

    public Task[] getTasks() {
        Task[] array = new Task[tasks.size()];
        tasks.toArray(array);
        return array;
    }

}

