package net.tslat.aoa3.entity.mobs.barathos;

import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityCryptid extends AoAMeleeMob implements SpecialPropertyEntity {
	public static final float entityWidth = 0.875f;

	public EntityCryptid(World world) {
		super(world, entityWidth, 1.0625f);

		this.mobProperties.add(Enums.MobProperties.FIRE_IMMUNE);
		this.isImmuneToFire = true;
	}

	@Override
	public float getEyeHeight() {
		return 0.75f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 40;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 3;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobCryptidLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobCryptidDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobCryptidHit;
	}

	@Override
	public boolean getCanSpawnHere() {
		return posY < 20 && super.getCanSpawnHere();
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(40 - lootingMod) == 0)
			dropItem(WeaponRegister.swordBaron, 1);

		if (rand.nextInt(60 - lootingMod) == 0)
			dropItem(WeaponRegister.staffUnderworld, 1);

		if (rand.nextBoolean())
			dropItem(ItemRegister.tokensBaron, 1 + rand.nextInt(2 + lootingMod));
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (world.getBlockState(getPosition().down()).getBlock() == BlockRegister.stoneBarathos && world.getBlockState(getPosition()).getMaterial().isReplaceable())
			world.setBlockState(getPosition(), Blocks.FIRE.getDefaultState());
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}
}
