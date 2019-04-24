package net.tslat.aoa3.entity.mobs.overworld;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
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

public class EntityMuckopede extends AoAMeleeMob {
	public static final float entityWidth = 1.3f;

	public EntityMuckopede(World world) {
		super(world, entityWidth, 0.85f);
	}

	@Override
	public float getEyeHeight() {
		return 0.6875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.7f;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 30;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 4;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobMuckopedeLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobMuckopedeHit;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobMuckopedeHit;
	}

	@Override
	protected boolean isDaylightMob() {
		return true;
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		if (rand.nextBoolean())
			dropItem(ItemRegister.mudBall, 1 + lootingMod);

		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(8 + lootingMod));
	}

	@Override
	protected boolean canSpawnOnBlock(IBlockState block) {
		return super.canSpawnOnBlock(block) && WorldUtil.isNaturalOverworldBlock(block);
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}

	@Override
	public boolean attackEntityAsMob(Entity target) {
		if (super.attackEntityAsMob(target)) {
			if (target instanceof EntityLivingBase)
				((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 150, 2, true, true));

			return true;
		}

		return false;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}
}
