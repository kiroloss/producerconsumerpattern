package com.company;




//The Consumer class implements Runnable to enable thread creation:
public class Consumer implements Runnable {
    private final Parkplatz parkplatz;
    private volatile boolean runflag;


    //Its constructor has a shared parkplatz as a parameter. The runFlag is initialized to true.
    // This flag stops the consumer process when needed
    public Consumer(Parkplatz parkplatz) {
        this.parkplatz = parkplatz;
        this.runflag = true;
    }

    @Override
    public void run() {
        consume();
    }
    public void consume(){
        while (!Thread.currentThread().isInterrupted()){
            Car car;
            parkplatz.lock.lock();
            try {

                while (parkplatz.cars.isEmpty()){
                       parkplatz.notFull.await();
                       // warten bis doe park nicht mehr leer
                }
                car=parkplatz.remove();
                parkplatz.notempty.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                parkplatz.lock.unlock();
            }

        }

    }

    public void stop() {
        runflag = false;
        parkplatz.notempty.signalAll();
    }

}
