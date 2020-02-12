package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "class")
public class CvarSlider extends Control {
    @JsonProperty("min")
    @Getter
    @Setter
    private Integer min;

    @JsonProperty("max")
    @Getter
    @Setter
    private Integer max;

    @JsonProperty("step")
    @Getter
    @Setter
    private Integer step;
}
