import com.fasterxml.jackson.databind.ObjectMapper;
import services.BoardService;
import services.DiceService;
import services.GameService;
import services.PlayerService;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        Scanner sc = new Scanner(System.in);
        try {
            GameConfiguration config = mapper.readValue(new File("src/config.json"), GameConfiguration.class);
            int numberOfPlayers = config.getNumberOfPlayers();
            int boardSize = config.getBoardSize();
            int numberOfSnakes = config.getNumberOfSnakes();
            int numberOfLadders = config.getNumberOfLadders();
            int numberOfDice = config.getNumberOfDice();
            String movementStrategy = config.getMovementStrategy();


            PlayerService playerService = new PlayerService();
            playerService.addPlayer("Gaurav",1);
            playerService.addPlayer("Sagar",1);

            for(int i = 0 ; i< numberOfPlayers;i++){
                System.out.println("enter player name and position");
                String name = sc.next();
                int position = sc.nextInt();
                playerService.addPlayer(name,position);

            }


            BoardService boardService = new BoardService(boardSize,playerService);
            boardService.addSnake(62,5);
            boardService.addSnake(33,6);
            boardService.addSnake(49,9);
            boardService.addSnake(88,16);
            boardService.addSnake(41,20);
            boardService.addSnake(56,53);
            boardService.addSnake(98,64);
            boardService.addSnake(93,73);
            boardService.addSnake(95,75);

            for(int i = 0 ; i< numberOfSnakes;i++){

                System.out.println("Enter the head position of the snake:");
                int head = sc.nextInt();

                System.out.println("Enter the tail position of the snake:");
                int tail = sc.nextInt();
                boardService.addSnake(head,tail);

            }



            for(int i = 0 ; i< numberOfLadders;i++){

                System.out.println("Enter the bottom position of the ladder:");
                int bottom = sc.nextInt();

                System.out.println("Enter the top position of the ladder:");
                int top = sc.nextInt();
                boardService.addLadder(bottom,top);

            }

            DiceService diceService = new DiceService(numberOfDice,movementStrategy);
            GameService gameService = new GameService(boardService, playerService, diceService);
            gameService.playGame();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}