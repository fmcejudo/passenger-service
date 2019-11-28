package com.ryanair.passenger.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@KeySpace("passengers")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Passenger implements Serializable {

    @Id
    private String ticketId;
    private String from;
    private String to;
    private LocalDateTime boardingTime;
}
