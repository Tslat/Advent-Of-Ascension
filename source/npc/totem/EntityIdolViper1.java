package net.nevermine.npc.totem;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.izer.equipment.Weaponizer;

public class EntityIdolViper1 extends EntityCreature {
	public EntityIdolViper1(final World par1World) {
		super(par1World);
		setSize(1.0f, 1.0f);
	}

	protected String getLivingSound() {
		return "nevermine:Shine";
	}

	protected String getDeathSound() {
		return "nevermine:OpenTotem";
	}

	protected String getHurtSound() {
		return "nevermine:OpenTotem";
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 32.0);
		return (entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(0.5);
	}

	protected boolean isAIEnabled() {
		return true;
	}

	private Item dropblock() {
		return Item.getItemFromBlock(SpecialBlockizer.LottoBanner);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		if (rand.nextInt(22) == 16) {
			dropItem(Weaponizer.Viper1, 1);
			playSound("nevermine:TotemWin", 1.0f, 1.0f / (getRNG().nextFloat() * 0.4f + 0.8f));
		}
		else if (rand.nextInt(10) < 8) {
			dropItem(Items.wheat_seeds, 4);
		}
		else if (rand.nextInt(10) < 4) {
			dropItem(dropblock(), 1);
		}
		else {
			dropItem(Itemizer.CoinsGreckon, 1);
		}
	}
}
