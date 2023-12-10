package services;

import services.stratergies.MovementStrategy;
import services.stratergies.impl.MaxStratergy;
import services.stratergies.impl.MinStratergy;
import services.stratergies.impl.SumStratergy;

import java.util.Random;

public class DiceService {
    private final int numberOfDice;
    private final Random random;
    private final MovementStrategy movementStrategy;


    public DiceService(int numberOfDice) {
        this.numberOfDice = numberOfDice;
        this.random = new Random();
        this.movementStrategy = new SumStratergy(); // Default to SumStrategy
    }

    public DiceService(int numberOfDice, String movementStrategy) {
        this.numberOfDice = numberOfDice;
        this.random = new Random();
        this.movementStrategy = getStratergy(movementStrategy);
    }

    public MovementStrategy getStratergy(String stratergy){
        if(stratergy.equals("SUM")){
            return new SumStratergy();
        }
        else if(stratergy.equals("MAX")){
            return new MaxStratergy();
        }
        return new MinStratergy();

    }


    public int rollDice() {
        int maxDieValue = 6; // Assuming a standard six-sided die
        int[] diceValues = new int[numberOfDice];

        for (int i = 0; i < numberOfDice; i++) {
            diceValues[i] = random.nextInt(maxDieValue) + 1; // Generate random number between 1 to 6
        }

        return movementStrategy.calculateMove(diceValues);
    }
}
