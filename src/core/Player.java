package core;

import java.awt.*;

/**
 * Player management class
 * used for creating and managing a player
 * @author Matej Kesl
 */
public class Player {
    private String value;
    private String name;
    private Color color;

    //constructor
    public Player(String id, String name, Color colour) {
        this.value = id;
        this.name = name;
        this.color = colour;
    }

    //getters
    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }
}