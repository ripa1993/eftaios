package it.polimi.ingsw.cg_8.controller.playerActions;

import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.cards.itemCards.AdrenalineCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.AttackCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.ItemCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.SedativesCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.TeleportCard;
import it.polimi.ingsw.cg_8.model.player.character.alien.Alien;
import it.polimi.ingsw.cg_8.model.player.character.human.Human;
import it.polimi.ingsw.cg_8.model.sectors.Sector;
import it.polimi.ingsw.cg_8.model.sectors.special.spawn.SpawnSector;

import java.util.List;

/**
 * When a player is trying to use a card, check if he actually has the card, and
 * if the player is Human.
 * 
 * @author Alberto Parravicini
 * @version 1.0
 */
public class UseItemCardValidator {
    /**
     * Constructor
     */
    private UseItemCardValidator() {

    }

    /**
     * Check if the player has the card he is trying to use, and if so, removes
     * it. It also prevent the player from using a card when he is already
     * upgraded with a card of the same type.
     * 
     * @param model
     *            The state of the game
     * @param card
     *            The card that the player is trying to use.
     * @return Whether the card can be used or not.
     */
    public static boolean validateItemCardUsage(Model model, ItemCard card) {

        if (model.getCurrentPlayerReference().getCharacter() instanceof Alien) {
            return false;
        }

        Human player = (Human) model.getCurrentPlayerReference().getCharacter();
        if (card instanceof AdrenalineCard
                && player.getMaxAllowedMovement() == 2) {
            return false;
        }
        if (card instanceof AttackCard && player.isAttackAllowed()) {
            return false;
        }
        if (card instanceof SedativesCard && !player.hasToDrawSectorCard()) {
            return false;
        }
        Sector playerPosition = model.getMap().getSectors()
                .get(model.getCurrentPlayerReference().getLastPosition());
        if (card instanceof TeleportCard
                && playerPosition instanceof SpawnSector) {
            return false;
        }

        List<ItemCard> heldCards = model.getCurrentPlayerReference().getHand()
                .getHeldCards();
        for (ItemCard i : heldCards) {
            if ((card.getClass()).equals(i.getClass())) {
                heldCards.remove(i);
                return true;
            }
        }
        return false;

    }
}
