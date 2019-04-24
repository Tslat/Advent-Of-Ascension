package net.nevermine.dimension.abyss.biome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;
import net.nevermine.izer.Blockizer;

import java.awt.*;

public class BiomeGenAbyss extends BiomeGenBase {

	public BiomeGenAbyss(int par1) {
		super(par1);
		this.setBiomeName("Abyssal Void");
		this.topBlock = Blockizer.AbyssGrass;
		this.fillerBlock = Blockizer.AbyssStone;
		this.rainfall = 100;
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.enableSnow = false;
		this.spawnableCaveCreatureList.clear();
		this.temperature = 0F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float par1) {
		return Color.RED.getRGB();
	}
}