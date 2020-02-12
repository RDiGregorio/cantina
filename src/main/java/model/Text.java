package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class Text {
    @JsonProperty("text")
    @Getter
    @Setter
    private String text;
}
