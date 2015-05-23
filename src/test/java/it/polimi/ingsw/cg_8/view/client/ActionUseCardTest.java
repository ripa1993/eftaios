package it.polimi.ingsw.cg_8.view.client;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_8.model.cards.itemCards.AdrenalineCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.AttackCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.SedativesCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.TeleportCard;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
import it.polimi.ingsw.cg_8.view.client.actions.ActionUseCard;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;
import it.polimi.ingsw.cg_8.view.client.exceptions.NotAValidInput;

import org.junit.Test;

public class ActionUseCardTest {

	@Test
	public void testCorrectSpotlight() throws NotAValidInput {
		String input = "USE SPOTLIGHT B12";
		ClientAction action = ActionParser.createEvent(input);
		assertEquals(new Coordinate(1, 11),
				((ActionUseCard) action).getCoordinate());
	}

	@Test(expected = NotAValidInput.class)
	public void testExceptionSpotlightNoParam() throws NotAValidInput {
		String input = "USE SPOTLIGHT";
		ClientAction action = ActionParser.createEvent(input);
	}

	@Test(expected = NotAValidInput.class)
	public void testExceptionSpotlightBadParam() throws NotAValidInput {
		String input = "USE SPOTLIGHT Z14";
		ClientAction action = ActionParser.createEvent(input);
	}

	@Test(expected = NotAValidInput.class)
	public void testExceptionSpotlightTooManyParam() throws NotAValidInput {
		String input = "USE SPOTLIGHT B11 extra params";
		ClientAction action = ActionParser.createEvent(input);
	}

	@Test
	public void testCorrectAttack() throws NotAValidInput {
		String input = "USE ATTACK";
		ClientAction action = ActionParser.createEvent(input);
		assertTrue(((ActionUseCard) action).getItemCard() instanceof AttackCard);
	}

	@Test(expected = NotAValidInput.class)
	public void testExceptionAttack() throws NotAValidInput {
		String input = "USE ATTACK nonvalid";
		ClientAction action = ActionParser.createEvent(input);
	}

	@Test
	public void testCorrectSedatives() throws NotAValidInput {
		String input = "USE SEDATIVES";
		ClientAction action = ActionParser.createEvent(input);
		assertTrue(((ActionUseCard) action).getItemCard() instanceof SedativesCard);
	}

	@Test(expected = NotAValidInput.class)
	public void testExceptionSedatives() throws NotAValidInput {
		String input = "USE SEDATIVES nonvalid";
		ClientAction action = ActionParser.createEvent(input);
	}

	@Test
	public void testCorrectAdrenaline() throws NotAValidInput {
		String input = "USE ADRENALINE";
		ClientAction action = ActionParser.createEvent(input);
		assertTrue(((ActionUseCard) action).getItemCard() instanceof AdrenalineCard);
	}

	@Test(expected = NotAValidInput.class)
	public void testExceptionAdrenaline() throws NotAValidInput {
		String input = "USE ADRENALINE nonvalid";
		ClientAction action = ActionParser.createEvent(input);
	}

	@Test(expected = NotAValidInput.class)
	public void testExceptionDefense() throws NotAValidInput {
		String input = "USE DEFENSE";
		ClientAction action = ActionParser.createEvent(input);
	}

	@Test
	public void testCorrectTeleport() throws NotAValidInput {
		String input = "USE TELEPORT";
		ClientAction action = ActionParser.createEvent(input);
		assertTrue(((ActionUseCard) action).getItemCard() instanceof TeleportCard);
	}

	@Test(expected = NotAValidInput.class)
	public void testExceptionTeleport() throws NotAValidInput {
		String input = "USE TELEPORT nonvalid";
		ClientAction action = ActionParser.createEvent(input);
	}

}
