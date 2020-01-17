package net.tslat.aoa3.entity.mobs.overworld;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.misc.EntityFakeTnt;

import javax.annotation.Nullable;

public class EntityBombCarrier extends AoAMeleeMob {
	public static final float entityWidth = 0.6f;
	private int cooldown = 150;

	public EntityBombCarrier(World world) {
		super(world, entityWidth, 1.5f);
	}

	@Override
	public float getEyeHeight() {
		return 1.2f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 20;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 2;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobBombCarrierLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobBombCarrierHit;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobBombCarrierHit;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityBombCarrier;
	}

	@Override
	protected boolean isDaylightMob() {
		return true;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (isDead)
			return;

		if (cooldown > 0)
			--cooldown;

		if (cooldown < 3 && !world.isRemote) {
			if (getAttackTarget() == null && getRevengeTarget() == null)
				return;

			cooldown = 150;
			EntityFakeTnt tnt = new EntityFakeTnt(world, getPosition());

			tnt.setFuse(80);
			world.spawnEntity(tnt);
		}
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}
}
