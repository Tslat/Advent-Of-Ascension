package net.tslat.aoa3.entity.projectile.blaster;

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

public class BubbleShotEntity extends BaseEnergyShot {
	public BubbleShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public BubbleShotEntity(World world) {
		super(AoAEntities.Projectiles.BUBBLE_SHOT.get(), world);
	}

	public BubbleShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.BUBBLE_SHOT.get(), shooter, weapon, maxAge);
	}

	public BubbleShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.BUBBLE_SHOT.get(), world, x, y, z);
	}

	@Override
	public void tick() {
		super.tick();

		setMotion(getMotion().mul(0.3d, 0.3d, 0.3d));

		if (getAge() >= 100)
			remove();

		if (!isAlive()) {
			world.playSound(null, getPosX(), getPosY(), getPosZ(), AoASounds.BUBBLE_SHOT_POP.get(), SoundCategory.PLAYERS, 1.0f, 1.0f);
		}
		else if (!world.isRemote && weapon != null) {
			List<LivingEntity> collidingEntities = world.getEntitiesWithinAABB(LivingEntity.class, getBoundingBox(), EntityUtil.Predicates.HOSTILE_MOB);

			if (!collidingEntities.isEmpty()) {
				weapon.doEntityImpact(this, collidingEntities.get(0), owner);
				remove();
			}
		}
	}
}
