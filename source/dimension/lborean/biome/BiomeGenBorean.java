package net.nevermine.dimension.lborean.biome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;
import net.nevermine.izer.Blockizer;

import java.awt.*;

public class BiomeGenBorean extends BiomeGenBase {

	public BiomeGenBorean(int par1) {
		super(par1);
		setBiomeName("Borean Ponds");
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