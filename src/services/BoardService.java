package services;

import exceptions.InvalidMoveException;
import exceptions.PlayerNotFoundException;
import lombok.Getter;
import models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class BoardService {
    private final Board board;
    private final List<Snake> snakes;

    private final Map<Integer,Integer> snakePositions;
    private final List<Ladder> ladders;

    private final Map<Integer,Integer> ladderPositions;
    private final List<SpecialObject> specialObjects;

    private PlayerService playerService;

    public BoardService(int boardSize,PlayerService playerService) {
        this.board = new Board(boardSize);
        this.snakePositions = new HashMap<>();
        this.ladderPositions = new HashMap<>();
        this.snakes = new ArrayList<>();
        this.ladders = new ArrayList<>();
        this.specialObjects = new ArrayList<>();
        this.playerService = playerService;
    }

    public void addSnake(int head, int tail) {
        snakes.add(new Snake(head, tail));
        snakePositions.put(head,tail);
    }

    public void addLadder(int bottom, int top) {
        ladders.add(new Ladder(bottom, top));
        ladderPositions.put(bottom,top);
    }

    public void addCrocodile(int pos) {
        specialObjects.add(new Crocodile(pos));
    }

    public void addMine(int pos) {
        specialObjects.add(new Mine(pos));
    }

    public void addSpecialObject(SpecialObject specialObject) {
        specialObjects.add(specialObject);
    }

    public void movePlayer(Player player, int diceValue) throws InvalidMoveException, PlayerNotFoundException {
        int currentPosition = player.getPosition();
        int newPosition = currentPosition + diceValue;

        if (newPosition > board.getBoardSize() * board.getBoardSize()) {
            throw new InvalidMoveException("Invalid move: Player cannot move beyond board limits.");
        }
        if(snakePositions.containsKey(newPosition)){
            System.out.println(player.getName() +" found a snake at " + newPosition);
            newPosition = snakePositions.get(newPosition);
        }
        if(ladderPositions.containsKey(newPosition)){
            System.out.println(player.getName() +" found a ladder at " + newPosition);
            newPosition = ladderPositions.get(newPosition);
        }
        for (SpecialObject specialObject : specialObjects) {
            if (specialObject.getPosition() == newPosition && specialObject instanceof Crocodile) {
                System.out.println(player.getName() +" found a special object croco at  " + newPosition);
                newPosition = new Crocodile(5).getNewPosition(newPosition);
                break;
            }
            if (specialObject.getPosition() == newPosition && specialObject instanceof Mine && playerService.getMineCount(player.getName()) < 2) {
                System.out.println(player.getName() +" found a special object Mine at  " + newPosition);
                playerService.increaseMineCount(player.getName());
                return;
            }
        }
        // Check if another player is already at the new position
        for (Player otherPlayer : playerService.getPlayers()) {
            if (otherPlayer != player && otherPlayer.getPosition() == newPosition) {
                otherPlayer.setPosition(1);
                System.out.println(otherPlayer.getName() + "is move to position 1 due to "+ player.getName());
            }
        }
        player.setPosition(newPosition);
        playerService.setPlayersPosition(player.getName(),newPosition);
        playerService.decreaseMineCount(player.getName());
        System.out.println(player.getName() +" rolled a " + diceValue + " and moved from " + currentPosition + " to " + newPosition);
    }

}
