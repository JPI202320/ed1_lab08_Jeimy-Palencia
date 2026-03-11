package ed.lab;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Function;

public class BTUtils {
    public static <T> TreeNode<T> parseBT(String input, Function<String, T> stringParser) {
        return new BTParser<T>(input, stringParser).parse();
    }

    private static class BTParser<T> {

        private final char[] input;
        private final Function<String, T> stringParser;
        private int i;
        private Queue<TreeNode<T>> queue;

        public BTParser(String input, Function<String, T> stringParser) {
            this.input = Optional.ofNullable(input)
                    .orElse(".")
                    .toCharArray();

            this.stringParser = stringParser;
        }

        public TreeNode<T> parse() {
            if (input[0] == '.')
                return null;

            i = 0;
            queue = new LinkedList<>();

            T value = findValue();

            TreeNode<T> root = new TreeNode<>(value);
            root.left = findNextNode();
            if (root.left != null) queue.offer(root.left);

            root.right = findNextNode();
            if (root.right != null) queue.offer(root.right);

            fillTree();

            return root;
        }

        private T findValue() {
            if (i >= input.length) return null;

            if ('.' == input[i]) {
                ++i;
                return null;
            }

            StringBuilder sb = new StringBuilder();

            while (i < input.length && input[i] != ',') {
                sb.append(input[i]);
                ++i;
            }

            return stringParser.apply(sb.toString());
        }

        private TreeNode<T> findNextNode() {
            if (i >= input.length) return null;

            if (',' == input[i]) ++i;

            T value = findValue();

            if (value == null)
                return null;

            return new TreeNode<>(value);
        }

        private void fillTree() {
            while (!queue.isEmpty()) {
                TreeNode<T> current = queue.poll();
                current.left = findNextNode();

                if (current.left != null) queue.offer(current.left);

                current.right = findNextNode();

                if (current.right != null) queue.offer(current.right);
            }
        }
    }
}
