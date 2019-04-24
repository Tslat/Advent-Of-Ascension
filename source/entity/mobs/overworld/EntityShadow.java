package net.tslat.aoa3.entity.mobs.overworld;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;

public class EntityShadow extends AoAMeleeMob {
	public static final float entityWidth = 0.6f;

	public EntityShadow(World world) {
		super(world, entityWidth, 1.75f);
	}

	@Override
	public float getEyeHeight() {
		return 1.5f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1.0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 1;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 4;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.1095;
	}

	@Override
	public boolean getCanSpawnHere() {
		return posY < 45 && super.getCanSpawnHere();
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobShadowLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobShadowHit;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobShadowHit;
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
		transform();
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}

	private void transform() {
		if (!world.isRemote) {
			EntityShade shade = new EntityShade(world);

			shade.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
			world.spawnEntity(shade);
		}

		this.setDead();
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
