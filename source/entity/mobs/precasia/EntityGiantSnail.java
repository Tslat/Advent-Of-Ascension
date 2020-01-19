package net.tslat.aoa3.entity.mobs.precasia;

import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.*;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityGiantSnail extends AoAMeleeMob implements SpecialPropertyEntity {
	public static final float entityWidth = 1.2f;

	public EntityGiantSnail(World world) {
		super(world, entityWidth, 1.5625f);

		mobProperties.add(Enums.MobProperties.STATUS_IMMUNE);
	}

	@Override
	public float getEyeHeight() {
		return 1.125f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.7d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 80d;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 7.5d;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.25d;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobGiantSnailLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobGiantSnailDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobGiantSnailHit;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundsRegister.mobGiantSnailStep;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityGiantSnail;
	}

	@Override
	public void addPotionEffect(PotionEffect effect) {}

	@Override
	public boolean canBeHitWithPotion() {
		return false;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (!world.isRemote && world.getGameRules().getBoolean("mobGriefing")) {
			if (world.getBlockState(getPosition().down()).isOpaqueCube() && world.getBlockState(getPosition()).getMaterial().isReplaceable())
				world.setBlockState(getPosition(), BlockRegister.giantSnailAcid.getDefaultState());
		}
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}
}
