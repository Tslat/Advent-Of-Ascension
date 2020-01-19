package net.tslat.aoa3.entity.mobs.precasia;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.utils.ConfigurationUtil;

import javax.annotation.Nullable;

public class EntityPrimitiveCarrotop extends AoAMeleeMob {
	public static final float entityWidth = 0.5625f;

	public EntityPrimitiveCarrotop(World world) {
		super(world, entityWidth, 2.375f);
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 70;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 9.5d;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.3;
	}

	@Nullable
	@Override
	public String getCurrentAnimation() {
		return currentAnimation == null ? "IDLE_1" : currentAnimation;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_ZOMBIE_PIG_AMBIENT;
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

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote && world.provider.getDimension() == ConfigurationUtil.MainConfig.dimensionIds.precasia)
			entityDropItem(new ItemStack(ItemRegister.realmstoneGardencia), 0f);
	}
}
