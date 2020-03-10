package net.tslat.aoa3.entity.mobs.overworld.fullmoon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityNightWatcher extends AoAMeleeMob {
	public static final float entityWidth = 0.6f;

	public EntityNightWatcher(World world) {
		super(world, entityWidth, 2.7f);
	}

	@Override
	public float getEyeHeight() {
		return 2.5625f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.3;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 45;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.295;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobNightWatcherLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobNightWatcherHit;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobNightWatcherHit;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityNightWatcher;
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		boolean success = super.attackEntityFrom(source, amount);

		if (success && source.getTrueSource() instanceof EntityLivingBase && EntityUtil.isMeleeDamage(source) && (!(source.getTrueSource() instanceof EntityPlayer) || !((EntityPlayer)source.getTrueSource()).capabilities.isCreativeMode))
			((EntityLivingBase)source.getTrueSource()).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 65, 0, true, true));

		return success;
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase)
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 95, 0, true, true));
	}

	@Nonnull
	@Override
	protected Enums.CreatureEvents getEventRequirement() {
		return Enums.CreatureEvents.FULL_MOON;
	}
}
