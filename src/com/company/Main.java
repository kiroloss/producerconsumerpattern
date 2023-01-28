package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here

       //  create a parkplatz object
        Parkplatz parkplatz=new Parkplatz(8);

        //Now, let's create producer object and a thread:
        Producer producer =new Producer(parkplatz);
        Thread producerThread=new Thread(producer);

        //Then, we'll initialize a consumer object and a thread:
        Consumer consumer=new Consumer(parkplatz);
        Thread consumerThread=new Thread(consumer);

        //start the threads to initiate the process
          producerThread.start();
          consumerThread.start();
        //It runs continuously until we want to stop
        // those threads

      producerThread.interrupt();
      consumerThread.interrupt();
    }
}
