package net.tslat.aoa3.content.entity.projectile.mob;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.mob.overworld.BombCarrierEntity;
import net.tslat.aoa3.library.builder.SoundBuilder;
import net.tslat.aoa3.library.object.explosion.ExplosionInfo;
import net.tslat.aoa3.library.object.explosion.StandardExplosion;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class BombCarrierDynamiteEntity extends BaseMobProjectile implements GeoEntity {
	public static final ExplosionInfo EXPLOSION_INFO = new ExplosionInfo().baseDamage(8).radius(3f).penetration(8.5f).blocksDropChance(0.3f).explodeInOneTick();

	private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	public BombCarrierDynamiteEntity(Level world, Vec3 position, BombCarrierEntity owner) {
		super(AoAProjectiles.BOMB_CARRIER_DYNAMITE.get(), world, owner, Type.PHYSICAL);

		moveTo(position.x, position.y, position.z, random.nextFloat() * 360.0f, 0.0f);
	}

	public BombCarrierDynamiteEntity(EntityType<? extends BombCarrierDynamiteEntity> entityType, Level level) {
		super(entityType, level);

		this.shooter = null;

		if (level.isClientSide())
			new SoundBuilder(AoASounds.LIT_FUSE).followEntity(this).category(SoundSource.HOSTILE).execute();
	}

	@Override
	public float getGravity() {
		return 0.05f;
	}

	@Override
	protected void onHitEntity(EntityHitResult hitResult) {
		explode(hitResult.getLocation());
	}

	@Override
	protected void onHitBlock(BlockHitResult hitResult) {
		explode(hitResult.getLocation());
	}

	private void explode(Vec3 position) {
		if (level instanceof ServerLevel serverLevel)
			new StandardExplosion(EXPLOSION_INFO, serverLevel, this, position).explode();
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.geoCache;
	}
}
