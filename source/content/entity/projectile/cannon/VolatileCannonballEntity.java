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


public class VolatileCannonballEntity extends BaseBullet implements HardProjectile {
	private LivingEntity shooter;

	public VolatileCannonballEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public VolatileCannonballEntity(Level world) {
		super(AoAProjectiles.VOLATILE_CANNONBALL.get(), world);
	}

	public VolatileCannonballEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAProjectiles.VOLATILE_CANNONBALL.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
		this.shooter = shooter;
	}

	public VolatileCannonballEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.VOLATILE_CANNONBALL.get(), world, x, y, z);
	}

	@Override
	protected float getGravity() {
		return 0.05f;
	}

	@Override
	public void tick() {
		super.tick();

		if (getAge() % 4 == 1)
			WorldUtil.createExplosion(getOwner(), level(), this, 2.0f);
	}
}
