package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {
    private String name;
    private int position;

    public Player(String name, int position) {
        this.name = name;
        this.position = position;
    }
    public Player(String name) {
        this.name = name;
        this.position = 1;
    }
}
