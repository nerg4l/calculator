package xyz.nergal.calc;

import java.util.Deque;
import java.util.LinkedList;

public class ShuntingYardAlgorithmParser {

    private ShuntingYardAlgorithmParser() {
    }

    /**
     * https://en.wikipedia.org/wiki/Shunting-yard_algorithm
     *
     * @param input infix operation
     * @return reverse polish notation (postfix operation)
     */
    public static String parse(String input) {
        StringBuilder output = new StringBuilder();
        Deque<String> stack = new LinkedList<>();

        String[] tokens = tokenizer(input);
        for (String token : tokens) {
            if (PolishCalculator.ops.containsKey(token)) {
                while (!stack.isEmpty() && isHigherPrecedence(token, stack.peek())) {
                    output.append(stack.pop()).append(' ');
                }
                stack.push(token);
                continue;
            }
            if (token.equals("(")) {
                stack.push(token);
                continue;
            }
            if (token.equals(")")) {
                while (!stack.peek().equals("(")) {
                    output.append(stack.pop()).append(' ');
                }
                stack.pop();
                continue;
            }
            output.append(token).append(' ');
        }

        while (!stack.isEmpty())
            output.append(stack.pop()).append(' ');

        return output.toString();
    }

    private static String[] tokenizer(String input) {
        String operators = String.join("", PolishCalculator.ops.keySet());
        return input.split("(?<=[" + operators + "()])|(?=[" + operators + "()])");
    }

//    private String[] tokenizer(String input) {
//        List<String> tokens = new ArrayList<>();
//        StringBuilder token = new StringBuilder();
//        for (char c : input.toCharArray()) {
//            if (Character.isWhitespace(c)) {
//                continue;
//            }
//            if (Character.isDigit(c)) {
//                token.append(c);
//                continue;
//            }
//            if (token.length() > 0) {
//                tokens.add(token.toString());
//                token.setLength(0);
//            }
//            token.append(c);
//            tokens.add(token.toString());
//            token.setLength(0);
//        }
//        tokens.add(token.toString());
//        return tokens.toArray(new String[tokens.size()]);
//    }

    private static boolean isHigherPrecedence(String op, String sub) {
        return PolishCalculator.ops.containsKey(sub)
                && PolishCalculator.ops.get(sub).precedence >= PolishCalculator.ops.get(op).precedence;
    }
}
