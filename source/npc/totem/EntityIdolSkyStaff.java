package net.nevermine.npc.totem;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.izer.equipment.Weaponizer;

public class EntityIdolSkyStaff extends EntityCreature {
	public EntityIdolSkyStaff(World par1World) {
		super(par1World);
		setSize(1.0F, 1.0F);
	}

	protected String getLivingSound() {
		return "nevermine:Shine";
	}

	protected String getDeathSound() {
		return "nevermine:OpenTotem";
	}

	protected String getHurtSound() {
		return "";
	}

	protected net.minecraft.entity.Entity findPlayerToAttack() {
		net.minecraft.entity.player.EntityPlayer entityPlayer = this.worldObj.getClosestVulnerablePlayerToEntity(this, 32.0D);
		return (entityPlayer != null) && (canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(0.5D);
	}

	protected boolean isAIEnabled() {
		return true;
	}

	private Item dropblock() {
		return Item.getItemFromBlock(SpecialBlockizer.LottoBanner);
	}

	protected void dropFewItems(boolean par1, int par2) {
		if (this.rand.nextInt(22) == 16) {
			dropItem(Weaponizer.SkyStaff, 1);
			playSound("nevermine:TotemWin", 1.0F, 1.0F / (getRNG().nextFloat() * 0.4F + 0.8F));
		}
		else if (this.rand.nextInt(10) < 8) {
			dropItem(net.minecraft.init.Items.wheat_seeds, 4);
		}
		else if (this.rand.nextInt(10) < 4) {
			dropItem(dropblock(), 1);
		}
		else {
			dropItem(Itemizer.CoinsShyrelands, 1);
		}
	}
}
