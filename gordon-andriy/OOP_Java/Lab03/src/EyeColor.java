package ua.lpnuai.oop.gordon03.person;

import java.io.Serializable;

public enum EyeColor implements Serializable {
    BLUE("blue"), BROWN("brown"), GREEN("green");

    EyeColor(String name){
        this.name = name;
    }
    String name;
}
