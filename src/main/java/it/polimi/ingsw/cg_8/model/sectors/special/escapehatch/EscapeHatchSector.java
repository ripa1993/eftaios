package it.polimi.ingsw.cg_8.model.sectors.special.escapehatch;

import it.polimi.ingsw.cg_8.model.sectors.SectorType;
import it.polimi.ingsw.cg_8.model.sectors.special.SpecialSector;

public class EscapeHatchSector extends SpecialSector {

	EscapeHatchBehaviour status;
	int number;

	public EscapeHatchSector(int x, int y, int number) {
		super(x, y);
		this.number=number;
		status = new NotUsedEHBehaviour();
	}

	private void setUsed() {

		// change the status of the hatch from not used to used

		this.status = new UsedEHBehaviour();
	}

	public boolean allowEscape() {

		// return true if you can use the hatch to escape, changes the status
		// from not used to used
		// return false if you cannot use the hatch to escape, because it has
		// been already used

		boolean allowedUse = status.allowEscape();
		if (allowedUse) {
			this.setUsed();
		}
		return allowedUse;
	}

	@Override
	public SectorType getSectorType() {
		return SectorType.EH_SECTOR;
	}

}
