package net.tslat.aoa3.content.entity.projectile.cannon;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.HardProjectile;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.WorldUtil;


public class MultiplyingGrenadeEntity extends BaseBullet implements HardProjectile {
	private int count;
	private LivingEntity shooter;
	private BaseGun gun;
	private InteractionHand hand;

	public MultiplyingGrenadeEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public MultiplyingGrenadeEntity(Level world) {
		super(AoAProjectiles.MULTIPLYING_GRENADE.get(), world);
	}

	public MultiplyingGrenadeEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int counter) {
		super(AoAProjectiles.MULTIPLYING_GRENADE.get(), shooter, gun, hand, maxAge, 1.0f, 0);
		this.count = counter;
		this.shooter = shooter;
		this.gun = gun;
		this.hand = hand;
	}

	public MultiplyingGrenadeEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.MULTIPLYING_GRENADE.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.075f;
	}

	@Override
	public void doImpactEffect() {
		WorldUtil.createExplosion(getOwner(), level, this, 1.5f);

		if (!level.isClientSide && getAge() < 10 && shooter instanceof Player && count < 5) {
			if (gun != null)
				gun.doRecoil((ServerPlayer)shooter, new ItemStack(gun), hand);

			level.addFreshEntity(new MultiplyingGrenadeEntity(shooter, gun, hand, 120, count + 1));
			level.playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), AoASounds.ITEM_MISSILE_MAKER_FIRE.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
		}
	}

	@Override
	public void tick() {
		super.tick();

		if (!level.isClientSide && count < 5 && getAge() == 10 && shooter instanceof Player) {
			if (gun != null)
				gun.doRecoil((ServerPlayer)shooter, new ItemStack(gun), hand);

			level.addFreshEntity(new MultiplyingGrenadeEntity(shooter, gun, hand, 120, count + 1));
			level.playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), AoASounds.ITEM_MISSILE_MAKER_FIRE.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
		}
	}
}
