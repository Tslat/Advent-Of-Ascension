package net.tslat.aoa3.entity.mobs.precasia;

import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityTerradon extends AoAMeleeMob implements SpecialPropertyEntity {
	private static final DataParameter<Boolean> INVULNERABLE = EntityDataManager.<Boolean>createKey(EntityTerradon.class, DataSerializers.BOOLEAN);
	public static final float entityWidth = 1.25f;
	private int invulnCooldown = 0;

	public EntityTerradon(World world) {
		super(world, entityWidth, 1.875f);

		this.mobProperties.add(Enums.MobProperties.GUN_IMMUNE);
		setSlipperyMovement();
		setAIMoveSpeed(1.8f);
	}

	@Override
	public float getEyeHeight() {
		return 1.3125f;
	}

	@Override
	protected void entityInit() {
		super.entityInit();

		this.dataManager.register(INVULNERABLE, false);
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 160;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 25;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobTerradonLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobTerradonDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobTerradonHit;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundsRegister.veryHeavyStep;
	}

	@Override
	protected int getSpawnChanceFactor() {
		return 5;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.tokensPrecasian, 2 + rand.nextInt(4 + lootingMod));
		dropItem(ItemRegister.heavyBoulder, 1);
		dropItem(ItemRegister.realmstonePrecasia, 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinSilver, 1 + rand.nextInt(4 + lootingMod));
	}

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source) {
		return EntityUtil.isGunDamage(source);
	}

	@Override
	public boolean getIsInvulnerable() {
		return this.dataManager.get(INVULNERABLE);
	}

	private void setInvulnerable(boolean invulnerable) {
		this.dataManager.set(INVULNERABLE, invulnerable);

		if (invulnerable)
			invulnCooldown = 200;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (!world.isRemote) {
			if (invulnCooldown > 0)
				invulnCooldown--;

			if (invulnCooldown <= 0)
				setInvulnerable(false);

			if (!getIsInvulnerable() && rand.nextInt(200) == 0)
				setInvulnerable(true);
		}
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}
}
