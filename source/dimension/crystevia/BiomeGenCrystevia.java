package net.nevermine.dimension.crystevia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;
import net.nevermine.izer.Blockizer;

import java.awt.*;

public class BiomeGenCrystevia extends BiomeGenBase {
	public BiomeGenCrystevia(final int par1) {
		super(par1);
		setBiomeName("Crystevia");
		topBlock = Blockizer.CrysteviaRock;
		fillerBlock = Blockizer.CrysteviaRock;
		rainfall = 100.0f;
		spawnableCreatureList.clear();
		spawnableMonsterList.clear();
		enableSnow = false;
		spawnableCaveCreatureList.clear();
		temperature = 0.4f;
		waterColorMultiplier = 13369446;
		spawnableWaterCreatureList.clear();
	}

	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(final float par1) {
		return Color.YELLOW.getRGB();
	}
}
