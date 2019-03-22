package ua.lpnuai.oop.gordon03.person;

import java.io.Serializable;

public enum Sex implements Serializable {
    MEN("men"), WOMEN("women");

    Sex(String name){
        this.name = name;
    }

    private String name;
}
