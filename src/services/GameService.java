package services;

import exceptions.InvalidMoveException;
import models.Player;

public class GameService {
    private final BoardService boardService;
    private final DiceService diceService;
    private final PlayerService playerService;

    public GameService(BoardService boardService,PlayerService playerService, DiceService diceService) {
        this.boardService = boardService;
        this.playerService = playerService;
        this.diceService = diceService;
    }


    public void playGame() throws Exception {
        while (!isGameFinished()) {
            for (Player player : playerService.getPlayers()) {
                int diceValue = diceService.rollDice();

                try {
                    boardService.movePlayer(player, diceValue);
                } catch (InvalidMoveException e) {
                    System.out.println("Invalid move for " + player.getName() + ": " + e.getMessage());
                }

                if (isPlayerWinner(player)) {
                    System.out.println(player.getName() + " wins!");
                    return;
                }
            }
        }
    }

    public boolean isGameFinished() throws Exception {
        // Check game end condition (e.g., if any player has reached the winning position)
        for (Player player : playerService.getPlayers()) {
            if (isPlayerWinner(player)) {
                return true;
            }
        }
        return false;
    }

    public boolean isPlayerWinner(Player player) throws Exception {
        int n = boardService.getBoard().getBoardSize();
        return playerService.getPlayerPosition(player.getName()) == n*n;
    }
}
