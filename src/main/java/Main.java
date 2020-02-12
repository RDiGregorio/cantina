import com.fasterxml.jackson.databind.ObjectMapper;
import model.Node;
import util.FileParser;
import util.Selector;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(final String[] arguments) throws IOException {
        final Scanner scanner = new Scanner(System.in);

        for (final Node node : new Selector().searchWithoutChaining(new FileParser().parseFile(new File(arguments[0])), scanner.nextLine())) {
            System.out.println(new ObjectMapper().writeValueAsString(node));
        }
    }
}
