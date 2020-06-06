package net.tslat.aoa3.entity.mobs.overworld.lunarinvasion;

import net.minecraft.entity.Entity;
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

public class EntityWalker extends AoAMeleeMob implements SpecialPropertyEntity {
	public static final float entityWidth = 0.6f;

	public EntityWalker(World world) {
		super(world, entityWidth, 1.9375f);

		mobProperties.add(Enums.MobProperties.FIRE_IMMUNE);
		isImmuneToFire = true;
	}

	@Override
	public float getEyeHeight() {
		return 1.75f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 45;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 3.5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.25;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_WALKER_LIVING;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_WALKER_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_WALKER_HIT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityWalker;
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (!world.isRemote)
			target.setFire(30);
	}

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source, int damage) {
		return source.isFireDamage();
	}

	@Nonnull
	@Override
	protected Enums.CreatureEvents getEventRequirement() {
		return Enums.CreatureEvents.LUNAR_INVASION;
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}
}
