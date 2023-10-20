package es.sixey;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PositionalRuleset  implements Ruleset {
    private final Map<Integer, Integer> states;
    private final int kernelDiameter;
    private final int numStates;

    public PositionalRuleset(Map<Integer, Integer> ruleMap, int kernelDiameter, int numStates) {
        states = ruleMap;
        this.kernelDiameter = kernelDiameter;
        this.numStates = numStates;
    }

    @Override
    public int lookup(Kernel kernel) {
        int state = 0;
        for(int y = 0; y < kernelDiameter; y++) {
            for(int x = 0; x < kernelDiameter; x++) {
                var address = (y*kernelDiameter) + x;
                var flag = (int) Math.pow(address, numStates);
                state += flag * kernel.cellAt(x, y).getState();
            }
        }
        var newState = states.get(state);
        if (newState == null) throw new RuntimeException("No rule for sum " + state);
        return newState;
    }

    public static PositionalRuleset generateArbitrary(int numStates, int kernelDiameter) {
        var cellsLength = kernelDiameter * kernelDiameter;
        var maxSum = Math.pow(numStates, cellsLength);
        var map = new HashMap<Integer, Integer>();
        var random = new Random();
        System.out.print("RULE: ");
        for (int i = 0; i <= maxSum; i++) {
            var state = random.nextInt(numStates + 5);
            if (state >= numStates) state = 0; // cheating some more
            map.put(i, state);
            System.out.print(state);
        }

        return new PositionalRuleset(map, kernelDiameter, numStates);
    }
}