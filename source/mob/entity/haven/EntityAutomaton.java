package net.nevermine.mob.entity.haven;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.assist.ConfigurationHelper;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.izer.equipment.Weaponizer;

import java.util.Random;

import static net.nevermine.container.PlayerContainer.Deities.Luxon;

public class EntityAutomaton extends EntityMob {
	public EntityAutomaton(final World par1World) {
		super(par1World);
		setSize(1.5f, 2.0f);
	}

	protected String getLivingSound() {
		return "nevermine:AutomatonLiving";
	}

	protected String getDeathSound() {
		return "nevermine:AutomatonDeath";
	}

	protected String getHurtSound() {
		return "nevermine:AutomatonHit";
	}

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);

		if (dimension == ConfigurationHelper.ancientcavern && var1.getEntity() instanceof EntityPlayer) {
			PlayerContainer.getProperties((EntityPlayer)var1.getEntity()).adjustTribute(Luxon, 8);
		}
	}

	private Item dropBanner() {
		return Item.getItemFromBlock(SpecialBlockizer.UtopianBanner);
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("nevermine:GolemStep", 0.55f, 1.0f);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));

		if (rand.nextInt(40) == 17) {
			dropItem(Itemizer.RealmstoneAncientCavern, 2);
		}

		if (rand.nextInt(7) == 0) {
			dropItem(dropBanner(), 1);
		}

		if (rand.nextInt(50) == 34) {
			switch (getClrType()) {
				case 1:
					dropItem(Weaponizer.Germinator, 1);
					break;
				case 2:
					dropItem(Weaponizer.Dragilator, 1);
					break;
				case 3:
					dropItem(Weaponizer.Krilinator, 1);
					break;
				case 4:
					dropItem(Weaponizer.Frosticator, 1);
					break;
				case 5:
					dropItem(Weaponizer.Electinator, 1);
					break;
				default:
					break;
			}
		}
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	protected void entityInit() {
		super.entityInit();
		getDataWatcher().addObject(12, (byte)0);
		getDataWatcher().addObject(13, (byte)0);
		final Random numb = new Random();
		final int i = numb.nextInt(5) + 1;
		getDataWatcher().addObject(25, i);
		getDataWatcher().updateObject(25, i);
	}

	public int getClrType() {
		return dataWatcher.getWatchableObjectInt(25);
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(10.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.0);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(80.0);
	}

	public boolean attackEntityAsMob(final Entity par1Entity) {
		super.attackEntityAsMob(par1Entity);
		if (entityToAttack != null) {
			if (entityToAttack instanceof EntityLivingBase && ((EntityLivingBase)entityToAttack).getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue() != 1.0) {
				entityToAttack.addVelocity(motionX * 7.5, motionY * 0.5, motionZ * 7.0);
			}
			return true;
		}
		return false;
	}
}
