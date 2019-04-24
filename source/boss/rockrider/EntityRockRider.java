package net.nevermine.boss.rockrider;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.boss.EntityBoss;
import net.nevermine.container.PlayerContainer;
import net.nevermine.event.creature.BossClear;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.mob.placement.EntityNoRange;

import java.util.List;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityRockRider extends EntityMob implements EntityNoRange, EntityBoss {
	public static int protType = 1;
	private int protTimer;
	private int musictick;

	public EntityRockRider(final World par1World) {
		super(par1World);
		protTimer = 100;
		musictick = 6;
		setSize(1.2f, 2.5f);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(dropStatue(), 1);
		final int wep = rand.nextInt(4);

		if (wep == 1) {
			dropItem(Weaponizer.RockBasherSword, 1);
		}
		else if (wep == 2) {
			dropItem(Weaponizer.RockPickSword, 1);
		}
		else if (wep == 3) {
			dropItem(Weaponizer.JackRocker, 1);
		}
		else {
			dropItem(Weaponizer.LongShot, 1);
		}

		if (rand.nextInt(4) > 1) {
			dropItem(Items.coal, 50);
		}

		if (rand.nextInt(4) > 1) {
			dropItem(Items.flint, 50);
		}
	}

	private Item dropStatue() {
		return Item.getItemFromBlock(SpecialBlockizer.RockriderStatue);
	}

	protected String getDeathSound() {
		return "nevermine:RockriderDeath";
	}

	protected String getHurtSound() {
		return "nevermine:RockriderHit";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("nevermine:HeavyStep", 0.85f, 1.0f);
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(30.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.9);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1500.0);
	}

	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(16, new Integer(100));
	}

	public float getAIMoveSpeed() {
		return 0.8f;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (rand.nextInt(400) == 43) {
			final EntityPlayer var1 = worldObj.getClosestVulnerablePlayerToEntity(this, 60.0);
			if (var1 == null || var1.getDistanceToEntity(this) > 60.0f) {
				return;
			}
			if (!worldObj.isRemote) {
				setPosition(var1.posX, var1.posY, var1.posZ);
			}
		}
		if (musictick == 4) {
			playSound("nevermine:MusicRockRider", 3.0f, 1.0f);
			musictick = 380;
		}
		else {
			--musictick;
		}
		if (protTimer == 0) {
			if (EntityRockRider.protType == 1) {
				EntityRockRider.protType = 2;
				protTimer = 300;
				playSound("nevermine:RockriderSwitch", 0.85f, 1.0f);
			}
			else {
				EntityRockRider.protType = 1;
				protTimer = 100;
				playSound("nevermine:RockriderSwitch", 0.85f, 1.0f);
			}
		}
		else {
			--protTimer;
		}
	}

	public int getProtectionType() {
		return EntityRockRider.protType;
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		if (par1DamageSource == DamageSource.inWall) {
			BossClear.clear((int)posX, (int)posY, (int)posZ, worldObj, this);
		}
		final Entity entity = par1DamageSource.getSourceOfDamage();
		return ((!par1DamageSource.isProjectile() && !(entity instanceof EntityArrow) && !(entity instanceof EntityThrowable)) || getProtectionType() != 1) && (par1DamageSource.isProjectile() || entity instanceof EntityArrow || entity instanceof EntityThrowable || getProtectionType() != 2) && super.attackEntityFrom(par1DamageSource, par2);
	}

	public boolean attackEntityAsMob(final Entity par1Entity) {
		super.attackEntityAsMob(par1Entity);
		if (entityToAttack != null) {
			if (EntityRockRider.protType == 1) {
				if (par1Entity instanceof EntityLivingBase) {
					((EntityLivingBase)par1Entity).addPotionEffect(new PotionEffect(Potion.weakness.id, 105, 3));
				}
				entityToAttack.addVelocity(motionX * 5.0, 1.0, motionZ * 0.0);
				return true;
			}
			if (EntityRockRider.protType == 2 && par1Entity instanceof EntityLivingBase) {
				par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), 5.0f);
				((EntityLivingBase)par1Entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 105, 3));
			}
		}
		return false;
	}

	public void moveEntityWithHeading(final float par1, final float par2) {
		if (isInWater()) {
			final double d0 = posY;
			moveFlying(par1, par2, 0.52f);
			moveEntity(motionX, motionY, motionZ);
			motionX *= 0.800000011920929;
			motionY *= 0.800000011920929;
			motionZ *= 0.800000011920929;
			motionY -= 0.02;
			if (isCollidedHorizontally && isOffsetPositionInLiquid(motionX, motionY + 0.6000000238418579 - posY + d0, motionZ)) {
				motionY = 0.30000001192092896;
			}
		}
		else if (handleLavaMovement()) {
			final double d0 = posY;
			moveFlying(par1, par2, 0.02f);
			moveEntity(motionX, motionY, motionZ);
			motionX *= 0.5;
			motionY *= 0.5;
			motionZ *= 0.5;
			motionY -= 0.02;
			if (isCollidedHorizontally && isOffsetPositionInLiquid(motionX, motionY + 0.6000000238418579 - posY + d0, motionZ)) {
				motionY = 0.30000001192092896;
			}
		}
		else {
			float f2 = 0.91f;
			final float f3 = 0.16277136f / (f2 * f2 * f2);
			float f4;
			if (onGround) {
				f4 = getAIMoveSpeed() * f3;
			}
			else {
				f4 = jumpMovementFactor;
			}
			moveFlying(par1, par2, f4);
			f2 = 0.91f;
			if (isOnLadder()) {
				final float f5 = 0.15f;
				if (motionX < -f5) {
					motionX = -f5;
				}
				if (motionX > f5) {
					motionX = f5;
				}
				if (motionZ < -f5) {
					motionZ = -f5;
				}
				if (motionZ > f5) {
					motionZ = f5;
				}
				fallDistance = 0.0f;
				if (motionY < -0.15) {
					motionY = -0.15;
				}
			}
			moveEntity(motionX, motionY, motionZ);
			if (isCollidedHorizontally && isOnLadder()) {
				motionY = 0.2;
			}
			if (worldObj.isRemote && (!worldObj.blockExists((int)posX, 0, (int)posZ) || !worldObj.getChunkFromBlockCoords((int)posX, (int)posZ).isChunkLoaded)) {
				if (posY > 0.0) {
					motionY = -0.1;
				}
				else {
					motionY = 0.0;
				}
			}
			else {
				motionY -= 0.08;
			}
			motionY *= 0.9800000190734863;
			motionX *= f2;
			motionZ *= f2;
		}
		prevLimbSwingAmount = limbSwingAmount;
		final double d0 = posX - prevPosX;
		final double d2 = posZ - prevPosZ;
		float f6 = MathHelper.sqrt_double(d0 * d0 + d2 * d2) * 4.0f;
		if (f6 > 1.0f) {
			f6 = 1.0f;
		}
		limbSwingAmount += (f6 - limbSwingAmount) * 0.4f;
		limbSwing += limbSwingAmount;
	}

	public void onDeath(final DamageSource d) {
		super.onDeath(d);

		if (!worldObj.isRemote && d.getEntity() != null && d.getEntity() instanceof EntityPlayer) {
			final EntityPlayer p = (EntityPlayer)d.getEntity();

			IChatComponent msg = StringUtil.getLocaleWithArguments("message.mob.rockrider.kill", p.getDisplayName());

			for (final EntityPlayer e : (List<EntityPlayer>)p.worldObj.getEntitiesWithinAABB(EntityPlayer.class, p.boundingBox.expand(50.0, 50.0, 50.0))) {
				e.addChatMessage(msg);
			}

			PlayerContainer cont = PlayerContainer.getProperties(p);

			if (cont.getLevel(Hunter) >= 30)
				cont.addExperience(2500, Hunter);
		}
	}
}
