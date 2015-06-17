package it.polimi.ingsw.cg_8.model.map;

import it.polimi.ingsw.cg_8.model.map.creator.MapCreator;

/**
 * Class for the map "Galvani". After instantiating a new empty map, it is
 * necessary to instance the corresponding constructor {@link MapCreator} to
 * populate the map through the {@link MapCreator#createMap()}.
 * 
 * @author Alberto Parravicini
 * @version 1.1
 */
public class GalvaniMap extends GameMap {
    /**
     * Empty constructor for the map, instantiating the coordinates of thwe map
     * is done by the {@link MapCreator}
     */
    public GalvaniMap() {
        super();
    }
}
