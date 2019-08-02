package me.infinityz.utils;

import java.util.concurrent.atomic.AtomicBoolean;

public class CustomThread implements Runnable{
    private Thread worker;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private int interval;

    public CustomThread(int sleepInterval) {
        interval = sleepInterval;
    }

    public void interrupt() {
        running.set(false);
        worker.interrupt();
    }

    boolean isRunning() {
        return running.get();
    }

    public void start() {
        worker = new Thread(this);
        worker.start();
    }

    public void stop(){
        running.set(false);
    }

    @Override
    public void run() {
        running.set(true);
        while(running.get()){
            try {
                logic();
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
                System.out.println("Thread was interrupted, Failed to complete operation");
            }
        }

    }

    public void logic(){

    }
}
