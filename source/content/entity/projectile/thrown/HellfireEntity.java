package net.tslat.aoa3.content.entity.projectile.thrown;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.common.registration.item.AoAWeapons;
import net.tslat.aoa3.content.entity.projectile.HardProjectile;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.content.entity.projectile.misc.HellfireProjectileEntity;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;

public class HellfireEntity extends BaseBullet implements HardProjectile, ItemSupplier {
	private LivingEntity shooter;

	public HellfireEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public HellfireEntity(Level world) {
		super(AoAProjectiles.HELLFIRE.get(), world);
	}

	public HellfireEntity(LivingEntity shooter, BaseGun gun) {
		super(AoAProjectiles.HELLFIRE.get(), shooter, gun, 1.0f, 0, 1.5f);
		this.shooter = shooter;
	}

	public HellfireEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAProjectiles.HELLFIRE.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public HellfireEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.HELLFIRE.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.075f;
	}

	@Override
	protected void onHit(HitResult result) {
		if (result instanceof BlockHitResult && tickCount <= 1 && getOwner() == null)
			return;

		super.onHit(result);
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
		int count = 0;

		for (LivingEntity e : level().getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(7.0D), EntityUtil.Predicates.HOSTILE_MOB)) {
			if (DamageUtil.doMiscMagicAttack(getOwner(), this, 3.5f, position())) {
				level().addFreshEntity(new HellfireProjectileEntity(this, e.getX(), e.getY(), e.getZ()));
				e.setSecondsOnFire(10);
				count++;
			}
		}

		if (shooter instanceof Player) {
			level().playSound(null, getX(), getY(), getZ(), AoASounds.HELLFIRE_IMPACT.get(), SoundSource.PLAYERS, 1.0f, 1.0f);

			if (count >= 20 && shooter instanceof ServerPlayer)
				AdvancementUtil.completeAdvancement((ServerPlayer)shooter, new ResourceLocation(AdventOfAscension.MOD_ID, "overworld/heckfire"), "20_target_hellfire");
		}
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(AoAWeapons.HELLFIRE.get());
	}
}
