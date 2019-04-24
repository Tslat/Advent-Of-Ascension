package net.tslat.aoa3.entity.mobs.overworld;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.HunterEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityElkanyne extends AoAMeleeMob implements HunterEntity {
	public static final float entityWidth = 0.85f;

	public EntityElkanyne(World world) {
		super(world, entityWidth, 1.1875f);
	}

	@Override
	public float getEyeHeight() {
		return 1.05f;
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
		return 9;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobElkanyneLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobElkanyneDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobElkanyneHit;
	}

	@Override
	protected boolean isDaylightMob() {
		return true;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(75 - lootingMod) == 0)
			dropItem(WeaponRegister.gunStampede, 1);
	}

	@Override
	protected boolean canSpawnOnBlock(IBlockState block) {
		return super.canSpawnOnBlock(block) && WorldUtil.isNaturalOverworldBlock(block);
	}

	@Override
	public int getHunterReq() {
		return 12;
	}

	@Override
	public float getHunterXp() {
		return 16f;
	}

	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return this.mobProperties;
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}
}
