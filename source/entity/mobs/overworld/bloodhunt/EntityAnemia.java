package net.tslat.aoa3.entity.mobs.overworld.bloodhunt;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAFlyingRangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityAnemiaBomb;
import net.tslat.aoa3.entity.properties.HunterEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.PlayerUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

import static net.tslat.aoa3.library.Enums.Resources.RAGE;

public class EntityAnemia extends AoAFlyingRangedMob implements HunterEntity {
	public static final float entityWidth = 2.25f;

	public EntityAnemia(World world) {
		super(world, entityWidth, 3f);

		this.mobProperties.add(Enums.MobProperties.BLASTER_IMMUNE);
	}

	@Override
	public float getEyeHeight() {
		return 2.0625f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 80;
	}

	@Override
	protected double getBaseProjectileDamage() {
		return 20;
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

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(40 - lootingMod) == 0)
			dropItem(WeaponRegister.gunStormer, 1);

		if (rand.nextInt(8) == 0)
			dropItem(ItemRegister.realmstoneAbyss, 1);

		if (rand.nextInt(5) == 0)
			dropItem(Item.getItemFromBlock(BlockRegister.bannerBlood), 1);
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new EntityAnemiaBomb(this, Enums.MobProjectileType.ENERGY);
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		if (target instanceof EntityPlayer)
			PlayerUtil.getAdventPlayer((EntityPlayer)target).consumeResource(RAGE, 20, true);

		world.createExplosion(this, projectile.posX, projectile.posY, projectile.posZ, 2.0f, false);
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, IBlockState blockHit, BlockPos pos, EnumFacing sideHit) {
		world.createExplosion(this, projectile.posX, projectile.posY, projectile.posZ, 2.0f, false);
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
	public int getHunterReq() {
		return 1;
	}

	@Override
	public float getHunterXp() {
		return 15;
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
