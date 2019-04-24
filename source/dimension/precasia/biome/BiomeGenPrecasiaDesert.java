package net.nevermine.dimension.precasia.biome;

import net.minecraft.world.biome.BiomeGenBase;
import net.nevermine.izer.Blockizer;

public class BiomeGenPrecasiaDesert extends BiomeGenBase {

	public BiomeGenPrecasiaDesert(int par1) {
		super(par1);
		setBiomeName("Precasian Desert");
		topBlock = Blockizer.PrecasianSand;
		fillerBlock = Blockizer.PrecasianSand;
		rainfall = 100;
		spawnableCreatureList.clear();
		spawnableMonsterList.clear();
		enableSnow = false;
		spawnableCaveCreatureList.clear();
		temperature = 2.0F;
	}
}