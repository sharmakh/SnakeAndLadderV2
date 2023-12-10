import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameConfiguration {
    private int numberOfPlayers;
    private int boardSize;
    private int numberOfSnakes;
    private int numberOfLadders;
    private int numberOfDice;
    private String movementStrategy;

}