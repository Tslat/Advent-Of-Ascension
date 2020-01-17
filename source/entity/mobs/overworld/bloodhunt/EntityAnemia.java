package net.tslat.aoa3.entity.mobs.overworld.bloodhunt;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAFlyingRangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityAnemiaBomb;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.WorldUtil;
import net.tslat.aoa3.utils.player.PlayerUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

import static net.tslat.aoa3.library.Enums.Resources.ENERGY;

public class EntityAnemia extends AoAFlyingRangedMob implements SpecialPropertyEntity {
	public static final float entityWidth = 2.25f;

	public EntityAnemia(World world) {
		super(world, entityWidth, 3f);

		mobProperties.add(Enums.MobProperties.BLASTER_IMMUNE);
	}

	@Override
	public float getEyeHeight() {
		return 2.0625f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.5d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 70;
	}

	@Override
	public double getBaseProjectileDamage() {
		return 10;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.1;
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return null;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobAnemiaLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobAnemiaDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobAnemiaHit;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityAnemia;
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new EntityAnemiaBomb(this, Enums.MobProjectileType.ENERGY);
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		if (target instanceof EntityPlayer)
			PlayerUtil.consumeResource((EntityPlayer)target, ENERGY, 50f, true);

		WorldUtil.createExplosion(this, world, projectile, 2.0f);
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, IBlockState blockHit, BlockPos pos, EnumFacing sideHit) {
		WorldUtil.createExplosion(this, world, projectile, 2.0f);
	}

	@Override
	protected int getSpawnChanceFactor() {
		return 4;
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source, int damage) {
		return EntityUtil.isBlasterDamage(source);
	}

	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return this.mobProperties;
	}

	@Nonnull
	@Override
	protected Enums.CreatureEvents getEventRequirement() {
		return Enums.CreatureEvents.BLOOD_HUNT;
	}
}
