package it.polimi.ingsw.cg_8.model.sectors.normal;

import it.polimi.ingsw.cg_8.model.sectors.SectorType;

public class DangerousSector extends NormalSector {

	public DangerousSector(int x, int y) {
		super (x,y);
	}

	@Override
	public SectorType getSectorType() {
		return SectorType.DANGEROUS_SECTOR;
	}
	
	

}
