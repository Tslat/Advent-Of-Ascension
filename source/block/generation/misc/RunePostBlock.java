package net.tslat.aoa3.block.generation.misc;

import net.minecraft.block.material.Material;
import net.tslat.aoa3.block.UnbreakableBlock;
import net.tslat.aoa3.item.misc.RuneItem;

public class RunePostBlock extends UnbreakableBlock {
	private RuneItem rune;
	private final int lvl;
	private final float xp;

	public RunePostBlock(String name, String registryName, int level, float xp) {
		super(name, registryName, Material.ROCK);
		this.lvl = level;
		this.xp = xp;
	}

	public int getLevelReq() {
		return lvl;
	}

	public void setRune(RuneItem rune) {
		this.rune = rune;
	}

	public float getXpGain() {
		return xp;
	}

	public RuneItem getRune() {
		return rune;
	}
}
