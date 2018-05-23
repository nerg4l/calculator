package xyz.nergal.calc;

import java.util.function.ToDoubleBiFunction;

public enum Operator {
    ADD(1, "+", (n1, n2) -> n2 + n1),
    SUBTRACT(1, "-", (n1, n2) -> n2 - n1),
    MULTIPLY(2, "*", (n1, n2) -> n2 * n1),
    DIVIDE(2, "/", (n1, n2) -> n2 / n1);

    final int precedence;
    final String sign;
    final ToDoubleBiFunction<Double, Double> operation;

    Operator(int precedence, String sign, ToDoubleBiFunction<Double, Double> operation) {
        this.precedence = precedence;
        this.sign = sign;
        this.operation = operation;
    }
}
