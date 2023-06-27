package net.tslat.aoa3.content.entity.projectile.cannon;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoAExplosions;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.HardProjectile;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;
import net.tslat.aoa3.library.object.explosion.StandardExplosion;


public class RPGEntity extends BaseBullet implements HardProjectile {
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
	public void doBlockImpact(Vec3 impactLocation, Direction face, BlockPos blockPos) {
		explode(impactLocation);
	}

	@Override
	public void doEntityImpact(Entity target, Vec3 impactLocation) {
		explode(impactLocation);
	}

	protected void explode(Vec3 position) {
		if (!level().isClientSide)
			new StandardExplosion(AoAExplosions.RPG, (ServerLevel)level(), this, getOwner(), position).explode();
	}
}
