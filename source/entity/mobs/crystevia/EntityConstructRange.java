package net.tslat.aoa3.entity.mobs.crystevia;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityConstructShot;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityConstructRange extends AoARangedMob implements SpecialPropertyEntity {
	public static final float entityWidth = 1.7f;

	public EntityConstructRange(World world) {
		super(world, entityWidth, 1.5f);

		mobProperties.add(Enums.MobProperties.RANGED_IMMUNE);
		mobProperties.add(Enums.MobProperties.MAGIC_IMMUNE);
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.3;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 70;
	}

	@Override
	public double getBaseProjectileDamage() {
		return 10.5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.207;
	}

	@Override
	protected double getBaseArmour() {
		return 3;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_CRYSTAL_CONSTRUCT_LIVING;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_CRYSTAL_CONSTRUCT_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_CRYSTAL_CONSTRUCT_HIT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityConstructOfRange;
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return null;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (isEntityAlive() && getHealth() < getMaxHealth())
			heal(0.1f);
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new EntityConstructShot(this, Enums.MobProjectileType.MAGIC);
	}

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source, int damage) {
		return EntityUtil.isMagicDamage(source, this, damage) || EntityUtil.isRangedDamage(source, this, damage);
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}
}
