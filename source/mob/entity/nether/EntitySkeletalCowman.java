package net.nevermine.mob.entity.nether;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.mob.placement.EntityNoFire;

public class EntitySkeletalCowman extends EntityMob implements IRangedAttackMob, EntityNoFire {
	private static final ItemStack defaultHeldItem;

	public EntitySkeletalCowman(final World par1World) {
		super(par1World);
		final float moveSpeed = 0.25f;
		isImmuneToFire = true;
		tasks.addTask(7, new EntityAIArrowAttack(this, 0.25, 5, 10.0f));
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIRestrictSun(this));
		tasks.addTask(3, new EntityAIFleeSun(this, (double)moveSpeed));
		tasks.addTask(5, new EntityAIWander(this, (double)moveSpeed));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		tasks.addTask(6, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(60.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.7);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(13.0);
	}

	protected String getLivingSound() {
		return "nevermine:SkeletalCowmanLiving";
	}

	protected String getHurtSound() {
		return "nevermine:SkeletalCowmanHit";
	}

	protected String getDeathSound() {
		return "nevermine:SkeletalCowmanHit";
	}

	private Item dropBanner() {
		return Item.getItemFromBlock(SpecialBlockizer.NetherBanner);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		if (rand.nextInt(7) == 0) {
			dropItem(dropBanner(), 1);
		}
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	public boolean isAIEnabled() {
		return true;
	}

	public ItemStack getHeldItem() {
		return EntitySkeletalCowman.defaultHeldItem;
	}

	public void attackEntityWithRangedAttack(final EntityLivingBase var1, final float f) {
		final EntityArrow var2 = new EntityArrow(worldObj, this, var1, 1.6f, 12.0f);
		var2.setDamage(3.0);
		final int var3 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, getHeldItem());
		final int var4 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, getHeldItem());
		if (var3 > 0) {
			var2.setDamage(var2.getDamage() + var3 * 0.5 + 0.5);
		}
		if (var4 > 0) {
			var2.setKnockbackStrength(var4);
		}
		var2.setFire(4);
		playSound("random.bow", 1.0f, 1.0f / (getRNG().nextFloat() * 0.4f + 0.8f));
		worldObj.spawnEntityInWorld(var2);
	}

	static {
		defaultHeldItem = new ItemStack(Items.bow, 1);
	}
}
