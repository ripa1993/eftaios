package it.polimi.ingsw.cg_8.view.client;

import it.polimi.ingsw.cg_8.model.cards.itemCards.AdrenalineCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.AttackCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.SedativesCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.SpotlightCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.TeleportCard;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
import it.polimi.ingsw.cg_8.view.client.actions.ActionAttack;
import it.polimi.ingsw.cg_8.view.client.actions.ActionChat;
import it.polimi.ingsw.cg_8.view.client.actions.ActionDisconnect;
import it.polimi.ingsw.cg_8.view.client.actions.ActionDrawCard;
import it.polimi.ingsw.cg_8.view.client.actions.ActionEndTurn;
import it.polimi.ingsw.cg_8.view.client.actions.ActionFakeNoise;
import it.polimi.ingsw.cg_8.view.client.actions.ActionGetAvailableAction;
import it.polimi.ingsw.cg_8.view.client.actions.ActionGetHand;
import it.polimi.ingsw.cg_8.view.client.actions.ActionGetReachableCoordinates;
import it.polimi.ingsw.cg_8.view.client.actions.ActionMove;
import it.polimi.ingsw.cg_8.view.client.actions.ActionSetName;
import it.polimi.ingsw.cg_8.view.client.actions.ActionUseCard;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;
import it.polimi.ingsw.cg_8.view.client.exceptions.NotAValidInput;

import java.util.StringTokenizer;

/**
 * Parser that analyses an input string and if legal, creates a ClientAction
 * object <br>
 * <br>
 * Legal inputs are:<br>
 * NAME player_name: {@link ActionSetName}<br>
 * MOVE coordinate: {@link ActionMove}<br>
 * ATTACK: {@link ActionAttack}<br>
 * USE item_card_type [coordinate]: {@link ActionUseCard}<br>
 * NOISE coordinate: {@link ActionFakeNoise}<br>
 * END: {@link ActionEndTurn}<br>
 * SAY message: {@link ActionChat}<br>
 * DRAW: {@link ActionDrawCard}<br>
 * ACTIONS: {@link ActionGetAvailableAction}<br>
 * COORDINATES: {@link ActionGetReachableCoordinates}<br>
 * CARDS: {@link ActionGetHand}<br>
 * DISCONNECT: {@link ActionDisconnect}<br>
 * 
 * @author Simone
 *
 */
public class ActionParser {
	/**
	 * Convert input string to coordinate
	 * 
	 * @param input
	 *            coordinate, composed by 1 letter and 2 numbers
	 * @return relative coordinate
	 * @throws NotAValidInput
	 */
	public static Coordinate parseCoordinate(String input)
			throws NotAValidInput {
		char[] inputArray = input.toUpperCase().toCharArray();

		if (inputArray[0] < 65 || inputArray[0] > 87 || inputArray[1] < 48
				|| inputArray[1] > 49 || inputArray[2] < 48
				|| inputArray[2] > 57) {
			// first letter is not in [A,W], first number not in [0,1] and
			// second number not in [0,9]
			throw new NotAValidInput("Not a valid coordinate");
		}

		int x, y;
		x = inputArray[0] - 65;
		y = (inputArray[1] - 48) * 10 + (inputArray[2] - 48) -1;
		return new Coordinate(x, y);

	}

