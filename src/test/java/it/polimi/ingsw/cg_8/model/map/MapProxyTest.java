package it.polimi.ingsw.cg_8.model.map;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_8.model.map.creator.FermiCreator;
import it.polimi.ingsw.cg_8.model.map.creator.GalvaniCreator;
import it.polimi.ingsw.cg_8.model.map.creator.MapCreator;

import org.junit.Test;

public class MapProxyTest {

	@Test
	public void equals() {
		MapCreator mc1 = new GalvaniCreator();
		MapCreator mc2 = new FermiCreator();
		GameMap map1 = mc1.createMap();
		GameMap map2 = mc2.createMap();
		MapProxy mp1 = map1.getMapProxy();
		MapProxy mp2 = map2.getMapProxy();
		assertFalse(mp1.equals(mp2));
	}

}
