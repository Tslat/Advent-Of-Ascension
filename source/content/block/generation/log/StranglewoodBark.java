package net.tslat.aoa3.content.block.generation.log;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.block.BlockRegistrar;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class StranglewoodBark extends LogBlock {
	public static final VoxelShape BARK_AABB = Shapes.create(new AABB(0.001D, 0.001D, 0.001D, 0.999D, 0.999D, 0.999D));

	public static void build(BlockRegistrar<StranglewoodBark> registrar) {
		registrar.basedOn(AoABlocks.STRANGLEWOOD_LOG).mapColour(MapColor.TERRACOTTA_BROWN).factory(properties -> new LogBlock(properties, AoABlocks.STRIPPED_STRANGLEWOOD));
	}

	public StranglewoodBark(BlockBehaviour.Properties properties, @Nullable Supplier<? extends Block> strippedBlock) {
		super(properties, strippedBlock);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return BARK_AABB;
	}
}
