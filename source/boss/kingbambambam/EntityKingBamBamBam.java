package net.nevermine.boss.kingbambambam;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
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
import net.nevermine.izer.equipment.Armorizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.projectiles.enemy.EntityBamShot;

import java.util.List;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityKingBamBamBam extends EntityMob implements IRangedAttackMob, EntityBoss {
	private EntityAIArrowAttack aiArrowAttack;
	private int ticktimer;
	private int musictick;

	public EntityKingBamBamBam(final World par1World) {
		super(par1World);
		aiArrowAttack = new EntityAIArrowAttack(this, getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(), 20, 60, 15.0f);
		ticktimer = 60;
		musictick = 6;
		final float moveSpeed = 0.45f;
		tasks.addTask(7, new EntityAIArrowAttack(this, getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(), 20, 64.0f));
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIRestrictSun(this));
		tasks.addTask(5, new EntityAIWander(this, (double)moveSpeed));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		tasks.addTask(6, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		setSize(1.2f, 2.5f);
	}

	protected String getLivingSound() {
		switch (rand.nextInt(2)) {
			case 0: {
				return "nevermine:KingBamBamBamLiving1";
			}
			case 1: {
				return "nevermine:KingBamBamBamLiving2";
			}
			default: {
				return "nevermine:KingBamBamBamLiving1";
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

		if (rand.nextInt(3) == 2) {
			dropItem(Weaponizer.FireborneSword, 1);
		}

		if (rand.nextInt(3) == 1) {
			dropItem(Weaponizer.ExplosiveBow, 1);
		}

		final int armorpiece = rand.nextInt(4);

		if (armorpiece == 1) {
			dropItem(Armorizer.ExplosiveBoots, 1);
		}
		else if (armorpiece == 2) {
			dropItem(Armorizer.ExplosiveLeggings, 1);
		}
		else if (armorpiece == 3) {
			dropItem(Armorizer.ExplosiveChestplate, 1);
		}
		else {
			dropItem(Armorizer.ExplosiveHelmet, 1);
		}
	}

	private Item dropStatue() {
		return Item.getItemFromBlock(SpecialBlockizer.KingBamBamBamStatue);
	}

	protected String getDeathSound() {
		return "nevermine:KingBamBamBamDeath";
	}

	protected String getHurtSound() {
		return "nevermine:KingBamBamBamHit";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("nevermine:HeavyStep", 0.85f, 1.0f);
	}

	public void onLivingUpdate() {
		if (musictick == 4) {
			playSound("nevermine:MusicKingBamBamBam", 3.0f, 1.0f);
			musictick = 300;
		}
		else {
			--musictick;
		}

		if (ticktimer > 2) {
			--ticktimer;
		}
		else if (ticktimer == 2 && !worldObj.isRemote) {
			final EntityLittleBam var2 = new EntityLittleBam(worldObj);
			var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
			worldObj.spawnEntityInWorld(var2);
			playSound("nevermine:KingBamBamBamCreate", 0.85f, 1.0f);
			ticktimer = 60;
		}

		super.onLivingUpdate();
	}

	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(16, new Integer(5));
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(20.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(900.0);
	}

	public boolean isAIEnabled() {
		return true;
	}

	public void attackEntityWithRangedAttack(final EntityLivingBase var1, final float f) {
		final EntityBamShot var2 = new EntityBamShot(worldObj, this, 20.0f);
		final double var3 = var1.posX - posX;
		final double var4 = var1.posY + var1.getEyeHeight() - 1.100000023841858 - var2.posY;
		final double var5 = var1.posZ - posZ;
		final float var6 = MathHelper.sqrt_double(var3 * var3 + var5 * var5) * 0.2f;
		var2.setThrowableHeading(var3, var4 + var6, var5, 1.6f, 12.0f);
		playSound("nevermine:Wizard", 1.0f, 1.0f / (getRNG().nextFloat() * 0.4f + 0.8f));
		worldObj.spawnEntityInWorld(var2);
	}

	public void onDeath(final DamageSource d) {
		if (!worldObj.isRemote && d.getEntity() instanceof EntityPlayer) {
			final EntityPlayer p = (EntityPlayer)d.getEntity();

			IChatComponent msg = StringUtil.getLocaleWithArguments("message.mob.kingBamBamBam.kill", p.getDisplayName());

			for (final EntityPlayer e : (List<EntityPlayer>)p.worldObj.getEntitiesWithinAABB(EntityPlayer.class, p.boundingBox.expand(50.0, 50.0, 50.0))) {
				e.addChatMessage(msg);
			}

			PlayerContainer cont = PlayerContainer.getProperties((EntityPlayer)d.getEntity());

			if (cont.getLevel(Hunter) >= 30)
				cont.addExperience(3000, Hunter);
		}

		super.onDeath(d);
	}
}
