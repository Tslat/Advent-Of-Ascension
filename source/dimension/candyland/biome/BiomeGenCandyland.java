package net.nevermine.dimension.candyland.biome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;
import net.nevermine.izer.Blockizer;

import java.awt.*;

public class BiomeGenCandyland extends BiomeGenBase {

	public BiomeGenCandyland(int par1) {
		super(par1);
		setBiomeName("Candyland");
		topBlock = Blockizer.GrassCandyland;
		fillerBlock = Blockizer.DirtCandyland;
		rainfall = 100;
		spawnableCreatureList.clear();
		spawnableMonsterList.clear();
		enableSnow = false;
		spawnableCaveCreatureList.clear();
		temperature = 0.4F;
		waterColorMultiplier = 0x993333;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float par1) {
		return Color.YELLOW.getRGB();
	}

}