package net.nevermine.mob.entity.dustopia;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.assist.AddPackets;
import net.nevermine.container.PlayerContainer;
import net.nevermine.gui.MobHitPacket;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.mob.ai.HuntAttempt;
import net.nevermine.mob.placement.EntityHunter;

import java.util.List;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityCrusilisk extends EntityMob implements EntityHunter {
	public int getLevReq() {
		return 95;
	}

	public EntityCrusilisk(final World par1World) {
		super(par1World);
		setSize(1.3f, 1.6f);
	}

	protected String getLivingSound() {
		return "nevermine:CrusiliskLiving";
	}

	protected String getDeathSound() {
		return "nevermine:CrusiliskDeath";
	}

	protected String getHurtSound() {
		return "nevermine:CrusiliskHit";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);

		if (!worldObj.isRemote && var1.getEntity() != null && var1.getEntity() instanceof EntityPlayer) {
			PlayerContainer.getProperties((EntityPlayer)var1.getEntity()).addExperience(1600.0f, Hunter);
		}
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		final Entity entity = par1DamageSource.getSourceOfDamage();
		return HuntAttempt.Hunt(entity, getLevReq(), par1DamageSource) && super.attackEntityFrom(par1DamageSource, par2);
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.SilverCoin, 1);

		if (rand.nextInt(100) == 45) {
			dropItem(Weaponizer.Beamer, 1);
		}
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (rand.nextInt(100) == 35) {
			for (final EntityPlayer e : (List<EntityPlayer>)worldObj.getEntitiesWithinAABB(EntityPlayer.class, boundingBox.expand(16.0, 16.0, 16.0))) {
				if (e.capabilities.isCreativeMode)
					continue;

				e.addVelocity(Math.signum(posX - e.posX) * 1.129, 0.0, Math.signum(posZ - e.posZ) * 1.129);
				playSound("nevermine:CrusiliskScream", 1.0f, 1.0f / (getRNG().nextFloat() * 0.4f + 0.8f));
			}
		}
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(25.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(250.0);
	}

	public boolean attackEntityAsMob(final Entity par1) {
		if (super.attackEntityAsMob(par1)) {
			if (par1 instanceof EntityLivingBase) {
				((EntityLivingBase)par1).addPotionEffect(new PotionEffect(Potion.blindness.id, 150, 30));
				if (par1 instanceof EntityPlayerMP) {
					AddPackets.network.sendTo(new MobHitPacket(50, 2), (EntityPlayerMP)par1);
				}
			}
			return true;
		}
		return false;
	}
}
