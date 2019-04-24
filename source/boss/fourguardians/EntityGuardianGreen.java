package net.nevermine.boss.fourguardians;

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
import net.nevermine.boss.EntityBoss;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.projectiles.enemy.EntityGuardianProjectileBlue;
import net.nevermine.projectiles.enemy.EntityGuardianProjectileGreen;
import net.nevermine.projectiles.enemy.EntityGuardianProjectileRed;
import net.nevermine.projectiles.enemy.EntityGuardianProjectileYellow;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityGuardianGreen extends EntityMob implements IRangedAttackMob, EntityBoss {
	private EntityAIArrowAttack aiArrowAttack;

	public EntityGuardianGreen(final World par1World) {
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
		setSize(2.8f, 2.8f);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(dropStatue(), 1);

		if (rand.nextInt(9) == 4) {
			dropItem(Weaponizer.GuardiansSword, 1);
		}
		if (rand.nextInt(9) == 3) {
			dropItem(Weaponizer.BayonetteRifle, 1);
		}
		if (rand.nextInt(9) == 7) {
			dropItem(Weaponizer.RedRocket, 1);
		}
		if (rand.nextInt(9) == 2) {
			dropItem(Weaponizer.Eradicator, 1);
		}
	}

	private Item dropStatue() {
		return Item.getItemFromBlock(SpecialBlockizer.GuardianStatueGreen);
	}

	protected String getDeathSound() {
		return "nevermine:GuardianDeath";
	}

	protected String getHurtSound() {
		return "nevermine:GuardianHit";
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
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(12.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(750.0);
	}

	public boolean isAIEnabled() {
		return true;
	}

	public void attackEntityWithRangedAttack(final EntityLivingBase var1, final float f) {
		final EntityGuardianProjectileGreen var2 = new EntityGuardianProjectileGreen(worldObj, this, 20.0f, 2);
		final double var3 = var1.posX - posX;
		final double var4 = var1.posY + var1.getEyeHeight() - 1.100000023841858 - var2.posY;
		final double var5 = var1.posZ - posZ;
		final float var6 = MathHelper.sqrt_double(var3 * var3 + var5 * var5) * 0.2f;
		var2.setThrowableHeading(var3, var4 + var6, var5, 1.6f, 12.0f);
		playSound("nevermine:GuardianFire", 1.0f, 1.0f / (getRNG().nextFloat() * 0.4f + 0.8f));
		worldObj.spawnEntityInWorld(var2);
	}

	public void onDeath(final DamageSource d) {
		super.onDeath(d);
		if (!worldObj.isRemote && d.getEntity() != null && d.getEntity() instanceof EntityPlayer) {
			PlayerContainer cont = PlayerContainer.getProperties((EntityPlayer)d.getEntity());

			if (cont.getLevel(Hunter) >= 30)
				cont.addExperience(1000, Hunter);
		}
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		final Entity entity = par1DamageSource.getSourceOfDamage();
		return (!par1DamageSource.isProjectile() || !(entity instanceof EntityGuardianProjectileBlue)) && !(entity instanceof EntityGuardianProjectileRed) && !(entity instanceof EntityGuardianProjectileYellow) && super.attackEntityFrom(par1DamageSource, par2);
	}
}
