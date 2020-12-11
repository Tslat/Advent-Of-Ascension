package net.tslat.aoa3.entity.projectile.blaster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.item.EnergyProjectileWeapon;
import net.tslat.aoa3.util.LocaleUtil;

public class ArcwormShotEntity extends BaseEnergyShot {
	public ArcwormShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public ArcwormShotEntity(World world) {
		super(AoAEntities.Projectiles.ARCWORM_SHOT.get(), world);
	}

	public ArcwormShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.ARCWORM_SHOT.get(), shooter, weapon, maxAge);
	}

	public ArcwormShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.ARCWORM_SHOT.get(), world, x, y, z);
	}

	@Override
	public float getGravityVelocity() {
		return 0.1f;
	}

	@Override
	public void tick() {
		super.tick();

		Vec3d motion = getMotion();
		float motionVector = MathHelper.sqrt(motion.getX() * motion.getX() + motion.getZ() * motion.getZ());
		rotationYaw = (float)(MathHelper.atan2(motion.getX(), motion.getZ()) * (180D / Math.PI));
		rotationPitch = (float)(MathHelper.atan2(motion.getY(), motionVector) * (180D / Math.PI));
		prevRotationYaw = rotationYaw;
		prevRotationPitch = rotationPitch;
	}

	@Override
	public ITextComponent getName() {
		if (hasCustomName()) {
			return getCustomName();
		}
		else {
			return LocaleUtil.getLocaleMessage("entity.aoa3.arcworm");
		}
	}
}
