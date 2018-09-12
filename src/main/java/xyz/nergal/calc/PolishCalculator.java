package xyz.nergal.calc;

import java.util.*;
import java.util.function.ToDoubleBiFunction;

public class PolishCalculator implements Calculator {
    public static final Map<String, Operator> ops;

    private BracketValidator bracketValidator;

    static {
        ops = new HashMap<>();
        for (Operator operator : Operator.values()) {
            ops.put(operator.sign, operator);
        }
    }

    public PolishCalculator() {
        bracketValidator = new BracketValidator();
    }

    @Override
    public double calc(String calculation) {
        bracketValidator.validate(calculation);
        String notation = ShuntingYardAlgorithmParser.parse(calculation);
        return this.reversePolishNotation(notation);
    }

    /**
     * https://en.wikipedia.org/wiki/Reverse_Polish_notation
     *
     * @param input postfix operatin
     * @return result of calculation
     */
    private double reversePolishNotation(String input) {
        Deque<Double> numbers = new ArrayDeque<>();

        String[] tokens = input.split("\\s+");
        for (String token : tokens) {
            if (ops.containsKey(token)) {
                this.calcSign(numbers, ops.get(token).operation);
            } else {
                numbers.push(Double.parseDouble(token));
            }
        }
        return numbers.pop();
    }

    private void calcSign(Deque<Double> numbers, ToDoubleBiFunction<Double, Double> operation) {
        numbers.push(operation.applyAsDouble(numbers.pop(), numbers.pop()));
    }

}
