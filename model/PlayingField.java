package Example.model;

import Example.model.PlayingFieldTile;
import java.util.Random;

/**
* This class describes the playingfield.
* This class generates a playingfield, fills it with
* Trees, sets the player and let him move.
* It's a public class, so it can be used in the whole
* package.
* @author Lukas Richter
* @version 2020 May 10
*/
public class PlayingField {

	/**generated field*/
	private PlayingFieldTile field[][] = new PlayingFieldTile[10][10];
	/**generates position array*/
	private PlayingFieldTile position[][] = new PlayingFieldTile[10][10];
	/**direction the player is looking at*/
	private Direction view;
	/**x-coordination of player*/
	private int px = -1;
	/**y-coordination of player*/
	private int py = -1;
	/**counts how often player was set*/
	private int count = 0;
	/**counts items*/
	private int item_apples = 0;

	/**
	* This method sets a specific tile on the field to 
	* a wanted tile (tree or ground).
	* It's a public method, so it can be used in the whole
	* package.
	* @param x x-coordination of tile
	* @param y y-coordination of tile
	* @param pf wanted PlayingFieldTile
	*/
	public void setPlayingFieldTile(int x, int y, PlayingFieldTile pf) {
		if (pf == PlayingFieldTile.GROUND) {
			this.field[x][y] = PlayingFieldTile.GROUND;
		} else if (pf == PlayingFieldTile.TREE) {
			this.field[x][y] = PlayingFieldTile.TREE;
			genApple(x, y);
		} else if (pf == PlayingFieldTile.APPLE) {
			this.field[x][y] = PlayingFieldTile.APPLE;
		}
	}

	/**
	* Gets PlayingFieldTile array.
	* It's a public method, so it can be used in the whole
	* package.
	* @return array with coordinations
	*/
	public PlayingFieldTile[][] getPlayingFieldTile() {
		return this.field;
	}

	/**
	* Sets new player direction.
	* It's a public method, so it can be used in the whole
	* package.
	* @param d direction
	*/
	public void setDirection(Direction d) {
		this.view = d;
	}

	/**
	* Gets direction of player.
	* It's a public method, so it can be used in the whole
	* package.
	* @return direction
	*/
	public Direction getDirection() {
		return this.view;
	}

	/**
	* Sets position for the player, if
	* player was set by setPlayer. If player 
	* was not set the program stops.
	* It's a public method, so it can be used in the whole
	* package.
	* @param x new x-coordinate
	* @param y new y-coordinate
	*/
	public void setPosition(int x, int y) {
		if (px >= 0 && py >= 0) {
			if (x < 10 && x >= 0 && y < 10 && y >= 0) {
				if (this.field[x][y] != PlayingFieldTile.GROUND) {
					System.out.println("Can't place player here.");
				} else { 
					if (count == 0) {
						this.position[x][y] = PlayingFieldTile.PLAYER;
						this.field[x][y] = PlayingFieldTile.PLAYER;
						px = x;
						py = y;
						count++;
					} else if (count > 0) {
						this.position[x][y] = PlayingFieldTile.PLAYER;
						this.field[x][y] = PlayingFieldTile.PLAYER;
						this.field[px][py] = PlayingFieldTile.GROUND;
						px = x;
						py = y;
						count++;
					}
				}
			} else {
				System.out.println("Can't place player here.");
				System.exit(1);
			}
		} else {
			System.out.println("Set player first.");
			System.exit(1);
		}
	}

	/**
	* Sets the player on a specific position and a specific
	* direction on the field.
	* It's a public method, so it can be used in the whole
	* package.
	* @param x x-coordination
	* @param y y-coordination
	* @param d Direction
	*/
	public void setPlayer(int x, int y, Direction d) {
		if (count == 0) {
			px = x;
			py = y;
			setPosition(x, y);
			setDirection(d);
		} else {
			System.out.println("Player already exists.");
		}
	}

	/**
	* Sets the player on a random position and a random 
	* direction on the field.
	* It's a public method, so it can be used in the whole
	* package.
	*/
	public void setRandomPlayer() {
		if (count == 0) {
			Random x = new Random();
			Random y = new Random();
			int randomx = x.nextInt(10);
			int randomy = y.nextInt(10);
			Direction randomd = Direction.getRandomDirection();
			px = randomx;
			py = randomy;
			setPosition(randomx, randomy);
			setDirection(randomd);
		} else {
			System.out.println("Player already exists.");
		}
	}

