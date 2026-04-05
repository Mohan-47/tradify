package com.mo.tradify.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
public class FinnhubTradeDto {
    @JsonProperty("s")
    private String symbol;

    @JsonProperty("p")
    private double price;

    @JsonProperty("t")
    private long timestamp;

    @JsonProperty("v")
    private double volume;
}

