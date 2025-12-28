package net.tslat.aoa3.content.entity.projectile.misc;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.tme.api.util.EntityRetrievalUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;

public class LuxonSticklerStuckEntity extends AttachedSticklerEntity {
	public LuxonSticklerStuckEntity(EntityType<? extends AttachedSticklerEntity> entityType, Level world) {
		super(entityType, world);
	}

	public LuxonSticklerStuckEntity(Level level, Entity shooter, LivingEntity target, Vec3 stuckOffset, WeaponFiringContext context) {
		super(AoAProjectiles.LUXON_STICKLER_STUCK.get(), level, shooter, target, stuckOffset, context);
	}

	@Override
	public void tick() {
		super.tick();

		if (level().getGameTime() % 10 == 0)
			EntityUtil.applyPotions(EntityRetrievalUtil.getEntities(this, 7, LivingEntity.class, target -> EntityUtil.areProbablyEnemies(target, getOwner())), getOwner(), new EffectBuilder(MobEffects.GLOWING, 15));
	}
}
