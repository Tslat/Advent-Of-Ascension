package net.nevermine.mob.entity.mysterium;

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
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.mob.ai.HuntAttempt;
import net.nevermine.mob.placement.EntityHunter;
import net.nevermine.mob.placement.EntityNoRange;
import net.nevermine.projectiles.enemy.EntitySurgeBlue;
import net.nevermine.projectiles.enemy.EntitySurgeRed;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityUndeadTroll extends EntityMob implements IRangedAttackMob, EntityHunter, EntityNoRange {
	private EntityAIArrowAttack aiArrowAttack;

	public int getLevReq() {
		return 28;
	}

	public EntityUndeadTroll(final World par1World) {
		super(par1World);
		aiArrowAttack = new EntityAIArrowAttack(this, getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(), 20, 60, 15.0f);
		final float moveSpeed = 0.45f;
		tasks.addTask(7, new EntityAIArrowAttack(this, getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(), 8, 64.0f));
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIRestrictSun(this));
		tasks.addTask(5, new EntityAIWander(this, (double)moveSpeed));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		tasks.addTask(6, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		setSize(0.6f, 2.3f);
	}

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);
		if (var1.getEntity() != null && var1.getEntity() instanceof EntityPlayer) {
			PlayerContainer.getProperties((EntityPlayer)var1.getEntity()).addExperience(80.0f, Hunter);
		}
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));

		if (rand.nextBoolean()) {
			dropItem(Itemizer.TrollSkull, 1);
		}

		if (rand.nextInt(50) == 13) {
			dropItem(Weaponizer.SurgeStaff, 1);
		}

		if (rand.nextInt(7) == 0) {
			dropItem(dropBanner(), 1);
		}
	}

	private Item dropBanner() {
		return Item.getItemFromBlock(SpecialBlockizer.HauntedBanner);
	}

	protected String getLivingSound() {
		return "nevermine:GoblinLiving";
	}

	protected String getDeathSound() {
		return "nevermine:GoblinDeath";
	}

	protected String getHurtSound() {
		return "nevermine:GoblinHit";
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
	}

	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(16, new Integer(5));
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0);
	}

	public boolean isAIEnabled() {
		return true;
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	public void attackEntityWithRangedAttack(final EntityLivingBase var1, final float f) {
		if (rand.nextBoolean()) {
			final EntitySurgeRed var2 = new EntitySurgeRed(worldObj, this, 30.0f, 1);
			final double var3 = var1.posX - posX;
			final double var4 = var1.posY + var1.getEyeHeight() - 1.100000023841858 - var2.posY;
			final double var5 = var1.posZ - posZ;
			final float var6 = MathHelper.sqrt_double(var3 * var3 + var5 * var5) * 0.2f;
			var2.setThrowableHeading(var3, var4 + var6, var5, 1.6f, 12.0f);
			playSound("nevermine:SurgeRed", 1.0f, 1.0f / (getRNG().nextFloat() * 0.4f + 0.8f));
			worldObj.spawnEntityInWorld(var2);
		}
		else {
			final EntitySurgeBlue var7 = new EntitySurgeBlue(worldObj, this, 30.0f, 1);
			final double var3 = var1.posX - posX;
			final double var4 = var1.posY + var1.getEyeHeight() - 1.100000023841858 - var7.posY;
			final double var5 = var1.posZ - posZ;
			final float var6 = MathHelper.sqrt_double(var3 * var3 + var5 * var5) * 0.2f;
			var7.setThrowableHeading(var3, var4 + var6, var5, 1.6f, 12.0f);
			playSound("nevermine:SurgeBlue", 1.0f, 1.0f / (getRNG().nextFloat() * 0.4f + 0.8f));
			worldObj.spawnEntityInWorld(var7);
		}
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		final Entity entity = par1DamageSource.getSourceOfDamage();
		return HuntAttempt.Hunt(entity, getLevReq(), par1DamageSource) && super.attackEntityFrom(par1DamageSource, par2);
	}
}
