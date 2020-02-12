package util;

import model.Node;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Selector {
    static final Pattern
            wordPattern = Pattern.compile("\\w+"),
            javaClassPattern = Pattern.compile("^\\w+"),
            cssClassPattern = Pattern.compile("\\.\\w+"),
            cssIdentifierPattern = Pattern.compile("#\\w+");

    public List<Node> searchWithoutChaining(final Node node, final String compound) {
        final List<Node> result = new ArrayList<>();

        if (matchesWithoutChaining(node, compound)) {

            // The node matches.

            result.add(node);
        }

        // Searches the node's children.

        if (node.getSubviews() != null) {
            for (final Node child : node.getSubviews()) {
                result.addAll(searchWithoutChaining(child, compound));
            }
        }

        return result;
    }

    public boolean matchesWithoutChaining(final Node node, final String compound) {
        final String trimmed = compound.trim();

        final Matcher
                wordMatcher = wordPattern.matcher(trimmed),
                javaClassMatcher = javaClassPattern.matcher(trimmed),
                cssClassMatcher = cssClassPattern.matcher(trimmed),
                cssIdentifierMatcher = cssIdentifierPattern.matcher(trimmed);

        if (!wordMatcher.find()) {

            // The query contains no words.

            return false;
        }

        while (javaClassMatcher.find()) {
            if (node.getJavaClass() == null || !node.getJavaClass().equals(javaClassMatcher.group())) {

                // The node does not contain the Java class.

                return false;
            }
        }

        while (cssIdentifierMatcher.find()) {
            if (node.getCssIdentifier() == null || !node.getCssIdentifier().equals(cssIdentifierMatcher.group().substring(1))) {

                // The node does not contain the css identifier.

                return false;
            }
        }

        while (cssClassMatcher.find()) {
            if (node.getCssClassNames() == null || !node.getCssClassNames().contains(cssClassMatcher.group().substring(1))) {

                // The node does not contain the css class.

                return false;
            }
        }

        // The node contains the Java class, the css identifier, and all css classes.

        return true;
    }


    public List<Node> searchWithChaining(final Node node, final String query) {
        final Set<Node> resultSet = new HashSet<>(searchWithChaining(node, Arrays.asList(query.trim().split("\\s+"))));
        return new ArrayList<>(resultSet);
    }

    private List<Node> searchWithChaining(final Node node, final List<String> compounds) {
        if (compounds.isEmpty()) {
            return Collections.emptyList();
        }

        final LinkedList<String> compoundsCopy = new LinkedList<>(compounds);

        // Contains the nodes that match the first compound selector in the chain.

        final List<Node> shallowMatches = searchWithoutChaining(node, compoundsCopy.removeFirst());

        if (compoundsCopy.isEmpty()) {

            // There are no more compound selectors, so the result is returned.

            return shallowMatches;
        }


        final List<Node> subviews = new ArrayList<>();

        for (final Node shallowMatch : shallowMatches) {
            if (shallowMatch.getSubviews() != null) {
                subviews.addAll(shallowMatch.getSubviews());
            }
        }

        final List<Node> result = new ArrayList<>();

        for (final Node subview : subviews) {
            result.addAll(searchWithChaining(subview, compoundsCopy));
        }

        return result;
    }
}
