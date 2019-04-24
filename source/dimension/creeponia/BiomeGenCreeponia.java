package net.nevermine.dimension.creeponia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;
import net.nevermine.izer.Blockizer;

import java.awt.*;

public class BiomeGenCreeponia extends BiomeGenBase {
	public BiomeGenCreeponia(final int par1) {
		super(par1);
		setBiomeName("Creeponia");
		topBlock = Blockizer.GrassCreeponia;
		fillerBlock = Blockizer.CreepDirt;
		rainfall = 100.0f;
		spawnableCreatureList.clear();
		spawnableMonsterList.clear();
		enableSnow = false;
		spawnableCaveCreatureList.clear();
		temperature = 0.4f;
	}

	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(final float par1) {
		return Color.YELLOW.getRGB();
	}
}
