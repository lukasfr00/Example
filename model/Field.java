package Example.model;

import java.util.Random;

public enum Field {
    UP, DOWN, LEFT, RIGHT,
    UPLEFT, UPRIGHT, DOWNLEFT, DOWNRIGTH;

    /**
    * Pick a random value of the Field enum.
    * @return a random Field.
    */
    public static Field getRandomField() {
        Random random = new Random();
        return values()[random.nextInt(Field.values().length)];
    }
}
