package net.tslat.aoa3.entity.mobs.overworld.lunarinvasion;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityRoloscope extends AoAMeleeMob {
	public static final float entityWidth = 1.2f;

	public EntityRoloscope(World world) {
		super(world, entityWidth, 1.0625f);
	}

	@Override
	public float getEyeHeight() {
		return 0.85f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.3d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 90;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 4;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobRoloscopeLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobRoloscopeDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobRoloscopeHit;
	}

	@Override
	protected boolean isDaylightMob() {
		return true;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.orbulon, 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(6) == 0)
			dropItem(ItemRegister.coinCopper, 1 + rand.nextInt(1 + lootingMod));
	}

	@Override
	public boolean attackEntityAsMob(Entity target) {
		if (super.attackEntityAsMob(target)) {
			if (target instanceof EntityLivingBase)
				((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 64, 1, true, true));

			return true;
		}

		return false;
	}

	@Override
	protected boolean canSpawnOnBlock(IBlockState block) {
		return super.canSpawnOnBlock(block) && WorldUtil.isNaturalOverworldBlock(block);
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}

	@Nonnull
	@Override
	protected Enums.CreatureEvents getEventRequirement() {
		return Enums.CreatureEvents.LUNAR_INVASION;
	}
}