	/**
	* This method generates a specific amount of trees
	* (1 - 100) and places them at a random position on the field.
	* @param t amount of trees
	*/
	public void randTrees(int t) {
		if (t <= 100 && t >= 1) {
			Random x = new Random();
			Random y = new Random();
			int tx = 0;
			int ty = 0;
			while (t > 0) {
				int randomx = x.nextInt(10);
				int randomy = y.nextInt(10);
				if (this.field[randomx][randomy] == PlayingFieldTile.GROUND) {
					setPlayingFieldTile(randomx, randomy, PlayingFieldTile.TREE);
					genApples(randomx, randomy);
					t = t - 1;
				}
			}
		} else {
			System.out.println("Insert number between 1 and 100 for randTrees.");
			System.exit(1);
		}
	}

	public void genApples(int tx, int ty) {
		Random rand = new Random();
		Random rand2 = new Random();
		int amount = rand.nextInt(8);
		int a = rand2.nextInt(3) - 1;
		int ax = tx + a;
		int ay = ty + a;
		if (amount == 0) {

		} else {
			while (amount > 0) {
				if (canWalk(ax, ay) == true) {
					setPlayingFieldTile(ax, ay, PlayingFieldTile.APPLE);
					amount--;
				} else {
					amount--;
				}	
			}
		}
	}

	public void genApple(int tx, int ty) {
		int amount = 1;
		int ax = tx + 1;
		int ay = ty + 1;
		if (canWalk(ax, ay) == true) {
			setPlayingFieldTile(ax, ay, PlayingFieldTile.APPLE);
			amount--;
		} else {
			amount--;
		}	
	}

	/**
	* Gets position of the player.
	* It's a public method, so it can be used in the whole
	* package.
	* @return position array with position
	*/
	public PlayingFieldTile[][] getPosition() {
		return this.position;
	}

	/**
	* Ends program if field is not 10x10.
	* It's a public method, so it can be used in the whole
	* package.
	@param field PlayingFieldTile array
	*/
	public PlayingField(PlayingFieldTile[][] field) {
		if (field[0].length != 10 && field[1].length != 10) {
			System.exit(1);
		}
	}

	/**
	* Generates an empty field with just
	* ground fieldtiles.
	* It's a public method, so it can be used in the whole
	* package.
	*/
	public PlayingField() {
		for (int i = 0; i < 10; i++){
    		for (int j = 0; j < 10; j++){
        		setPlayingFieldTile(i, j, PlayingFieldTile.GROUND);
    		} 
		}
	}

	/**
	* Returns true if entered coordinations are on
	* the field and ground (so the player is able to
	* walk on that field).
	* It's a public method, so it can be used in the whole
	* package.
	* @param x x-coordinate
	* @param y y-coordinate
	* @return true or false
	*/
	public boolean canWalk(int x, int y) {
		if (0 <= x && x <= 9 && 0 <= y && y <= 9) {
			if (this.field[x][y] == PlayingFieldTile.GROUND) {
				return true;
			} else if (this.field[x][y] == PlayingFieldTile.APPLE) {
				return true;
			} else return false;
		} else return false;
	}

