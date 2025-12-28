package net.tslat.aoa3.content.entity.projectile.blaster;

import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.NonPhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.util.LocaleUtil;

public class ArcwormShotEntity extends NonPhysicalWeaponProjectile {
	public ArcwormShotEntity(EntityType<? extends ArcwormShotEntity> entityType, Level level) {
		super(entityType, level);
	}

	public ArcwormShotEntity(EntityType<? extends ArcwormShotEntity> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public ArcwormShotEntity(Level level, WeaponFiringContext context) {
		this(AoAProjectiles.ARCWORM_SHOT.get(), level, context);
	}

	@Override
	public double getDefaultGravity() {
		return 0.1f;
	}

	@Override
	public void tick() {
		super.tick();

		Vec3 motion = getDeltaMovement();

		setYRot((float)(Mth.atan2(motion.x(), motion.z()) * Mth.RAD_TO_DEG));
		setXRot((float)(Mth.atan2(motion.y(), motion.horizontalDistance()) * Mth.RAD_TO_DEG));

		this.yRotO = getYRot();
		this.xRotO = getXRot();
	}

	@Override
	public Component getName() {
		if (hasCustomName())
			return getCustomName();

		return LocaleUtil.getLocaleMessage("entity.aoa3.arcworm");
	}
}
