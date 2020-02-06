package net.tslat.aoa3.entity.minions;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;

import javax.annotation.Nullable;

public class EntityHellquin extends AoAMinion {
	public static final float entityWidth = 0.5f;

	public EntityHellquin(final World world){
		super(world, -1, entityWidth, 1.25f);
	}

	@Override
	public float getEyeHeight() {
		return 0.96875f;
	}

	@Override
	protected double getBaseMoveSpeed() {
		return 0.3d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 120;
	}

	@Override
	protected boolean isHostile() {
		return true;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 10.0d;
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (super.attackEntityAsMob(entity)) {
			entity.setFire(7);

			return true;
		}
		else {
			return false;
		}
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobPenguinLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobPenguinHit;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobPenguinDeath;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityHellquin;
	}
}
