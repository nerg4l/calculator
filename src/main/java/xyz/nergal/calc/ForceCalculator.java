package xyz.nergal.calc;

import java.util.Deque;
import java.util.LinkedList;

public class ForceCalculator implements Calculator {
    private BracketValidator bracketValidator;

    public ForceCalculator() {
        bracketValidator = new BracketValidator();
    }

    @Override
    public double calc(String calculation) {
        bracketValidator.validate(calculation);

        Deque<Deque<Double>> resultStack = new LinkedList<>();
        resultStack.push(new LinkedList<>());
        Deque<Character> signStack = new LinkedList<>();

        double number = 0;
        char sign = '+';

        for (char c : calculation.toCharArray()) {
            if (Character.isDigit(c)) {
                number = 10 * number + (c - '0');
            }

            if (Character.isWhitespace(c) || Character.isDigit(c)) {
                continue;
            }

            switch (c) {
                case '(':
                    signStack.push(sign);
                    resultStack.push(new LinkedList<>());

                    sign = '+';
                    break;
                case ')':
                    calcResult(sign, number, resultStack.peek());

                    number = 0;
                    for (Double d : resultStack.pop()) {
                        number += d;
                    }

                    sign = signStack.pop();
                    calcResult(sign, number, resultStack.peek());

                    number = 0;
                    sign = c;
                    break;
                default:
                    calcResult(sign, number, resultStack.peek());

                    number = 0;
                    sign = c;
            }
        }

        if (number != 0) {
            calcResult(sign, number, resultStack.peek());
        }

        double result = 0;
        for (Double d : resultStack.pop()) {
            result += d;
        }
        return result;
    }

    private void calcResult(char sign, double number, Deque<Double> numberStack) {
        switch (sign) {
            case '+':
                numberStack.push(number);
                break;
            case '-':
                numberStack.push(-number);
                break;
            case '*':
                numberStack.push(numberStack.pop() * number);
                break;
            case '/':
                numberStack.push(numberStack.pop() / number);
                break;
            default:
        }
    }
}
