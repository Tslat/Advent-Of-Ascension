package net.tslat.aoa3.entity.mobs.runandor;

import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityPaladin extends AoAMeleeMob implements SpecialPropertyEntity {
	public static final float entityWidth = 0.6875f;

	public EntityPaladin(World world) {
		super(world, entityWidth, 2.0f);

		mobProperties.add(Enums.MobProperties.MELEE_IMMUNE);
		mobProperties.add(Enums.MobProperties.RANGED_IMMUNE);
	}

	@Override
	public float getEyeHeight() {
		return 1.71875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.2d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 109;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 16d;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.207d;
	}

	@Override
	protected double getBaseArmour() {
		return 18;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.BLOCK_ANVIL_LAND;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.BLOCK_ANVIL_LAND;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundsRegister.veryHeavyStep;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityPaladin;
	}

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source, int damage) {
		return EntityUtil.isMeleeDamage(source) || EntityUtil.isRangedDamage(source, this, damage);
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}
}
