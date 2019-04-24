package net.nevermine.dimension.iromine.biome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;
import net.nevermine.izer.Blockizer;

import java.awt.*;

public class BiomeGenIromine extends BiomeGenBase {

	public BiomeGenIromine(int par1) {
		super(par1);
		this.setBiomeName("Golden Hills");
		this.topBlock = Blockizer.GrassIromine;
		this.fillerBlock = Blockizer.IroStone;
		this.rainfall = 100;
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.enableSnow = false;
		this.spawnableCaveCreatureList.clear();
		this.temperature = 0.4F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float par1) {
		return Color.YELLOW.getRGB();
	}

}