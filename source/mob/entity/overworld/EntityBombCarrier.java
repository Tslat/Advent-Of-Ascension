package net.nevermine.mob.entity.overworld;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;

public class EntityBombCarrier extends EntityMob {
	private int cooldown;

	public EntityBombCarrier(final World par1World) {
		super(par1World);
		cooldown = 150;
		setSize(0.6f, 1.2f);
	}

	protected String getLivingSound() {
		return "nevermine:BombCarrierLiving";
	}

	protected String getDeathSound() {
		return "nevermine:BombCarrierHit";
	}

	protected String getHurtSound() {
		return "nevermine:BombCarrierHit";
	}

	private Item dropblock() {
		return Item.getItemFromBlock(Blocks.tnt);
	}

	public boolean getCanSpawnHere() {
		final Block b = worldObj.getBlock((int)posX, (int)posY - 1, (int)posZ);
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && (b == Blocks.grass || b == Blocks.dirt || b == Blocks.stone || b == Blocks.gravel) && rand.nextInt(6) == 2 && isValidLightLevel() && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		if (rand.nextInt(2) == 0) {
			dropItem(Weaponizer.HuntersRifle, 1);
		}
		dropItem(Itemizer.SilverCoin, 5 + rand.nextInt(10));
		dropItem(dropblock(), 5);
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.1);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (cooldown > 0) {
			--cooldown;
		}
		if (cooldown < 3 && !worldObj.isRemote) {
			final EntityPlayer var1 = worldObj.getClosestVulnerablePlayerToEntity(this, 10.0);
			if (var1 == null || var1.getDistanceToEntity(this) > 10.0f) {
				return;
			}
			final EntityFakeTnt var2 = new EntityFakeTnt(worldObj);
			var2.setLocationAndAngles(posX, posY + 0.5f, posZ, rand.nextFloat() * 360.0f, 0.0f);
			var2.fuse = 80;
			worldObj.spawnEntityInWorld(var2);
			cooldown = 150;
		}
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		final Entity entity = par1DamageSource.getSourceOfDamage();
		return !par1DamageSource.isExplosion() && super.attackEntityFrom(par1DamageSource, par2);
	}
}
