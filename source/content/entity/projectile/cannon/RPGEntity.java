package net.tslat.aoa3.content.entity.projectile.cannon;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.HardProjectile;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.WorldUtil;


public class RPGEntity extends BaseBullet implements HardProjectile {
	private LivingEntity shooter;

	public RPGEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public RPGEntity(Level world) {
		super(AoAProjectiles.RPG.get(), world);
	}

	public RPGEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAProjectiles.RPG.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
		this.shooter = shooter;
	}

	public RPGEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.RPG.get(), world, x, y, z);
	}

	@Override
	public void doImpactEffect() {
		if (!level.isClientSide)
			WorldUtil.createExplosion(shooter, level, this, 2.7f);
	}
}
