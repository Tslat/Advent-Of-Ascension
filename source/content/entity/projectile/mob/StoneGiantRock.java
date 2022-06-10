package net.tslat.aoa3.content.entity.projectile.mob;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.content.entity.base.AoARangedAttacker;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class StoneGiantRock extends BaseMobProjectile implements IAnimatable {
	private final AnimationFactory animationFactory = new AnimationFactory(this);

	public StoneGiantRock(EntityType<? extends ThrowableProjectile> entityType, Level level) {
		super(entityType, level);
	}

	public StoneGiantRock(EntityType<? extends ThrowableProjectile> entityType, Level world, AoARangedAttacker shooter, double posX, double posY, double posZ, Type projectileType) {
		super(entityType, world, shooter, posX, posY, posZ, projectileType);
	}

	public StoneGiantRock(EntityType<? extends ThrowableProjectile> entityType, Level world, AoARangedAttacker shooter, Entity target, Type projectileType) {
		super(entityType, world, shooter, target, projectileType);
	}

	public StoneGiantRock(EntityType<? extends ThrowableProjectile> entityType, Level world, AoARangedAttacker shooter, Type projectileType) {
		super(entityType, world, shooter, projectileType);
	}

	@Override
	public float getGravity() {
		return 0.5f;
	}

	@Override
	public void registerControllers(AnimationData data) {}

	@Override
	public AnimationFactory getFactory() {
		return this.animationFactory;
	}
}
