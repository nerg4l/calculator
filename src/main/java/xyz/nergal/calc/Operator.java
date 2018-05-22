package xyz.nergal.calc;

public enum Operator {
    ADD(1), SUBTRACT(1), MULTIPLY(2), DIVIDE(2);
    final int precedence;

    Operator(int p) {
        precedence = p;
    }
}
