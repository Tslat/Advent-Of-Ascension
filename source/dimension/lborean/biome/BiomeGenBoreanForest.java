package net.nevermine.dimension.lborean.biome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;
import net.nevermine.izer.Blockizer;

import java.awt.*;

public class BiomeGenBoreanForest extends BiomeGenBase {

	public BiomeGenBoreanForest(int par1) {
		super(par1);
		setBiomeName("Coral Forest");
		topBlock = Blockizer.GrassBorean;
		fillerBlock = Blockizer.DirtBorean;
		rainfall = 500;
		spawnableCreatureList.clear();
		spawnableMonsterList.clear();
		enableSnow = false;
		spawnableCaveCreatureList.clear();
		temperature = 0.5F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float par1) {
		return Color.BLUE.getRGB();
	}

}