package com.rest.business01.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class EventModel {

    @Schema(implementation = Long.class)
    private final Long id;

    @Schema(implementation = Date.class)
    private final Date when;
}
