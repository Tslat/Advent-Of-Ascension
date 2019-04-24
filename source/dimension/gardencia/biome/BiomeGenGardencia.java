package net.nevermine.dimension.gardencia.biome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;
import net.nevermine.izer.Blockizer;

import java.awt.*;

public class BiomeGenGardencia extends BiomeGenBase {

	public BiomeGenGardencia(int par1) {
		super(par1);
		setBiomeName("Gardencia");
		topBlock = Blockizer.GrassGardencia;
		fillerBlock = Blockizer.DirtGardencia;
		rainfall = 7000;
		spawnableCreatureList.clear();
		spawnableMonsterList.clear();
		enableSnow = false;
		spawnableCaveCreatureList.clear();
		waterColorMultiplier = 0x19D119;
		temperature = 0.5F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float par1) {
		return Color.GREEN.getRGB();
	}

}