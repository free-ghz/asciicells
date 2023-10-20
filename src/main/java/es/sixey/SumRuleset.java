package es.sixey;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SumRuleset implements Ruleset {
    private final Map<Integer, Integer> stateOfSum;

    public SumRuleset(Map<Integer, Integer> ruleMap) {
        stateOfSum = ruleMap;
    }

    @Override
    public Cell lookup(Kernel kernel) {
        var newState = stateOfSum.get(kernel.getSum());
        if (newState == null) throw new RuntimeException("No!");
        return new Cell(newState);
    }

    public static SumRuleset generateArbitrary(int maxState, int kernelDiameter) {
        var cellsLength = kernelDiameter * kernelDiameter;
        var maxSum = cellsLength * (maxState - 1);
        var map = new HashMap<Integer, Integer>();
        var random = new Random();
        for (int i = 0; i < maxSum; i++) {
            map.put(i, random.nextInt(maxState));
        }
        return new SumRuleset(map);
    }
}
