package com.mec.dida.core;


public abstract class Dida implements Runnable {
    private long time;
    private final Object lock;
    private volatile boolean goon;
    private static final long DEFAULT = 1000;

    public abstract void work();

    protected Dida() {
        goon = false;
        time = DEFAULT;
        lock = new Object();
    }

    public Dida setTime(long time) {
        this.time = time;
        return this;
    }

    public void start () {
        if (goon) {
            return;
        }

        goon = true;
        new Thread(this, "计时").start();
        new Thread(new Action(), "工作").start();
    }

    @Override
    public void run() {
        while (goon) {
                try {
                    synchronized (lock) {
                        lock.wait(time);
                        lock.notify();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }

    class Action implements Runnable {

        @Override
        public void run() {
            while (goon) {
                try {
                    synchronized (lock) {
                        lock.wait();
                    }
                    work();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }
    }
}
