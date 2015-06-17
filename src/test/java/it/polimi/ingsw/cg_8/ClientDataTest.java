package it.polimi.ingsw.cg_8;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import it.polimi.ingsw.cg_8.client.ClientData;
import it.polimi.ingsw.cg_8.model.cards.item.ItemCard;
import it.polimi.ingsw.cg_8.model.map.GameMapName;
import it.polimi.ingsw.cg_8.model.noises.MovementNoise;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
import it.polimi.ingsw.cg_8.view.server.ResponseCard;
import it.polimi.ingsw.cg_8.view.server.ResponseChat;
import it.polimi.ingsw.cg_8.view.server.ResponseMap;
import it.polimi.ingsw.cg_8.view.server.ResponseNoise;
import it.polimi.ingsw.cg_8.view.server.ResponsePrivate;
import it.polimi.ingsw.cg_8.view.server.ResponseState;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.junit.Before;
import org.junit.Test;

public class ClientDataTest {

    private class Dummy implements Observer {
        String out = "test";
        boolean changed = false;

        public Dummy() {
            // TODO Auto-generated constructor stub
        }

        @Override
        public void update(Observable o, Object arg) {
            out = (String) arg;
            changed = true;
        }

    }

    ClientData cd;
    Dummy dummy;

    @Before
    public void init() {
        cd = new ClientData();
        dummy = new Dummy();
        cd.addObserver(dummy);
    }

    @Test
    public void testStoreResponseChat() {
        cd.storeResponse(new ResponseChat("a", "b"));
        assertEquals("Chat", dummy.out);
    }

    @Test
    public void testStoreResponseNoise() {
        cd.storeResponse(new ResponseNoise(new MovementNoise(0,
                new Player("a"), new Coordinate(1, 1))));
        while (!dummy.changed) {

        }
        assertEquals("Noise", dummy.out);
    }

    @Test
    public void testStoreResponsePrivate() {
        cd.storeResponse(new ResponsePrivate("a"));
        while (!dummy.changed) {

        }
        assertEquals("Private", dummy.out);
    }

    @Test
    public void testStoreResponseCards() {
        cd.storeResponse(new ResponseCard(new ArrayList<ItemCard>()));
        while (!dummy.changed) {

        }
        assertEquals("Cards", dummy.out);
    }

    @Test
    public void testStoreResponseState() {
        cd.storeResponse(new ResponseState("a", "v", "a", new Coordinate(1, 1),
                1));
        while (!dummy.changed) {

        }
        assertEquals("State", dummy.out);
    }

    @Test
    public void testStoreResponseMap() {
        cd.storeResponse(new ResponseMap(GameMapName.FERMI));
        while (!dummy.changed) {

        }
        assertEquals("Map", dummy.out);
    }

    @Test
    public void testGetChat() {
        List<ResponseChat> chat = cd.getChat();
        assertEquals(0, chat.size());
    }

    @Test
    public void testGetNoise() {
        List<ResponseNoise> noise = cd.getNoise();
        assertEquals(0, noise.size());
    }

    @Test
    public void testGetPrivateMessages() {
        List<ResponsePrivate> p = cd.getPrivateMessages();
        assertEquals(0, p.size());
    }

    @Test
    public void testGetMap() {
        cd.storeResponse(new ResponseMap(GameMapName.FERMI));
        ResponseMap map = cd.getMap();
        assertEquals(GameMapName.FERMI, map.getMapName());
    }

    @Test
    public void testGetCards() {
        cd.storeResponse(new ResponseCard(new ArrayList<ItemCard>()));
        ResponseCard card = cd.getCards();
        assertEquals("No Card", card.getCard1());
    }

    @Test
    public void testGetState() {
        cd.storeResponse(new ResponseState("a", "b", "c", new Coordinate(1, 1),
                0));
        ResponseState state = cd.getState();
        assertEquals("0", state.getRoundNumber());
    }

    @Test
    public void testGetLastChat() {
        ResponseChat chat = new ResponseChat("a", "b");
        cd.storeResponse(chat);
        assertEquals(chat, cd.getLastChat());
    }

    @Test
    public void testGetLastNoise() {
        ResponseNoise chat = new ResponseNoise(new MovementNoise(0, new Player(
                "a"), new Coordinate(1, 1)));
        cd.storeResponse(chat);
        assertEquals(chat, cd.getLastNoise());
    }

    @Test
    public void testGetLastPrivate() {
        ResponsePrivate chat = new ResponsePrivate("a");
        cd.storeResponse(chat);
        assertEquals(chat, cd.getLastPrivate());
    }

    @Test
    public void testStoreAck() {
        cd.storeAck(true);
        while (!dummy.changed) {

        }
        assertEquals("Ack", dummy.out);
    }

    @Test
    public void testGetAck() {
        cd.storeAck(true);
        assertEquals("Your action has been accepted by the server", cd.getAck());
    }

}
