package net.tslat.aoa3.util.constant;

public enum Skills {
	ALCHEMY(0),
	ANIMA(1),
	AUGURY(2),
	BUTCHERY(3),
	CREATION(4),
	ENGINEERING(5),
	EXPEDITION(6),
	EXTRACTION(7),
	FORAGING(8),
	HAULING(9),
	HUNTER(10),
	INFUSION(11),
	INNERVATION(12),
	LOGGING(13),
	RUNATION(14);

	public final int id;

	Skills(final int id) {
		this.id = id;
	}

	public static Skills getById(final int id) {
		switch(id) {
			case 0:
				return ALCHEMY;
			case 1:
				return ANIMA;
			case 2:
				return AUGURY;
			case 3:
				return BUTCHERY;
			case 4:
				return CREATION;
			case 5:
				return ENGINEERING;
			case 6:
				return EXPEDITION;
			case 7:
				return EXTRACTION;
			case 8:
				return FORAGING;
			case 9:
				return HAULING;
			case 10:
				return HUNTER;
			case 11:
				return INFUSION;
			case 12:
				return INNERVATION;
			case 13:
				return LOGGING;
			case 14:
				return RUNATION;
			default:
				return null;
		}
	}
}
