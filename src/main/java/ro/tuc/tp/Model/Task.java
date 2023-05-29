package ro.tuc.tp.Model;

import java.util.concurrent.atomic.AtomicInteger;

public class Task implements Comparable{
    private int arrivalTime;
    private AtomicInteger processingTIime;
    private int ID;
    private float waitingTime;
    private static int k = 0; //contorizeaza id
    public Task(int arrivalTime, AtomicInteger processingTIime){
        this.arrivalTime = arrivalTime;
        this.processingTIime = processingTIime;
        this.k++;
        this.ID = this.k;

    }

    @Override
    public int compareTo(Object obj){
        Task t = (Task) obj;
        return arrivalTime - t.arrivalTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }
    public AtomicInteger getProcessingPeriod() {
        return processingTIime;
    }
    public float getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(float waitingTime) {
        this.waitingTime = waitingTime;
    }

    public void dec(){
        processingTIime.addAndGet(-1);
    }

    public String toString(){
        return "(" + this.ID + ", " + this.arrivalTime + ", " + this.processingTIime + ")";
    }

}
