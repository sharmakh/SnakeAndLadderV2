package services.stratergies.impl;

import services.stratergies.MovementStrategy;

public class MinStratergy implements MovementStrategy {
    @Override
    public int calculateMove(int[] diceValues) {
        int min = diceValues[0];
        for (int i = 1; i < diceValues.length; i++) {
            min = Math.max(diceValues[i],min);

        }
        return min;
    }
}