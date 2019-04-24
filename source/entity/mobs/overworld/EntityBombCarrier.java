package net.tslat.aoa3.entity.mobs.overworld;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.misc.EntityFakeTnt;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;

public class EntityBombCarrier extends AoAMeleeMob {
	public static final float entityWidth = 0.6f;
	private int cooldown = 150;

	public EntityBombCarrier(World world) {
		super(world, entityWidth, 1.5f);
	}

	@Override
	public float getEyeHeight() {
		return 1.2f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 100;
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
		return SoundsRegister.mobBombCarrierLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobBombCarrierHit;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobBombCarrierHit;
	}

	@Override
	protected boolean isDaylightMob() {
		return true;
	}

	@Override
	protected int getSpawnChanceFactor() {
		return 6;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (isDead)
			return;

		if (cooldown > 0)
			--cooldown;

		if (cooldown < 3 && !world.isRemote) {
			if (getAttackTarget() == null && getRevengeTarget() == null)
				return;

			cooldown = 150;
			EntityFakeTnt tnt = new EntityFakeTnt(world, getPosition());

			tnt.setFuse(80);
			world.spawnEntity(tnt);
		}
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinSilver, 5 + rand.nextInt(10 + lootingMod));
		dropItem(Item.getItemFromBlock(Blocks.TNT), 5);
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(50 - lootingMod) < 25)
			dropItem(WeaponRegister.gunHuntersRifle, 1);
	}

	@Override
	protected boolean canSpawnOnBlock(IBlockState block) {
		return super.canSpawnOnBlock(block) && WorldUtil.isNaturalOverworldBlock(block);
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}
}
