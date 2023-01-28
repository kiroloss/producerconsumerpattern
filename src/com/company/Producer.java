package com.company;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import  java.util.concurrent.locks.ReentrantLock;


//The Producer class implements the Runnable interface to enable thread creation:
public class Producer implements Runnable {
    Car c =new Car();
    private final Parkplatz parkplatz;


    //The constructor uses the shared  Parkplatz parameter.
    //Thread start calls the produce() method:
    public Producer(Parkplatz parkplatz) {
        this.parkplatz = parkplatz;

    }

    @Override
    public void run() {
        produce();
    }
    public void produce(){
        while (!Thread.currentThread().isInterrupted()){
            //// producer thread waits while list
            //is full
            parkplatz.lock.lock();

            try {
                while (parkplatz.cars.size()==parkplatz.maxsize){
                    parkplatz.notFull.await();
                }
                parkplatz.add(c );
                parkplatz.notempty.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                parkplatz.lock.unlock();
            }


        }
    }

}
