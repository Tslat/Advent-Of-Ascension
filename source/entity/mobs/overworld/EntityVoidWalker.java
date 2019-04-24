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
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;

public class EntityVoidWalker extends AoAMeleeMob {
	public static final float entityWidth = 1.0f;

	public EntityVoidWalker(World world) {
		super(world, entityWidth, 1.125f);
	}

	@Override
	public float getEyeHeight() {
		return 0.75f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.7;
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
		return SoundsRegister.mobVoidWalkerLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobVoidWalkerDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobVoidWalkerHit;
	}

	@Override
	public boolean getCanSpawnHere() {
		return posY < 50 && super.getCanSpawnHere();
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase)
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 200, 4, true, true));
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(15 - lootingMod) == 0)
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
