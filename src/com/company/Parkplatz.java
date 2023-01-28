package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.System.out;

public class Parkplatz {
     public Queue<Car> cars=new LinkedList<>();
     public int maxsize;
    Lock lock = new ReentrantLock();
    Condition notFull = lock.newCondition();
    Condition notempty=lock.newCondition();

    public Parkplatz(int maxsize) {
        this.maxsize = maxsize;
    }
    // When the queue is full, the producer waits on the FULL_QUEUE object.
    // And, the consumer notifies as soon as it consumes a message.


      //And the producer uses the add() method to add a message to the queue:
    public void add(Car car){
        synchronized (cars){
            cars.add(car);
            out.println("Car is added");
        }
    }
    //The consumer calls the remove method to retrieve a message from the queue
    public Car remove(){
        synchronized (cars){
            out.println("Car is removed");
            return cars.poll();
        }
    }
}
