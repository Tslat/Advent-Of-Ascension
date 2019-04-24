package net.nevermine.dimension.iromine.biome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;
import net.nevermine.izer.Blockizer;

import java.awt.*;

public class BiomeGenIromineTech extends BiomeGenBase {

	public BiomeGenIromineTech(int par1) {
		super(par1);
		setBiomeName("Iro-Tech Hills");
		topBlock = Blockizer.GrassIromine;
		fillerBlock = Blockizer.IroStone;
		rainfall = 100;
		spawnableCreatureList.clear();
		spawnableMonsterList.clear();
		enableSnow = false;
		spawnableCaveCreatureList.clear();
		temperature = 0.4F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float par1) {
		return Color.YELLOW.getRGB();
	}

}