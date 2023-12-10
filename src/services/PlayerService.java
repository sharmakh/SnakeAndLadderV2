package services;

import exceptions.PlayerNotFoundException;
import models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerService {
    private final List<Player> players;
    private final Map<String,Integer> playersPosition;

    private Map<String,Integer> mineCounts;


    public PlayerService() {
        this.players = new ArrayList<>();
        this.playersPosition = new HashMap();
        this.mineCounts = new HashMap<>();
    }

    public void addPlayer(String playerName,int position) {
        players.add(new Player(playerName, position));
        playersPosition.put(playerName,position);
        mineCounts.put(playerName,0);
    }

    public void increaseMineCount(String playerName) {
        mineCounts.put(playerName,mineCounts.getOrDefault(playerName,0)+1);
    }
    public void decreaseMineCount(String playerName) {
        mineCounts.put(playerName,0);
    }
    public int getMineCount(String playerName) {
        return mineCounts.get(playerName);
    }

    public void removePlayer(Player player) throws PlayerNotFoundException {
        if (!players.contains(player)) {
            throw new PlayerNotFoundException("Player does not exist.");
        }
        players.remove(player);
        playersPosition.remove(player.getName());
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public int getPlayerPosition(String playerName) throws PlayerNotFoundException {
        if (!playersPosition.containsKey(playerName)) {
            throw new PlayerNotFoundException("Player does not exist.");
        }
        return playersPosition.get(playerName);
    }

    public void setPlayersPosition(String playerName, int position) throws PlayerNotFoundException{
        if (!playersPosition.containsKey(playerName)) {
            throw new PlayerNotFoundException("Player does not exist.");
        }
        playersPosition.put(playerName,position);
    }

}
