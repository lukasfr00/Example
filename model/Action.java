package Example.model;

import java.util.Random;

public enum Action {
    MOVE_FORWARD, TURN_LEFT, TURN_RIGHT;

    /**
    * Pick a random value of the Action enum.
    * @return a random Action.
    */
    public static Action getRandomAction() {
        Random random = new Random();
        return values()[random.nextInt(Action.values().length)];
    }
}
