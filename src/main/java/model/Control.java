package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, defaultImpl = Control.class, property = "class", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CvarCheckbox.class),
        @JsonSubTypes.Type(value = CvarSelect.class),
        @JsonSubTypes.Type(value = CvarSlider.class)
})
public class Control extends Node {
    @JsonProperty("var")
    @Getter
    @Setter
    private String variable;
}
