package it.polimi.ingsw.cg_8.controller;

import it.polimi.ingsw.cg_8.controller.playeraction.Attack;
import it.polimi.ingsw.cg_8.controller.playeraction.DrawDangerousSectorCard;
import it.polimi.ingsw.cg_8.controller.playeraction.EndTurn;
import it.polimi.ingsw.cg_8.controller.playeraction.FakeNoise;
import it.polimi.ingsw.cg_8.controller.playeraction.Movement;
import it.polimi.ingsw.cg_8.controller.playeraction.other.Disconnect;
import it.polimi.ingsw.cg_8.controller.playeraction.other.GetAllowedActions;
import it.polimi.ingsw.cg_8.controller.playeraction.other.GetReachableSectors;
import it.polimi.ingsw.cg_8.controller.playeraction.useitemcard.UseAdrenalineCard;
import it.polimi.ingsw.cg_8.controller.playeraction.useitemcard.UseAttackCard;
import it.polimi.ingsw.cg_8.controller.playeraction.useitemcard.UseSedativesCard;
import it.polimi.ingsw.cg_8.controller.playeraction.useitemcard.UseSpotlightCard;
import it.polimi.ingsw.cg_8.controller.playeraction.useitemcard.UseTeleportCard;
import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.TurnPhase;
import it.polimi.ingsw.cg_8.model.cards.item.AdrenalineCard;
import it.polimi.ingsw.cg_8.model.cards.item.AttackCard;
import it.polimi.ingsw.cg_8.model.cards.item.ItemCard;
import it.polimi.ingsw.cg_8.model.cards.item.SedativesCard;
import it.polimi.ingsw.cg_8.model.cards.item.SpotlightCard;
import it.polimi.ingsw.cg_8.model.cards.item.TeleportCard;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.player.PlayerState;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
import it.polimi.ingsw.cg_8.model.sectors.Sector;
import it.polimi.ingsw.cg_8.model.sectors.normal.DangerousSector;
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
import it.polimi.ingsw.cg_8.view.client.actions.ActionUseCard;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;
import it.polimi.ingsw.cg_8.view.server.ResponseCard;
import it.polimi.ingsw.cg_8.view.server.ResponseChat;
import it.polimi.ingsw.cg_8.view.server.ResponsePrivate;
import it.polimi.ingsw.cg_8.view.server.ResponseState;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Simulation of a state machine, used to handle {@link ClientAction} generated
 * by the client
 * 
 * @author Simone
 * @version 1.0
 */
public class StateMachine {
    /**
     * Log4j logger
     */
    private static final Logger LOGGER = LogManager
            .getLogger(StateMachine.class);

    /**
     * Constructor
     */
    private StateMachine() {

    }

