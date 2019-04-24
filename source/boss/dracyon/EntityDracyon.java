package net.nevermine.boss.dracyon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.assist.AddPackets;
import net.nevermine.assist.StringUtil;
import net.nevermine.boss.EntityBoss;
import net.nevermine.container.PlayerContainer;
import net.nevermine.event.creature.BossClear;
import net.nevermine.gui.MobHitPacket;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.mob.ai.EntityAIFlying;
import net.nevermine.projectiles.enemy.EntitySpectralShot;

import java.util.List;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityDracyon extends EntityAIFlying implements EntityBoss {
	private ChunkCoordinates currentFlightTarget;
	private int flyTimer;
	private int musicTick;

	public EntityDracyon(final World var1) {
		super(var1);
		musicTick = 1;
		final double moveSpeed = 1.25;
		getNavigator().setAvoidsWater(true);
		setSize(1.5f, 1.5f);
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(5, new EntityAIAttackOnCollide(this, moveSpeed, true));
		tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 32.0f));
		tasks.addTask(9, new EntityAILookIdle(this));
		tasks.addTask(6, new EntityAIWander(this, moveSpeed));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
		flyTimer = 0;
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Item.getItemFromBlock(SpecialBlockizer.DracyonStatue), 1);
		final int choose = rand.nextInt(3);

		if (choose == 1) {
			dropItem(Weaponizer.Draco, 1);
		}
		else if (choose == 2) {
			dropItem(Weaponizer.BoreicBow, 1);
		}
		else {
			dropItem(Weaponizer.ReefStaff, 1);
		}

	}

	public float getAIMoveSpeed() {
		return 3.7f;
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	public boolean attackEntityAsMob(final Entity par1) {
		if (super.attackEntityAsMob(par1)) {
			if (par1 instanceof EntityPlayer) {
				if (par1 instanceof EntityPlayerMP) {
					AddPackets.network.sendTo(new MobHitPacket(40, 1), (EntityPlayerMP)par1);
				}
				playSound("nevermine:DracyonScratch", 1.55f, 1.0f);
			}
			return true;
		}
		return false;
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6499999761581421);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(25.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1700.0);
	}

	protected boolean isAIEnabled() {
		return true;
	}

	protected float getSoundPitch() {
		return super.getSoundPitch() * 0.95f;
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		if (par1DamageSource == DamageSource.inWall) {
			BossClear.clear((int)posX, (int)posY, (int)posZ, worldObj, this);
		}
		motionY = 2.0999999046325684;
		return super.attackEntityFrom(par1DamageSource, par2);
	}

	protected String getLivingSound() {
		return "nevermine:DracyonLiving";
	}

	protected String getHurtSound() {
		return "nevermine:DracyonLiving";
	}

	protected String getDeathSound() {
		return "nevermine:DracyonDeath";
	}

	public void onDeath(final DamageSource d) {
		super.onDeath(d);

		if (!worldObj.isRemote && d.getEntity() instanceof EntityPlayer) {
			final EntityPlayer p = (EntityPlayer)d.getEntity();

			IChatComponent msg = StringUtil.getLocaleWithArguments("message.mob.dracyon.kill", p.getDisplayName());

			for (final EntityPlayer e : (List<EntityPlayer>)p.worldObj.getEntitiesWithinAABB(EntityPlayer.class, p.boundingBox.expand(50.0, 50.0, 50.0))) {
				e.addChatMessage(msg);
			}

			PlayerContainer cont = PlayerContainer.getProperties(p);

			if (cont.getLevel(Hunter) >= 30)
				cont.addExperience(10000, Hunter);
		}
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (rand.nextInt(70) == 37 && entityToAttack != null) {
			final Entity var1 = entityToAttack;
			final EntitySpectralShot var2 = new EntitySpectralShot(worldObj, this, 13.5f, 1.0f);
			final double var3 = var1.posX - posX;
			final double var4 = var1.posY + var1.getEyeHeight() - 1.100000023841858 - var2.posY;
			final double var5 = var1.posZ - posZ;
			final float var6 = MathHelper.sqrt_double(var3 * var3 + var5 * var5) * 0.2f;
			var2.setThrowableHeading(var3, var4 + var6, var5, 1.6f, 12.0f);
			playSound("nevermine:DracyonLiving", 1.0f, 1.0f / (getRNG().nextFloat() * 0.4f + 0.8f));
			worldObj.spawnEntityInWorld(var2);
		}
		--musicTick;
		if (musicTick == 0) {
			musicTick = 290;
			playSound("nevermine:MusicDracyon", 2.8f, 1.0f);
		}
	}

	public void onUpdate() {
		super.onUpdate();
		motionY *= 0.2000000238418579;
	}

	protected void updateAITasks() {
		super.updateAITasks();
		if (rand.nextInt(200) == 17) {
			int var1 = MathHelper.floor_double(posX);
			int var2 = MathHelper.floor_double(posZ);
			if (worldObj.provider.dimensionId != -1) {
				for (var1 = 0; var1 < 4; ++var1) {
					var2 = MathHelper.floor_double(posX + (var1 % 2 * 2 - 1) * 0.25f);
					final int var3 = MathHelper.floor_double(posY);
					final int var4 = MathHelper.floor_double(posZ + (var1 / 2 % 2 * 2 - 1) * 0.25f);
					if (worldObj.getBlock(var2, var3, var4) == Blocks.air) {
						worldObj.setBlock(var2, var3, var4, Blocks.water);
					}
				}
			}
		}
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
			final double var5 = currentFlightTarget.posX - posX;
			final double var6 = currentFlightTarget.posY + rand.nextFloat() + 1.0 - posY;
			final double var7 = currentFlightTarget.posZ - posZ;
			if (Math.signum(var5) != 0.0 || Math.signum(var6) != 0.0 || Math.signum(var7) != 0.0) {
				motionX += (Math.signum(var5) * 0.15 - motionX) * 0.10000000149011612;
				motionY += (Math.signum(var6) * 1.699999988079071 - motionY) * 0.10000000149011612;
				motionZ += (Math.signum(var7) * 0.15 - motionZ) * 0.10000000149011612;
				final float var8 = (float)(Math.atan2(motionZ, motionX) * 180.0 / 3.141592653589793) - 90.0f;
				final float var9 = MathHelper.wrapAngleTo180_float(var8 - rotationYaw);
				moveForward = 0.5f;
				rotationYaw += var9;
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
