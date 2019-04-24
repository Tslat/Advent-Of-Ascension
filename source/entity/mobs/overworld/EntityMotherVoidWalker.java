package net.tslat.aoa3.entity.mobs.overworld;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ArmourRegister;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;

public class EntityMotherVoidWalker extends AoAMeleeMob {
	public static final float entityWidth = 1.0f;

	public EntityMotherVoidWalker(World world) {
		super(world, entityWidth, 1.62f);
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.7;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 80;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 6;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobMotherVoidWalkerLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobMotherVoidWalkerDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobMotherVoidWalkerDeath;
	}

	@Override
	public boolean getCanSpawnHere() {
		return posY < 35 && super.getCanSpawnHere();
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase)
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 200, 4, true, true));
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(20 - lootingMod) == 0)
			dropItem(ArmourRegister.VoidBoots, 1);

		if (rand.nextInt(20 - lootingMod) == 0)
			dropItem(ArmourRegister.VoidLegs, 1);

		if (rand.nextInt(20 - lootingMod) == 0)
			dropItem(ArmourRegister.VoidBody, 1);

		if (rand.nextInt(20 - lootingMod) == 0)
			dropItem(ArmourRegister.VoidHelmet, 1);

		if (rand.nextInt(20 - lootingMod) == 0)
			dropItem(WeaponRegister.bowVoid, 1);

		if (rand.nextInt(5) == 0)
			dropItem(Item.getItemFromBlock(BlockRegister.bannerVoid), 1);
	}

	@Override
	protected boolean canSpawnOnBlock(IBlockState block) {
		return super.canSpawnOnBlock(block) && WorldUtil.isNaturalOverworldBlock(block);
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}
}
