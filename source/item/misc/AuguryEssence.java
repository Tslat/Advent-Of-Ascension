package net.tslat.aoa3.item.misc;

public class AuguryEssence extends SimpleItem {
	private int lvlReq;
	private float xp;

	public AuguryEssence(String name, String registryName, int lvl, float xp) {
		super(name, registryName);
		this.lvlReq = lvl;
		this.xp = xp;
	}

	public int getLvlReq() {
		return lvlReq;
	}

	public float getXp() {
		return xp;
	}
}
