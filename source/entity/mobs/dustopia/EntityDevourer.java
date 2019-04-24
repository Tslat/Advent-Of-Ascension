package net.tslat.aoa3.entity.mobs.dustopia;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.packet.PacketScreenOverlay;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.boss.penumbra.EntityPenumbra;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.PacketUtil;

import javax.annotation.Nullable;

public class EntityDevourer extends AoAMeleeMob {
	public static final float entityWidth = 0.875f;

	public EntityDevourer(World world) {
		super(world, entityWidth, 1.1875f);
	}

	@Override
	public float getEyeHeight() {
		return 0.9375f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.3;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 40;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobDevourerLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobDevourerDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobDevourerHit;
	}

	@Override
	public void setAttackTarget(@Nullable EntityLivingBase target) {
		if (target instanceof EntityPenumbra)
			return;

		super.setAttackTarget(target);
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(30 - lootingMod) == 0)
			dropItem(WeaponRegister.swordHarvester, 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		heal(40);

		if (target instanceof EntityPlayerMP)
			PacketUtil.network.sendTo(new PacketScreenOverlay(50, Enums.ScreenOverlays.BLOODY), (EntityPlayerMP)target);
	}
}
