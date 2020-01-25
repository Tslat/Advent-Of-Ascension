package net.tslat.aoa3.entity.minions;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nullable;

public class EntityConstructOfServility extends AoAMinion {
	public static final float entityWidth = 0.75f;

	public EntityConstructOfServility(final World world){
		super(world, -1, entityWidth, 1.75f);
	}

	@Override
	public float getEyeHeight() {
		return 1.5f;
	}

	@Override
	protected double getBaseMoveSpeed() {
		return 0.41618d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 260;
	}

	@Override
	protected boolean isHostile() {
		return true;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 15.0d;
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (super.attackEntityAsMob(entity)) {
			if (getOwner() != null)
				EntityUtil.healEntity(getOwner(), 2.0f);

			return true;
		}
		else {
			return false;
		}
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobCrystalConstructLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobCrystalConstructHit;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobCrystalConstructDeath;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityConstructOfServility;
	}
}
