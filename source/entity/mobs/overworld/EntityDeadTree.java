package net.tslat.aoa3.entity.mobs.overworld;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.utils.ModUtil;

import javax.annotation.Nullable;

public class EntityDeadTree extends AoAMeleeMob {
	public static final float entityWidth = 0.875f;

	public EntityDeadTree(World world) {
		super(world, entityWidth, 3f);
	}

	@Nullable
	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingData) {
		setPositionAndRotation(((int)posX) + 0.5d, posY, ((int)posZ) + 0.5d, 0, 0);

		return null;
	}

	@Override
	public float getEyeHeight() {
		return 2.4f;
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

		if (!world.isRemote && !isDead && source.getImmediateSource() instanceof EntityPlayer) {
			EntityTreeSpirit treeSpirit = new EntityTreeSpirit(world);

			treeSpirit.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
			world.spawnEntity(treeSpirit);
			treeSpirit.attackEntityFrom(source, amount);

			if (treeSpirit.getHealth() <= 0)
				ModUtil.completeAdvancement((EntityPlayerMP)source.getImmediateSource(), "overworld/mightiest_tree_in_the_forest", "tree_spirit_instakill");

			world.playSound(null, posX, posY, posZ, SoundsRegister.mobTreeSpiritLiving, SoundCategory.HOSTILE, 1.0f, 1.0f);
			setDead();

			return true;
		}

		return false;
	}

	@Override
	public boolean isPushedByWater() {
		return false;
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer entityIn) {}

	@Override
	protected void collideWithEntity(Entity entityIn) {}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}
}