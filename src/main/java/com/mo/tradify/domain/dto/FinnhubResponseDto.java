package com.mo.tradify.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
public class FinnhubResponseDto {

    @JsonProperty("type")
    private String type;
    private List<FinnhubTradeDto> data;

}
