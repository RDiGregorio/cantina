package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Node;

import java.io.File;
import java.io.IOException;

public class FileParser {
    public Node parseFile(final File file) throws IOException {
        return new ObjectMapper().readValue(file, Node.class);
    }
}
