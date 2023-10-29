package com.hazelcast.service;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class TickServiceTest {

    @Inject
    TickService tickService;

    @Test
    void testIncrement() {
        long firstTick = tickService.getUniqueTick();
        long secondTick = tickService.getUniqueTick();
        assertEquals(firstTick + 1, secondTick);
    }

    @Test
    void testConcurrency() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Set<Future<Long>> futures = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            Future<Long> future = executor.submit((Callable<Long>) tickService::getUniqueTick);
            futures.add(future);
        }

        Set<Long> tickValues = new HashSet<>();
        for (Future<Long> future : futures) {
            tickValues.add(future.get());
        }

        assertEquals(10, tickValues.size(), "Should have 100 unique values");
    }
}
