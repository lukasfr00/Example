package Example;

import Example.model.*;
import Example.simulation.*;
import Example.util.*;

/**
* This class is the main class of this package.
* This class says where to fill the field with Trees
* and starts a simulation.
* @author Lukas Richter
* @version 2020 May 12
*/
public class Main {
	
	public static void main(String[] args) {

		/*
		- setRandomPlayer to place a random player
		  on the field
		- setPlayer to set at a specific point with 
		  specific direction
		- setPosition and setDirection to change
		  players position and direction
		- randTrees to generate x-times trees at
		  random positions (has to be between 1-99)
		- setPlayingFieldTile to set trees manually
		- randActions to generate random actions
		- setAction to set the first action manually
		- addAction to add an action manually
		*/

		PlayingField field = new PlayingField();
		Simulation s = new Simulation(field);
		//s.setAction(Action.MOVE_FORWARD);
		s.execute(field, s);
	}
}
