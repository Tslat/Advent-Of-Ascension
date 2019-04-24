package net.nevermine.mob.entity.night;

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
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.mob.placement.EntityHunter;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityHeadlessHunter extends EntityMob implements EntityHunter {
	public EntityHeadlessHunter(final World par1World) {
		super(par1World);
		setSize(1.2f, 1.7f);
	}

	protected String getLivingSound() {
		return null;
	}

	protected String getDeathSound() {
		return "nevermine:SlimerDeath";
	}

	protected String getHurtSound() {
		return "nevermine:SlimerHit";
	}

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);
		if (!worldObj.isRemote && var1.getEntity() != null && var1.getEntity() instanceof EntityPlayer) {
			PlayerContainer.getProperties((EntityPlayer)var1.getEntity()).addExperience(20.0f, Hunter);
		}
	}

	private Item dropBanner() {
		return Item.getItemFromBlock(SpecialBlockizer.EnergyBanner);
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("nevermine:VeryHeavyStep", 1.25f, 1.0f);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		if (rand.nextBoolean()) {
			dropItem(Itemizer.CopperCoin, 2 + rand.nextInt(3));
		}
		if (rand.nextInt(7) == 0) {
			dropItem(dropBanner(), 1);
		}
	}

	public boolean getCanSpawnHere() {
		final Block b = worldObj.getBlock((int)posX, (int)posY - 1, (int)posZ);
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && isValidLightLevel() && (b == Blocks.grass || b == Blocks.dirt || b == Blocks.stained_hardened_clay || b == Blocks.stone || b == Blocks.sand || b == Blocks.snow || b == Blocks.snow_layer || b == Blocks.hardened_clay || b == Blocks.clay) && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox) && !worldObj.isDaytime();
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
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(16.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.1);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(70.0);
	}

	public int getLevReq() {
		return 0;
	}
}
