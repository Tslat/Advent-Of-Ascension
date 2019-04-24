package net.tslat.aoa3.entity.projectiles.thrown;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.HardProjectile;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.item.weapon.thrown.GooBall;

public class EntityGooBall extends BaseBullet implements HardProjectile {
	public EntityGooBall(World world) {
		super(world);
	}

	public EntityGooBall(EntityLivingBase shooter, BaseGun gun) {
		super(shooter, gun, 1.0f, 0, 3.0f);
	}

	public EntityGooBall(EntityLivingBase shooter, BaseGun gun, EnumHand hand, int maxAge, int piercingValue) {
		super(shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public EntityGooBall(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	public float getGravityVelocity() {
		return 0.05f;
	}

	@Override
	public void doEntityImpact(Entity target) {
		target.attackEntityFrom(DamageSource.causeThrownDamage(this, null), GooBall.dmg);

		if (target instanceof EntityLivingBase)
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 60, 1));

		world.playSound(null, posX, posY, posZ, SoundsRegister.gooBallImpact, SoundCategory.PLAYERS, 1.0f, 1.0f);
	}
}
