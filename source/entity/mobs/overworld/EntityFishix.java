package net.tslat.aoa3.entity.mobs.overworld;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.HunterEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityFishix extends AoAMeleeMob implements HunterEntity {
	public static final float entityWidth = 1.2f;

	public EntityFishix(World world) {
		super(world, entityWidth, 1.95f);
	}

	@Override
	public float getEyeHeight() {
		return 1.6875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 35;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Override
	protected float getWaterSlowDown() {
		return 1;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobFishixLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobFishixDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobFishixHit;
	}

	@Override
	protected boolean isDaylightMob() {
		return true;
	}

	@Override
	protected boolean canSpawnOnBlock(IBlockState block) {
		return super.canSpawnOnBlock(block) && block.getBlock() == Blocks.WATER || WorldUtil.isNaturalOverworldBlock(block);
	}

	@Override
	public int getHunterReq() {
		return 6;
	}

	@Override
	public float getHunterXp() {
		return 20;
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
