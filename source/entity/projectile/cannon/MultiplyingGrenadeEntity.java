package net.tslat.aoa3.entity.projectile.cannon;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.projectile.HardProjectile;
import net.tslat.aoa3.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.WorldUtil;


public class MultiplyingGrenadeEntity extends BaseBullet implements HardProjectile {
	private int count;
	private LivingEntity shooter;
	private BaseGun gun;
	private Hand hand;

	public MultiplyingGrenadeEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public MultiplyingGrenadeEntity(World world) {
		super(AoAEntities.Projectiles.MULTIPLYING_GRENADE.get(), world);
	}

	public MultiplyingGrenadeEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int counter) {
		super(AoAEntities.Projectiles.MULTIPLYING_GRENADE.get(), shooter, gun, hand, maxAge, 1.0f, 0);
		this.count = counter;
		this.shooter = shooter;
		this.gun = gun;
		this.hand = hand;
	}

	public MultiplyingGrenadeEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.MULTIPLYING_GRENADE.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.075f;
	}

	@Override
	public void doImpactEffect() {
		WorldUtil.createExplosion(getOwner(), level, this, 1.5f);

		if (!level.isClientSide && getAge() < 10 && shooter instanceof PlayerEntity && count < 5) {
			if (gun != null)
				gun.doRecoil((ServerPlayerEntity)shooter, new ItemStack(gun), hand);

			level.addFreshEntity(new MultiplyingGrenadeEntity(shooter, gun, hand, 120, count + 1));
			level.playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), AoASounds.ITEM_MISSILE_MAKER_FIRE.get(), SoundCategory.PLAYERS, 1.0f, 1.0f);
		}
	}

	@Override
	public void tick() {
		super.tick();

		if (!level.isClientSide && count < 5 && getAge() == 10 && shooter instanceof PlayerEntity) {
			if (gun != null)
				gun.doRecoil((ServerPlayerEntity)shooter, new ItemStack(gun), hand);

			level.addFreshEntity(new MultiplyingGrenadeEntity(shooter, gun, hand, 120, count + 1));
			level.playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), AoASounds.ITEM_MISSILE_MAKER_FIRE.get(), SoundCategory.PLAYERS, 1.0f, 1.0f);
		}
	}
}
