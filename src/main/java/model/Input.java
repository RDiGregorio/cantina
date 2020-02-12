package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "class")
public class Input extends Node {
    @JsonProperty("label")
    @Getter
    @Setter
    private Label label;

    @JsonProperty("control")
    @Getter
    @Setter
    private Control control;

    public List<Node> getSubviews() {
        if (control == null) {
            return null;
        }

        return Collections.singletonList(control);
    }
}
