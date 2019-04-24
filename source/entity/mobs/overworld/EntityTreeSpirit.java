package net.tslat.aoa3.entity.mobs.overworld;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;

import static net.minecraft.entity.SharedMonsterAttributes.KNOCKBACK_RESISTANCE;

public class EntityTreeSpirit extends AoAMeleeMob {
	public static final float entityWidth = 1.0f;

	public EntityTreeSpirit(World world) {
		super(world, entityWidth, 3.5f);
	}

	@Override
	public float getEyeHeight() {
		return 2.4f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1.0f;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 25;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobTreeSpiritLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobTreeSpiritDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobTreeSpiritHit;
	}

	@Override
	protected boolean isDaylightMob() {
		return true;
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(5) == 0)
			dropItem(ItemRegister.seedsHollyTop, 1 + rand.nextInt(1 + lootingMod));

		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(8 + lootingMod));
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

				((EntityLivingBase)target).getEntityAttribute(KNOCKBACK_RESISTANCE).getAttributeValue();

				((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 50, 0, true, true));
				target.addVelocity(motionX * 5.0 * resist, 1.0 * resist, motionZ * 5.0 * resist);
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
}
