package net.nevermine.mob.entity.overworld;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.event.dimensional.overworld.LunarEvent;
import net.nevermine.izer.Itemizer;

import java.util.List;

public class EntityRoloscope extends EntityMob {
	public EntityRoloscope(final World par1World) {
		super(par1World);
		setSize(1.2f, 1.0f);
	}

	protected String getLivingSound() {
		return "nevermine:RoloscopeLiving";
	}

	protected String getDeathSound() {
		return "nevermine:RoloscopeDeath";
	}

	protected String getHurtSound() {
		return "nevermine:RoloscopeHit";
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && LunarEvent.isLunar() && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.Orbulon, 1);
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.3);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(90.0);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		for (final EntityPlayer e : (List<EntityPlayer>)worldObj.getEntitiesWithinAABB(EntityPlayer.class, boundingBox.expand(9.0, 9.0, 9.0))) {
			if (!e.capabilities.isCreativeMode)
				e.addPotionEffect(new PotionEffect(Potion.weakness.id, 65, 1));
		}
	}
}
