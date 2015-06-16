package it.polimi.ingsw.cg_8.model.map.creator;

import java.io.File;
import java.util.Iterator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import it.polimi.ingsw.cg_8.Resource;
import it.polimi.ingsw.cg_8.model.map.FermiMapXML;
import it.polimi.ingsw.cg_8.model.map.GameMap;
import it.polimi.ingsw.cg_8.model.map.GameMapSet;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
import it.polimi.ingsw.cg_8.model.sectors.Sector;

public class FermiCreatorXML extends MapCreator {

	/**
	 * Map that is going to be populated
	 */
	private final GameMap fermiMap;

	/**
	 * Constructor
	 */
	public FermiCreatorXML() {
		super(new FermiMapXML());
		fermiMap = this.getGm();
	}

	@Override
	protected GameMapSet sectorParser() {

		try {
			JAXBContext jc = JAXBContext.newInstance(GameMapSet.class);

			Unmarshaller unmarshaller = jc.createUnmarshaller();
			GameMapSet sectorSet = (GameMapSet) unmarshaller
					.unmarshal(new File(Resource.FERMI_XML));

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
		return fermiMap;
		
	}

}



















