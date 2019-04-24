package net.tslat.aoa3.entity.minions;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nullable;

public class EntityBlissard extends AoAMinion {
	public static final float entityWidth = 1.0f;

	public EntityBlissard(final World world){
		super(world, -1, entityWidth, 1);
	}

	@Override
	public float getEyeHeight() {
		return 0.6f;
	}

	@Override
	protected double getBaseMoveSpeed() {
		return 0.3d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 100;
	}

	@Override
	protected boolean isHostile() {
		return true;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 11.0d;
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
		return SoundsRegister.mobZhinxLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobZhinxHit;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobZhinxDeath;
	}
}
