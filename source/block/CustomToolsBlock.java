package net.tslat.aoa3.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.block.AbstractBlock;

public class CustomToolsBlock extends Block {
	@Nonnull
	private final ToolType[] validToolTypes;

	public CustomToolsBlock(AbstractBlock.Properties properties, @Nonnull String primaryType, String... secondaryTypes) {
		super(properties);

		validToolTypes = new ToolType[secondaryTypes.length + 1];

		validToolTypes[0] = ToolType.get(primaryType);

		for (int i = 0; i < secondaryTypes.length; i++) {
			validToolTypes[i + 1] = ToolType.get(secondaryTypes[i]);
		}
	}

	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return validToolTypes[0];
	}

	@Override
	public boolean isToolEffective(BlockState state, ToolType tool) {
		for (ToolType validToolType : validToolTypes) {
			if (validToolType == tool)
				return true;
		}

		return false;
	}
}
