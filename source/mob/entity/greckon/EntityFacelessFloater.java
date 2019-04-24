package net.nevermine.mob.entity.greckon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.assist.AddPackets;
import net.nevermine.container.PlayerContainer;
import net.nevermine.gui.MobHitPacket;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.mob.ai.HuntAttempt;
import net.nevermine.mob.placement.EntityHunter;
import net.nevermine.mob.placement.EntityNoBows;
import net.nevermine.mob.placement.EntityNoRange;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityFacelessFloater extends EntityMob implements EntityNoRange, EntityHunter, EntityNoBows {
	public int getLevReq() {
		return 46;
	}

	public EntityFacelessFloater(final World par1World) {
		super(par1World);
		setSize(1.2f, 1.9f);
	}

	protected String getLivingSound() {
		return "nevermine:FacelessRunnerLiving";
	}

	protected String getDeathSound() {
		return "nevermine:FacelessRunnerDeath";
	}

	protected String getHurtSound() {
		return "nevermine:FacelessRunnerHit";
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);
		if (!worldObj.isRemote && var1.getEntity() != null && var1.getEntity() instanceof EntityPlayer) {
			PlayerContainer.getProperties((EntityPlayer)var1.getEntity()).addExperience(240.0f, Hunter);
		}
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		final Entity entity = par1DamageSource.getSourceOfDamage();
		return HuntAttempt.Hunt(entity, getLevReq(), par1DamageSource) && super.attackEntityFrom(par1DamageSource, par2);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));

		if (rand.nextBoolean()) {
			dropItem(Itemizer.CoinsGreckon, 2);
		}

		if (rand.nextInt(14) == 4) {
			dropItem(Itemizer.DarklyPowder, 2);
		}

		if (rand.nextInt(7) == 2) {
			dropItem(dropBanner(), 1);
		}
	}

	private Item dropBanner() {
		return Item.getItemFromBlock(SpecialBlockizer.GhoulBanner);
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(9.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(70.0);
	}

	public boolean attackEntityAsMob(final Entity par1) {
		if (super.attackEntityAsMob(par1)) {
			if (par1 instanceof EntityPlayer && par1 instanceof EntityPlayerMP) {
				AddPackets.network.sendTo(new MobHitPacket(300, 7), (EntityPlayerMP)par1);
			}
			return true;
		}
		return false;
	}
}
