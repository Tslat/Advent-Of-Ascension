package net.nevermine.boss.cavern;

import net.minecraft.block.Block;
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
import net.nevermine.boss.cavern.holder.EntityHolderPenumbra;
import net.nevermine.container.AncientBossesContainer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.projectiles.enemy.EntityPenumbraShot;

public class EntityPenumbra extends EntityMob implements IRangedAttackMob, CavernBoss {
	private EntityAIArrowAttack aiArrowAttack;

	public EntityPenumbra(final World par1World) {
		super(par1World);
		aiArrowAttack = new EntityAIArrowAttack(this, getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(), 15, 40, 17.0f);
		final float moveSpeed = 0.45f;
		tasks.addTask(7, new EntityAIArrowAttack(this, getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(), 20, 64.0f));
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIRestrictSun(this));
		tasks.addTask(5, new EntityAIWander(this, (double)moveSpeed));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		tasks.addTask(6, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		setSize(1.5f, 3.0f);
	}

	protected boolean canDespawn() {
		return false;
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		if (rand.nextInt(10) == 4) {
			dropItem(Weaponizer.ErebonScythe, 1);
		}

		if (rand.nextInt(10) == 4) {
			dropItem(Weaponizer.ErebonStickler, 1);
		}

		if (rand.nextInt(5) == 3) {
			dropItem(Item.getItemFromBlock(SpecialBlockizer.ErebonBanner), 1);
		}

		if (rand.nextInt(5) == 3) {
			dropItem(Item.getItemFromBlock(SpecialBlockizer.PenumbraStatue), 1);
		}
	}

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);

		AncientBossesContainer.killPenumbra(this);
	}

	private void transform() {
		if (!worldObj.isRemote) {
			final EntityHolderPenumbra var2 = new EntityHolderPenumbra(worldObj);
			var2.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
			worldObj.spawnEntityInWorld(var2);
			if (!worldObj.isRemote) {
				setDead();
			}
		}
	}

	protected String getLivingSound() {
		return "nevermine:PenumbraLiving";
	}

	protected String getDeathSound() {
		return "nevermine:PenumbraDeath";
	}

	protected String getHurtSound() {
		return "nevermine:PenumbraHit";
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
	}

	public boolean getCanSpawnHere() {
		return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(16, new Integer(5));
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(15.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(3000.0);
	}

	public boolean isAIEnabled() {
		return true;
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	public void attackEntityWithRangedAttack(final EntityLivingBase var1, final float f) {
		final EntityPenumbraShot var2 = new EntityPenumbraShot(worldObj, this, 18.0f, 1);
		final double var3 = var1.posX - posX;
		final double var4 = var1.posY + var1.getEyeHeight() - 1.100000023841858 - var2.posY;
		final double var5 = var1.posZ - posZ;
		final float var6 = MathHelper.sqrt_double(var3 * var3 + var5 * var5) * 0.2f;
		var2.setThrowableHeading(var3, var4 + var6, var5, 1.6f, 12.0f);
		playSound("nevermine:PenumbraFire", 1.0f, 1.0f / (getRNG().nextFloat() * 0.4f + 0.8f));
		worldObj.spawnEntityInWorld(var2);
	}
}
