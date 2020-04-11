package com.rest.business01.controller;

import com.rest.business01.model.EventModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = { ("${server.servlet.context-path}") + "/a"})
public class AController {
    @GetMapping(value = {"/events"}, produces = {MediaType.APPLICATION_STREAM_JSON_VALUE})
    @Operation(
            method = "POST",
            tags = {"Controller A Tag action"},
            responses = {
                    @ApiResponse(
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_STREAM_JSON_VALUE,
                                    schema = @Schema(implementation = EventModel.class)
                            )
                    ),
                    @ApiResponse(responseCode = "200", description = "Data")
            })
    public Flux<EventModel> getEvents() {
        Flux<EventModel> eventModelFlux = Flux.fromStream(Stream.generate(() -> new EventModel(System.currentTimeMillis(),new Date())));
        Flux<Long> longFlux = Flux.interval(Duration.ofSeconds(1));
        return Flux.zip(eventModelFlux,longFlux).map(Tuple2::getT1).take(10);
    }
}
