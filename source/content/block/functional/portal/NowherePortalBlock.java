package net.tslat.aoa3.content.block.functional.portal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.util.ColourUtil;

public class NowherePortalBlock extends PortalBlock {
	public NowherePortalBlock(BlockBehaviour.Properties properties) {
		super(properties, AoADimensions.NOWHERE, ColourUtil.RGB(255, 227, 117), AoASounds.BLOCK_NOWHERE_PORTAL_AMBIENT);
	}

	@Override
	public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
		if (entity instanceof Player || (entity instanceof TamableAnimal && ((TamableAnimal)entity).isTame()))
			super.entityInside(state, world, pos, entity);
	}
}