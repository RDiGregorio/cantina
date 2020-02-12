package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ContentView {
    @JsonProperty("subviews")
    @Getter
    @Setter
    private List<Node> subviews;
}
