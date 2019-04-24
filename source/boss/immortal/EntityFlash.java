package net.nevermine.boss.immortal;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.nevermine.assist.ConfigurationHelper;
import net.nevermine.assist.StringUtil;
import net.nevermine.boss.EntityBoss;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Armorizer;

import java.util.List;

public class EntityFlash extends EntityMob implements EntityBoss {
	private int pick;
	private int armr;

	public EntityFlash(final World par1World) {
		super(par1World);
		setSize(0.8f, 2.5f);
	}

	protected String getLivingSound() {
		final int pick = rand.nextInt(3);
		if (pick == 1) {
			return "nevermine:ImmortalLiving1";
		}
		if (pick == 2) {
			return "nevermine:ImmortalLiving2";
		}
		return "nevermine:ImmortalLiving3";
	}

	protected String getDeathSound() {
		return "nevermine:ImmortalDeath";
	}

	protected String getHurtSound() {
		return "random.anvil_land";
	}

	protected boolean canDespawn() {
		return true;
	}

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);

		if (!worldObj.isRemote && var1.getEntity() != null) {
			EntityPlayer p = null;

			if (var1.getEntity() instanceof EntityTameable) {
				if (((EntityTameable)var1.getEntity()).getOwner() instanceof EntityPlayer)
					p = (EntityPlayer)((EntityTameable)var1.getEntity()).getOwner();
			}
			else if (var1.getEntity() instanceof EntityPlayer) {
				p = ((EntityPlayer)var1.getEntity());
			}

			if (p != null) {
				p.addChatMessage(StringUtil.getLocale("message.feedback.immortallisProgression.rewards"));
				if (worldObj.provider.dimensionId == ConfigurationHelper.immortallis) {
					if (!p.inventory.addItemStackToInventory(new ItemStack(Itemizer.DungeonTokens, 7 + rand.nextInt(5)))) {
						p.dropItem(Itemizer.DungeonTokens, 7 + rand.nextInt(5));
					}
				}

				if (rand.nextInt(3) == 2) {
					armr = rand.nextInt(4);
					if (armr == 1) {
						if (!p.inventory.addItemStackToInventory(new ItemStack(Armorizer.ArchaicBoots))) {
							p.dropItem(Armorizer.ArchaicBoots, 1);
						}
					}
					else if (armr == 3) {
						if (!p.inventory.addItemStackToInventory(new ItemStack(Armorizer.ArchaicChestplate))) {
							p.dropItem(Armorizer.ArchaicChestplate, 1);
						}
					}
					else if (armr == 2) {
						if (!p.inventory.addItemStackToInventory(new ItemStack(Armorizer.ArchaicHelmet))) {
							p.dropItem(Armorizer.ArchaicHelmet, 1);
						}
					}
					else if (!p.inventory.addItemStackToInventory(new ItemStack(Armorizer.ArchaicLeggings))) {
						p.dropItem(Armorizer.ArchaicLeggings, 1);
					}
				}
			}
		}
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.ProgressCoin4, 1);
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 32.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (ticksExisted % 80 == 0) {
			IChatComponent msg = StringUtil.getLocale("message.mob.flash.taunt");

			for (final EntityPlayer e : (List<EntityPlayer>)worldObj.getEntitiesWithinAABB(EntityPlayer.class, boundingBox.expand(13.0, 13.0, 13.0))) {
				if (e.capabilities.isCreativeMode)
					continue;

				if (e.posZ > 9.0 || e.posZ < -2.0 || e.posX < 229.0 || e.posX > 236.0) {
					e.addVelocity(Math.signum(233.0 - e.posX) * 1.629, 0.0, Math.signum(3.0 - e.posZ) * 1.629);
					if (worldObj.isRemote) {
						continue;
					}
					e.addChatMessage(msg);
				}
			}
		}
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		if (!worldObj.isRemote) {
			if (worldObj.provider.dimensionId == ConfigurationHelper.immortallis) {
				pick = rand.nextInt(3);
				if (pick == 1) {
					setPosition(235.0, 22.0, 10.0);
				}
				else if (pick == 2) {
					setPosition(235.0, 22.0, -3.0);
				}
				else {
					setPosition(228.0, 22.0, 3.0);
				}
			}
			else {
				double x = posX + (rand.nextInt(14) - 7);
				double z = posZ + (rand.nextInt(14) - 7);
				setPosition(x, worldObj.getHeightValue((int)x, (int)z), z);
			}
		}
		return super.attackEntityFrom(par1DamageSource, par2);
	}

	public boolean attackEntityAsMob(final Entity par1) {
		if (super.attackEntityAsMob(par1)) {
			if (!worldObj.isRemote) {
				if (worldObj.provider.dimensionId == ConfigurationHelper.immortallis) {
					pick = rand.nextInt(3);

					if (pick == 1) {
						setPosition(235.0, 22.0, 10.0);
					}
					else if (pick == 2) {
						setPosition(235.0, 22.0, -3.0);
					}
					else {
						setPosition(228.0, 22.0, 3.0);
					}
				}
				else {
					double x = posX + (rand.nextInt(14) - 7);
					double z = posZ + (rand.nextInt(14) - 7);
					setPosition(x, worldObj.getHeightValue((int)x, (int)z), z);
				}
			}
			return true;
		}
		return false;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(9.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1000.0);
	}

	public void moveEntityWithHeading(final float par1, final float par2) {
		if (isInWater()) {
			final double d0 = posY;
			moveFlying(par1, par2, 0.52f);
			moveEntity(motionX, motionY, motionZ);
			motionX *= 0.800000011920929;
			motionY *= 0.800000011920929;
			motionZ *= 0.800000011920929;
			motionY -= 0.02;
			if (isCollidedHorizontally && isOffsetPositionInLiquid(motionX, motionY + 0.6000000238418579 - posY + d0, motionZ)) {
				motionY = 0.30000001192092896;
			}
		}
		else if (handleLavaMovement()) {
			final double d0 = posY;
			moveFlying(par1, par2, 0.02f);
			moveEntity(motionX, motionY, motionZ);
			motionX *= 0.5;
			motionY *= 0.5;
			motionZ *= 0.5;
			motionY -= 0.02;
			if (isCollidedHorizontally && isOffsetPositionInLiquid(motionX, motionY + 0.6000000238418579 - posY + d0, motionZ)) {
				motionY = 0.30000001192092896;
			}
		}
		else {
			float f2 = 0.91f;
			final float f3 = 0.16277136f / (f2 * f2 * f2);
			float f4;
			if (onGround) {
				f4 = getAIMoveSpeed() * f3;
			}
			else {
				f4 = jumpMovementFactor;
			}
			moveFlying(par1, par2, f4);
			f2 = 0.91f;
			if (isOnLadder()) {
				final float f5 = 0.15f;
				if (motionX < -f5) {
					motionX = -f5;
				}
				if (motionX > f5) {
					motionX = f5;
				}
				if (motionZ < -f5) {
					motionZ = -f5;
				}
				if (motionZ > f5) {
					motionZ = f5;
				}
				fallDistance = 0.0f;
				if (motionY < -0.15) {
					motionY = -0.15;
				}
			}
			moveEntity(motionX, motionY, motionZ);
			if (isCollidedHorizontally && isOnLadder()) {
				motionY = 0.2;
			}
			if (worldObj.isRemote && (!worldObj.blockExists((int)posX, 0, (int)posZ) || !worldObj.getChunkFromBlockCoords((int)posX, (int)posZ).isChunkLoaded)) {
				if (posY > 0.0) {
					motionY = -0.1;
				}
				else {
					motionY = 0.0;
				}
			}
			else {
				motionY -= 0.08;
			}
			motionY *= 0.9800000190734863;
			motionX *= f2;
			motionZ *= f2;
		}
		prevLimbSwingAmount = limbSwingAmount;
		final double d0 = posX - prevPosX;
		final double d2 = posZ - prevPosZ;
		float f6 = MathHelper.sqrt_double(d0 * d0 + d2 * d2) * 4.0f;
		if (f6 > 1.0f) {
			f6 = 1.0f;
		}
		limbSwingAmount += (f6 - limbSwingAmount) * 0.4f;
		limbSwing += limbSwingAmount;
	}

	public float getAIMoveSpeed() {
		return 3.2f;
	}
}
