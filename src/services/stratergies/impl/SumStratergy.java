package services.stratergies.impl;

import services.stratergies.MovementStrategy;

public class SumStratergy implements MovementStrategy {
    @Override
    public int calculateMove(int[] diceValues) {
        int sum = 0;
        for (int value : diceValues) {
            sum += value;
        }
        return sum;
    }
}
