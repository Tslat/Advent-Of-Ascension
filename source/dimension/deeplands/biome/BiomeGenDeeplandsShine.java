package net.nevermine.dimension.deeplands.biome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;
import net.nevermine.izer.Blockizer;

import java.awt.*;

public class BiomeGenDeeplandsShine extends BiomeGenBase {

	public BiomeGenDeeplandsShine(int par1) {
		super(par1);
		setBiomeName("Deepshine Cave");
		topBlock = Blockizer.DeepRock;
		fillerBlock = Blockizer.DeepRock;
		rainfall = 500;
		spawnableCreatureList.clear();
		spawnableMonsterList.clear();
		enableSnow = false;
		spawnableCaveCreatureList.clear();
		temperature = 0.0F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float par1) {
		return Color.GREEN.getRGB();
	}

}