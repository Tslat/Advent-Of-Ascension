package net.tslat.aoa3.entity.mobs.overworld.bloodhunt;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.player.PlayerUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static net.tslat.aoa3.library.Enums.Resources.ENERGY;

public class EntityBloodmist extends AoAMeleeMob {
	public static final float entityWidth = 0.8f;

	public EntityBloodmist(World world) {
		super(world, entityWidth, 1.2f);
	}

	@Override
	public float getEyeHeight() {
		return 0.55f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 40d;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 5d;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.26d;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobBloodmistLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobBloodmistDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobBloodmistHit;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityBloodmist;
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityPlayer)
			PlayerUtil.consumeResource((EntityPlayer)target, ENERGY, 40f, true);
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}

	@Nonnull
	@Override
	protected Enums.CreatureEvents getEventRequirement() {
		return Enums.CreatureEvents.BLOOD_HUNT;
	}
}
