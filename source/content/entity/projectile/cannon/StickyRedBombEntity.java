package net.tslat.aoa3.content.entity.projectile.cannon;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoAExplosions;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.PhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.item.ProjectileFiringWeapon;
import net.tslat.aoa3.content.item.weapon.cannon.AoACannon;
import net.tslat.aoa3.library.builder.AoAExplosionBuilder;
import net.tslat.tme.api.explosion.StandardExplosion;
import net.tslat.tme.api.object.RayTrace;

public class StickyRedBombEntity extends PhysicalWeaponProjectile {
	protected BlockPos stuckTo;
	protected int countdown = 80;

	public StickyRedBombEntity(EntityType<? extends StickyRedBombEntity> entityType, Level level) {
		super(entityType, level);
	}

	public StickyRedBombEntity(EntityType<? extends StickyRedBombEntity> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public StickyRedBombEntity(Level level, WeaponFiringContext context) {
		this(AoAProjectiles.STICKY_RED_BOMB.get(), level, context);
	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		final BlockState impactedBlock = level().getBlockState(result.getBlockPos());

		impactedBlock.onProjectileHit(level(), impactedBlock, result, this);

		doBlockImpactFx(result, impactedBlock);

		if (doBlockDestruction(result, impactedBlock))
			return;

		if (!impactedBlock.blocksMotion())
			return;

		if (getShotContext().weaponStack().getItem() instanceof ProjectileFiringWeapon projectileFiringWeapon)
			projectileFiringWeapon.doBlockImpact(level(), this, RayTrace.wrap(position(), result), impactedBlock);

		doBlockImpact(result, impactedBlock);
	}

	@Override
	protected void doBlockImpact(BlockHitResult rayTrace, BlockState impactedBlock) {
		setPos(rayTrace.getLocation());
		setDeltaMovement(0, 0, 0);
		setNoGravity(true);

		this.stuckTo = rayTrace.getBlockPos();
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		explode(result.getLocation());
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);

		compound.putInt("Countdown", this.countdown);
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		if (compound.contains("Countdown", Tag.TAG_INT))
			this.countdown = compound.getInt("Countdown");
	}

	@Override
	public void tick() {
		super.tick();

		if (--this.countdown <= 0) {
			explode(position());
			discard();
		}
	}

	protected void explode(Vec3 position) {
		if (level() instanceof ServerLevel level)
			AoAExplosionBuilder.at(level, position, AoAExplosions.bombLauncher(target -> getShotContext().weaponStack().getItem() instanceof AoACannon cannon ? cannon.addCannonDamageBonus(1, target) : 1),
								   StandardExplosion::new).explodingEntity(this).explode();
	}
}
