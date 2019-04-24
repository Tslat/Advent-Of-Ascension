package net.nevermine.boss.baroness;

import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;

import java.util.List;

public class EntityBaronBomb extends EntityMob {
	private int lifecount = 150;

	public EntityBaronBomb(World par1World) {
		super(par1World);
		setSize(2F, 2F);
	}

	protected String getLivingSound() {
		return null;
	}

	protected String getDeathSound() {
		return null;
	}

	protected String getHurtSound() {
		return null;
	}

	@Override
	public void addVelocity(double x, double y, double z) {
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
		playSound("mob.pig.step", 0.55F, 1.0F);
	}

	@Override
	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && (worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox));
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		lifecount--;

		if (lifecount == 20) {
			for (EntityPlayer e : (List<EntityPlayer>)worldObj.getEntitiesWithinAABB(EntityPlayer.class, boundingBox.expand(23, 23, 23))) {
				if (e.capabilities.isCreativeMode)
					continue;

				if (e.capabilities.isFlying) {
					if (!worldObj.isRemote) {
						e.addChatMessage(StringUtil.getColourLocale("message.mob.baroness.taunt.1", EnumChatFormatting.RED));
						e.addChatMessage(StringUtil.getColourLocale("message.mob.baroness.taunt.2", EnumChatFormatting.RED));
					}

					e.addVelocity(Math.signum(posX - e.posX) * 1.029, Math.signum(posY - e.posY) * 3.029, Math.signum(posZ - e.posZ) * 1.029);
				}
				else {
					e.addVelocity(Math.signum(posX - e.posX) * 3.029, 0, Math.signum(posZ - e.posZ) * 3.029);
				}
			}
		}

		if (lifecount == 16)
			playSound("nevermine:BaronBombExplode", 2.0F, 1.0F / (getRNG().nextFloat() * 0.4F + 0.8F));

		if (lifecount == 1) {
			worldObj.createExplosion(this, posX, posY, posZ, 7.0f, false);
			setDead();
		}
	}

	@Override
	protected boolean isValidLightLevel() {
		return true;
	}

	@Override
	protected boolean isAIEnabled() {
		return true;
	}

	@Override
	protected void dropFewItems(boolean par1, int par2) {

	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();

		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(0.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0D);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0D);
	}

}
