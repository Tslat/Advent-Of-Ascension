package net.tslat.aoa3.content.entity.projectile.thrown;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.ThrownItemProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.misc.HellfireProjectileEntity;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.tme.api.util.EntityRetrievalUtil;

public class HellfireEntity extends ThrownItemProjectile {
	public HellfireEntity(EntityType<? extends HellfireEntity> entityType, Level level) {
		super(entityType, level);
	}

	public HellfireEntity(EntityType<? extends HellfireEntity> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public HellfireEntity(Level level, WeaponFiringContext context) {
		this(AoAProjectiles.HELLFIRE.get(), level, context);
	}

	@Override
	public double getDefaultGravity() {
		return 0.075f;
	}

	@Override
	protected void doBlockImpact(BlockHitResult rayTrace, BlockState impactedBlock) {
		explode(rayTrace.getLocation());
	}

	@Override
	protected void doEntityImpact(EntityHitResult rayTrace, Entity hitEntity) {
		explode(rayTrace.getLocation());
	}

	protected void explode(Vec3 position) {
		int count = 0;
		Entity shooter = getShooter();

		for (LivingEntity target : EntityRetrievalUtil.getEntities(this, 7, LivingEntity.class, target -> EntityUtil.areProbablyEnemies(target, shooter))) {
			if (DamageUtil.doMiscMagicAttack(shooter, target, getShotContext().damage(), position)) {
				HellfireProjectileEntity tail = new HellfireProjectileEntity(this, target.getX(), target.getY(), target.getZ());

				tail.setOwner(shooter);

				level().addFreshEntity(tail);
				target.igniteForSeconds(10);

				if (shooter != null)
					target.hurt(level().damageSources().indirectMagic(shooter, this), 0.1f);

				count++;
			}
		}

		level().playSound(null, getX(), getY(), getZ(), AoASounds.HELLFIRE_IMPACT.get(), getShooter() != null ? getShooter().getSoundSource() : SoundSource.PLAYERS, 1, 1);

		if (count >= 20 && shooter instanceof ServerPlayer)
			AdvancementUtil.grantCriterion((ServerPlayer)shooter, AdventOfAscension.id("overworld/heckfire"), "20_target_hellfire");
	}
}
