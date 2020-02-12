package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, defaultImpl = Node.class, property = "class", visible = true)
@JsonSubTypes({
        @Type(value = Box.class),
        @Type(value = Button.class),
        @Type(value = Control.class),
        @Type(value = Input.class),
        @Type(value = StackView.class),
        @Type(value = VideoModeSelect.class)
})
public class Node {
    @JsonProperty("class")
    @Getter
    @Setter
    private String javaClass;

    @JsonProperty("identifier")
    @Getter
    @Setter
    private String cssIdentifier;

    @JsonProperty("classNames")
    @Getter
    @Setter
    private List<String> cssClassNames;

    @JsonProperty("subviews")
    @Getter
    @Setter
    private List<Node> subviews;
}
