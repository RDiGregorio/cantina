package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "class")
public class CvarSelect extends Control {
    @JsonProperty("expectsStringValue")
    @Getter
    @Setter
    private Boolean expectsStringValue;
}
