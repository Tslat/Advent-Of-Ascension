package net.tslat.aoa3.content.block.tileentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.block.AoABlockEntities;

public class BossAltarTileEntity extends BlockEntity {
	public BossAltarTileEntity(BlockPos pos, BlockState blockState) {
		super(AoABlockEntities.BOSS_ALTAR.get(), pos, blockState);
	}
}
