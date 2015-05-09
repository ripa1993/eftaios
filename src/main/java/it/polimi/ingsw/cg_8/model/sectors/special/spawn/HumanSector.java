package it.polimi.ingsw.cg_8.model.sectors.special.spawn;

import it.polimi.ingsw.cg_8.model.sectors.SectorType;

public class HumanSector extends SpawnSector {

	public HumanSector(int x, int y) {
		super(x,y);
	}

	@Override
	public SectorType getSectorType() {
		return SectorType.HUMAN_SECTOR;
	}

}