    /**
     * This method evaluates an action created by a player though a client.
     * 
     * @param controller
     *            reference to the game
     * @param action
     *            action that needs to be evaluated
     * @param player
     *            player that submitted the action
     * @return true, if the action has been validated<br>
     *         false, if the action has been refused
     */
    public static boolean evaluateAction(Controller controller,
            ClientAction action, Player player) {

        // local variables, calculated every time a new evaluation is launched
        Model model = controller.getModel();
        Player currentPlayer = model.getCurrentPlayerReference();
        TurnPhase turnPhase = model.getTurnPhase();
        Rules rules = controller.getRules();

        /**
         * A disconnected player isn't allowed to use any command. The same goes
         * if the game is over.
         */
        if (player.getState().equals(PlayerState.DISCONNECTED)
                || model.getTurnPhase().equals(TurnPhase.GAME_END)) {
            return false;
        }

        // handles chat and disconnect action, always true
        if (action instanceof ActionChat) {
            String message = ((ActionChat) action).getMessage();
            controller.writeToAll(new ResponseChat(player.getName(), message));
            return true;
        }

        if (action instanceof ActionDisconnect) {
            Disconnect.disconnect(player);
            if (player.equals(model.getCurrentPlayerReference())) {
                model.nextPlayer();
                model.setTurnPhase(TurnPhase.TURN_BEGIN);
            }

            controller.writeToAll(new ResponsePrivate(player.getName()
                    + " has been disconnected."));
            return true;
        }

        if (!(model.getTurnPhase() == TurnPhase.GAME_SETUP)
                || !(model.getTurnPhase() == TurnPhase.GAME_END)) {
            if (action instanceof ActionGetReachableCoordinates) {
                controller.writeToPlayer(
                        player,
                        new ResponsePrivate(GetReachableSectors
                                .printReachableSectors(model, player)));
                return true;
            }
            if (action instanceof ActionGetHand) {
                controller.writeToPlayer(player, new ResponseCard(player
                        .getHand().getHeldCards()));
                return true;
            }
            if (action instanceof ActionGetAvailableAction) {
                controller.writeToPlayer(player, new ResponsePrivate(
                        GetAllowedActions.printActions()));
                return true;
            }
        }

        // if not currentPlayer, then refuse all actions
        if (!currentPlayer.equals(player)) {
            return false;
        }
        // handle TURN_BEGIN
        if (turnPhase == TurnPhase.TURN_BEGIN) {

            // movement

            if (action instanceof ActionMove) {
                // validate movement
                Coordinate destination = ((ActionMove) action).getCoordinate();
                if (rules.movementValidator(model, destination)) {
                    // execute movement
                    Movement move = new Movement(model, destination);
                    move.makeMove();
                    controller.writeToAll(new ResponsePrivate(player.getName()
                            + " has moved."));
                    Sector destinationSector = model.getMap().getSectors()
                            .get(destination);

                    controller
                            .writeToPlayer(
                                    player,
                                    new ResponseState(player.getName(), player
                                            .getCharacter().toString(), player
                                            .getState().toString(), player
                                            .getLastPosition(), model
                                            .getRoundNumber()));
                    if (!currentPlayer.getCharacter().hasToDrawSectorCard()) {
                        model.setTurnPhase(TurnPhase.MOVEMENT_DONE_NOT_DS);

                    } else {
                        if (destinationSector instanceof DangerousSector) {
                            model.setTurnPhase(TurnPhase.MOVEMENT_DONE_DS);

                        } else {
                            model.setTurnPhase(TurnPhase.MOVEMENT_DONE_NOT_DS);

                        }
                    }
                    return true;
                }
                controller.writeToPlayer(player, new ResponsePrivate(
                        "Moving to " + destination + " is not allowed"));
                return false;

            }

            // use item card

            if (action instanceof ActionUseCard) {
                ItemCard card = ((ActionUseCard) action).getItemCard();
                Coordinate coordinate = ((ActionUseCard) action)
                        .getCoordinate();
                if (card instanceof AdrenalineCard) {
                    return StateMachine.useAdrenalineCard(card, player,
                            controller);
                }
                if (card instanceof AttackCard) {
                    return StateMachine.useAttackCard(card, player, controller);
                }
                if (card instanceof TeleportCard) {
                    return StateMachine.useTeleportCard(card, player,
                            controller);
                }
                if (card instanceof SedativesCard) {
                    return StateMachine.useSedativesCard(card, player,
                            controller);
                }
                if (card instanceof SpotlightCard) {
                    return StateMachine.useSpotlightCard(card, player,
                            controller, coordinate);
                }

            }
            return false;
        }
        // handle MOVEMENT_DONE_NOT_DS
        if (turnPhase == TurnPhase.MOVEMENT_DONE_NOT_DS) {

            /**
             * Attack
             */
            if (action instanceof ActionAttack) {
                return StateMachine.attackMove(rules, model, controller);
            }

            // end turn

            if (action instanceof ActionEndTurn) {
                endTurn(controller, model, player);
                return true;
            }

            // use card

            if (action instanceof ActionUseCard) {

                return actionUseCardFull(controller, action, player);
            }
            return false;
        }

        // handle MOVEMENT_DONE_DS
        if (turnPhase == TurnPhase.MOVEMENT_DONE_DS) {

            /**
             * Attack
             */
            if (action instanceof ActionAttack) {
                return StateMachine.attackMove(rules, model, controller);
            }

            // draw card

            if (action instanceof ActionDrawCard) {
                DrawDangerousSectorCard draw = new DrawDangerousSectorCard(
                        model);
                boolean hasToMakeFakeNoise = false;

                try {
                    hasToMakeFakeNoise = draw.drawDangerousSectorCard();
                    controller.writeToPlayer(
                            player,
                            new ResponsePrivate("You have drawn a "
                                    + draw.getDangerousSectorCard()));
                    LOGGER.debug(draw.getItemCard());

                    if (draw.getItemCard() != null
                            && !draw.isDiscardedItemCard()) {
                        controller.writeToPlayer(player, new ResponsePrivate(
                                "You have drawn a " + draw.getItemCard()));

                    } else if (draw.getItemCard() != null
                            && draw.isDiscardedItemCard()) {
                        controller.writeToPlayer(player, new ResponsePrivate(
                                "You have drawn " + draw.getItemCard()
                                        + " but your hand is full,"
                                        + " so the card has been discarded."));

                    } else if (draw.getItemCard() == null
                            && draw.isEmptyItemDeck()) {
                        controller.writeToPlayer(player, new ResponsePrivate(
                                "The item card deck is empty"));
                    } else {
                        controller.writeToPlayer(player, new ResponsePrivate(
                                "No item card was drawn"));
                    }

                } finally {
                    controller.writeToPlayer(player, new ResponseCard(player
                            .getHand().getHeldCards()));
                    if (hasToMakeFakeNoise) {
                        model.setTurnPhase(TurnPhase.WAITING_FAKE_NOISE);
                        controller.writeToPlayer(player, new ResponsePrivate(
                                "Make a noise on a coordinate of your choice"));
                    } else {
                        model.setTurnPhase(TurnPhase.DRAWN_CARD);
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            LOGGER.error("Failed to sleep");
                        }
                        controller.writeToAll(new ResponsePrivate(player
                                .getName()
                                + " has drawn a Dangerous Sector Card"));
                    }
                }
                return true;
            }

            // use item card

            if (action instanceof ActionUseCard) {
                return actionUseCardFull(controller, action, player);
            }

            if (action instanceof ActionEndTurn
                    && !player.getCharacter().hasToDrawSectorCard()) {
                EndTurn.endTurn(model);
                return true;

            }
        }

