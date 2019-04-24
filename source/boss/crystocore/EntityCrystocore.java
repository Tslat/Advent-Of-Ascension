package net.nevermine.boss.crystocore;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
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
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.mob.ai.EntityAIFlying;
import net.nevermine.mob.placement.EntityNoRange;

import java.util.List;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityCrystocore extends EntityAIFlying implements EntityBoss, EntityNoRange {
	private ChunkCoordinates currentFlightTarget;
	private int flyTimer;
	private int musicTick;
	private int stateSwitch;

	public EntityCrystocore(final World var1) {
		super(var1);
		musicTick = 1;
		stateSwitch = 220;
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
		setSize(6.0f, 6.0f);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(dropStatue(), 1);
		final int staff = rand.nextInt(3);

		if (staff == 1) {
			dropItem(Weaponizer.CrystonStaff, 1);
		}
		else if (staff == 2) {
			dropItem(Weaponizer.CrystalStaff, 1);
		}
		else {
			dropItem(Weaponizer.CrystikStaff, 1);
		}
	}

	private Item dropStatue() {
		return Item.getItemFromBlock(SpecialBlockizer.CrystocoreStatue);
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
			musicTick = 233;
			playSound("nevermine:MusicCrystocore", 3.0f, 1.0f);
		}
		--stateSwitch;
		if (stateSwitch == 0) {
			if (!worldObj.isRemote) {
				dataWatcher.updateObject(25, rand.nextInt(6));
			}
			stateSwitch = 220;
			if (!worldObj.isRemote) {
				for (final EntityPlayer e : (List<EntityPlayer>)worldObj.getEntitiesWithinAABB(EntityPlayer.class, boundingBox.expand(60.0, 60.0, 60.0))) {
					if (e.capabilities.isCreativeMode)
						continue;

					if (getState() == 1) {
						e.addPotionEffect(new PotionEffect(Potion.poison.id, 180, 1));
					}
					else if (getState() == 2) {
						e.addPotionEffect(new PotionEffect(Potion.blindness.id, 180, 2));
					}
					else if (getState() == 3) {
						e.addPotionEffect(new PotionEffect(Potion.weakness.id, 180, 1));
					}
					else if (getState() == 4) {
						e.addPotionEffect(new PotionEffect(Potion.confusion.id, 180, 10));
					}
					else if (getState() == 5) {
						e.addPotionEffect(new PotionEffect(Potion.wither.id, 180, 1));
					}
					else {
						e.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 180, 1));
					}
				}
			}
		}
	}

	protected void entityInit() {
		super.entityInit();
		getDataWatcher().addObject(25, 1);
		getDataWatcher().updateObject(25, 1);
	}

	public int getState() {
		return dataWatcher.getWatchableObjectInt(25);
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		if (par1DamageSource == DamageSource.inWall) {
			BossClear.clear((int)posX, (int)posY, (int)posZ, worldObj, this);
		}
		return super.attackEntityFrom(par1DamageSource, par2);
	}

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);

		if (!worldObj.isRemote && var1.getEntity() != null && var1.getEntity() instanceof EntityPlayer) {
			final EntityPlayer p = (EntityPlayer)var1.getEntity();

			IChatComponent msg = StringUtil.getLocaleWithArguments("message.mob.crystocore.kill", p.getDisplayName());

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
		final int pick = rand.nextInt(6);
		if (pick == 1) {
			return "nevermine:CrystalConstructLiving1";
		}
		if (pick == 2) {
			return "nevermine:CrystalConstructLiving2";
		}
		if (pick == 3) {
			return "nevermine:CrystalConstructLiving3";
		}
		if (pick == 4) {
			return "nevermine:CrystalConstructLiving4";
		}
		if (pick == 5) {
			return "nevermine:CrystalConstructLiving6";
		}
		return "nevermine:CrystalConstructLiving5";
	}

	protected String getDeathSound() {
		return "nevermine:ConstructDeath";
	}

	protected String getHurtSound() {
		return "nevermine:ConstructHit";
	}

	public void onUpdate() {
		super.onUpdate();
		motionY *= 0.6000000238418579;
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
}
