package net.tslat.aoa3.entity.mobs.mysterium;

import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
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

public class EntityRunicGolem extends AoAMeleeMob implements SpecialPropertyEntity {
	public static final float entityWidth = 0.75f;
	private static final DataParameter<Boolean> SHIELDED = EntityDataManager.<Boolean>createKey(EntityRunicGolem.class, DataSerializers.BOOLEAN);
	private int shieldCooldown = 120;
	private int runeStoneCooldown = 0;

	public EntityRunicGolem(World world) {
		super(world, entityWidth, 1.75f);

		this.mobProperties.add(Enums.MobProperties.RANGED_IMMUNE);
		this.mobProperties.add(Enums.MobProperties.GUN_IMMUNE);
	}

	@Override
	public float getEyeHeight() {
		return 1.59375f;
	}

	@Override
	protected void entityInit() {
		super.entityInit();

		this.dataManager.register(SHIELDED, false);
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 200;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 12;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobRunicGolemHit;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobRunicGolemHit;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundEvents.ENTITY_IRONGOLEM_STEP;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.runeStone, 1);

		if (rand.nextInt(200 - lootingMod) == 0)
			dropItem(ItemRegister.upgradeKitRunic, 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
	}

	public void deactivateShield() {
		this.shieldCooldown = 120;
		setShielded(false);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (!world.isRemote) {
			if (isShielded()) {
				if (EntityUtil.isMeleeDamage(source) && runeStoneCooldown <= 0) {
					runeStoneCooldown = 120;
					dropItem(ItemRegister.activeRuneStone, 1);
				}

				return false;
			}
		}

		return super.attackEntityFrom(source, amount);
	}

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source) {
		return EntityUtil.isGunDamage(source) || EntityUtil.isRangedDamage(source, this, 1);
	}

	@Override
	public boolean getIsInvulnerable() {
		return isShielded();
	}

	public boolean isShielded() {
		return this.dataManager.get(SHIELDED);
	}

	private void setShielded(boolean shielded) {
		this.dataManager.set(SHIELDED, shielded);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (runeStoneCooldown > 0)
			runeStoneCooldown--;

		if (shieldCooldown > 0) {
			shieldCooldown--;
		}
		else if (shieldCooldown == 0) {
			shieldCooldown = -1;
			world.playSound(null, posX, posY, posZ, SoundsRegister.mobRunicGolemChange, SoundCategory.HOSTILE, 1.0f, 1.0f);
			setShielded(true);
		}
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}
}
