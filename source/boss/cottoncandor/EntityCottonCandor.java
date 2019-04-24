package net.nevermine.boss.cottoncandor;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.boss.EntityBoss;
import net.nevermine.container.PlayerContainer;
import net.nevermine.event.creature.BossClear;
import net.nevermine.izer.Blockizer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.izer.equipment.Armorizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.mob.ai.EntityAIFlying;
import net.nevermine.mob.placement.EntitySpecialRange;
import net.nevermine.projectiles.enemy.EntityCandorShot;
import net.nevermine.projectiles.staff.*;

import java.util.List;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityCottonCandor extends EntityAIFlying implements EntityBoss, EntitySpecialRange {
	private ChunkCoordinates currentFlightTarget;
	private int flyTimer;
	private int musicTick;
	private int stateSwitch;

	public EntityCottonCandor(final World var1) {
		super(var1);
		musicTick = 1;
		stateSwitch = 100;
		final double moveSpeed = 0.75;
		isImmuneToFire = true;
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIRestrictSun(this));
		tasks.addTask(3, new EntityAIFleeSun(this, moveSpeed));
		tasks.addTask(5, new EntityAIWander(this, moveSpeed));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		tasks.addTask(6, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		flyTimer = 0;
		setSize(3.0f, 3.0f);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(dropStatue(), 1);
		dropItem(Item.getItemFromBlock(Blockizer.CandylandCottonBlue), 64);
		dropItem(Item.getItemFromBlock(Blockizer.CandylandCottonPink), 64);

		final int armr = rand.nextInt(4);
		if (armr == 1) {
			dropItem(Armorizer.CandyBoots, 1);
		}
		else if (armr == 2) {
			dropItem(Armorizer.CandyHelmet, 1);
		}
		else if (armr == 3) {
			dropItem(Armorizer.CandyLeggings, 1);
		}
		else {
			dropItem(Armorizer.CandyChestplate, 1);
		}

		if (rand.nextInt(6) == 3) {
			dropItem(Weaponizer.CottonCrusher, 1);
		}

	}

	private Item dropStatue() {
		return Item.getItemFromBlock(SpecialBlockizer.CottonCandorStatue);
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (worldObj.difficultySetting == EnumDifficulty.PEACEFUL) {
			despawnEntity();
		}

		--musicTick;

		if (musicTick == 0) {
			musicTick = 287;
			playSound("nevermine:MusicCottonCandor", 3.0f, 1.0f);
		}

		--stateSwitch;

		if (stateSwitch == 0) {
			if (!worldObj.isRemote) {
				dataWatcher.updateObject(25, rand.nextInt(6));
			}

			stateSwitch = 100;
		}

		if (rand.nextInt(70) == 37 && entityToAttack != null) {
			if (entityToAttack instanceof EntityPlayer && ((EntityPlayer)entityToAttack).capabilities.isCreativeMode) {
				entityToAttack = null;
				return;
			}

			final EntityCandorShot entitylargefireball = new EntityCandorShot(worldObj, this);
			final double var3 = entityToAttack.posX - posX;
			final double var4 = entityToAttack.posY + entityToAttack.getEyeHeight() - 1.100000023841858 - entitylargefireball.posY;
			final double var5 = entityToAttack.posZ - posZ;
			final float var6 = MathHelper.sqrt_double(var3 * var3 + var5 * var5) * 0.2f;
			entitylargefireball.setThrowableHeading(var3, var4 + var6, var5, 1.6f, 12.0f);
			worldObj.spawnEntityInWorld(entitylargefireball);
			playSound("nevermine:CandorRoar", 1.0f, 1.0f / (getRNG().nextFloat() * 0.4f + 0.8f));
		}
	}

	public boolean attackEntityFrom(final DamageSource dmgSource, final float par2) {
		if (dmgSource == DamageSource.inWall) {
			BossClear.clear((int)posX, (int)posY, (int)posZ, worldObj, this);
		}

		if (!dmgSource.isProjectile() || !(dmgSource.getSourceOfDamage() instanceof IProjectile))
			return false;

		if (canDamage((IProjectile)dmgSource.getSourceOfDamage(), this, dmgSource.getEntity(), par2))
			return super.attackEntityFrom(dmgSource, par2);

		return false;
	}

	protected void entityInit() {
		super.entityInit();
		getDataWatcher().addObject(25, 1);
		getDataWatcher().updateObject(25, 1);
	}

	public int getState() {
		return dataWatcher.getWatchableObjectInt(25);
	}

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);

		if (!worldObj.isRemote && var1.getEntity() != null && var1.getEntity() instanceof EntityPlayer) {
			final EntityPlayer p = (EntityPlayer)var1.getEntity();

			IChatComponent msg = StringUtil.getLocaleWithArguments("message.mob.cottonCandor.kill", p.getDisplayName());

			for (final EntityPlayer e : (List<EntityPlayer>)p.worldObj.getEntitiesWithinAABB(EntityPlayer.class, p.boundingBox.expand(50.0, 50.0, 50.0))) {
				e.addChatMessage(msg);
			}

			PlayerContainer cont = PlayerContainer.getProperties(p);

			if (cont.getLevel(Hunter) >= 30)
				cont.addExperience(3500, Hunter);
		}
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3499999940395355);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(15.0);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(3000.0);
	}

	protected boolean isAIEnabled() {
		return true;
	}

	protected float getSoundPitch() {
		return super.getSoundPitch() * 2.95f;
	}

	protected String getLivingSound() {
		return "nevermine:CottonCandorLiving";
	}

	protected String getDeathSound() {
		return "nevermine:CottonCandorDeath";
	}

	protected String getHurtSound() {
		return "nevermine:CottonCandorHit";
	}

	public void onUpdate() {
		super.onUpdate();
		motionY *= 0.3000000238418579;
	}

	protected void updateAITasks() {
		super.updateAITasks();
		if (getAttackTarget() != null) {
			final int var1 = (int)getAttackTarget().posX;
			final int var2 = (int)getAttackTarget().posY;
			final int var3 = (int)getAttackTarget().posZ;
			currentFlightTarget = new ChunkCoordinates(var1, var2, var3);
		}
		else if (flyTimer != 0) {
			flyTimer = 120;
			currentFlightTarget = new ChunkCoordinates((int)(posX + rand.nextInt(16)) - 8, (int)(posY + rand.nextInt(32)) - 16, (int)(posZ + rand.nextInt(16)) - 8);
		}
		if (currentFlightTarget != null) {
			final double var4 = currentFlightTarget.posX - posX;
			final double var5 = currentFlightTarget.posY + rand.nextFloat() + 1.0 - posY;
			final double var6 = currentFlightTarget.posZ - posZ;
			if (Math.signum(var4) != 0.0 || Math.signum(var5) != 0.0 || Math.signum(var6) != 0.0) {
				motionX += (Math.signum(var4) * 0.15 - motionX) * 0.10000000149011612;
				motionY += (Math.signum(var5) * 1.699999988079071 - motionY) * 0.10000000149011612;
				motionZ += (Math.signum(var6) * 0.15 - motionZ) * 0.10000000149011612;
				final float var7 = (float)(Math.atan2(motionZ, motionX) * 180.0 / 3.141592653589793) - 90.0f;
				final float var8 = MathHelper.wrapAngleTo180_float(var7 - rotationYaw);
				moveForward = 0.5f;
				rotationYaw += var8;
			}
			--flyTimer;
		}
	}

	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	protected void fall(final float par1) {
	}

	@Override
	protected void updateFallState(final double par1, final boolean par3) {
	}

	public boolean doesEntityNotTriggerPressurePlate() {
		return true;
	}

	public void onCollideWithPlayer(final EntityPlayer par1EntityPlayer) {
		if (!isDead)
			attackEntityAsMob(par1EntityPlayer);
	}

	@Override
	public boolean canDamage(final IProjectile projectile, final EntityLivingBase target, final Entity shooter, final float dmg) {
		switch (getState()) {
			case 1:
				if (projectile instanceof EntityStaffWind)
					return true;
				break;
			case 2:
				if (projectile instanceof EntityStaffWater)
					return true;
				break;
			case 3:
				if (projectile instanceof EntityStaffFire || projectile instanceof EntityUnderworldBarrage || projectile instanceof EntityFireFall)
					return true;
				break;
			case 4:
				if (projectile instanceof EntityStaffPoison)
					return true;
				break;
			case 5:
				if (projectile instanceof EntityStaffWither)
					return true;
				break;
			case 6:
				if (projectile instanceof EntityLunarFall)
					return true;
				break;
			default:
				break;
		}

		return false;
	}
}
