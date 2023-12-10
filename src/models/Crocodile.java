package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Crocodile extends SpecialObject {
    public Crocodile(int position) {
        super(position);
    }

    public int getNewPosition(int playerPosition) {
        if(playerPosition - position > 0){
            return playerPosition - position;
        }
        return 1;
    }

}
