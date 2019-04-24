package net.nevermine.mob.entity.precasia;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.projectiles.enemy.EntitySkelemanShot;

public class EntityKaiyu extends EntityMob implements IRangedAttackMob {
	private int updatecount;
	private int form;
	private int switcher;
	private EntityAIArrowAttack aiArrowAttack;

	public EntityKaiyu(final World par1World) {
		super(par1World);
		updatecount = 4;
		form = 1;
		switcher = 50;
		aiArrowAttack = new EntityAIArrowAttack(this, getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(), 20, 60, 15.0f);
		final float moveSpeed = 0.45f;
		tasks.addTask(7, new EntityAIArrowAttack(this, getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(), 5, 64.0f));
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIRestrictSun(this));
		tasks.addTask(5, new EntityAIWander(this, (double)moveSpeed));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		tasks.addTask(6, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		setSize(0.6f, 2.3f);
	}

	protected String getLivingSound() {
		return "nevermine:KaiyuLiving";
	}

	protected String getDeathSound() {
		return "nevermine:KaiyuDeath";
	}

	protected String getHurtSound() {
		return "nevermine:KaiyuHit";
	}

	private Item dropBanner() {
		return Item.getItemFromBlock(SpecialBlockizer.AncientBanner);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));
		if (rand.nextInt(80) == 63) {
			dropItem(Weaponizer.KaiyuStaff, 1);
		}
		if (rand.nextInt(30) == 27) {
			dropItem(Itemizer.AncientOrb, 1);
		}
		if (rand.nextInt(7) == 0) {
			dropItem(dropBanner(), 1);
		}
		if (rand.nextInt(200) == 199) {
			dropItem(Itemizer.UpgradeKitAncient, 1);
		}
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (switcher == 1) {
			if (form == 1) {
				form = 0;
				switcher = 200;
			}
			else {
				form = 1;
				switcher = 80;
			}
		}
		--switcher;
	}

	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(16, new Integer(5));
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(125.0);
	}

	private int getAtkSpeed() {
		if (form == 0) {
			return 5;
		}
		return 1;
	}

	public boolean isAIEnabled() {
		return true;
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	public void attackEntityWithRangedAttack(final EntityLivingBase var1, final float f) {
		if (updatecount == 1) {
			final EntitySkelemanShot var2 = new EntitySkelemanShot(worldObj, this, 6.0f);
			final double var3 = var1.posX - posX;
			final double var4 = var1.posY + var1.getEyeHeight() - 1.100000023841858 - var2.posY;
			final double var5 = var1.posZ - posZ;
			final float var6 = MathHelper.sqrt_double(var3 * var3 + var5 * var5) * 0.2f;
			var2.setThrowableHeading(var3, var4 + var6, var5, 1.6f, 12.0f);
			playSound("nevermine:KaiyuFaster", 1.25f, 1.0f);
			worldObj.spawnEntityInWorld(var2);
			updatecount = getAtkSpeed();
		}
		else if (updatecount > 1) {
			--updatecount;
		}
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		final Entity entity = par1DamageSource.getSourceOfDamage();
		return (!par1DamageSource.isProjectile() || !(entity instanceof EntitySkelemanShot)) && super.attackEntityFrom(par1DamageSource, par2);
	}
}
