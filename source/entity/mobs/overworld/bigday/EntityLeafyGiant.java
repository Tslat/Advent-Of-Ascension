package net.tslat.aoa3.entity.mobs.overworld.bigday;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nonnull;

import static net.minecraft.entity.SharedMonsterAttributes.KNOCKBACK_RESISTANCE;

public class EntityLeafyGiant extends AoAMeleeMob {
	public static final float entityWidth = 1.125f;

	public EntityLeafyGiant(World world) {
		super(world, entityWidth, 6.5f);
	}

	@Override
	public float getEyeHeight() {
		return 5.7f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1.0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 150;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 10;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobGiantDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobGiantHit;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundsRegister.veryHeavyStep;
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	protected boolean isDaylightMob() {
		return true;
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(Item.getItemFromBlock(Blocks.LEAVES), 32 + rand.nextInt(1 + lootingMod * 4));
	}

	@Override
	protected boolean canSpawnOnBlock(IBlockState block) {
		return super.canSpawnOnBlock(block) && WorldUtil.isNaturalOverworldBlock(block);
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}

	@Nonnull
	@Override
	protected Enums.CreatureEvents getEventRequirement() {
		return Enums.CreatureEvents.BIG_DAY;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		Entity target = getAttackTarget();

		if (target != null && target.getDistance(this) < 20)
			this.addVelocity(Math.signum(target.posX - posX) * 0.029, 0.0, Math.signum(target.posZ - posZ) * 0.029);
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase) {
			double resist = 1;
			IAttributeInstance attrib = ((EntityLivingBase)target).getEntityAttribute(KNOCKBACK_RESISTANCE);

			if (attrib != null)
				resist -= attrib.getAttributeValue();

			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 50, 0, true, true));
			target.addVelocity(motionX * 10.5 * resist, motionY * 0.5 * resist, motionZ * 10.5 * resist);
			target.velocityChanged = true;
		}
	}
}
