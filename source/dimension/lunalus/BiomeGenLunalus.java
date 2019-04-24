package net.nevermine.dimension.lunalus;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;
import net.nevermine.izer.Blockizer;

import java.awt.*;

public class BiomeGenLunalus extends BiomeGenBase {
	public BiomeGenLunalus(final int par1) {
		super(par1);
		setBiomeName("Lunalus");
		topBlock = Blockizer.GrassLunalyte;
		fillerBlock = Blockizer.DirtLunalyte;
		rainfall = 100.0f;
		spawnableCreatureList.clear();
		spawnableMonsterList.clear();
		enableSnow = false;
		waterColorMultiplier = 1644825;
		spawnableCaveCreatureList.clear();
		temperature = 0.0f;
	}

	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(final float par1) {
		return Color.PINK.getRGB();
	}
}
