package net.tslat.aoa3.entity.mobs.barathos;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.packet.PacketScreenOverlay;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.PacketUtil;
import net.tslat.aoa3.utils.PredicateUtil;

import javax.annotation.Nullable;

public class EntityEilosapien extends AoAMeleeMob {
	public static final float entityWidth = 0.5f;

	public EntityEilosapien(World world) {
		super(world, entityWidth, 1.875f);
	}

	@Override
	public float getEyeHeight() {
		return 1.625f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.7;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 100;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 17;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.3833;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobEilosapienLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobEilosapienDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobEilosapienHit;
	}

	@Override
	protected int getSpawnChanceFactor() {
		return 5;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextBoolean())
			dropItem(WeaponRegister.gunBaronator, 1);

		dropItem(ItemRegister.realmstoneBarathos, 1);
		dropItem(Item.getItemFromBlock(BlockRegister.bannerBaron), 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinSilver, 5 + rand.nextInt(9 + lootingMod));
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityPlayerMP)
			PacketUtil.network.sendTo(new PacketScreenOverlay(100, Enums.ScreenOverlays.EILOSAPIEN), (EntityPlayerMP)target);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		for (EntityPlayer pl : world.getEntitiesWithinAABB(EntityPlayer.class, getEntityBoundingBox().grow(12), PredicateUtil.IS_VULNERABLE_PLAYER)) {
			if (canEntityBeSeen(pl))
				pl.setFire(6);
		}
	}
}
