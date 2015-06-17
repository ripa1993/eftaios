package it.polimi.ingsw.cg_8.controller.playerActions.useItemCard;

import it.polimi.ingsw.cg_8.controller.playerActions.PlayerAction;

/**
 * Action that use an item card. It is extend by specific item card types:
 * {@link UseAdrenalineCard Adrenaline}, {@link UseAttackCard Attack},
 * {@link UseDefenseCard Defense}, {@link UseSedativesCard Sedatives},
 * {@link UseSpotlightCard Spotlight} and {@link UseTeleportCard Teleport}
 * 
 * @author Simone
 * @version 1.0
 */
public abstract class UseItemCard implements PlayerAction {

    /**
     * Constructor
     */
    public UseItemCard() {
    }
}
