package net.nevermine.boss.kingshroomus;

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
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.projectiles.enemy.EntityFungiShot;

import java.util.List;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityKingShroomus extends EntityMob implements IRangedAttackMob, EntityBoss {
	private EntityAIArrowAttack aiArrowAttack;
	private int musicTick;
	private int healcount;
	private boolean isHeal;

	public EntityKingShroomus(final World par1World) {
		super(par1World);
		aiArrowAttack = new EntityAIArrowAttack(this, getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(), 10, 40, 15.0f);
		musicTick = 1;
		healcount = 80;
		isHeal = false;
		final float moveSpeed = 0.45f;
		tasks.addTask(7, new EntityAIArrowAttack(this, getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(), 10, 40.0f));
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIRestrictSun(this));
		tasks.addTask(5, new EntityAIWander(this, (double)moveSpeed));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		tasks.addTask(6, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		setSize(2.4f, 3.8f);
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		if (par1DamageSource == DamageSource.inWall) {
			BossClear.clear((int)posX, (int)posY, (int)posZ, worldObj, this);
		}
		return super.attackEntityFrom(par1DamageSource, par2);
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("nevermine:HeavyStep", 0.85f, 1.0f);
	}

	public void onDeath(final DamageSource d) {
		super.onDeath(d);
		if (!worldObj.isRemote && d.getEntity() != null && d.getEntity() instanceof EntityPlayer) {
			final EntityPlayer p = (EntityPlayer)d.getEntity();

			IChatComponent msg = StringUtil.getLocaleWithArguments("message.mob.kingShroomus.kill", p.getDisplayName());

			for (final EntityPlayer e : (List<EntityPlayer>)p.worldObj.getEntitiesWithinAABB(EntityPlayer.class, p.boundingBox.expand(50.0, 50.0, 50.0))) {
				e.addChatMessage(msg);
			}

			PlayerContainer cont = PlayerContainer.getProperties(p);

			if (cont.getLevel(Hunter) >= 30)
				cont.addExperience(1000, Hunter);
		}
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Item.getItemFromBlock(SpecialBlockizer.KingShroomusStatue), 1);

		final int pick = rand.nextInt(4);

		if (pick == 1) {
			dropItem(Weaponizer.ShroomusSword, 1);
		}
		else if (pick == 2) {
			dropItem(Weaponizer.Demolisher, 1);
		}
		else if (pick == 3) {
			dropItem(Weaponizer.Miasma, 1);
		}
		else {
			dropItem(Weaponizer.FungalStaff, 1);
		}
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (rand.nextInt(200) == 25 && !isHeal) {
			playSound("nevermine:ShroomusHeal", 2.2f, 1.0f);
			isHeal = true;
			healcount = 80;
		}

		if (isHeal) {
			--healcount;
			heal(2.0f);
			if (healcount == 1) {
				isHeal = false;
			}
		}

		--musicTick;

		if (musicTick == 0) {
			musicTick = 250;
			playSound("nevermine:MusicKingShroomus", 2.8f, 1.0f);
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
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1800.0);
	}

	public boolean isAIEnabled() {
		return true;
	}

	protected String getLivingSound() {
		return "nevermine:FungiLiving";
	}

	protected String getDeathSound() {
		return "nevermine:KingShroomusDeath";
	}

	protected String getHurtSound() {
		return "nevermine:FungiHit";
	}

	public void attackEntityWithRangedAttack(final EntityLivingBase var1, final float f) {
		final int effect = rand.nextInt(4) + 1;
		final EntityFungiShot var2 = new EntityFungiShot(worldObj, this, 20.0f, effect);
		final double var3 = var1.posX - posX;
		final double var4 = var1.posY + var1.getEyeHeight() - 1.100000023841858 - var2.posY;
		final double var5 = var1.posZ - posZ;
		final float var6 = MathHelper.sqrt_double(var3 * var3 + var5 * var5) * 0.2f;
		var2.setThrowableHeading(var3, var4 + var6, var5, 1.6f, 12.0f);
		playSound("nevermine:Wizard", 1.0f, 1.0f / (getRNG().nextFloat() * 0.4f + 0.8f));
		worldObj.spawnEntityInWorld(var2);
	}
}
