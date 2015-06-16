package it.polimi.ingsw.cg_8.client.gui;

import java.io.File;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

public class MapParserClient {

	@XmlRootElement(name = "map")
	@XmlAccessorType(XmlAccessType.FIELD)
	private static class GameMapSet {

		@XmlElementWrapper(name = "sectorList")
		@XmlElements( { 
			@XmlElement( name="safeSector", type = ClientCoordinate.class ),
			@XmlElement( name="dangerousSector", type = ClientCoordinate.class),
			@XmlElement( name="escapeHatchSector", type = ClientCoordinate.class),
			@XmlElement( name="humanSector", type = ClientCoordinate.class),
			@XmlElement( name="alienSector", type = ClientCoordinate.class),
			} )
		private Set<ClientCoordinate> sectorSet;
		

		public Set<ClientCoordinate> getSectorList() {
			return this.sectorSet;
		}
	}
	
	public static Set<ClientCoordinate> parse(String mapName) {

		try {
			JAXBContext jc = JAXBContext.newInstance(GameMapSet.class);

			Unmarshaller unmarshaller = jc.createUnmarshaller();
			GameMapSet sectorSet = (GameMapSet) unmarshaller
					.unmarshal(new File(mapName));

			return sectorSet.getSectorList();

		} catch (Exception e) {
			e.printStackTrace(System.out);
			return null;
		}
	}
}
