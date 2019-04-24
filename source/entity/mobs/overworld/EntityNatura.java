package net.tslat.aoa3.entity.mobs.overworld;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.PlayerUtil;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;

public class EntityNatura extends AoAMeleeMob {
	public static final float entityWidth = 0.8f;

	public EntityNatura(World world) {
		super(world, entityWidth, 1.6875f);
	}

	@Override
	public float getEyeHeight() {
		return 1.4375f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1f;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 40;
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
		return SoundsRegister.mobNaturaLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobNaturaDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobNaturaHit;
	}

	@Override
	protected boolean isDaylightMob() {
		return true;
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.natureMelonSlice, 2 + rand.nextInt(2 + lootingMod));
	}

	@Override
	protected boolean canSpawnOnBlock(IBlockState block) {
		return super.canSpawnOnBlock(block) && WorldUtil.isNaturalOverworldBlock(block);
	}

	@Override
	public boolean attackEntityAsMob(Entity target) {
		if (super.attackEntityAsMob(target)) {
			if (target instanceof EntityLivingBase) {
				heal(15);


				if (target instanceof EntityPlayer) {
					AdventPlayerCapability cap = PlayerUtil.getAdventPlayer((EntityPlayer)target);

					cap.consumeResource(Enums.Resources.ENERGY, 50, true);
					cap.sendPlayerMessage(StringUtil.getLocale("message.mob.natura.attack"));
				}
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
