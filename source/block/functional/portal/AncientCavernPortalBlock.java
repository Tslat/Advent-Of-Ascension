package net.tslat.aoa3.block.functional.portal;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.util.NumberUtil;

public class AncientCavernPortalBlock extends PortalBlock {
	public AncientCavernPortalBlock() {
		super(AoADimensions.ANCIENT_CAVERN::type, MaterialColor.GREEN_TERRACOTTA, NumberUtil.RGB(0, 147, 66));
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		if (entity instanceof PlayerEntity || (entity instanceof TameableEntity && ((TameableEntity)entity).isTamed()))
			super.onEntityCollision(state, world, pos, entity);
	}
}
