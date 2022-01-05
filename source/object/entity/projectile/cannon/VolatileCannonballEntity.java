package net.tslat.aoa3.object.entity.projectile.cannon;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.object.entity.projectile.HardProjectile;
import net.tslat.aoa3.object.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.object.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.WorldUtil;


public class VolatileCannonballEntity extends BaseBullet implements HardProjectile {
	private LivingEntity shooter;

	public VolatileCannonballEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public VolatileCannonballEntity(World world) {
		super(AoAEntities.Projectiles.VOLATILE_CANNONBALL.get(), world);
	}

	public VolatileCannonballEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.VOLATILE_CANNONBALL.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
		this.shooter = shooter;
	}

	public VolatileCannonballEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.VOLATILE_CANNONBALL.get(), world, x, y, z);
	}

	@Override
	protected float getGravity() {
		return 0.05f;
	}

	@Override
	public void tick() {
		super.tick();

		if (getAge() % 4 == 1)
			WorldUtil.createExplosion(getOwner(), level, this, 2.0f);
	}
}
