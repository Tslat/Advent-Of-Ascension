package net.nevermine.dimension.precasia.biome;

import net.minecraft.world.biome.BiomeGenBase;
import net.nevermine.izer.Blockizer;

public class BiomeGenPrecasia extends BiomeGenBase {

	public BiomeGenPrecasia(int par1) {
		super(par1);
		setBiomeName("Precasian Forest");
		topBlock = Blockizer.GrassPrecasia;
		fillerBlock = Blockizer.StonePrecasianHigh;
		rainfall = 100;
		spawnableCreatureList.clear();
		spawnableMonsterList.clear();
		enableSnow = false;
		spawnableCaveCreatureList.clear();
		temperature = 2.0F;
	}
}