package net.tslat.aoa3.entity.mobs.overworld.soulscurry;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityMagicBall;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityGhostlyGoblin extends AoARangedMob {
	public static final float entityWidth = 0.6f;

	public EntityGhostlyGoblin(World world) {
		super(world, entityWidth, 1.8f);
	}

	@Override
	public float getEyeHeight() {
		return 1.59375f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 30;
	}

	@Override
	protected double getBaseProjectileDamage() {
		return 7;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.207;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobGoblinLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobGoblinDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobGoblinHit;
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return SoundsRegister.shotWizardBlast;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(5) == 0)
			dropItem(Item.getItemFromBlock(BlockRegister.bannerGhostly), 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(3) == 0)
			dropItem(ItemRegister.ghostlyStone, 2 + rand.nextInt(2 + lootingMod));
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new EntityMagicBall(this, Enums.MobProjectileType.ENERGY);
	}

	@Nonnull
	@Override
	protected Enums.CreatureEvents getEventRequirement() {
		return Enums.CreatureEvents.SOUL_SCURRY;
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}
}
