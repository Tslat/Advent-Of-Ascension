package net.nevermine.dimension.shyrelands;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.world.biome.BiomeGenBase;
import net.nevermine.izer.Blockizer;

import java.awt.*;

public class BiomeGenShyrelands extends BiomeGenBase {
	public BiomeGenShyrelands(int par1) {
		super(par1);
		setBiomeName("Shyrelands");
		topBlock = Blockizer.ShyreGrass;
		fillerBlock = Blockizer.ShyreRock;
		rainfall = 500.0F;
		spawnableCreatureList.clear();
		spawnableMonsterList.clear();
		enableSnow = false;
		spawnableCaveCreatureList.clear();
		temperature = 2.0F;
	}

	@cpw.mods.fml.relauncher.SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float par1) {
		return Color.BLUE.getRGB();
	}
}
