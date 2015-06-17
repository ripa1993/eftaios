package it.polimi.ingsw.cg_8.model.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_8.model.map.creator.FermiCreator;
import it.polimi.ingsw.cg_8.model.map.creator.GalvaniCreator;
import it.polimi.ingsw.cg_8.model.map.creator.MapCreator;

import org.junit.Before;
import org.junit.Test;

public class MapProxyTest {

	MapCreator mc;
	GameMap map;
	MapProxy mp;
	
	@Before
	public void init(){
		mc = new GalvaniCreator();
		map = mc.createMap();
		mp = map.getMapProxy();
	}
	
	@Test
	public void equals() {
		MapCreator mc2 = new FermiCreator();
		GameMap map2 = mc2.createMap();
		MapProxy mp2 = map2.getMapProxy();
		assertFalse(mp.equals(mp2));
	}
	
	@Test
	public void equals2(){
		GameMap map2 = mp.getMap();
		MapProxy mp2 = map2.getMapProxy();
		assertTrue(mp.equals(mp2));
	}

	@Test
	public void getMap(){
		assertEquals(map, mp.getMap());
	}
}
