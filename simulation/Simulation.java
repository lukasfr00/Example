package Example.simulation;

import Example.model.*;
import Example.simulation.*;
import Example.util.*;

/**
* This class generates a simulation of a
* run through the playingfield.
* @author Lukas Richter
* @version 2020 May 12
*/

public class Simulation {
	/**array with actions the player should do*/
	private Action[] action;
	/**playing field*/
	private PlayingField field = new PlayingField();
	/**counter for how many actions are in the action array*/
	private int count = 0;

	/**
	* Gets a playingfield and an array filled with actions
	* and set them as membervariables.
	* It's a public method, so it can be used in the whole
	* package.
	* @param f playingfield
	* @param act array filled with actions
	*/
	public Simulation(PlayingField f, Action[] act) {
		this.field = f;
		this.action = act;
	}

	/**
	* Gets a playingfield and sets it as membervariable.
	* Generates an empty action array with length 100.
	* It's a public method, so it can be used in the whole
	* package.
	* @param f playingfield
	*/
	public Simulation(PlayingField f) {
		this(f, new Action[100]);
	}

	/**
	* Adds first action to the action array.
	* It's a public method, so it can be used in the whole
	* package.
	* @param a action
	*/
	public void setAction(Action a) {
		if (count == 0) {
			this.action[0] = a;
			count = 1;
		} else {
			System.out.println("No setAction after setAction or randActions!");
			System.exit(1);
		}
	}

	/**
	* Gets action array.
	* It's a public method, so it can be used in the whole
	* package.
	* @return action array
	*/
	public Action[] getAction() {
		return this.action;
	}

	/**
	* Adds action to the action array at last position.
	* It's a public method, so it can be used in the whole
	* package.
	* @param action action
	* @return true or false
	*/
	public boolean addAction(Action action) {
		if (this.action.length > 0) {
			if (count <= 99) {
				this.action[count + 1] = action;
				count++;
				return true;
			} else {
				System.out.println("Maximum of 99 actions.");
				return false;
			} 
		} else {
			System.out.println("You have to setAction first.");
			return false;
		}
	}

	/**
	* Generates a Action[] with random actions. The amount of
	* actions depends on the inserted value.
	* It's a public method, so it can be used in the whole
	* package.
	* @param a amount of actions
	* @return action action array
	*/
	public Action[] randActions(int a) {
		if (a <= 100 && a > 0) {
			if (count == 0) {
				Action movement = Action.getRandomAction();
				setAction(movement);
				for (int i = 1; i < a; i++) {
					Action movement2 = Action.getRandomAction();
					addAction(movement2);
				}
				count = a;
				return this.action;
			} else if (count + a <= 100) {
				for (int i = count; i < a; i++) {
					Action movement = Action.getRandomAction();
					addAction(movement);
				}
				count = count + a;
				return this.action;
			} else {
				System.out.println("Maximum of 99 actions.");
				Action[] nullArray = new Action[0];
				return nullArray;
			}
		} else {
			System.out.println("Maximum of 99 actions.");
			Action[] nullArray = new Action[0];
			return nullArray;
		}
	}

	/**
	* Set's given field to membervariable.
	* It's a public method, so it can be used in the whole
	* package.
	* @param f field
	*/
	public void setPlayingField(PlayingField f) {
		this.field = f;
	}

	/**
	* Get's playingfield.
	* It's a public method, so it can be used in the whole
	* package.
	* @return field field
	*/
	public PlayingField getPlayingField() {
		return this.field;
	}

	/**
	* Prints the field on the console, executes
	* given actions and prints updated field after
	* every action until no more actions left.
	* It's a public method, so it can be used in the whole
	* package.
	*/
	public void execute(PlayingField field, Simulation s) {
		field.setRandomPlayer();
		field.randTrees(30);
		//Simulation s = new Simulation(field);
		s.randActions(15);
		System.out.println(this.field.str());
		for (int i = 0; i < this.action.length; i++) {
			if (this.action[i] == Action.MOVE_FORWARD) {
				System.out.println("Action: MOVE_FORWARD \n");
				this.field.playerWalk();
				System.out.println(this.field.str());
			} else if (this.action[i] == Action.TURN_RIGHT) {
				System.out.println("Action: TURN_RIGHT \n");
				this.field.playerTurnRight();
				System.out.println(this.field.str());
			} else if (this.action[i] == Action.TURN_LEFT) {
				System.out.println("Action: TURN_LEFT \n");
				this.field.playerTurnLeft();
				System.out.println(this.field.str());
			}
		}
	}
}
