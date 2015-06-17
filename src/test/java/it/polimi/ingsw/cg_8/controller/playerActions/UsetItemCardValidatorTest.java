package it.polimi.ingsw.cg_8.controller.playerActions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_8.controller.playeraction.EndTurn;
import it.polimi.ingsw.cg_8.controller.playeraction.UseItemCardValidator;
import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.cards.item.AdrenalineCard;
import it.polimi.ingsw.cg_8.model.cards.item.AttackCard;
import it.polimi.ingsw.cg_8.model.cards.item.ItemCard;
import it.polimi.ingsw.cg_8.model.cards.item.SedativesCard;
import it.polimi.ingsw.cg_8.model.exceptions.EmptyDeckException;
import it.polimi.ingsw.cg_8.model.exceptions.GameAlreadyRunningException;
import it.polimi.ingsw.cg_8.model.exceptions.NotAValidMapException;
import it.polimi.ingsw.cg_8.model.exceptions.TooManyCardsException;
import it.polimi.ingsw.cg_8.model.map.GameMapName;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.player.character.alien.Alien;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class UsetItemCardValidatorTest {
    Model model;
    Player currentPlayer;

    @Before
    public void init() throws NotAValidMapException,
            GameAlreadyRunningException, EmptyDeckException,
            TooManyCardsException {
        model = new Model(GameMapName.FERMI);
        model.addPlayer("Ann");
        model.addPlayer("Bob");
        model.initGame();
        currentPlayer = model.getPlayers().get(model.getCurrentPlayer());
        if (currentPlayer.getCharacter() instanceof Alien) {
            EndTurn.endTurn(model);
            currentPlayer = model.getPlayers().get(model.getCurrentPlayer());
        }
        currentPlayer.getHand().addItemCard(new AttackCard());
        currentPlayer.getHand().addItemCard(new AdrenalineCard());
    }

    @Test
    public void testCardUsage() {
        assertTrue(UseItemCardValidator.validateItemCardUsage(model,
                new AdrenalineCard()));
    }

    @Test
    public void testCardUsage2() {
        assertTrue(UseItemCardValidator.validateItemCardUsage(model,
                new AttackCard()));
    }

    @Test
    public void testCardUsage3() {
        assertFalse(UseItemCardValidator.validateItemCardUsage(model,
                new SedativesCard()));
    }

    @Test
    public void testCardRemoval() {
        boolean removalCheck = false;
        UseItemCardValidator.validateItemCardUsage(model, new AdrenalineCard());
        List<ItemCard> heldCards = currentPlayer.getHand().getHeldCards();
        for (ItemCard c : heldCards) {
            if (c instanceof AdrenalineCard) {
                removalCheck = true;
            }
        }
        assertFalse(removalCheck);
    }
}
