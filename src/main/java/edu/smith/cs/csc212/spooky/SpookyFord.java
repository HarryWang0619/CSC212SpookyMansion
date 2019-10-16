package edu.smith.cs.csc212.spooky;

import java.util.HashMap;
import java.util.Map;

/**
 * SpookyFord, the game.
 * @author jfoley
 * 
 * 
 *
 */
public class SpookyFord implements GameWorld {
	private Map<String, Place> places = new HashMap<>();

	/**
	 * Where should the player start?
	 */
	@Override
	public String getStart() {
		return "entranceHall";
	}

	/**
	 * This constructor builds our SpookyMansion game.
	 */
	public SpookyFord() {
		Place entranceHall = insert(
				Place.create("entranceHall", "You are in the grand entrance hall of a large building.\n"
						+ "The front door is locked. How did you get here?"));
		entranceHall.addExit(new Exit("basement", "There are stairs leading down."));
		entranceHall.addExit(new Exit("secondFloor", "There are stairs leading up."));
		entranceHall.addExit(new Exit("thisFloor", "I'm a Hampshire Student So I don't know Ford Hall!"));
		entranceHall.addExit(new Exit("b43", "You heard something..."));

		String EMOJI_SKULL = "\uD83D\uDC80";
		Place closet = insert(Place.create("thisFloor", "Ahhh I Don't know this space! ("+EMOJI_SKULL+").\n"));
		closet.addExit(new Exit("entranceHall", "Go back."));

		Place basement = insert(
				Place.create("basement", "You have found the basement of the mansion.\n" + "It is darker down here.\n"
						+ "You get the sense a secret is nearby, but you only see the stairs you came from."));
		basement.addExit(new Exit("entranceHall", "There are stairs leading up."));
		basement.addExit(new Exit("teleport", "There a teleporter!"));
		basement.addExit(new Exit("dustII", "Ahrrr Run! A gun!"));
		
		Place teleport = insert(Place.terminal("teleport", "teleporter: I wonder what you expected to happen here."));
		Place helicopter = insert(Place.terminal("helicopter", "I wonder what you expected to happen here. VROOOOOMMMMMM"));
		Place wakeup = insert(Place.terminal("wakeup", "You find out that it's just a dream!"));
		Place toilet = insert(Place.terminal("toilet", "toilet: Harry Potter, That's how you get to the magic council!"));
		Place dustII = insert(Place.terminal("dustII", "dust II : You find a AK47 so you become a terrist and fear no more!"));
		Place b43 = insert(Place.terminal("b43", "I don't know Why but the B43 to Amherst is here. It will take you out, probably."));
		Place limbo = insert(Place.terminal("limbo", "You get in the Limbo of Inception!"));
		Place java = insert(Place.terminal("java", "You find out a Data Structure Class! Since JJ is still coding, everything is alright!"));

		Place secondFloor = insert(Place.create("secondFloor", "This is second floor... "));
		secondFloor.addExit(new Exit("entranceHall", "Go down to first floor."));
		secondFloor.addExit(new Exit("java", "You saw a classroom."));
		secondFloor.addExit(new Exit("toilet", "Oh there is a restroom!"));
		secondFloor.addExit(new Exit("thirdFloor", "Go upstair?"));

		Place thirdFloor = insert(Place.create("thirdFloor", "This is third floor... "));
		thirdFloor.addExit(new Exit("secondFloor", "Go downstair."));
		thirdFloor.addExit(new Exit("helicopter", "You saw a helicopter!"));
		thirdFloor.addExit(new Exit("wakeup", "Yawning..."));
		thirdFloor.addExit(new SecretExit("secretRoom", "Wanna Try?"));

		Place secretRoom = insert(Place.create("secretRoom", "It's dark inside"));
		secretRoom.addExit(new SecretExit("limbo", "You are falling down to Limbo!! The world is collapsing!"));

		// Make sure your graph makes sense!
		checkAllExitsGoSomewhere();
	}

	/**
	 * This helper method saves us a lot of typing. We always want to map from p.id
	 * to p.
	 * 
	 * @param p - the place.
	 * @return the place you gave us, so that you can store it in a variable.
	 */
	private Place insert(Place p) {
		places.put(p.getId(), p);
		return p;
	}

	/**
	 * I like this method for checking to make sure that my graph makes sense!
	 */
	private void checkAllExitsGoSomewhere() {
		boolean missing = false;
		// For every place:
		for (Place p : places.values()) {
			// For every exit from that place:
			for (Exit x : p.getVisibleExits()) {
				// That exit goes to somewhere that exists!
				if (!places.containsKey(x.getTarget())) {
					// Don't leave immediately, but check everything all at once.
					missing = true;
					// Print every exit with a missing place:
					System.err.println("Found exit pointing at " + x.getTarget() + " which does not exist as a place.");
				}
			}
		}

		// Now that we've checked every exit for every place, crash if we printed any
		// errors.
		if (missing) {
			throw new RuntimeException("You have some exits to nowhere!");
		}
	}

	/**
	 * Get a Place object by name.
	 */
	public Place getPlace(String id) {
		return this.places.get(id);
	}
}
