package com.epam.threadtask.entity;

import org.apache.log4j.Logger;

import java.util.ArrayList;

public class Car extends Thread {

    private static final Logger LOG = Logger.getLogger(Car.class);

    public static final int WAIT_TIME_BEFORE_PARK = 500;
    private String nameCar;

    private ArrayList<Parking> parkings;

    public Car(String name, ArrayList<Parking> parkings) {
        this.nameCar = name;
        this.parkings = parkings;
    }

    public String getCarName() {
        return nameCar;
    }

    @Override
    public void run() {
        long stayTime = Math.round(1000 + (Math.random() * 5000));

        for (Parking parking : parkings) {

            if (parking.tryPark(this, WAIT_TIME_BEFORE_PARK)) {
                try {
                    LOG.info("entity.Car " + Thread.currentThread().getName() + "has  parked");
                    Thread.sleep(stayTime);
                    parking.leave(this);
                    LOG.info("entity.Car " + Thread.currentThread().getName() + "has  left parking");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
