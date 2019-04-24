package net.nevermine.boss.tyrosaur;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.boss.EntityBoss;
import net.nevermine.container.PlayerContainer;
import net.nevermine.mob.placement.EntityNoBows;
import net.nevermine.mob.placement.EntityNoRange;

import java.util.List;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityTyrosaur extends EntityMob implements EntityBoss, EntityNoRange, EntityNoBows {
	private int musictick = 6;
	private int stomp = 100;

	public EntityTyrosaur(World par1World) {
		super(par1World);
		setSize(0.8f, 1.9f);
	}

	protected String getLivingSound() {
		switch (rand.nextInt(3)) {
			case 0: {
				return "nevermine:TyrosaurLiving1";
			}
			case 1: {
				return "nevermine:TyrosaurLiving2";
			}
			case 2: {
				return "nevermine:TyrosaurLiving3";
			}
		}
		return "nevermine:TyrosaurLiving";
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		Entity entity = par1DamageSource.getSourceOfDamage();
		if (par1DamageSource.isProjectile() || entity instanceof EntityArrow || entity instanceof EntityThrowable) {
			return false;
		}
		return super.attackEntityFrom(par1DamageSource, par2);
	}

	protected void dropFewItems(boolean par1, int par2) {
	}

	protected String getDeathSound() {
		return "nevermine:TyrosaurDeath";
	}

	protected String getHurtSound() {
		return "nevermine:TyrosaurHit";
	}

	protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
		playSound("nevermine:TyrosaurStep", 0.85f, 1.0f);
	}

	protected Entity findPlayerToAttack() {
		EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return entityPlayer != null && canEntityBeSeen(entityPlayer) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(15.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.9);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(4000.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
	}

	protected void fall(float par1) {
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (musictick == 4) {
			playSound("nevermine:MusicTyrosaur", 3.0f, 1.0f);
			musictick = 283;
		}
		else {
			--musictick;
		}
		if (rand.nextInt(150) == 35 && stomp > 70) {
			EntityPlayer var1 = worldObj.getClosestVulnerablePlayerToEntity(this, 40.0);
			if (var1 == null || var1.getDistanceToEntity(this) > 40.0f) {
				return;
			}
			playSound("nevermine:TyrosaurCharge", 3.0f, 1.0f);
			addVelocity(Math.signum(var1.posX - posX) * 1.029, (var1.posY - posY) * 0.429, Math.signum(var1.posZ - posZ) * 1.029);
		}
		--stomp;
		if (stomp == 1) {
			stomp = 100;
			playSound("nevermine:TyrosaurStomp", 3.0f, 1.0f);
			for (EntityPlayer e : (List<EntityPlayer>)worldObj.getEntitiesWithinAABB(EntityPlayer.class, boundingBox.expand(35.0, 35.0, 35.0))) {
				if (e.capabilities.isCreativeMode || e.worldObj.getBlock((int)e.posX, (int)(e.posY - 0.5), (int)e.posZ) == Blocks.air)
					continue;

				e.setHealth(e.getHealth() - 10.0f);
				e.attackEntityFrom(DamageSource.causeMobDamage(this), 0.1f);

				if (!e.worldObj.isRemote)
					e.addChatMessage(StringUtil.getColourLocale("message.mob.tyrosaur.stomp", EnumChatFormatting.DARK_GREEN));
			}
		}
		else if (stomp == 40) {
			playSound("nevermine:TyrosaurStompReady", 4.0f, 1.0f);
		}
	}

	public void onDeath(DamageSource d) {
		super.onDeath(d);
		if (!worldObj.isRemote && d.getEntity() instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer)d.getEntity();
			IChatComponent msg = StringUtil.getLocaleWithArguments("message.mob.tyrosaur.kill", p.getDisplayName());

			for (EntityPlayer e : (List<EntityPlayer>)p.worldObj.getEntitiesWithinAABB(EntityPlayer.class, p.boundingBox.expand(50.0, 50.0, 50.0))) {
				e.addChatMessage(msg);
			}

			PlayerContainer cont = PlayerContainer.getProperties(p);

			if (cont.getLevel(Hunter) >= 30)
				cont.addExperience(3000, Hunter);
		}
	}
}

