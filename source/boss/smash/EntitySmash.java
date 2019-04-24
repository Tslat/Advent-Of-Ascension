package net.nevermine.boss.smash;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
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

import java.util.List;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntitySmash extends EntityMob implements EntityBoss {
	private int musictick;

	public EntitySmash(final World par1World) {
		super(par1World);
		musictick = 6;
		setSize(0.8f, 3.3f);
	}

	protected String getLivingSound() {
		switch (rand.nextInt(3)) {
			case 0: {
				return "nevermine:SmashLiving1";
			}
			case 1: {
				return "nevermine:SmashLiving2";
			}
			case 2: {
				return "nevermine:SmashLiving3";
			}
			default: {
				return "nevermine:SmashLiving";
			}
		}
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		if (par1DamageSource == DamageSource.inWall) {
			BossClear.clear((int)posX, (int)posY, (int)posZ, worldObj, this);
		}
		return super.attackEntityFrom(par1DamageSource, par2);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(dropStatue(), 1);

		final int wep = rand.nextInt(4);

		if (wep == 1) {
			dropItem(Weaponizer.TrollsArchergun, 1);
		}
		else if (wep == 2) {
			dropItem(Weaponizer.TrollBasherAxe, 1);
		}
		else if (wep == 3) {
			dropItem(Weaponizer.BlueBarrel, 1);
		}
		else {
			dropItem(Weaponizer.BoomCannon, 1);
		}

		final int item = rand.nextInt(3);

		if (item == 1) {
			dropItem(Items.diamond, 15);
		}
		else if (item == 2) {
			dropItem(Items.coal, 50);
		}
		else {
			dropItem(Items.leather, 10);
		}
	}

	private Item dropStatue() {
		return Item.getItemFromBlock(SpecialBlockizer.SmashStatue);
	}

	protected String getDeathSound() {
		return "nevermine:SmashDeath";
	}

	protected String getHurtSound() {
		return "nevermine:SmashHit";
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
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(15.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.9);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(500.0);
	}

	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(16, new Integer(100));
	}

	public float getAIMoveSpeed() {
		return 2.1f;
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

	public boolean attackEntityAsMob(final Entity par1Entity) {
		super.attackEntityAsMob(par1Entity);

		if (entityToAttack != null) {
			final float cur = getHealth();

			if (cur > 300.0f && cur < 401.0f) {
				entityToAttack.addVelocity(motionX * 5.0, 2.0, motionZ * 0.0);
			}

			if ((cur > 200.0f && cur < 300.0f) || cur == 300.0f) {
				entityToAttack.addVelocity(motionX * 5.4, 2.5, motionZ * 0.0);
			}

			if ((cur > 100.0f && cur < 200.0f) || cur == 200.0f) {
				entityToAttack.addVelocity(motionX * 5.8, 3.0, motionZ * 0.0);
			}

			if ((cur > 0.0f && cur < 100.0f) || cur == 100.0f) {
				entityToAttack.addVelocity(motionX * 5.2, 3.5, motionZ * 0.0);
			}

			return true;
		}

		return false;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (musictick == 4) {
			playSound("nevermine:MusicSmash", 3.0f, 1.0f);
			musictick = 380;
		}
		else {
			--musictick;
		}
	}

	public void onDeath(final DamageSource d) {
		super.onDeath(d);

		if (!worldObj.isRemote && d.getEntity() != null && d.getEntity() instanceof EntityPlayer) {
			final EntityPlayer p = (EntityPlayer)d.getEntity();

			IChatComponent msg = StringUtil.getLocaleWithArguments("message.mob.smash.kill", p.getDisplayName());

			for (final EntityPlayer e : (List<EntityPlayer>)p.worldObj.getEntitiesWithinAABB(EntityPlayer.class, p.boundingBox.expand(50.0, 50.0, 50.0))) {
				e.addChatMessage(msg);
			}

			PlayerContainer cont = PlayerContainer.getProperties((EntityPlayer)d.getEntity());

			if (cont.getLevel(Hunter) >= 30)
				cont.addExperience(400, Hunter);
		}
	}
}
