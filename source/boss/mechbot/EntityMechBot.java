package net.nevermine.boss.mechbot;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.boss.EntityBoss;
import net.nevermine.container.PlayerContainer;
import net.nevermine.projectiles.enemy.EntityMechFall;
import net.nevermine.projectiles.enemy.EntityMechShot;

import java.util.List;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityMechBot extends EntityMob implements EntityBoss {
	private int musictick = 6;
	private boolean jumpseq;
	private int jumpcount = 0;
	private int jumpreset = 100;
	private int jumpdelay = 10;

	public EntityMechBot(World par1World) {
		super(par1World);
		setSize(1.3f, 2.2f);
	}

	protected String getLivingSound() {
		return "nevermine:MechyonLiving";
	}

	protected String getDeathSound() {
		return "nevermine:MechyonDeath";
	}

	protected String getHurtSound() {
		return "nevermine:MechyonHit";
	}

	protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
		playSound("nevermine:HeavyStep", 0.85f, 1.0f);
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
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(2500.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		if (par1DamageSource.isExplosion()) {
			return false;
		}
		return super.attackEntityFrom(par1DamageSource, par2);
	}

	protected void fall(float par1) {
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (musictick == 4) {
			playSound("nevermine:MusicMechBot", 3.0f, 1.0f);
			musictick = 340;
		}
		else {
			--musictick;
		}
		if (jumpreset == 1) {
			jumpreset = 200;
			jumpseq = true;
			jumpcount = 0;
		}
		if (jumpseq) {
			if (jumpdelay == 1 && jumpcount < 4) {
				motionY = 0.699999988079071;
				if (entityToAttack != null) {
					motionZ = (entityToAttack.posZ - posZ) * 0.04500000178813934;
					motionX = (entityToAttack.posX - posX) * 0.04500000178813934;
				}
				++jumpcount;
				jumpdelay = 15;
				playSound("nevermine:MechBotJump", 1.85f, 1.0f);
				if (!worldObj.isRemote) {
					worldObj.spawnEntityInWorld(new EntityMechFall(worldObj, posX, posY - 0.25, posZ, this));
					if (getHealth() < 1250.0f) {
						worldObj.spawnEntityInWorld(new EntityMechFall(worldObj, posX - 2.25, posY - 0.25, posZ - 2.25, this));
						worldObj.spawnEntityInWorld(new EntityMechFall(worldObj, posX - 2.25, posY - 0.25, posZ + 2.25, this));
						worldObj.spawnEntityInWorld(new EntityMechFall(worldObj, posX + 2.25, posY - 0.25, posZ, this));
						worldObj.spawnEntityInWorld(new EntityMechFall(worldObj, posX + 2.25, posY - 0.25, posZ + 2.25, this));
						worldObj.spawnEntityInWorld(new EntityMechFall(worldObj, posX, posY - 2.25, posZ - 2.25, this));
						worldObj.spawnEntityInWorld(new EntityMechFall(worldObj, posX, posY - 2.25, posZ + 2.25, this));
					}
				}
			}
			--jumpdelay;
			if (jumpcount == 4) {
				jumpseq = false;
			}
		}
		if (!jumpseq) {
			--jumpreset;
		}
		if (getHealth() < 1000.0f) {
			if (rand.nextInt(10) == 7 && entityToAttack != null) {
				Entity var1 = entityToAttack;
				EntityMechShot var2 = new EntityMechShot(worldObj, this, 5.0f);
				double var3 = var1.posX - posX;
				double var5 = var1.posY + (double)var1.getEyeHeight() - 1.100000023841858 - var2.posY;
				double var7 = var1.posZ - posZ;
				float var9 = MathHelper.sqrt_double((var3 * var3 + var7 * var7)) * 0.2f;
				var2.setThrowableHeading(var3, var5 + (double)var9, var7, 1.6f, 12.0f);
				playSound("nevermine:MechBotFire", 1.0f, 1.0f / (getRNG().nextFloat() * 0.4f + 0.8f));
				worldObj.spawnEntityInWorld(var2);
			}
		}
		else if (rand.nextInt(40) == 37 && entityToAttack != null) {
			Entity var1 = entityToAttack;
			EntityMechShot var2 = new EntityMechShot(worldObj, this, 5.0f);
			double var3 = var1.posX - posX;
			double var5 = var1.posY + (double)var1.getEyeHeight() - 1.100000023841858 - var2.posY;
			double var7 = var1.posZ - posZ;
			float var9 = MathHelper.sqrt_double((double)(var3 * var3 + var7 * var7)) * 0.2f;
			var2.setThrowableHeading(var3, var5 + (double)var9, var7, 1.6f, 12.0f);
			playSound("nevermine:MechBotFire", 1.0f, 1.0f / (getRNG().nextFloat() * 0.4f + 0.8f));
			worldObj.spawnEntityInWorld(var2);
		}
	}

	public void onDeath(DamageSource d) {
		super.onDeath(d);
		if (!worldObj.isRemote && d.getEntity() != null && d.getEntity() instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer)d.getEntity();

			IChatComponent msg = StringUtil.getLocaleWithArguments("message.mob.mechbot.kill", p.getDisplayName());

			for (EntityPlayer e : (List<EntityPlayer>)p.worldObj.getEntitiesWithinAABB(EntityPlayer.class, p.boundingBox.expand(50.0, 50.0, 50.0))) {
				e.addChatMessage(msg);
			}

			PlayerContainer cont = PlayerContainer.getProperties(p);

			if (cont.getLevel(Hunter) >= 30)
				cont.addExperience(3000, Hunter);
		}
	}
}

