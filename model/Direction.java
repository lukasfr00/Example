package Example.model;

import java.util.Random;

public enum Direction {
    NORTH, SOUTH, EAST, WEST;

    /**
    * Pick a random value of the Direction enum.
    * @return a random Direction.
    */
    public static Direction getRandomDirection() {
        Random random = new Random();
        return values()[random.nextInt(Direction.values().length)];
    }
}
