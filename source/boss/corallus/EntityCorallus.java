package net.nevermine.boss.corallus;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.boss.EntityBoss;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.izer.equipment.Armorizer;
import net.nevermine.izer.equipment.Slabizer;
import net.nevermine.izer.equipment.Weaponizer;

import java.util.List;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityCorallus extends EntityMob implements EntityBoss {
	private int jumpdelay;
	private int timebetweenshots;
	private int isshooting;
	private int shootofftimer;
	private int waitSwitchON;
	private int waitSwitchOFF;
	private int pullSwitchON;
	private int pullSwitchOFF;
	private int isPull;
	private int timesincewater;
	private double curheight;
	private int musictick;

	public EntityCorallus(final World par1World) {
		super(par1World);
		jumpdelay = 320;
		timebetweenshots = 7;
		isshooting = 0;
		shootofftimer = 0;
		waitSwitchON = 200;
		waitSwitchOFF = 0;
		pullSwitchON = 240;
		pullSwitchOFF = 0;
		isPull = 0;
		timesincewater = 10;
		musictick = 6;
		setSize(1.8f, 3.2f);
	}

	protected String getLivingSound() {
		return "nevermine:CorallusLiving";
	}

	protected String getDeathSound() {
		return "nevermine:CorallusDeath";
	}

	protected String getHurtSound() {
		return "nevermine:CorallusHit";
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Slabizer.CorbySlab, 10);
		dropItem(dropStatue(), 1);
		dropItem(Itemizer.RealmstoneBorean, 5);

		if (rand.nextBoolean()) {
			dropItem(Weaponizer.CoralStaff, 1);
		}

		final int helmet = rand.nextInt(3);

		if (helmet == 1) {
			dropItem(Armorizer.OceanusHelmet, 1);
		}
		else if (helmet == 2) {
			dropItem(Armorizer.SealordHelmet, 1);
		}
		else {
			dropItem(Armorizer.AchelosHelmet, 1);
		}

	}

	private Item dropStatue() {
		return Item.getItemFromBlock(SpecialBlockizer.CorallusStatue);
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("nevermine:HeavyStep", 1.35f, 1.0f);
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	protected void entityInit() {
		super.entityInit();
		getDataWatcher().addObject(25, 0);
		getDataWatcher().updateObject(25, 0);
	}

	public int isEnraged() {
		return dataWatcher.getWatchableObjectInt(25);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (musictick == 4) {
			playSound("nevermine:MusicCorallus", 3.0f, 1.0f);
			musictick = 320;
		}
		else {
			--musictick;
		}
		if (jumpdelay == 1) {
			curheight = posY;
			motionY = 1.600000023841858;
			playSound("nevermine:CorallusRoar", 1.35f, 1.0f);
			jumpdelay = 320;
			isshooting = 1;
			shootofftimer = 60;
		}
		else {
			--jumpdelay;
		}
		if (isshooting == 1) {
			if (timebetweenshots == 1 && !worldObj.isRemote) {
				final EntityCorallusShot var2 = new EntityCorallusShot(worldObj);
				final int placement = rand.nextInt(7);
				var2.setLocationAndAngles(posX, curheight - 3.0 + placement, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var2);
				timebetweenshots = 7;
			}
			else {
				--timebetweenshots;
			}
			--shootofftimer;
		}
		if (shootofftimer == 1) {
			isshooting = 0;
		}
		if (isEnraged() == 0) {
			--waitSwitchON;
		}
		else {
			--waitSwitchOFF;
		}
		if (waitSwitchON == 1) {
			if (!worldObj.isRemote) {
				getDataWatcher().updateObject(25, 1);
			}
			waitSwitchOFF = 80;
			waitSwitchON = 200;
			addPotionEffect(new PotionEffect(Potion.damageBoost.id, 80, 1));
		}
		if (waitSwitchOFF == 1) {
			if (!worldObj.isRemote) {
				getDataWatcher().updateObject(25, 0);
			}
			waitSwitchON = 200;
			waitSwitchOFF = 80;
		}
		if (isPull == 1) {
			--pullSwitchOFF;
			final EntityPlayer var3 = worldObj.getClosestVulnerablePlayerToEntity(this, 64.0);
			if (var3 == null || var3.getDistanceToEntity(this) > 64.0f) {
				return;
			}
			var3.addVelocity(Math.signum(posX - var3.posX) * 0.039, 0.0, Math.signum(posZ - var3.posZ) * 0.039);
			var3.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 20, 2));
		}
		if (pullSwitchON == 1) {
			pullSwitchON = 240;
			isPull = 1;
			pullSwitchOFF = 60;
		}
		if (pullSwitchOFF == 1) {
			pullSwitchON = 240;
			isPull = 0;
			pullSwitchOFF = 60;
		}
		if (isInWater()) {
			timesincewater = 10;
			if (motionX > -1.2000000476837158 && motionX < 1.2000000476837158) {
				motionX *= 1.2000000476837158;
			}
			if (motionZ > -1.2000000476837158 && motionZ < 1.2000000476837158) {
				motionZ *= 1.2000000476837158;
			}
		}
		else {
			--timesincewater;
		}
	}

	public boolean canBreatheUnderwater() {
		return true;
	}

	public void onEntityUpdate() {
		super.onEntityUpdate();
		setAir(300);
	}

	protected void fall(final float var1) {
	}

	public boolean attackEntityAsMob(final Entity par1) {
		if (super.attackEntityAsMob(par1)) {
			if (par1 instanceof EntityLivingBase) {
				((EntityLivingBase)par1).motionY = -1.7999999523162842;
			}
			if (timesincewater < 1) {
				((EntityLivingBase)par1).addPotionEffect(new PotionEffect(Potion.weakness.id, 100, 15));
				((EntityLivingBase)par1).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 50, 3));
			}
			return true;
		}
		return false;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(25.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1800.0);
	}

	public void onDeath(final DamageSource d) {
		super.onDeath(d);
		if (!worldObj.isRemote && d.getEntity() != null && d.getEntity() instanceof EntityPlayer) {
			final EntityPlayer p = (EntityPlayer)d.getEntity();

			IChatComponent msg = StringUtil.getLocaleWithArguments("message.mob.corallus.kill", p.getDisplayName());

			for (final EntityPlayer e : (List<EntityPlayer>)p.worldObj.getEntitiesWithinAABB(EntityPlayer.class, p.boundingBox.expand(50.0, 50.0, 50.0))) {
				e.addChatMessage(msg);
			}

			PlayerContainer cont = PlayerContainer.getProperties(p);

			if (cont.getLevel(Hunter) >= 30)
				cont.addExperience(7500, Hunter);
		}
	}
}