	/**
	 * Starts the parsing
	 * 
	 * @param input
	 *            input to be analyzed
	 * @return if successful, a valid ClientAction
	 * @throws NotAValidInput
	 *             if the string parsed is illegal
	 */
	public static ClientAction createEvent(String input) throws NotAValidInput {
		/**
		 * If the input is empty, then the input is discarded
		 */
		if (input.length() == 0) {
			throw new NotAValidInput("Empty input");
		}

		StringTokenizer st = new StringTokenizer(input);

		String action = st.nextToken().toUpperCase();

		if (action.equals("NAME")) {
			if (!st.hasMoreTokens()) {
				/**
				 * NAME command without the player_name parameter
				 */
				throw new NotAValidInput("Missing player name!");
			} else {
				/**
				 * NAME command with player_name parameter
				 */
				String playerName = st.nextToken();
				while (st.hasMoreTokens()) {
					playerName = playerName + " " + st.nextToken();
				}
				return new ActionSetName(playerName);
			}

		}
		if (action.equals("SAY")) {
			if (!st.hasMoreTokens()) {
				throw new NotAValidInput("Nothing to say!");
			} else {
				String message = st.nextToken();
				while (st.hasMoreTokens()) {
					message = message + " " + st.nextToken();
				}
				return new ActionChat(message);
			}

		} else if (action.equals("MOVE")) {
			if (!st.hasMoreTokens()) {
				throw new NotAValidInput("Missing coordinate");
			}
			String coordinate = st.nextToken();
			if (!(coordinate.length() == 3)) {
				throw new NotAValidInput("Not a valid coordinate");
			}
			Coordinate parsedCoordinate = parseCoordinate(coordinate);
			if (st.hasMoreTokens()) {
				throw new NotAValidInput("Not a valid command");
			}
			return new ActionMove(parsedCoordinate);

		} else if (action.equals("ATTACK")) {
			if (st.hasMoreTokens()) {
				throw new NotAValidInput("Not a valid command");
			}
			return new ActionAttack();

		} else if (action.equals("USE")) {
			if (!st.hasMoreTokens()) {
				throw new NotAValidInput("Missing card type");
			}
			String card = st.nextToken();
			if (card.equals("SPOTLIGHT")) {
				if (!st.hasMoreTokens()) {
					throw new NotAValidInput("Missing coordinate");
				} else {
					Coordinate target = parseCoordinate(st.nextToken());
					if (st.hasMoreTokens()) {
						throw new NotAValidInput("Not a valid command");
					} else {
						return new ActionUseCard(new SpotlightCard(), target);
					}
				}

			}
			if (st.hasMoreTokens()) {
				throw new NotAValidInput("Not a valid command");
			}
			if (card.equals("ATTACK")) {
				return new ActionUseCard(new AttackCard());
			}
			if (card.equals("ADRENALINE")) {
				return new ActionUseCard(new AdrenalineCard());
			}
			if (card.equals("DEFENSE")) {
				throw new NotAValidInput("DEFENSE is a passive item card");
			}
			if (card.equals("SEDATIVES")) {
				return new ActionUseCard(new SedativesCard());
			}
			if (card.equals("TELEPORT")) {
				return new ActionUseCard(new TeleportCard());
			}
			throw new NotAValidInput("Not a valid card");

		} else if (action.equals("NOISE")) {
			String coordinate = st.nextToken();
			if (st.hasMoreTokens()) {
				throw new NotAValidInput("Not a valid command");
			}
			Coordinate parsedCoordinate = parseCoordinate(coordinate);
			return new ActionFakeNoise(parsedCoordinate);

		} else if (action.equals("END")) {
			if (st.hasMoreTokens()) {
				throw new NotAValidInput("Not a valid command");
			}
			return new ActionEndTurn();

		} else if (action.equals("DRAW")) {
			if (st.hasMoreTokens()) {
				throw new NotAValidInput("Not a valid command");
			}
			return new ActionDrawCard();
		} else if (action.equals("ACTIONS")) {
			if (st.hasMoreTokens()) {
				throw new NotAValidInput("Not a valid command");
			}
			return new ActionGetAvailableAction();
		} else if (action.equals("COORDINATES")) {
			if (st.hasMoreTokens()) {
				throw new NotAValidInput("Not a valid command");
			}
			return new ActionGetReachableCoordinates();
		} else if (action.equals("CARDS")) {
			if (st.hasMoreTokens()) {
				throw new NotAValidInput("Not a valid command");
			}
			return new ActionGetHand();
		} else if (action.equals("DISCONNECT")) {
			if (st.hasMoreTokens()) {
				throw new NotAValidInput("Not a valid command");
			}
			return new ActionDisconnect();
		} else {
			throw new NotAValidInput("Not a valid command");
		}
	}
}
