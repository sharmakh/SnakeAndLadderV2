package test.java;

import models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.BoardService;
import services.DiceService;
import services.GameService;
import services.PlayerService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {
    private GameService gameService;

    private PlayerService playerService;

    private BoardService boardService;

    private DiceService diceService;
    private Player player1;
    private Player player2;
    private Snake snake;
    private Ladder ladder;

    @BeforeEach
    public void setUp() {
        playerService = new PlayerService();
        boardService = new BoardService(10,playerService);
        diceService = new DiceService(2,"MAX");
        gameService = new GameService(boardService,playerService,diceService);
        snake = new Snake(15, 5);
        ladder = new Ladder(8, 25);
    }

    @Test
    public void testAddPlayer() {
        playerService.addPlayer("Gaurav",1);
        playerService.addPlayer("Sagar",1);
        assertEquals(2, playerService.getPlayers().size());
    }

    @Test
    public void testAddSnake() {
        boardService.addSnake(62,5);
        boardService.addSnake(33,6);
        boardService.addSnake(49,9);
        boardService.addSnake(88,16);
        boardService.addSnake(41,20);
        boardService.addSnake(56,53);
        boardService.addSnake(98,64);
        boardService.addSnake(93,73);
        boardService.addSnake(95,75);
        assertEquals(9, boardService.getSnakes().size());
    }

    @Test
    public void testAddLadder() {
        boardService.addLadder(2,37);
        boardService.addLadder(27,46);
        boardService.addLadder(10,32);
        boardService.addLadder(51,68);
        boardService.addLadder(61,79);
        boardService.addLadder(65,84);
        boardService.addLadder(71,91);
        boardService.addLadder(81,100);

        assertEquals(8, boardService.getLadders().size());
    }

    @Test
    public void testSpecialObjectCroc() throws Exception {
        boardService.addCrocodile(8);
    }
    @Test
    public void testSpecialObjectMine() throws Exception {
        boardService.addMine(5);
    }


    @Test
    public void testStartGame() throws Exception {
        testAddPlayer();
        testAddSnake();
        testAddLadder();
        testSpecialObjectCroc();
        testSpecialObjectMine();
        gameService.playGame();
        assertTrue(gameService.isGameFinished());
        assertTrue(gameService.isPlayerWinner(playerService.getPlayers().get(0)));
        assertTrue(gameService.isPlayerWinner(playerService.getPlayers().get(1)));

    }
}
