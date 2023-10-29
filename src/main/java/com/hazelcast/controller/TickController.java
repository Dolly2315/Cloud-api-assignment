package com.hazelcast.controller;

import com.hazelcast.service.TickService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import javax.inject.Inject;

@Controller("/tick")
public class TickController {

    @Inject
    private TickService tickService;

    @Get(produces = MediaType.TEXT_PLAIN)
    public long getUniqueTick() {
        return tickService.getUniqueTick();
    }
}
