package net.nevermine.dimension.dustopia.biome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;
import net.nevermine.izer.Blockizer;

import java.awt.*;

public class BiomeGenDustopiaPlains extends BiomeGenBase {

	public BiomeGenDustopiaPlains(int par1) {
		super(par1);
		setBiomeName("Dustopian Plains");
		topBlock = Blockizer.GrassDustopia;
		fillerBlock = Blockizer.DirtDustopia;
		rainfall = 500;
		spawnableCreatureList.clear();
		spawnableMonsterList.clear();
		enableSnow = false;
		spawnableCaveCreatureList.clear();
		temperature = 0.25F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float par1) {
		return Color.BLACK.getRGB();
	}

}