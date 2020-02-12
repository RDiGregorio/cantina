package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "class")
public class Box extends Node {
    @JsonProperty("label")
    @Getter
    @Setter
    private Label label;

    @JsonProperty("contentView")
    @Getter
    @Setter
    private ContentView contentView;

    public List<Node> getSubviews() {
        if (contentView == null) {
            return null;
        }

        return contentView.getSubviews();
    }
}
