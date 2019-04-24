package net.nevermine.mob.entity.greckon;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.assist.AddPackets;
import net.nevermine.gui.MobHitPacket;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.izer.equipment.Weaponizer;

public class EntityGrillface extends EntityMob {
	public EntityGrillface(final World par1World) {
		super(par1World);
		setSize(1.2f, 1.9f);
	}

	protected String getLivingSound() {
		return "nevermine:GrillfaceLiving";
	}

	protected String getDeathSound() {
		return "nevermine:GrillfaceDeath";
	}

	protected String getHurtSound() {
		return "nevermine:GrillfaceHit";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));
		if (rand.nextInt(35) == 5) {
			dropItem(Weaponizer.GhastBlaster, 1);
		}
		if (rand.nextInt(7) == 2) {
			dropItem(dropBanner(), 1);
		}
		if (rand.nextInt(200) == 135) {
			dropItem(Itemizer.UpgradeKitHaunted, 1);
		}
		if (rand.nextInt(20) == 17) {
			dropItem(Itemizer.RealmstoneDustopia, 2);
		}
	}

	private Item dropBanner() {
		return Item.getItemFromBlock(SpecialBlockizer.GhoulBanner);
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return (entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		final EntityPlayer var1 = worldObj.getClosestVulnerablePlayerToEntity(this, 20.0);
		if (var1 == null || var1.getDistanceToEntity(this) > 20.0f) {
			return;
		}
		if (rand.nextInt(400) == 34) {
			if (!worldObj.isRemote) {
				setPosition(var1.posX, var1.posY, var1.posZ);
			}
			playSound("nevermine:GrillfaceAppear", 0.85f, 1.0f);
			if (!worldObj.isRemote && var1 instanceof EntityPlayerMP) {
				AddPackets.network.sendTo(new MobHitPacket(20, 4), (EntityPlayerMP)var1);
			}
		}
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.4);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(70.0);
	}
}
