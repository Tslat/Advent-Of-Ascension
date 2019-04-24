package net.nevermine.dimension.celeve;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;
import net.nevermine.izer.Blockizer;

import java.awt.*;

public class BiomeGenCeleve extends BiomeGenBase {
	public BiomeGenCeleve(final int par1) {
		super(par1);
		setBiomeName("Celeve");
		topBlock = Blockizer.GrassCeleve;
		fillerBlock = Blockizer.DirtCeleve;
		spawnableCreatureList.clear();
		spawnableMonsterList.clear();
		spawnableCaveCreatureList.clear();
		spawnableWaterCreatureList.clear();
		spawnableCaveCreatureList.clear();
		temperature = 0.5f;
	}

	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(final float par1) {
		return Color.green.getRGB();
	}
}