	/**
	* This method generates a string. 
	* This string builds the playingfield and
	* inclueds all trees, empty fields (ground) and the player.
	* It's a public method, so it can be used in the whole
	* package.
	* @return playingfield
	*/
	public String str() {
		String s = "";
		String umbruch = "\n";
		String north = "            NORTH            ";
		String east = "";
		String south = "            SOUTH            ";
		String west = "";
		String ground = "  ______________________________";
		String items = "";
		if (item_apples == 1) {
			items = "Items: " + item_apples + " apple";
		} else {
			items = "Items: " + item_apples + " apples";
		}
		int countx = 0;
		int county = 0;
		s = s + "  " + north + "\n";
		s = s + "  ";
		for (int i = 0; i<field.length; i++){
    		for (int j = 0; j<field[i].length; j++) {
				if (this.field[j][i] == PlayingFieldTile.GROUND) {
					s = s + "[ ]";
					countx++;
					if (countx == 10 && county < 2) {
						s = s + umbruch + "  ";
						countx = 0;
						county++;
					} else if (countx == 10 && county == 2) {
						s = s + umbruch + " W";
						countx = 0;
						county++;
					} else if (countx == 10 && county == 3) {
						s = s + "E" + umbruch + " E";
						countx = 0;
						county++;
					} else if (countx == 10 && county == 4) {
						s = s + "A" + umbruch + " S";
						countx = 0;
						county++;
					} else if (countx == 10 && county == 5) {
						s = s + "S" + umbruch + " T";
						countx = 0;
						county++;
					} else if (countx == 10 && county == 6) {
						s = s + "T" + umbruch + "  ";
						countx = 0;
						county++;
					} else if (countx == 10 && county < 10) {
						s = s  + umbruch + "  ";
						countx = 0;
						county++;
					}
				} else if (this.field[j][i] == PlayingFieldTile.TREE) {
					s = s + "[♣]";
					countx++;
					if (countx == 10 && county < 2) {
						s = s + umbruch + "  ";
						countx = 0;
						county++;
					} else if (countx == 10 && county == 2) {
						s = s + umbruch + " W";
						countx = 0;
						county++;
					} else if (countx == 10 && county == 3) {
						s = s + "E" + umbruch + " E";
						countx = 0;
						county++;
					} else if (countx == 10 && county == 4) {
						s = s + "A" + umbruch + " S";
						countx = 0;
						county++;
					} else if (countx == 10 && county == 5) {
						s = s + "S" + umbruch + " T";
						countx = 0;
						county++;
					} else if (countx == 10 && county == 6) {
						s = s + "T" + umbruch + "  ";
						countx = 0;
						county++;
					} else if (countx == 10 && county < 10) {
						s = s  + umbruch + "  ";
						countx = 0;
						county++;
					} 
				} else if (this.field[j][i] == PlayingFieldTile.APPLE) {
					s = s + "[৹]";
					countx++;
					if (countx == 10 && county < 2) {
						s = s + umbruch + "  ";
						countx = 0;
						county++;
					} else if (countx == 10 && county == 2) {
						s = s + umbruch + " W";
						countx = 0;
						county++;
					} else if (countx == 10 && county == 3) {
						s = s + "E" + umbruch + " E";
						countx = 0;
						county++;
					} else if (countx == 10 && county == 4) {
						s = s + "A" + umbruch + " S";
						countx = 0;
						county++;
					} else if (countx == 10 && county == 5) {
						s = s + "S" + umbruch + " T";
						countx = 0;
						county++;
					} else if (countx == 10 && county == 6) {
						s = s + "T" + umbruch + "  ";
						countx = 0;
						county++;
					} else if (countx == 10 && county < 10) {
						s = s  + umbruch + "  ";
						countx = 0;
						county++;
					} 
				} else if (this.field[j][i] == PlayingFieldTile.PLAYER) {
					String northup = "[↑]";
					String southdown = "[↓]";
					String westleft = "[←]";
					String eastright = "[→]";
					if (view == Direction.NORTH) {
						s = s + northup;
					} else if (view == Direction.SOUTH) {
						s = s + southdown;
					} else if (view == Direction.WEST) {
						s = s + westleft;
					} else if (view == Direction.EAST) {
						s = s + eastright;
					}
					countx++;
					if (countx == 10 && county < 2) {
						s = s + umbruch + "  ";
						countx = 0;
						county++;
					} else if (countx == 10 && county == 2) {
						s = s + umbruch + " W";
						countx = 0;
						county++;
					} else if (countx == 10 && county == 3) {
						s = s + "E" + umbruch + " E";
						countx = 0;
						county++;
					} else if (countx == 10 && county == 4) {
						s = s + "A" + umbruch + " S";
						countx = 0;
						county++;
					} else if (countx == 10 && county == 5) {
						s = s + "S" + umbruch + " T";
						countx = 0;
						county++;
					} else if (countx == 10 && county == 6) {
						s = s + "T" + umbruch + "  ";
						countx = 0;
						county++;
					} else if (countx == 10 && county < 10) {
						s = s  + umbruch + "  ";
						countx = 0;
						county++;
					} 
				}
			}
		}
		s = s + south + "\n" + items + "\n" + ground + "\n";
		return s;
	}

	/**
	* This method checks if the player is able to
	* move forward and returns true if it's possible to go
	* one field forward in the direction he's looking at.
	* It's a public method, so it can be used in the whole
	* package.
	* @return true if he can walk and false if he can't
	*/
	public boolean playerCanWalk() {
		switch(getDirection()){
			case NORTH:
				if (canWalk(px, py - 1) == true) {
					if (this.field[px][py - 1] == PlayingFieldTile.GROUND) {
						return true;
					} else if (this.field[px][py - 1] == PlayingFieldTile.APPLE) {
						return true;
					} else {
						System.out.println("Can't walk in this direction.");
						return false;
					}
				} else {
					System.out.println("Can't walk in this direction.");
					return false;
				}
			case SOUTH:
				if (canWalk(px, py + 1) == true) {
					if (this.field[px][py + 1] == PlayingFieldTile.GROUND) {
						return true;
					} else if (this.field[px][py + 1] == PlayingFieldTile.APPLE) {
						return true;
					} else {
						System.out.println("Can't walk in this direction.");
						return false;
					}
				} else {
					System.out.println("Can't walk in this direction.");
					return false;
				}
			case WEST:
				if (canWalk(px - 1, py) == true) {
					if (this.field[px - 1][py] == PlayingFieldTile.GROUND) {
						return true;
					} else if (this.field[px - 1][py] == PlayingFieldTile.APPLE) {
						return true;
					} else {
						System.out.println("Can't walk in this direction.");
						return false;
					}
				} else {
					System.out.println("Can't walk in this direction.");
					return false;
				}
			case EAST:
				if (canWalk(px + 1, py) == true) {
					if (this.field[px + 1][py] == PlayingFieldTile.GROUND) {
						return true;
					} else if (this.field[px + 1][py] == PlayingFieldTile.APPLE) {
						return true;
					} else {
						System.out.println("Can't walk in this direction.");
						return false;
					}
				} else {
					System.out.println("Can't walk in this direction.");
					return false;
				}
			default:
				return false;		
		}
	}

