package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class Label {
    @JsonProperty("text")
    @Getter
    @Setter
    private Text text;
}
