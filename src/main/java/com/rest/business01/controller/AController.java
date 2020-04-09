package com.rest.business01.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = { ("${server.servlet.context-path}") + "/a"})
public class AController {
    @GetMapping(value = {""}, produces = {MediaType.TEXT_PLAIN_VALUE})
    @Operation(
            description = "Controller A",
            method = "POST",
            responses = {
                    @ApiResponse(content = {@Content(mediaType = MediaType.TEXT_PLAIN_VALUE)})
            } ,
            tags = {"Controller A"})
    public String getText() {
        return "Text";
    }
}
