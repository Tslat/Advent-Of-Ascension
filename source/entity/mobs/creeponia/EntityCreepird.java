package net.tslat.aoa3.entity.mobs.creeponia;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAFlyingMeleeMob;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;

public class EntityCreepird extends AoAFlyingMeleeMob {
	public static final float entityWidth = 0.5f;

	public EntityCreepird(World world) {
		super(world, entityWidth, 0.6875f);
	}

	@Override
	public float getEyeHeight() {
		return 0.46875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 40;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 4;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.1;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_CREEPIRD_LIVING;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_CREEPIRD_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_CREEPIRD_HIT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityCreepird;
	}

	@Override
	public boolean getCanSpawnHere() {
		return posY > 50 && super.getCanSpawnHere();
	}

	@Override
	public boolean attackEntityAsMob(Entity target) {
		boolean success = super.attackEntityAsMob(target);

		if (success) {
			WorldUtil.createExplosion(this, world, 2.5f);
			setDead();
		}

		return success;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		boolean success = super.attackEntityFrom(source, amount);

		if (!world.isRemote && success && EntityUtil.isPoisonDamage(source, this, amount) && attackingPlayer != null && ItemUtil.consumeItem(attackingPlayer, new ItemStack(ItemRegister.BLANK_REALMSTONE)))
			ItemUtil.givePlayerItemOrDrop(attackingPlayer, new ItemStack(ItemRegister.MYSTERIUM_REALMSTONE));

		return success;
	}

	@Override
	public void onDeath(DamageSource source) {
		super.onDeath(source);
	}
}
