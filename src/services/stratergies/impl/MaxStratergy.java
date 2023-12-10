package services.stratergies.impl;

import services.stratergies.MovementStrategy;

public class MaxStratergy implements MovementStrategy {
    @Override
    public int calculateMove(int[] diceValues) {
        int max = diceValues[0];
        for (int i = 1; i < diceValues.length; i++) {
            max = Math.max(diceValues[i],max);
        }
        return max;
    }

}

