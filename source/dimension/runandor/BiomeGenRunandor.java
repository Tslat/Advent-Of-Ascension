package net.nevermine.dimension.runandor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;
import net.nevermine.izer.Blockizer;

import java.awt.*;

public class BiomeGenRunandor extends BiomeGenBase {
	public BiomeGenRunandor(final int par1) {
		super(par1);
		setBiomeName("Runandor");
		topBlock = Blockizer.GrassRunic;
		fillerBlock = Blockizer.RunicBrick;
		rainfall = 0.0f;
		spawnableCreatureList.clear();
		spawnableMonsterList.clear();
		enableSnow = false;
		spawnableCaveCreatureList.clear();
		temperature = 0.5f;
	}

	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(final float par1) {
		return Color.BLUE.getRGB();
	}
}
