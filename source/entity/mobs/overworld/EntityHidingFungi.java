package net.tslat.aoa3.entity.mobs.overworld;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.HunterEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.PlayerUtil;
import net.tslat.aoa3.utils.WorldUtil;

import java.util.TreeSet;

public class EntityHidingFungi extends AoAMeleeMob implements HunterEntity {
	public static final float entityWidth = 1.0f;

	public EntityHidingFungi(World world) {
		super(world, entityWidth, 1.0f);
	}

	@Override
	public float getEyeHeight() {
		return 0.4f;
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	protected void initEntityAI() {}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1.0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 0.5;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 0;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0;
	}

	@Override
	protected boolean isDaylightMob() {
		return true;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (source == DamageSource.OUT_OF_WORLD) {
			this.setDead();

			return true;
		}

		if (source.getImmediateSource() instanceof EntityPlayer && PlayerUtil.doesPlayerHaveLevel((EntityPlayer)source.getImmediateSource(), Enums.Skills.HUNTER, getHunterReq())) {
			transform();

			return true;
		}

		return false;
	}

	private void transform() {
		if (!world.isRemote) {
			EntityLivingFungi livingFungi = new EntityLivingFungi(world);

			livingFungi.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
			world.spawnEntity(livingFungi);
			world.playSound(null, posX, posY, posZ, SoundsRegister.mobLivingFungiSpawn, SoundCategory.HOSTILE, 1.0f, 1.0f);
		}

		this.setDead();
	}

	@Override
	protected boolean canSpawnOnBlock(IBlockState block) {
		return super.canSpawnOnBlock(block) && WorldUtil.isNaturalOverworldBlock(block);
	}

	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return this.mobProperties;
	}

	@Override
	public int getHunterReq() {
		return 77;
	}

	@Override
	public float getHunterXp() {
		return 0;
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}
}
