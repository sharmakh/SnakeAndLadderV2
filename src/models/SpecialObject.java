package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class SpecialObject {

    protected int position;

    public SpecialObject(int position){
        this.position = position;
    }

}
