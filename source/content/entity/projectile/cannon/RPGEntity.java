package net.tslat.aoa3.content.entity.projectile.cannon;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.HardProjectile;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;
import net.tslat.aoa3.library.object.explosion.ExplosionInfo;
import net.tslat.aoa3.library.object.explosion.StandardExplosion;


public class RPGEntity extends BaseBullet implements HardProjectile {
	private static final ExplosionInfo RPG_EXPLOSION = new ExplosionInfo().explodeInOneTick().radius(4).penetration(10).blocksDropChance(0.85f).baseDamage(16).baseKnockbackStrength(2.5f);

	public RPGEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public RPGEntity(Level world) {
		super(AoAProjectiles.RPG.get(), world);
	}

	public RPGEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAProjectiles.RPG.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public RPGEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.RPG.get(), world, x, y, z);
	}

	@Override
	public void doImpactEffect() {
		if (!level.isClientSide)
			new StandardExplosion(ExplosionInfo.from(RPG_EXPLOSION).radius(4.5f).penetration(15), (ServerLevel)getLevel(), this, getOwner()).explode();
	}
}
