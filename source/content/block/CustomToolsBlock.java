package net.tslat.aoa3.content.block;

import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;


public class CustomToolsBlock extends Block {
	//@NotNull
	//private final ToolType[] validToolTypes;

	public CustomToolsBlock(Block.Properties properties, @NotNull String primaryType, String... secondaryTypes) {
		super(properties);

		// This doesn't appear to be possible anymore? I think Forge outright broke this functionality as of 1.17

		/*validToolTypes = new ToolType[secondaryTypes.length + 1];

		validToolTypes[0] = ToolType.get(primaryType);

		for (int i = 0; i < secondaryTypes.length; i++) {
			validToolTypes[i + 1] = ToolType.get(secondaryTypes[i]);
		}*/
	}

	/*@Nullable
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
	}*/
}
