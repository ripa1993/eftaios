package it.polimi.ingsw.cg_8.model.sectors.special.spawn;

import it.polimi.ingsw.cg_8.model.sectors.special.SpecialSector;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Spawn sector abstract class
 * 
 * @author Simone
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class SpawnSector extends SpecialSector {

    /**
     * 
     */
    private static final long serialVersionUID = -1851902876364291445L;

    /**
     * Default constructor for {@link SpecialSector}, used to performs checks
     * and validations.
     * 
     * @param x
     *            column number
     * @param y
     *            row number
     */
    public SpawnSector(int x, int y) {
        super(x, y);
    }

    /**
     * Default constructor used in the XML parsing.
     */
    public SpawnSector() {
        super();
    }

}
