package net.nevermine.mob.entity.night;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.container.PlayerContainer;
import net.nevermine.event.dimensional.overworld.BloodHuntEvent;
import net.nevermine.fx.trail.RedTrail;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.resource.rage.rageHelper;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityBloodmist extends EntityMob {
	public EntityBloodmist(final World par1World) {
		super(par1World);
		setSize(1.5f, 1.2f);
	}

	protected String getLivingSound() {
		return "nevermine:BloodmistLiving";
	}

	protected String getDeathSound() {
		return "nevermine:BloodmistDeath";
	}

	protected String getHurtSound() {
		return "nevermine:BloodmistHit";
	}

	private Item dropBanner() {
		return Item.getItemFromBlock(SpecialBlockizer.EnergyBanner);
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 1.0f, 1.0f);
	}

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);
		if (!worldObj.isRemote && var1.getEntity() != null && var1.getEntity() instanceof EntityPlayer) {
			PlayerContainer.getProperties((EntityPlayer)var1.getEntity()).addExperience(15.0f, Hunter);
		}
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		if (rand.nextInt(15) == 3) {
			dropItem(Itemizer.RealmstoneAbyss, 3);
		}

		if (rand.nextInt(5) == 3) {
			dropItem(Item.getItemFromBlock(SpecialBlockizer.BloodBanner), 1);
		}
	}

	public boolean attackEntityAsMob(final Entity par1) {
		if (super.attackEntityAsMob(par1)) {
			if (par1 instanceof EntityPlayer) {
				rageHelper.getProperties((EntityPlayer)par1).removeBarValue(20.0f);
			}
			return true;
		}
		return false;
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && BloodHuntEvent.isHunt() && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox) && !worldObj.isDaytime();
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.1);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(35.0);
	}

	@SideOnly(Side.CLIENT)
	public void onUpdate() {
		super.onUpdate();
		if (worldObj.isRemote) {
			for (int var3 = 0; var3 < 8; ++var3) {
				final RedTrail var4 = new RedTrail(worldObj, posX, posY - 0.2, posZ, 0.0, 0.0, 0.0, 5);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
				final RedTrail var5 = new RedTrail(worldObj, posX, posY + 0.2, posZ, 0.0, 0.0, 0.0, 5);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var5);
			}
		}
	}
}
