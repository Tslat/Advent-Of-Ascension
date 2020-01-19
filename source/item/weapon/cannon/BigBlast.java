package net.tslat.aoa3.item.weapon.cannon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;

public class BigBlast extends BaseCannon implements AdventWeapon {
	public BigBlast(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
		setTranslationKey("BigBlast");
		setRegistryName("aoa3:big_blast");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.gunBigBlast;
	}

	@Override
	protected void doImpactEffect(Entity target, EntityLivingBase shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		WorldUtil.createExplosion(shooter, bullet.world, bullet, 3f);
	}
}
