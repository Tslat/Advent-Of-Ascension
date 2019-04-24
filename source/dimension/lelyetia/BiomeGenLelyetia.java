package net.nevermine.dimension.lelyetia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;
import net.nevermine.izer.Blockizer;

import java.awt.*;

public class BiomeGenLelyetia extends BiomeGenBase {
	public BiomeGenLelyetia(final int par1) {
		super(par1);
		setBiomeName("Lelyetia");
		topBlock = Blockizer.GrassLelyetiaUp;
		fillerBlock = Blockizer.StoneLelyetia;
		rainfall = 500.0f;
		spawnableCreatureList.clear();
		spawnableMonsterList.clear();
		enableSnow = false;
		spawnableCaveCreatureList.clear();
		temperature = 2.0f;
	}

	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(final float par1) {
		return Color.BLUE.getRGB();
	}
}
