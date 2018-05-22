package xyz.nergal.calc;

import java.util.*;

/**
 * http://people.inf.elte.hu/keszeiabel/algo1/bead/
 * https://brilliant.org/wiki/shunting-yard-algorithm/
 * https://ir.cwi.nl/pub/9251 @page 23
 */
public class ShuntingYardCalculator implements Calculator {

    private final Map<String, Operator> ops;

    ShuntingYardCalculator() {
        ops = new HashMap<>();
        ops.put("+", Operator.ADD);
        ops.put("-", Operator.SUBTRACT);
        ops.put("*", Operator.MULTIPLY);
        ops.put("/", Operator.DIVIDE);
    }


    public double calc(String calculation) {
        try {
            return evalPostfix(toPostfix(beautifyString(calculation)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private String beautifyString(String str){
        return str
                .trim()
                .replaceAll(" ", "")
                .replaceAll("([0-9]+|\\(|\\)|\\+|-|\\*|/)", "$1 ")
                .trim();
    }

    private double evalPostfix(String postfix) {
        Double result = 0.0;
        Deque<String> stack = new LinkedList<>();
        StringTokenizer tokens = new StringTokenizer(postfix, " ");
        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken();
            if (ops.containsKey(token)) {
                double num2 = Double.parseDouble(stack.pop());
                double num1 = Double.parseDouble(stack.pop());
                switch (token) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "/":
                        result = num1 / num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    default: break;
                }
                stack.push(result.toString());
            } else {
                stack.push(token);
            }
        }
        return result;
    }

    private String toPostfix(String infix) throws Exception {
        StringTokenizer tokens = new StringTokenizer(infix, " ");
        Deque<String> stack = new LinkedList<>();
        List<String> output = new ArrayList<>();
        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken();
            if (token.matches("[0-9]+")) { // if number
                output.add(token);
            } else if (ops.containsKey(token)) { // if operand
                String op;
                while (!stack.isEmpty() && ops.containsKey(op = stack.peek())) {
                    if (ops.get(op).precedence >= ops.get(token).precedence) {
                        output.add(stack.pop());
                    } else {
                        break;
                    }
                }
                stack.push(token);
            } else if (token.equals("(")) { // if opening parenthesis
                stack.push(token);
            } else if (token.equals(")")) { // if closing parenthesis
                String top = "";
                while (!stack.isEmpty() && !(top = stack.pop()).equals("(")) {
                    output.add(top);
                }
                if (!top.equals("(")) {
                    throw new Exception("Missing opening parenthesis.");
                }
            }
        }
        while (!stack.isEmpty()) {
            String top = stack.pop();
            if (!ops.containsKey(top)) {
                throw new Exception("Missing closing parenthesis.");
            }
            output.add(top);
        }
        return String.join(" ", output);
    }
}