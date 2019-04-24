package net.nevermine.dimension.haven;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;
import net.nevermine.izer.Blockizer;

import java.awt.*;

public class BiomeGenHaven extends BiomeGenBase {
	public BiomeGenHaven(final int par1) {
		super(par1);
		setBiomeName("Haven");
		topBlock = Blockizer.GrassHaven;
		fillerBlock = Blockizer.DirtHaven;
		spawnableCreatureList.clear();
		spawnableMonsterList.clear();
		spawnableCaveCreatureList.clear();
		spawnableWaterCreatureList.clear();
		theBiomeDecorator.treesPerChunk = 8;
		spawnableCaveCreatureList.clear();
		temperature = 0.5f;
	}

	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(final float par1) {
		return Color.green.getRGB();
	}
}
