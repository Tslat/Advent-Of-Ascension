package net.nevermine.boss.baroness;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.boss.EntityBoss;
import net.nevermine.container.PlayerContainer;
import net.nevermine.mob.placement.EntityNoExplosions;
import net.nevermine.projectiles.enemy.EntityBaronessShot;

import java.util.List;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityBaroness extends EntityMob implements EntityBoss, IRangedAttackMob, EntityNoExplosions {

	private int musicTick = 1;
	private int placetick = 150;

	public EntityBaroness(World par1World) {
		super(par1World);
		float moveSpeed = 0.35F;
		tasks.addTask(7, new EntityAIArrowAttack(this, getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(), 10, 64.0F));
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIRestrictSun(this));
		tasks.addTask(5, new EntityAIWander(this, moveSpeed));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(6, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		;
		setSize(1.1F, 2.7F);
	}

	@Override
	protected void dropFewItems(boolean par1, int par2) {
	}

	protected String getLivingSound() {
		return "nevermine:ArielLiving";
	}

	protected String getDeathSound() {
		return "nevermine:ArielDeath";
	}

	protected String getHurtSound() {
		return "nevermine:ArielHit";
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
		playSound("mob.pig.step", 1.00F, 1.0F);
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase var1, float f) {

		EntityBaronessShot var2 = new EntityBaronessShot(worldObj, this, 8.0F);
		double var3 = var1.posX - posX;
		double var5 = var1.posY + (double)var1.getEyeHeight() - 1.100000023841858D - var2.posY;
		double var7 = var1.posZ - posZ;
		float var9 = MathHelper.sqrt_double(var3 * var3 + var7 * var7) * 0.2F;
		var2.setThrowableHeading(var3, var5 + (double)var9, var7, 1.6F, 12.0F);

		playSound("nevermine:BaronessFire", 1.0F, 1.0F / (getRNG().nextFloat() * 0.4F + 0.8F));
		worldObj.spawnEntityInWorld(var2);
	}

	@Override
	public boolean isEntityInvulnerable() {
		return (getDataWatcher().getWatchableObjectInt(25) > 0);
	}

	public void setInv(int i) {
		getDataWatcher().updateObject(25, i);
	}

	protected void entityInit() {
		super.entityInit();
		getDataWatcher().addObject(25, 0);
		getDataWatcher().updateObject(25, 0);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		musicTick--;
		if (musicTick == 0) {
			musicTick = 290;
			playSound("nevermine:MusicBaroness", 2.80F, 1.0F);
		}

		if (rand.nextInt(15) == 7) {
			if (entityToAttack != null) {
				Entity var1 = entityToAttack;
				EntityBaronessShot var2 = new EntityBaronessShot(worldObj, this, 8.0F);
				double var3 = var1.posX - posX;
				double var5 = var1.posY + (double)var1.getEyeHeight() - 1.100000023841858D - var2.posY;
				double var7 = var1.posZ - posZ;
				float var9 = MathHelper.sqrt_double(var3 * var3 + var7 * var7) * 0.2F;
				var2.setThrowableHeading(var3, var5 + (double)var9, var7, 1.6F, 12.0F);

				playSound("nevermine:BaronessFire", 1.0F, 1.0F / (getRNG().nextFloat() * 0.4F + 0.8F));
				worldObj.spawnEntityInWorld(var2);
			}
		}

		getDataWatcher().updateObject(25, getDataWatcher().getWatchableObjectInt(25) - 1);

		placetick--;
		if (placetick == 1) {
			placetick = 70;
			if (entityToAttack != null) {
				addVelocity(Math.signum(entityToAttack.posX - posX) * 2.329, Math.signum((entityToAttack.posY + 1) - posY) * 0.929, Math.signum(entityToAttack.posZ - posZ) * 2.329);
			}
			if (!worldObj.isRemote) {
				EntityBaronBomb var2 = new EntityBaronBomb(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0F, 0.0F);
				worldObj.spawnEntityInWorld(var2);
				var2.playSound("nevermine:BaronBombSummon", 3.85F, 1.0F);
			}
		}

	}

	@Override
	protected void fall(float par1) {
	}

	@Override
	public void onDeath(DamageSource d) {
		super.onDeath(d);
		if (!worldObj.isRemote)
			if (d.getEntity() != null)
				if (d.getEntity() instanceof EntityPlayer) {
					EntityPlayer p = (EntityPlayer)d.getEntity();

					IChatComponent message = StringUtil.getLocaleWithArguments("message.mob.baroness.kill", p.getDisplayName());

					for (EntityPlayer e : (List<EntityPlayer>)p.worldObj.getEntitiesWithinAABB(EntityPlayer.class, p.boundingBox.expand(50, 50, 50)))
						e.addChatMessage(message);

					PlayerContainer cont = PlayerContainer.getProperties(p);

					if (cont.getLevel(Hunter) >= 30)
						cont.addExperience(3000, Hunter);
				}
	}

	protected Entity findPlayerToAttack() {
		EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
		return entityPlayer != null && canEntityBeSeen(entityPlayer) ? entityPlayer : null;

	}

	public int getInvulnTicks() {
		return dataWatcher.getWatchableObjectInt(25);
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		if (par1DamageSource.isExplosion()) {
			return false;
		}

		return super.attackEntityFrom(par1DamageSource, par2);

	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();

		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0D);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.35D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(2000.0D);
	}

}
