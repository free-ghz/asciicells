package es.sixey;

public class Main {
    public static void main(String[] args) {
        int kernelSize = 3;
        int numberOfStates = 3;
        int boardWidth = 16;
        int boardHeight = 10;

        var ruleset = SumRuleset.generateArbitrary(numberOfStates, kernelSize);
    }
}