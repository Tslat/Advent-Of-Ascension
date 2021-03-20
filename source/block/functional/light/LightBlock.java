package net.tslat.aoa3.block.functional.light;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

import net.minecraft.block.AbstractBlock;

public class LightBlock extends Block {
	public LightBlock(MaterialColor mapColour, Material material, float hardness, float resistance, int lightLevel) {
		this(mapColour, material, hardness, resistance, lightLevel, null, -1, null);
	}

	public LightBlock(MaterialColor mapColour, Material material, float hardness, float resistance, int lightLevel, @Nullable ToolType harvestTool) {
		this(mapColour, material, hardness, resistance, lightLevel, harvestTool, -1, null);
	}

	public LightBlock(MaterialColor mapColour, Material material, float hardness, float resistance, int lightLevel, @Nullable ToolType harvestTool, int harvestLevel) {
		this(mapColour, material, hardness, resistance, lightLevel, harvestTool, harvestLevel, null);
	}

	public LightBlock(MaterialColor mapColour, Material material, float hardness, float resistance, int lightLevel, @Nullable ToolType harvestTool, int harvestLevel, @Nullable SoundType soundType) {
		super(generateBlockProperties(material, mapColour, hardness, resistance, lightLevel, harvestTool, harvestLevel, soundType));
	}

	private static AbstractBlock.Properties generateBlockProperties(Material material, MaterialColor mapColour, float hardness, float resistance, int lightLevel, @Nullable ToolType harvestTool, int harvestLevel, @Nullable SoundType soundType) {
		AbstractBlock.Properties blockProperties = AbstractBlock.Properties.of(material, mapColour);

		blockProperties.strength(hardness, resistance);
		blockProperties.harvestLevel(harvestLevel);
		blockProperties.lightLevel(state -> lightLevel);

		if (hardness == -1 && resistance == 999999999f)
			blockProperties.noDrops();

		if (harvestTool != null)
			blockProperties.harvestTool(harvestTool);

		if (soundType != null) {
			blockProperties.sound(soundType);
		}
		else {
			if (material == null || material == Material.STONE) {
				blockProperties.sound(SoundType.STONE);
			}
			else if (material == Material.WOOD) {
				blockProperties.sound(SoundType.WOOD);
			}
			else if (material == Material.DIRT) {
				blockProperties.sound(SoundType.GRAVEL);
			}
			else if (material == Material.GLASS) {
				blockProperties.sound(SoundType.GLASS);
			}
		}

		return blockProperties;
	}
}
