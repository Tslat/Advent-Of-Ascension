package net.nevermine.mob.entity.shyrelands;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.mob.placement.EntityHunter;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntitySoulscorne extends EntityMob implements EntityHunter {
	private int count = 0;
	private int timer = 0;

	public EntitySoulscorne(World par1World) {
		super(par1World);
		setSize(0.6F, 1.9F);
	}

	public void onDeath(DamageSource src) {
		super.onDeath(src);

		if (!worldObj.isRemote && src.getEntity() != null && src.getEntity() instanceof EntityPlayer) {
			PlayerContainer.getProperties((EntityPlayer)src.getEntity()).addExperience(120.0f, Hunter);
		}
	}

	public int getLevReq() {
		return 75;
	}

	protected void dropFewItems(boolean par1, int par2) {
		dropItem(Itemizer.CopperCoin, 3 + this.rand.nextInt(5));
		if (this.rand.nextInt(100) == 42) {
			dropItem(Weaponizer.Sublimus, 1);
		}
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		Entity entity = par1DamageSource.getSourceOfDamage();

		if (net.nevermine.mob.ai.HuntAttempt.Hunt(entity, getLevReq(), par1DamageSource).booleanValue()) {
			return super.attackEntityFrom(par1DamageSource, par2);
		}
		return false;
	}

	protected String getLivingSound() {
		return "nevermine:SoulscorneLiving";
	}

	protected String getDeathSound() {
		return "nevermine:SoulscorneDeath";
	}

	protected String getHurtSound() {
		return "nevermine:SoulscorneHit";
	}

	protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
		playSound("mob.pig.step", 1.0F, 1.0F);
	}

	public boolean getCanSpawnHere() {
		return (this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL) && (this.worldObj.checkNoEntityCollision(this.boundingBox)) && (this.posY < 35.0D) && (this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty()) && (!this.worldObj.isAnyLiquid(this.boundingBox));
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (this.count > 0) {
			this.timer -= 1;
		}
		if ((this.timer == 0) && (this.count > 0)) {
			this.count -= 1;
		}
	}

	public boolean attackEntityAsMob(Entity par1Entity) {
		super.attackEntityAsMob(par1Entity);
		if (par1Entity != null) {
			if ((par1Entity instanceof EntityPlayer)) {
				this.count += 1;
				this.timer = 60;
				if (((EntityPlayer)par1Entity).getHealth() - (2 * count) > 0) {
					((EntityPlayer)par1Entity).setHealth(((EntityPlayer)par1Entity).getHealth() - 2 * this.count);
				}
				else {
					par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this).setMagicDamage(), 2 * count);
				}
			}
			return true;
		}
		return false;
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected Entity findPlayerToAttack() {
		EntityPlayer entityPlayer = this.worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
		return (entityPlayer != null) && (canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();

		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0D);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.1D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(120.0D);
	}
}
