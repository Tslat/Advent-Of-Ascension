package net.tslat.aoa3.entity.mobs.candyland;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntitySpearmintSlug extends AoAMeleeMob {
	public static final float entityWidth = 0.5f;

	public EntitySpearmintSlug(World world) {
		super(world, entityWidth, 0.8125f);
	}

	@Override
	public float getEyeHeight() {
		return 0.3125f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.8;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 120;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.candyThump;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.candyThump;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundsRegister.candySlugStep;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(7) == 0)
			dropItem(Item.getItemFromBlock(BlockRegister.bannerCandy), 1);

		if (rand.nextInt(15 - lootingMod) == 0)
			dropItem(ItemRegister.spearmintCandy, 1);

		if (rand.nextInt(3) == 0)
			dropItem(ItemRegister.tokensCandyland, 1 + rand.nextInt(2 + lootingMod));

		if (rand.nextInt(105 - lootingMod) == 0)
			dropItem(WeaponRegister.greatbladeCandyBlade, 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 1 + rand.nextInt(3 + lootingMod));
		dropItem(Item.getItemFromBlock(BlockRegister.candyWhite), 1 + rand.nextInt(2 + lootingMod));
		dropItem(Item.getItemFromBlock(BlockRegister.candyRed), 1 + rand.nextInt(2 + lootingMod));
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (isInWater() && getHealth() > 0)
			heal(0.4f);
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase)
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 0, true, false));
	}
}
