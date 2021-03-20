package net.tslat.aoa3.block.functional.light;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneLampBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;
import net.tslat.aoa3.util.BlockUtil;

import javax.annotation.Nullable;
import java.util.Random;

public class LampBlock extends RedstoneLampBlock {
	public static final BooleanProperty TOGGLEABLE = BooleanProperty.create("toggleable");

	public LampBlock(MaterialColor mapColour, Material material, float hardness, float resistance, int lightLevel) {
		this(mapColour, material, hardness, resistance, lightLevel, null, -1, null);
	}

	public LampBlock(MaterialColor mapColour, Material material, float hardness, float resistance, int lightLevel, @Nullable ToolType harvestTool) {
		this(mapColour, material, hardness, resistance, lightLevel, harvestTool, -1, null);
	}

	public LampBlock(MaterialColor mapColour, Material material, float hardness, float resistance, int lightLevel, @Nullable ToolType harvestTool, int harvestLevel) {
		this(mapColour, material, hardness, resistance, lightLevel, harvestTool, harvestLevel, null);
	}

	public LampBlock(MaterialColor mapColour, Material material, float hardness, float resistance, int lightLevel, @Nullable ToolType harvestTool, int harvestLevel, @Nullable SoundType soundType) {
		super(BlockUtil.generateBlockProperties(material, mapColour, hardness, resistance, harvestTool, soundType, lightLevel, harvestLevel).lightLevel(state -> state.getValue(LIT) ? 14 : 0));

		registerDefaultState(defaultBlockState().setValue(TOGGLEABLE, true));
	}

	@Override
	public void neighborChanged(BlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
		if (!world.isClientSide() && state.getValue(TOGGLEABLE)) {
			boolean lit = state.getValue(LIT);

			if (lit != world.hasNeighborSignal(pos)) {
				if (lit) {
					world.getBlockTicks().scheduleTick(pos, this, 4);
				}
				else {
					world.setBlock(pos, state.cycle(LIT), 2);
				}
			}
		}
	}

	@Override
	public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
		if (state.getValue(TOGGLEABLE) && state.getValue(LIT) && !world.hasNeighborSignal(pos))
			world.setBlock(pos, state.cycle(LIT), 2);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(LIT).add(TOGGLEABLE);
	}
}
