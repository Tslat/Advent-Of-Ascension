package net.tslat.aoa3.entity.minions;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;

import javax.annotation.Nullable;

public class EntityHealingGolem extends AoAMinion {
	public static final float entityWidth = 1.25f;

	public EntityHealingGolem(final World world){
		super(world, -1, entityWidth, 2.125f);
	}

	@Override
	public float getEyeHeight() {
		return 2.03125f;
	}

	@Override
	protected double getBaseMoveSpeed() {
		return 0.3d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 200.0d;
	}

	@Override
	protected boolean isHostile() {
		return false;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		EntityLivingBase owner = getOwner();

		if (!isDead && isTamed() && owner != null && owner.getHealth() < owner.getMaxHealth())
			owner.heal(0.15f);
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_AUTOMATON_LIVING;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_AUTOMATON_HIT;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_AUTOMATON_DEATH;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityHealingGolem;
	}
}
