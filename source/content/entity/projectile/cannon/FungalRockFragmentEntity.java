package net.tslat.aoa3.content.entity.projectile.cannon;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.SectionPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.phys.BlockHitResult;
import net.tslat.aoa3.common.registration.AoAGameRules;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.PhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.util.WorldUtil;

public class FungalRockFragmentEntity extends PhysicalWeaponProjectile {
	public FungalRockFragmentEntity(EntityType<? extends FungalRockFragmentEntity> entityType, Level level) {
		super(entityType, level);
	}

	public FungalRockFragmentEntity(EntityType<? extends FungalRockFragmentEntity> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public FungalRockFragmentEntity(Level level, WeaponFiringContext context) {
		this(AoAProjectiles.FUNGAL_ROCK_FRAGMENT.get(), level, context);
	}

	@Override
	public double getDefaultGravity() {
		return 0.06f;
	}

	@Override
	protected void doBlockImpact(BlockHitResult rayTrace, BlockState impactedBlock) {
		if (level() instanceof ServerLevel level && AoAGameRules.checkDestructiveWeaponPhysics(level)) {
			BlockPos.MutableBlockPos placePos = rayTrace.getBlockPos().mutable().move(rayTrace.getDirection());
			LevelChunk chunk = level.getChunk(SectionPos.blockToSectionCoord(placePos.getX()), SectionPos.blockToSectionCoord(placePos.getZ()));

			while (placePos.getY() >= level.getMinBuildHeight() && chunk.getBlockState(placePos.move(Direction.DOWN)).canBeReplaced());

			if (placePos.move(Direction.UP).getY() <= level.getMinBuildHeight())
				return;

			if (!WorldUtil.canPlaceBlock(level, placePos, getShotContext().getShooter(), null))
				return;

			level.setBlockAndUpdate(placePos, Blocks.MOSSY_COBBLESTONE.defaultBlockState());
		}
	}
}
