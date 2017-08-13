package com.example.controller;

import org.apache.log4j.Logger;

/**
 * Created by LKW on 2017/8/7.
 */
public class ThreadTesting implements Runnable {

    Thread t;

    Logger log = Logger.getLogger(ThreadTesting.class);

    private int number;
    public ThreadTesting(int number) {
        this.number = number;
    }

    public void run() {
        int counter = 0;
        int guess = 0;

        try {
            Thread.sleep(5000);
            log.info("Thread Done.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
