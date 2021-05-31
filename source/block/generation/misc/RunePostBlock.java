package net.tslat.aoa3.block.generation.misc;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.util.BlockUtil;

import java.util.function.Supplier;

public class RunePostBlock extends Block {
	private Supplier<RuneItem> rune;
	private final int lvl;
	private final float xp;

	public RunePostBlock(int level, float xp, Supplier<RuneItem> runeItem) {
		super(new BlockUtil.CompactProperties(Material.STONE, MaterialColor.COLOR_BLACK).stats(10f, 15f).get());
		this.lvl = level;
		this.xp = xp;
		this.rune = runeItem;
	}

	public int getLevelReq() {
		return lvl;
	}

	public float getXpGain() {
		return xp;
	}

	public RuneItem getRune() {
		return rune.get();
	}
}
