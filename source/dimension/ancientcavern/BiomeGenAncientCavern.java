package net.nevermine.dimension.ancientcavern;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import java.awt.*;

public class BiomeGenAncientCavern extends BiomeGenBase {
	public BiomeGenAncientCavern(final int par1) {
		super(par1);
		setBiomeName("Ancient Cavern");
		topBlock = Blocks.air;
		fillerBlock = Blocks.air;
		rainfall = 0.0f;
		spawnableCreatureList.clear();
		spawnableMonsterList.clear();
		enableSnow = false;
		spawnableCaveCreatureList.clear();
		temperature = 0.0f;
	}

	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(final float par1) {
		return Color.BLUE.getRGB();
	}
}
