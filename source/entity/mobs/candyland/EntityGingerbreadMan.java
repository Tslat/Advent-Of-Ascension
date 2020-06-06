package net.tslat.aoa3.entity.mobs.candyland;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityGingerbreadMan extends AoAMeleeMob implements SpecialPropertyEntity {
	public static final float entityWidth = 0.59375f;
	public EntityGingerbreadMan(World world) {
		super(world, entityWidth, 2.125f);

		mobProperties.add(Enums.MobProperties.FIRE_IMMUNE);
		isImmuneToFire = true;
	}

	@Override
	public float getEyeHeight() {
		return 1.875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.15;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 95;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 11.5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.28;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.PLANT_THUMP;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.PLANT_THUMP;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityGingerbreadMan;
	}

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source, int damage) {
		return source.isFireDamage();
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}
}
