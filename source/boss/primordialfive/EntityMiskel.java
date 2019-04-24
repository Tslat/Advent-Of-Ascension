package net.nevermine.boss.primordialfive;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.nevermine.boss.EntityBoss;
import net.nevermine.container.PlayerContainer;
import net.nevermine.projectiles.enemy.EntityMiskelShot;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityMiskel extends EntityMob implements IRangedAttackMob, EntityBoss {
	private int musicTick;
	private EntityAIArrowAttack aiArrowAttack;

	public EntityMiskel(final World par1World) {
		super(par1World);
		aiArrowAttack = new EntityAIArrowAttack(this, getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(), 20, 60, 15.0f);
		final float moveSpeed = 0.45f;
		tasks.addTask(7, new EntityAIArrowAttack(this, getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(), 20, 64.0f));
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIRestrictSun(this));
		tasks.addTask(5, new EntityAIWander(this, (double)moveSpeed));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		tasks.addTask(6, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		setSize(0.6f, 2.3f);
	}

	public EntityMiskel(final World par1World, final int music) {
		this(par1World);
		musicTick = music;
	}

	protected String getLivingSound() {
		return "nevermine:PrimordialLiving";
	}

	protected String getDeathSound() {
		return "nevermine:PrimordialDeath";
	}

	protected String getHurtSound() {
		return "nevermine:PrimordialLiving";
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		--musicTick;

		if (musicTick == 0) {
			musicTick = 290;
			playSound("nevermine:MusicPrimordialFive", 2.8f, 1.0f);
		}
	}

	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(16, new Integer(5));
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1300.0);
	}

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);
		if (var1.getEntity() != null && var1.getEntity() instanceof EntityPlayer) {
			PlayerContainer cont = PlayerContainer.getProperties((EntityPlayer)var1.getEntity());

			if (cont.getLevel(Hunter) >= 30)
				cont.addExperience(800, Hunter);
		}

		transform();
	}

	private void transform() {
		if (!worldObj.isRemote) {
			final EntityHarkos var2 = new EntityHarkos(worldObj, musicTick);
			var2.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
			worldObj.spawnEntityInWorld(var2);
			if (!worldObj.isRemote) {
				setDead();
			}
		}
	}

	public boolean isAIEnabled() {
		return true;
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	public void attackEntityWithRangedAttack(final EntityLivingBase var1, final float f) {
		final int ability = rand.nextInt(2);
		final EntityMiskelShot var2 = new EntityMiskelShot(worldObj, this, 14.0f, ability);
		final double var3 = var1.posX - posX;
		final double var4 = var1.posY + var1.getEyeHeight() - 1.100000023841858 - var2.posY;
		final double var5 = var1.posZ - posZ;
		final float var6 = MathHelper.sqrt_double(var3 * var3 + var5 * var5) * 0.2f;
		var2.setThrowableHeading(var3, var4 + var6, var5, 1.6f, 12.0f);
		playSound("nevermine:PrimordialWizard", 1.0f, 1.0f / (rand.nextFloat() * 0.4f + 0.8f));
		worldObj.spawnEntityInWorld(var2);
	}
}
