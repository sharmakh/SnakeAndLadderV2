package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mine extends SpecialObject {
    private int turnsToSkip;

    public Mine(int position) {
        super(position);
        this.turnsToSkip = 2;
    }

}
