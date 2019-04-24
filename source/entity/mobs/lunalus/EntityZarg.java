package net.tslat.aoa3.entity.mobs.lunalus;

import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ArmourRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.HunterEntity;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityZarg extends AoAMeleeMob implements HunterEntity {
	public static final float entityWidth = 0.875f;

	public EntityZarg(World world) {
		super(world, entityWidth, 2.0625f);
	}

	@Override
	public float getEyeHeight() {
		return 1.875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 70;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 20;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobZargLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobZargDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobZargHit;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(100 - lootingMod) == 0)
			dropItem(WeaponRegister.sniperDualSight, 1);

		if (rand.nextInt(20 - lootingMod) == 0)
			switch (rand.nextInt(4)) {
				case 0:
					dropItem(ArmourRegister.ZargoniteHelmet, 1);
					break;
				case 1:
					dropItem(ArmourRegister.ZargoniteBody, 1);
					break;
				case 2:
					dropItem(ArmourRegister.ZargoniteLegs, 1);
					break;
				case 3:
					dropItem(ArmourRegister.ZargoniteBoots, 1);
					break;
			}
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (!world.isRemote && getAttackTarget() != null && rand.nextInt(120) == 0) {
			EntityFakeZorp fakeZorp = new EntityFakeZorp(getAttackTarget());

			world.spawnEntity(fakeZorp);
		}
	}

	@Override
	public int getHunterReq() {
		return 82;
	}

	@Override
	public float getHunterXp() {
		return 890;
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}
}
