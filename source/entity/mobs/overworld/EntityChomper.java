package net.tslat.aoa3.entity.mobs.overworld;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nullable;

import static net.tslat.aoa3.library.misc.AoAAttributes.BLOODTHIRSTY_BUFF;

public class EntityChomper extends AoAMeleeMob {
	public static final float entityWidth = 0.8f;

	public EntityChomper(World world) {
		super(world, entityWidth, 1.375f);
	}

	@Override
	public float getEyeHeight() {
		return 1.25f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.2d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 30;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 2;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.23;
	}

	@Override
	protected float getWaterSlowDown() {
		return 1;
	}

	@Override
	public void setAttackTarget(@Nullable EntityLivingBase target) {
		if (target == null) {
			EntityUtil.removeAttributeModifier(this, SharedMonsterAttributes.MOVEMENT_SPEED, BLOODTHIRSTY_BUFF);
		}
		else {
			EntityUtil.applyAttributeModifierSafely(this, SharedMonsterAttributes.MOVEMENT_SPEED, BLOODTHIRSTY_BUFF);
		}

		super.setAttackTarget(target);
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobChomperLiving;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobChomperHit;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundsRegister.heavyStep;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityChomper;
	}

	@Override
	protected boolean isDaylightMob() {
		return true;
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (super.attackEntityAsMob(entity)) {
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase)entity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20, 2, true, true));

			return true;
		}

		return false;
	}

	@Override
	protected boolean canSpawnOnBlock(IBlockState block) {
		return super.canSpawnOnBlock(block) || block.getBlock() == Blocks.WATER;
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}
}
