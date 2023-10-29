package com.hazelcast.controller;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import javax.inject.Inject;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class TickControllerTest {

    @Inject
    TickController tickController;

    @Test
    public void testTickIncrement() {
        long firstTick = tickController.getUniqueTick();
        long secondTick = tickController.getUniqueTick();
        assertEquals(firstTick + 1, secondTick);
    }
}