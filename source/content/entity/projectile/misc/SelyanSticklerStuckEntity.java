package net.tslat.aoa3.content.entity.projectile.misc;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.util.EntityUtil;

public class SelyanSticklerStuckEntity extends AttachedSticklerEntity {
	public SelyanSticklerStuckEntity(EntityType<? extends AttachedSticklerEntity> entityType, Level world) {
		super(entityType, world);
	}

	public SelyanSticklerStuckEntity(Level level, Entity shooter, LivingEntity target, Vec3 stuckOffset, WeaponFiringContext context) {
		super(AoAProjectiles.SELYAN_STICKLER_STUCK.get(), level, shooter, target, stuckOffset, context);
	}

	@Override
	public void tick() {
		super.tick();

		if (!isExpired() && getShooter() instanceof LivingEntity owner)
			EntityUtil.healEntity(owner, 0.03f);
	}
}
