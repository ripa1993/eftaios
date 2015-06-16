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

/**
 * Simplified XML parser that return the coordinates of a given map, so that the
 * user can only click on them and not on the blank spaces of the map, and as a
 * consequence create wrong actions (which the server would refuse anyway).
 * 
 * @author Alberto
 * @version 1.0
 */
public class MapParserClient {

	/**
	 * Private class containing the set of the coordinates of the map.
	 * 
	 * @author Alberto
	 * @version 1.0
	 */
	@XmlRootElement(name = "map")
	@XmlAccessorType(XmlAccessType.FIELD)
	private static class GameMapSet {

		/**
		 * Set of the coordinates of the map.
		 */
		@XmlElementWrapper(name = "sectorList")
		@XmlElements({
				@XmlElement(name = "safeSector", type = ClientCoordinate.class),
				@XmlElement(name = "dangerousSector", type = ClientCoordinate.class),
				@XmlElement(name = "escapeHatchSector", type = ClientCoordinate.class),
				@XmlElement(name = "humanSector", type = ClientCoordinate.class),
				@XmlElement(name = "alienSector", type = ClientCoordinate.class), })
		private Set<ClientCoordinate> sectorSet;

		/**
		 * Return the coordinates of the current map
		 * 
		 * @return the coordinates of the map referred by the document used as
		 *         input in the parsing.
		 */
		public Set<ClientCoordinate> getSectorList() {
			return this.sectorSet;
		}
	}

	/**
	 * Parse an XML document referring to the map passed as parameter.
	 * @param mapName the reference to the XML document of the current map.
	 * @return the coordinates of the current map.
	 */
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
