package net.nevermine.dimension.greckon.biome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;
import net.nevermine.izer.Blockizer;

import java.awt.*;

public class BiomeGenGreckon extends BiomeGenBase {

	public BiomeGenGreckon(int par1) {
		super(par1);
		setBiomeName("Greckon");
		topBlock = Blockizer.GrassGreckon;
		fillerBlock = Blockizer.DirtGreckon;
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
		return Color.PINK.getRGB();
	}

}