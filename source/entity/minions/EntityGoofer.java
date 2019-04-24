package net.tslat.aoa3.entity.minions;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;

import javax.annotation.Nullable;

public class EntityGoofer extends AoAMinion {
	public static final float entityWidth = 1.5f;

	public EntityGoofer(final World world){
		super(world, -1, entityWidth, 1.375f);
	}

	@Override
	public float getEyeHeight() {
		return 1.1875f;
	}

	@Override
	protected double getBaseMoveSpeed() {
		return 0.3d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 270.0d;
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
		if (rand.nextInt(6) == 0) {
			attackEntityFrom(DamageSource.causeMobDamage(this), (float)getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());

			return true;
		}
		else {
			return super.attackEntityAsMob(entity);
		}
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobCeleveClownLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobCeleveClownHit;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobCeleveClownDeath;
	}
}
