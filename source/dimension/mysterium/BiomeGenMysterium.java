package net.nevermine.dimension.mysterium;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;
import net.nevermine.izer.Blockizer;

import java.awt.*;

public class BiomeGenMysterium extends BiomeGenBase {
	public BiomeGenMysterium(final int par1) {
		super(par1);
		setBiomeName("Mysterium");
		topBlock = Blockizer.GrassMysterium;
		fillerBlock = Blockizer.DirtMysterium;
		rainfall = 100.0f;
		spawnableCreatureList.clear();
		spawnableMonsterList.clear();
		enableSnow = false;
		spawnableCaveCreatureList.clear();
		temperature = 0.4f;
	}

	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(final float par1) {
		return Color.RED.getRGB();
	}
}
