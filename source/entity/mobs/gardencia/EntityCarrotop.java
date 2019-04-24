package net.tslat.aoa3.entity.mobs.gardencia;

import net.minecraft.init.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityCarrotop extends AoAMeleeMob {
	public static final float entityWidth = 0.5625f;

	public EntityCarrotop(World world) {
		super(world, entityWidth, 2.375f);
	}

	@Override
	public float getEyeHeight() {
		return 1.125f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.8;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 60;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 7;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobCarrotopLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobCarrotopDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobCarrotopHit;
	}

	@Override
	public boolean getCanSpawnHere() {
		return posY > 66 && super.getCanSpawnHere();
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(65 - lootingMod) == 0)
			dropItem(WeaponRegister.cannonCarrotCannon, 1);

		if (rand.nextInt(40 - lootingMod) == 0)
			dropItem(ItemRegister.smallPetalOrange, 1);

		if (rand.nextBoolean())
			dropItem(ItemRegister.tokensGardencia, 1 + rand.nextInt(1 + lootingMod));

		if (rand.nextInt(40 - lootingMod) == 0)
			dropItem(Items.GOLDEN_CARROT, 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
		dropItem(Items.CARROT, 1 + rand.nextInt(3 + lootingMod));
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (world.isRaining() && getHealth() > 0)
			heal(0.2f);
	}
}
