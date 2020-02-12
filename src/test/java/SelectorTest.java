import com.fasterxml.jackson.databind.ObjectMapper;
import model.Node;
import model.StackView;
import org.junit.BeforeClass;
import org.junit.Test;
import util.FileParser;
import util.Selector;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SelectorTest {
    public static Node systemViewController;

    @BeforeClass
    public static void init() throws IOException {
        systemViewController = new FileParser().parseFile(new File("SystemViewController.json"));
    }

    @Test
    public void testMain() throws IOException {
        final InputStream in = System.in;
        final PrintStream out = System.out;
        final ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            final String[] arguments = {"SystemViewController.json"};
            System.setIn(new ByteArrayInputStream("StackView".getBytes()));
            System.setOut(new PrintStream(output));
            Main.main(arguments);
        } finally {
            System.setIn(in);
            System.setOut(out);
        }

        for (final String line : output.toString().split("\\R+")) {
            assertEquals(StackView.class, new ObjectMapper().readValue(line, StackView.class).getClass());
        }
    }

    @Test
    public void testSearchByJavaClass() {
        assertEquals(26, new Selector().searchWithoutChaining(systemViewController, "Input").size());
        assertEquals(26, new Selector().searchWithChaining(systemViewController, "Input").size());
    }

    @Test
    public void testCssClassMatching() {
        final List<String> classes = new ArrayList<>();
        classes.add("test");
        final Node node = new Node();
        node.setJavaClass("F");
        node.setCssClassNames(classes);
        assertTrue(new Selector().matchesWithoutChaining(node, "F.test"));
        node.setCssClassNames(new ArrayList<>());
        assertFalse(new Selector().matchesWithoutChaining(node, "F.test"));
    }

    @Test
    public void testSearchByCssClass() {
        assertEquals(6, new Selector().searchWithoutChaining(systemViewController, "StackView.container").size());
        assertEquals(1, new Selector().searchWithoutChaining(systemViewController, "StackView.columns").size());
        assertEquals(3, new Selector().searchWithoutChaining(systemViewController, "StackView.column").size());
    }

    @Test
    public void testSearchByCssIdentifier() {
        assertEquals("videoMode", new Selector().searchWithoutChaining(systemViewController, "#videoMode").get(0).getCssIdentifier());
    }

    @Test
    public void testCompoundSearch() {
        final Node result = new Selector().searchWithoutChaining(systemViewController, "CvarSelect#verticalSync").get(0);
        assertEquals("CvarSelect", result.getJavaClass());
        assertEquals("verticalSync", result.getCssIdentifier());
    }

    @Test
    public void testEmptySearch() {
        final List<Node> result = new Selector().searchWithoutChaining(systemViewController, "");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSearchByJavaClassWithChaining() {
        assertEquals(26, new Selector().searchWithChaining(systemViewController, "Input").size());
    }

    @Test
    public void testSearchWithChainingTwice() {
        assertEquals(7, new Selector().searchWithChaining(systemViewController, "Input CvarSelect").size());
    }

    @Test
    public void testSearchByJavaClassCssClassChain() {
        assertEquals(5, new Selector().searchWithChaining(systemViewController, "StackView .container").size());
        assertEquals(6, new Selector().searchWithChaining(systemViewController, "StackView.container").size());
    }

    @Test
    public void testSearchByLongChain() {
        assertEquals(5, new Selector().searchWithChaining(systemViewController, "#System StackView.columns StackView.column Input CvarCheckbox").size());
    }
}