package com.hazelcast.service;

import javax.inject.Singleton;

@Singleton
public class TickService {

    private long tick = 0;

    public synchronized long getUniqueTick() {
        return tick++;
    }
}