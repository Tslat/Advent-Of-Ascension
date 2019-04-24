package net.tslat.aoa3.entity.mobs.overworld;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.HunterEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;
import java.util.TreeSet;

import static net.minecraft.entity.SharedMonsterAttributes.KNOCKBACK_RESISTANCE;

public class EntityRammerhead extends AoAMeleeMob implements HunterEntity {
	public static final float entityWidth = 1.5f;

	public EntityRammerhead(World world) {
		super(world, entityWidth, 1.875f);
		this.setSlipperyMovement();
		this.setAIMoveSpeed(2.6f);
	}

	@Override
	public float getEyeHeight() {
		return 1.6f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1.0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 55;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 6;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobRammerheadLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobRammerheadDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobRammerheadHit;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundsRegister.veryHeavyStep;
	}

	@Override
	protected boolean isDaylightMob() {
		return true;
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(4) == 0)
			dropItem(ItemRegister.rammerheadHide, 1);
	}

	@Override
	protected boolean canSpawnOnBlock(IBlockState block) {
		return super.canSpawnOnBlock(block) && WorldUtil.isNaturalOverworldBlock(block);
	}

	@Override
	public boolean attackEntityAsMob(Entity target) {
		if (super.attackEntityAsMob(target)) {
			if (target instanceof EntityLivingBase) {
				double resist = 1;
				IAttributeInstance attrib = ((EntityLivingBase)target).getEntityAttribute(KNOCKBACK_RESISTANCE);

				if (attrib != null)
					resist -= attrib.getAttributeValue();

				target.addVelocity(motionX * 20.5 * resist, 0.5 * resist, motionZ * 20.5 * resist);
				target.velocityChanged = true;
			}

			return true;
		}

		return false;
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}

	@Override
	public int getHunterReq() {
		return 12;
	}

	@Override
	public float getHunterXp() {
		return 26;
	}

	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return this.mobProperties;
	}
}
