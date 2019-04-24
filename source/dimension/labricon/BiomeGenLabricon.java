package net.nevermine.dimension.labricon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import java.awt.*;

public class BiomeGenLabricon extends BiomeGenBase {
	public BiomeGenLabricon(final int par1) {
		super(par1);
		setBiomeName("Vox Ponds");
		topBlock = Blocks.air;
		fillerBlock = Blocks.air;
		rainfall = 0.0f;
		spawnableCreatureList.clear();
		spawnableMonsterList.clear();
		enableSnow = false;
		spawnableCaveCreatureList.clear();
		temperature = 1.0f;
	}

	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(final float par1) {
		return Color.green.getRGB();
	}
}
