package net.nevermine.dimension.barathos.biome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;
import net.nevermine.izer.Blockizer;

import java.awt.*;

public class BiomeGenBaronForest extends BiomeGenBase {

	public BiomeGenBaronForest(int par1) {
		super(par1);
		setBiomeName("Alien Forest");
		topBlock = Blockizer.BaronGround;
		fillerBlock = Blockizer.BaronStone;
		rainfall = 500;
		spawnableCreatureList.clear();
		spawnableMonsterList.clear();
		enableSnow = false;
		spawnableCaveCreatureList.clear();
		temperature = 2.0F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float par1) {
		return Color.ORANGE.getRGB();
	}

}