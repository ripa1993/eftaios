package it.polimi.ingsw.cg_8.model.sectors.normal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import it.polimi.ingsw.cg_8.model.sectors.Sector;

/**
 * Abstract class for normal sector in the game. It is extended by
 * {@link DangerousSector} and {@link SecureSector}
 * 
 * @author Simone
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class NormalSector extends Sector {
    /**
	 * 
	 */
    private static final long serialVersionUID = -3925058303611953264L;

    /**
     * Default constructor for normal sector, used to perform checks and
     * validations.
     * 
     * @param x
     *            column number
     * @param y
     *            row number
     */
    public NormalSector(int x, int y) {
        super(x, y);
    }

    /**
     * Default constructor used in the XML parsing.
     */
    public NormalSector() {
        super();
    }

}
