package net.tslat.aoa3.entity.mobs.nether;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityEmbrake extends AoAMeleeMob implements SpecialPropertyEntity {
	public static final float entityWidth = 0.75f;

	public EntityEmbrake(World world) {
		super(world, entityWidth, 1f);

		this.mobProperties.add(Enums.MobProperties.FIRE_IMMUNE);
		this.isImmuneToFire = true;
	}

	@Override
	public float getEyeHeight() {
		return 0.9375f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.2d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 70d;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 7d;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobEmbrakeLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobEmbrakeDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobEmbrakeHit;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundsRegister.dinoStep;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityEmbrake;
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		target.setFire(5);

		if (world.getBlockState(target.getPosition().down()) != Blocks.AIR)
			world.setBlockState(target.getPosition(), Blocks.FIRE.getDefaultState());
	}

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source, int damage) {
		return source.isFireDamage();
	}

	@Override
	protected float getSpawnChanceFactor() {
		return 0.5f;
	}

	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return this.mobProperties;
	}
}