        // handle ATTACK_PHASE
        if (turnPhase == TurnPhase.ATTACK_DONE) {

            // end turn

            if (action instanceof ActionEndTurn) {
                endTurn(controller, model, player);
                return true;
            }

            // use item card

            if (action instanceof ActionUseCard) {
                return actionUseCardTelAndSpot(controller, action, player);
            }
        }

        // handle WAITING_FAKE_NOISE
        if (turnPhase == TurnPhase.WAITING_FAKE_NOISE
                && action instanceof ActionFakeNoise) {

            Coordinate target = ((ActionFakeNoise) action).getCoordinate();
            if (model.getMap().getSectors().keySet().contains(target)) {
                FakeNoise.fakeNoise(model, target);
                model.setTurnPhase(TurnPhase.DRAWN_CARD);
                return true;
            }
            return false;
        }

        // handle DRAWN_CARD
        if (turnPhase == TurnPhase.DRAWN_CARD) {

            // end turn

            if (action instanceof ActionEndTurn) {
                endTurn(controller, model, player);
                return true;
            }

            // use item card

            if (action instanceof ActionUseCard) {
                return actionUseCardTelAndSpot(controller, action, player);
            }
        }
        // other cases
        return false;
    }

    /**
     * Check if the player can use teleport or spotlight card
     * 
     * @param controller
     *            the game
     * @param a
     *            action
     * @param player
     *            requesting player
     * @return true, if the action has been validated<br>
     *         false, if not
     */
    private static boolean actionUseCardTelAndSpot(Controller controller,
            ClientAction a, Player player) {
        ItemCard card = ((ActionUseCard) a).getItemCard();
        Coordinate coordinate = ((ActionUseCard) a).getCoordinate();
        if (card instanceof TeleportCard) {
            return StateMachine.useTeleportCard(card, player, controller);
        }
        if (card instanceof SpotlightCard) {
            return StateMachine.useSpotlightCard(card, player, controller,
                    coordinate);
        }
        return false;
    }

    /**
     * Check if the player can use attack, teleport, sedatives or spotlight card
     * 
     * @param controller
     *            the game
     * @param a
     *            action
     * @param player
     *            the requesting player
     * @return true, if the action has been validated<br>
     *         false, if not
     */
    private static boolean actionUseCardFull(Controller controller,
            ClientAction a, Player player) {
        ItemCard card = ((ActionUseCard) a).getItemCard();
        Coordinate coordinate = ((ActionUseCard) a).getCoordinate();

        if (card instanceof AttackCard) {
            return StateMachine.useAttackCard(card, player, controller);
        }
        if (card instanceof TeleportCard) {
            return StateMachine.useTeleportCard(card, player, controller);
        }
        if (card instanceof SedativesCard) {
            return StateMachine.useSedativesCard(card, player, controller);
        }
        if (card instanceof SpotlightCard) {
            return StateMachine.useSpotlightCard(card, player, controller,
                    coordinate);
        }
        return false;
    }

    /**
     * Ends the turn calling the EndTurn class and notifying all the player of
     * the change
     * 
     * @param controller
     *            game controller
     * @param model
     *            game model
     * @param player
     *            player that ends his turn
     */
    private static void endTurn(Controller controller, Model model,
            Player player) {
        controller.writeToAll(new ResponsePrivate(player.getName()
                + " has finished his turn"));
        EndTurn.endTurn(model);

    }

    /**
     * Method used to handle an attack move
     * 
     * @param rules
     * @param model
     * @param controller
     * @return Whether the attack was allowed or not.
     */
    private static boolean attackMove(Rules rules, Model model,
            Controller controller) {
        if (rules.attackValidator(model)) {
            controller.writeToAll(new ResponsePrivate(model
                    .getCurrentPlayerReference().getName()
                    + " has attacked in "
                    + model.getCurrentPlayerReference().getLastPosition()));
            Attack attack = new Attack(model);
            attack.makeAttack();

            model.setTurnPhase(TurnPhase.ATTACK_DONE);

            List<Player> victims = attack.getVictims();
            for (Player p : victims) {
                controller.writeToAll(new ResponsePrivate(p.getName()
                        + " has been killed!"));
                controller.writeToPlayer(
                        p,
                        new ResponseState(p.getName(), p.getCharacter()
                                .toString(), p.getState().toString(), p
                                .getLastPosition(), model.getRoundNumber()));
                List<Player> survivors = attack.getSurvivor();
                for (Player p2 : survivors) {
                    controller
                            .writeToAll(new ResponsePrivate(
                                    p2.getName()
                                            + " survived to the attack by using a defense card"));
                    controller.writeToPlayer(p2, new ResponseCard(p2.getHand()
                            .getHeldCards()));
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Handles the use of an adrenaline card
     * 
     * @param card
     *            adrenaline card
     * @param player
     *            requesting player
     * @param controller
     *            game
     * @return true, if the action has been done<br>
     *         false, if not
     */
    private static boolean useAdrenalineCard(ItemCard card, Player player,
            Controller controller) {
        if (controller.getRules().useItemCardValidator(controller.getModel(),
                card)) {
            UseAdrenalineCard.useCard(controller.getModel());
            controller.writeToAll(new ResponsePrivate(player.getName()
                    + " has used an Adrenaline Card"));
            controller.writeToPlayer(player, new ResponseCard(player.getHand()
                    .getHeldCards()));
            return true;
        }
        return false;

    }

    /**
     * Handles the use of an attack card
     * 
     * @param card
     *            attack card
     * @param player
     *            requesting player
     * @param controller
     *            game
     * @return true, if the action has been done<br>
     *         false, if not
     */
    private static boolean useAttackCard(ItemCard card, Player player,
            Controller controller) {
        if (controller.getRules().useItemCardValidator(controller.getModel(),
                card)) {
            UseAttackCard.useCard(controller.getModel());
            controller.writeToAll(new ResponsePrivate(player.getName()
                    + " has used an Attack Card"));
            controller.writeToPlayer(player, new ResponseCard(player.getHand()
                    .getHeldCards()));
            return true;
        }
        return false;
    }

    /**
     * Handles the use of a teleport card
     * 
     * @param card
     *            teleport card
     * @param player
     *            requesting player
     * @param controller
     *            game
     * @return true, if the action has been done<br>
     *         false, if not
     */
    private static boolean useTeleportCard(ItemCard card, Player player,
            Controller controller) {
        if (controller.getRules().useItemCardValidator(controller.getModel(),
                card)) {
            UseTeleportCard.useCard(controller.getModel());
            controller.writeToAll(new ResponsePrivate(player.getName()
                    + " has used a Teleport Card"));
            controller.writeToPlayer(player, new ResponseCard(player.getHand()
                    .getHeldCards()));
            controller.writeToPlayer(player, new ResponseState(
                    player.getName(), player.getCharacter().toString(), player
                            .getState().toString(), player.getLastPosition(),
                    controller.getModel().getRoundNumber()));
            return true;
        }
        return false;

    }

    /**
     * Handles the use of a sedatives card
     * 
     * @param card
     *            sedatives card
     * @param player
     *            requesting player
     * @param controller
     *            game
     * @return true, if the action has been done<br>
     *         false, if not
     */
    private static boolean useSedativesCard(ItemCard card, Player player,
            Controller controller) {
        if (controller.getRules().useItemCardValidator(controller.getModel(),
                card)) {
            UseSedativesCard.useCard(controller.getModel());
            controller.writeToAll(new ResponsePrivate(player.getName()
                    + " has used a Sedatives Card"));
            controller.writeToPlayer(player, new ResponseCard(player.getHand()
                    .getHeldCards()));

            return true;
        }
        return false;
    }

    /**
     * Handles the use of a spotlight card
     * 
     * @param card
     *            spotlight card
     * @param player
     *            requesting player
     * @param controller
     *            game
     * @param coordinate
     *            target coordinate
     * @return true, if the action has been done<br>
     *         false, if not
     */
    private static boolean useSpotlightCard(ItemCard card, Player player,
            Controller controller, Coordinate coordinate) {
        if (controller.getRules().useItemCardValidator(controller.getModel(),
                card)) {
            UseSpotlightCard.useCard(controller.getModel(), coordinate);
            controller.writeToAll(new ResponsePrivate(player.getName()
                    + " has used a Spotlight Card"));
            controller.writeToPlayer(player, new ResponseCard(player.getHand()
                    .getHeldCards()));

            return true;
        }
        return false;
    }
}
