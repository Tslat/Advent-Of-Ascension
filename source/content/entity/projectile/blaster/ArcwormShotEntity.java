package net.tslat.aoa3.content.entity.projectile.blaster;

import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;
import net.tslat.aoa3.util.LocaleUtil;

public class ArcwormShotEntity extends BaseEnergyShot {
	public ArcwormShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public ArcwormShotEntity(Level world) {
		super(AoAProjectiles.ARCWORM_SHOT.get(), world);
	}

	public ArcwormShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAProjectiles.ARCWORM_SHOT.get(), shooter, weapon, maxAge);
	}

	public ArcwormShotEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.ARCWORM_SHOT.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.1f;
	}

	@Override
	public void tick() {
		super.tick();

		Vec3 motion = getDeltaMovement();
		double motionVector = Math.sqrt(motion.x() * motion.x() + motion.z() * motion.z());
		setYRot((float)(Mth.atan2(motion.x(), motion.z()) * (180D / Math.PI)));
		setXRot((float)(Mth.atan2(motion.y(), motionVector) * (180D / Math.PI)));
		yRotO = getYRot();
		xRotO = getXRot();
	}

	@Override
	public Component getName() {
		if (hasCustomName()) {
			return getCustomName();
		}
		else {
			return LocaleUtil.getLocaleMessage("entity.aoa3.arcworm");
		}
	}
}
