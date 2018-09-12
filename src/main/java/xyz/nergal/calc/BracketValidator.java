package xyz.nergal.calc;

public class BracketValidator {
    public void validate(String calculation) {
        int numberOfOpenBrackets = calculation.length() - calculation.replace("(", "").length();
        int numberOfCloseBrackets = calculation.length() - calculation.replace(")", "").length();

        if (numberOfCloseBrackets != numberOfOpenBrackets) {
            throw new UnsupportedOperationException("Number of open and close brackets must mach");
        }
    }
}
