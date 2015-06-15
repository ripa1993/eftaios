package it.polimi.ingsw.cg_8.model.map.creator;

import it.polimi.ingsw.cg_8.Resource;
import it.polimi.ingsw.cg_8.model.map.FermiMapXML;
import it.polimi.ingsw.cg_8.model.map.GalvaniMapXML;
import it.polimi.ingsw.cg_8.model.map.GameMap;
import it.polimi.ingsw.cg_8.model.map.GameMapSet;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
import it.polimi.ingsw.cg_8.model.sectors.Sector;

import java.io.File;
import java.util.Iterator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class GalvaniCreatorXML extends MapCreator {
	/**
	 * Map that is going to be populated
	 */
	private final GameMap galvaniMap;

	/**
	 * Constructor
	 */
	public GalvaniCreatorXML() {
		super(new GalvaniMapXML());
		galvaniMap = this.getGm();
	}

	@Override
	protected GameMapSet sectorParser() {

		try {
			JAXBContext jc = JAXBContext.newInstance(GameMapSet.class);

			Unmarshaller unmarshaller = jc.createUnmarshaller();
			GameMapSet sectorSet = (GameMapSet) unmarshaller
					.unmarshal(new File(Resource.GALVANI_XML));

			return sectorSet;

		} catch (Exception e) {
			e.printStackTrace(System.out);
			return null;
		}
	}

	@Override
	public GameMap createMap() {
		GameMapSet sectorList = this.sectorParser();
		
		Iterator<Sector> iterator = sectorList.getSectorList().iterator();
		while (iterator.hasNext()) {
			Sector currentSector = iterator.next();
			int x = currentSector.getX();
			int y = currentSector.getY();
			addSector(new Coordinate(x, y), currentSector);
		}
		galvaniMap.setAlienSpawn(sectorList.getAlienSpawnSector());
		galvaniMap.setHumanSpawn(sectorList.getHumanSpawnSector());
		return galvaniMap;
		
	}

}
