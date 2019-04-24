package net.tslat.aoa3.entity.projectiles.cannon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.HardProjectile;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

public class EntityMultiplyingGrenade extends BaseBullet implements HardProjectile {
	private int count;
	private EntityLivingBase shooter;
	private BaseGun gun;
	private EnumHand hand;

	public EntityMultiplyingGrenade(World world) {
		super(world);
	}

	public EntityMultiplyingGrenade(EntityLivingBase shooter, BaseGun gun, EnumHand hand, int maxAge, int counter) {
		super(shooter, gun, hand, maxAge, 1.0f, 0);
		this.count = counter;
		this.shooter = shooter;
		this.gun = gun;
		this.hand = hand;
	}

	public EntityMultiplyingGrenade(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	public float getGravityVelocity() {
		return 0.075f;
	}

	@Override
	public void doImpactEffect() {
		world.createExplosion(shooter, posX, posY, posZ, 1.5f, false);

		if (!world.isRemote && getAge() < 10 && shooter instanceof EntityPlayer && count < 5) {
			world.spawnEntity(new EntityMultiplyingGrenade(shooter, gun, hand, 120, count + 1));
			world.playSound(null, shooter.posX, shooter.posY, shooter.posZ, SoundsRegister.gunMissileMaker, SoundCategory.PLAYERS, 1.0f, 1.0f);
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (!world.isRemote && count < 5 && getAge() == 10 && shooter instanceof EntityPlayer) {
			world.spawnEntity(new EntityMultiplyingGrenade(shooter, gun, hand, 120, count + 1));
			world.playSound(null, shooter.posX, shooter.posY, shooter.posZ, SoundsRegister.gunMissileMaker, SoundCategory.PLAYERS, 1.0f, 1.0f);
		}
	}
}
