package net.nevermine.dimension.voxponds;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;
import net.nevermine.izer.Blockizer;

import java.awt.*;

public class BiomeGenVoxponds extends BiomeGenBase {
	public BiomeGenVoxponds(final int par1) {
		super(par1);
		setBiomeName("Vox Ponds");
		topBlock = Blockizer.GrassToxic;
		fillerBlock = Blockizer.DirtToxic;
		rainfall = 0.0f;
		spawnableCreatureList.clear();
		spawnableMonsterList.clear();
		enableSnow = false;
		spawnableCaveCreatureList.clear();
		waterColorMultiplier = 3394611;
		temperature = 0.0f;
	}

	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(final float par1) {
		return Color.green.getRGB();
	}
}