	/**
	* This method lets the player move forward and 
	* returns true if player walked one field forward
	* in the direction he's looking at.
	* It's a public method, so it can be used in the whole
	* package.
	* @return true if he walked and false if he couldn't
	*/
	public boolean playerWalk() {
		switch(getDirection()){
			case NORTH:
				if (playerCanWalk() == true) {
					if (py - 1 <= 9 && py - 1 >= 0) {
						if (this.field[px][py - 1] == PlayingFieldTile.GROUND){
							setPlayingFieldTile(px, py, PlayingFieldTile.GROUND);
							setPosition(px, py - 1);
							return true;
						} else if (this.field[px][py - 1] == PlayingFieldTile.APPLE) {
							setPlayingFieldTile(px, py, PlayingFieldTile.GROUND);
							setPlayingFieldTile(px, py - 1, PlayingFieldTile.GROUND);
							setPosition(px, py - 1);
							item_apples++;
							return true;
						}
					} else {
						setPosition(px, py);
						return false;
					}
				} else return false;
			case SOUTH:
				if (playerCanWalk() == true) {
					if (py + 1 <= 9 && py + 1 >= 0) {
						if (this.field[px][py + 1] == PlayingFieldTile.GROUND){
							setPlayingFieldTile(px, py, PlayingFieldTile.GROUND);
							setPosition(px, py + 1);
							return true;
						} else if (this.field[px][py + 1] == PlayingFieldTile.APPLE) {
							setPlayingFieldTile(px, py, PlayingFieldTile.GROUND);
							setPlayingFieldTile(px, py + 1, PlayingFieldTile.GROUND);
							setPosition(px, py + 1);
							item_apples++;
							return true;
						}
					} else {
						setPosition(px, py);
						return false;
					}
				} else return false;
			case WEST:
				if (playerCanWalk() == true) {
					if (px - 1 <= 9 && px - 1 >= 0) {
						if (this.field[px - 1][py] == PlayingFieldTile.GROUND){
							setPlayingFieldTile(px, py, PlayingFieldTile.GROUND);
							setPosition(px - 1, py);
							return true;
						} else if (this.field[px - 1][py] == PlayingFieldTile.APPLE) {
							setPlayingFieldTile(px, py, PlayingFieldTile.GROUND);
							setPlayingFieldTile(px - 1, py, PlayingFieldTile.GROUND);
							setPosition(px - 1, py);
							item_apples++;
							return true;
						}
					} else {
						setPosition(px, py);
						return false;
					}
				} else return false;
			case EAST:
				if (playerCanWalk() == true) {
					if (px + 1 <= 9 && px + 1 >= 0) {
						if (this.field[px + 1][py] == PlayingFieldTile.GROUND){
							setPlayingFieldTile(px, py, PlayingFieldTile.GROUND);
							setPosition(px + 1, py);
							return true;
						} else if (this.field[px + 1][py] == PlayingFieldTile.APPLE) {
							setPlayingFieldTile(px, py, PlayingFieldTile.GROUND);
							setPlayingFieldTile(px + 1, py, PlayingFieldTile.GROUND);
							setPosition(px + 1, py);
							item_apples++;
							return true;
						}
					} else {
						setPosition(px, py);
						return false;
					}
				} else return false;
			default:
				return false;		
		}
	}

	/**
	* This method lets the player turn right on the field he's on 
	* and returns true when he turned.
	* It's a public method, so it can be used in the whole
	* package.
	* @return true when player turned right
	*/
	public boolean playerTurnRight() {
		switch(getDirection()){
			case NORTH:
				setDirection(Direction.EAST);
				return true;
			case SOUTH:
				setDirection(Direction.WEST);
				return true;
			case WEST:
				setDirection(Direction.NORTH);
				return true;
			case EAST:
				setDirection(Direction.SOUTH);
				return true;
			default:
				return false;		
		}
	}

	/**
	* This method lets the player turn left on the field he's on 
	* and returns true when he turned.
	* It's a public method, so it can be used in the whole
	* package.
	* @return true when player turned left
	*/
	public boolean playerTurnLeft() {
		switch(getDirection()){
			case NORTH:
				setDirection(Direction.WEST);
				return true;
			case SOUTH:
				setDirection(Direction.EAST);
				return true;
			case WEST:
				setDirection(Direction.SOUTH);
				return true;
			case EAST:
				setDirection(Direction.NORTH);
				return true;
			default:
				return false;		
		}
	}
}
