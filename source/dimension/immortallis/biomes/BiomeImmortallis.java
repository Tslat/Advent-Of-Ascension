package net.tslat.aoa3.dimension.immortallis.biomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

public class BiomeImmortallis extends Biome {
	private static final BiomeProperties properties = new BiomeProperties("Immortallis");

	static {
		properties.setRainDisabled();
		properties.setTemperature(0f);
		properties.setRainfall(0);
		properties.setWaterColor(new Color(145, 96, 5).getRGB());
		properties.setRainDisabled();
		properties.setBaseHeight(0f);
		properties.setHeightVariation(0.0f);
	}

	public BiomeImmortallis() {
		super(properties);
		setRegistryName("aoa3", "immortallis");
		topBlock = Blocks.AIR.getDefaultState();
		fillerBlock = Blocks.AIR.getDefaultState();
		spawnableCaveCreatureList.clear();
		spawnableCreatureList.clear();
		spawnableMonsterList.clear();
		spawnableWaterCreatureList.clear();
	}

	public void biomeInit() {
		BiomeDictionary.addTypes(this, BiomeDictionary.Type.VOID);
	}

	@Override
	public float getSpawningChance() {
		return 0;
	}

	@Override
	public boolean ignorePlayerSpawnSuitability() {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float currentTemperature) {
		return Color.BLUE.getRGB();
	}
}
