package net.tslat.aoa3.entity.projectile.blaster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.item.EnergyProjectileWeapon;
import net.tslat.aoa3.util.EntityUtil;

import java.util.List;

public class HellBubbleShotEntity extends BaseEnergyShot {
	public HellBubbleShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public HellBubbleShotEntity(World world) {
		super(AoAEntities.Projectiles.HELL_BUBBLE_SHOT.get(), world);
	}

	public HellBubbleShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.HELL_BUBBLE_SHOT.get(), shooter, weapon, maxAge);
	}

	public HellBubbleShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.HELL_BUBBLE_SHOT.get(), world, x, y, z);
	}

	@Override
	public void tick() {
		super.tick();

		setDeltaMovement(getDeltaMovement().multiply(0.3d, 0.3d, 0.3d));

		if (getAge() >= 100)
			remove();

		if (!isAlive()) {
			level.playSound(null, getX(), getY(), getZ(), AoASounds.BUBBLE_SHOT_POP.get(), SoundCategory.PLAYERS, 1.0f, 1.0f);
		}
		else if (!level.isClientSide && weapon != null) {
			List<LivingEntity> collidingEntities = level.getEntitiesOfClass(LivingEntity.class, getBoundingBox(), EntityUtil.Predicates.HOSTILE_MOB);

			if (!collidingEntities.isEmpty()) {
				Entity shooter = getOwner();

				if (shooter instanceof LivingEntity)
					weapon.doEntityImpact(this, collidingEntities.get(0), (LivingEntity)shooter);

				remove();
			}
		}
	}
}
