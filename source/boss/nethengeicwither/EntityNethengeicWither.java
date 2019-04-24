package net.nevermine.boss.nethengeicwither;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
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
import net.nevermine.projectiles.enemy.EntityNethengeicWitherShot;

import java.util.List;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityNethengeicWither extends EntityMob implements IRangedAttackMob, EntityBoss {
	private float[] field_82220_d;
	private float[] field_82221_e;
	private float[] field_82217_f;
	private float[] field_82218_g;
	private int[] field_82223_h;
	private int[] field_82224_i;
	private int field_82222_j;
	private int updatecount;
	private int musictick;
	private static final IEntitySelector attackEntitySelector;
	private static final String __OBFID = "CL_00001661";

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(dropStatue(), 1);

		final int wep = rand.nextInt(4);

		if (wep == 1) {
			dropItem(Weaponizer.DischargeCannon, 1);
		}
		else if (wep == 2) {
			dropItem(Weaponizer.WitherCannon, 1);
		}
		else if (wep == 3) {
			dropItem(Weaponizer.WithersWrath, 1);
		}
		else {
			dropItem(Weaponizer.ChainWrecker, 1);
		}
	}

	private Item dropStatue() {
		return Item.getItemFromBlock(SpecialBlockizer.NethengeicWitherStatue);
	}

	public EntityNethengeicWither(final World par1World) {
		super(par1World);
		field_82220_d = new float[2];
		field_82221_e = new float[2];
		field_82217_f = new float[2];
		field_82218_g = new float[2];
		field_82223_h = new int[2];
		field_82224_i = new int[2];
		updatecount = 45;
		musictick = 6;
		setHealth(getMaxHealth());
		setSize(1.3f, 3.0f);
		isImmuneToFire = true;
		getNavigator().setCanSwim(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIArrowAttack(this, 0.34, 1, 20.0f));
		tasks.addTask(5, new EntityAIWander(this, 0.34));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		tasks.addTask(7, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, false, EntityNethengeicWither.attackEntitySelector));
		experienceValue = 50;
	}

	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(17, new Integer(0));
		dataWatcher.addObject(18, new Integer(0));
		dataWatcher.addObject(19, new Integer(0));
		dataWatcher.addObject(20, new Integer(0));
	}

	public void writeEntityToNBT(final NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("Invul", func_82212_n());
	}

	public void readEntityFromNBT(final NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);
		func_82215_s(par1NBTTagCompound.getInteger("Invul"));
	}

	@SideOnly(Side.CLIENT)
	public float getShadowSize() {
		return height / 8.0f;
	}

	public int getform() {
		final float current = getHealth();
		if (current > 550.0f) {
			return 1;
		}
		if (current > 220.0f) {
			return 2;
		}
		return 3;
	}

	public int getAtkSpeed() {
		final int form = getform();
		if (form == 1) {
			return 45;
		}
		if (form == 2) {
			return 20;
		}
		return 7;
	}

	protected String getLivingSound() {
		switch (rand.nextInt(3)) {
			case 0: {
				return "nevermine:NethengeicWitherLiving1";
			}
			case 1: {
				return "nevermine:NethengeicWitherLiving2";
			}
			case 2: {
				return "nevermine:NethengeicWitherLiving3";
			}
			default: {
				return "nevermine:NethengeicWitherLiving1";
			}
		}
	}

	protected String getHurtSound() {
		return "nevermine:NethengeicWitherHit";
	}

	protected String getDeathSound() {
		return "nevermine:NethengeicWitherDeath";
	}

	public void onLivingUpdate() {
		if (musictick == 4) {
			playSound("nevermine:MusicNethengeicWither", 3.0f, 1.0f);
			musictick = 380;
		}
		else {
			--musictick;
		}
		final int curform = getform();
		if (curform == 2 || curform == 3) {
			final float currenthp = getHealth();
			if (currenthp > 1.0f) {
				heal(0.3f);
			}
		}
		motionY *= 0.2000000238418579;
		if (!worldObj.isRemote && getWatchedTargetId(0) > 0) {
			final Entity entity = worldObj.getEntityByID(getWatchedTargetId(0));
			if (entity != null) {
				if (posY < entity.posY || (!isArmored() && posY < entity.posY + 5.0)) {
					if (motionY < 0.0) {
						motionY = 0.0;
					}
					motionY += (0.5 - motionY) * 0.2000000238418579;
				}
				final double d0 = entity.posX - posX;
				final double d2 = entity.posZ - posZ;
				final double d3 = d0 * d0 + d2 * d2;
				if (d3 > 9.0) {
					final double d4 = MathHelper.sqrt_double(d3);
					motionX += (d0 / d4 * 0.5 - motionX) * 0.2000000238418579;
					motionZ += (d2 / d4 * 0.5 - motionZ) * 0.2000000238418579;
				}
			}
		}
		if (motionX * motionX + motionZ * motionZ > 0.02000000074505806) {
			rotationYaw = (float)Math.atan2(motionZ, motionX) * 57.295776f - 90.0f;
		}
		super.onLivingUpdate();
		for (int i = 0; i < 2; ++i) {
			field_82218_g[i] = field_82221_e[i];
			field_82217_f[i] = field_82220_d[i];
		}
		for (int i = 0; i < 2; ++i) {
			final int j = getWatchedTargetId(i + 1);
			Entity entity2 = null;
			if (j > 0) {
				entity2 = worldObj.getEntityByID(j);
			}
			if (entity2 != null) {
				final double d2 = func_82214_u(i + 1);
				final double d3 = func_82208_v(i + 1);
				final double d4 = func_82213_w(i + 1);
				final double d5 = entity2.posX - d2;
				final double d6 = entity2.posY + entity2.getEyeHeight() - d3;
				final double d7 = entity2.posZ - d4;
				final double d8 = MathHelper.sqrt_double(d5 * d5 + d7 * d7);
				final float f = (float)(Math.atan2(d7, d5) * 180.0 / 3.141592653589793) - 90.0f;
				final float f2 = (float)(-(Math.atan2(d6, d8) * 180.0 / 3.141592653589793));
				field_82220_d[i] = func_82204_b(field_82220_d[i], f2, 40.0f);
				field_82221_e[i] = func_82204_b(field_82221_e[i], f, 10.0f);
			}
			else {
				field_82221_e[i] = func_82204_b(field_82221_e[i], renderYawOffset, 10.0f);
			}
		}
		final boolean flag = isArmored();
		for (int j = 0; j < 3; ++j) {
			final double d9 = func_82214_u(j);
			final double d10 = func_82208_v(j);
			final double d11 = func_82213_w(j);
			if (worldObj.isRemote) {
				worldObj.spawnParticle("smoke", d9 + rand.nextGaussian() * 0.30000001192092896, d10 + rand.nextGaussian() * 0.30000001192092896, d11 + rand.nextGaussian() * 0.30000001192092896, 0.0, 0.0, 0.0);
			}
			if (flag && worldObj.rand.nextInt(4) == 0 && worldObj.isRemote) {
				worldObj.spawnParticle("mobSpell", d9 + rand.nextGaussian() * 0.30000001192092896, d10 + rand.nextGaussian() * 0.30000001192092896, d11 + rand.nextGaussian() * 0.30000001192092896, 0.699999988079071, 0.699999988079071, 0.5);
			}
		}
		if (func_82212_n() > 0) {
			for (int j = 0; j < 3; ++j) {
				if (worldObj.isRemote) {
					worldObj.spawnParticle("mobSpell", posX + rand.nextGaussian() * 1.0, posY + rand.nextFloat() * 3.3f, posZ + rand.nextGaussian() * 1.0, 0.699999988079071, 0.699999988079071, 0.8999999761581421);
				}
			}
		}
	}

	protected void updateAITasks() {
		if (func_82212_n() > 0) {
			final int i = func_82212_n() - 1;
			if (i <= 0) {
				worldObj.newExplosion(this, posX, posY + getEyeHeight(), posZ, 7.0f, false, worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
				worldObj.playBroadcastSound(1013, (int)posX, (int)posY, (int)posZ, 0);
			}
			func_82215_s(i);
		}
		else {
			super.updateAITasks();
			for (int i = 1; i < 3; ++i) {
				if (ticksExisted >= field_82223_h[i - 1]) {
					field_82223_h[i - 1] = ticksExisted + 10 + rand.nextInt(10);
					if (worldObj.difficultySetting == EnumDifficulty.NORMAL || worldObj.difficultySetting == EnumDifficulty.HARD) {
						final int k2 = i - 1;
						final int l2 = field_82224_i[i - 1];
						field_82224_i[k2] = field_82224_i[i - 1] + 1;
						if (l2 > 15) {
							final float f = 10.0f;
							final float f2 = 5.0f;
							final double d0 = MathHelper.getRandomDoubleInRange(rand, posX - f, posX + f);
							final double d2 = MathHelper.getRandomDoubleInRange(rand, posY - f2, posY + f2);
							final double d3 = MathHelper.getRandomDoubleInRange(rand, posZ - f, posZ + f);
							func_82209_a(i + 1, d0, d2, d3, true);
							field_82224_i[i - 1] = 0;
						}
					}
					final int i2 = getWatchedTargetId(i);
					if (i2 > 0) {
						final Entity entity = worldObj.getEntityByID(i2);
						if (entity != null && entity.isEntityAlive() && getDistanceSqToEntity(entity) <= 900.0 && canEntityBeSeen(entity)) {
							func_82216_a(i + 1, (EntityLivingBase)entity);
							field_82223_h[i - 1] = ticksExisted + 40 + rand.nextInt(20);
							field_82224_i[i - 1] = 0;
						}
						else {
							func_82211_c(i, 0);
						}
					}
					else {
						final List<EntityLivingBase> list = worldObj.selectEntitiesWithinAABB(EntityLivingBase.class, boundingBox.expand(20.0, 8.0, 20.0), EntityNethengeicWither.attackEntitySelector);
						int k3 = 0;
						while (k3 < 10 && !list.isEmpty()) {
							final EntityLivingBase entitylivingbase = list.get(rand.nextInt(list.size()));
							if (entitylivingbase != this && entitylivingbase.isEntityAlive() && canEntityBeSeen(entitylivingbase)) {
								if (!(entitylivingbase instanceof EntityPlayer)) {
									func_82211_c(i, entitylivingbase.getEntityId());
									break;
								}
								if (!((EntityPlayer)entitylivingbase).capabilities.disableDamage) {
									func_82211_c(i, entitylivingbase.getEntityId());
									break;
								}
								break;
							}
							else {
								list.remove(entitylivingbase);
								++k3;
							}
						}
					}
				}
			}
			if (getAttackTarget() != null) {
				func_82211_c(0, getAttackTarget().getEntityId());
			}
			else {
				func_82211_c(0, 0);
			}
			if (field_82222_j > 0) {
				--field_82222_j;
				if (field_82222_j == 0 && worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
					final int i = MathHelper.floor_double(posY);
					final int i2 = MathHelper.floor_double(posX);
					final int j1 = MathHelper.floor_double(posZ);
					final boolean flag = false;
				}
			}
		}
	}

	public int getTotalArmorValue() {
		return 4;
	}

	private double func_82214_u(final int par1) {
		if (par1 <= 0) {
			return posX;
		}
		final float f = (renderYawOffset + 180 * (par1 - 1)) / 180.0f * 3.1415927f;
		final float f2 = MathHelper.cos(f);
		return posX + f2 * 1.3;
	}

	private double func_82208_v(final int par1) {
		return (par1 <= 0) ? (posY + 3.0) : (posY + 2.2);
	}

	private double func_82213_w(final int par1) {
		if (par1 <= 0) {
			return posZ;
		}
		final float f = (renderYawOffset + 180 * (par1 - 1)) / 180.0f * 3.1415927f;
		final float f2 = MathHelper.sin(f);
		return posZ + f2 * 1.3;
	}

	private float func_82204_b(final float par1, final float par2, final float par3) {
		float f3 = MathHelper.wrapAngleTo180_float(par2 - par1);
		if (f3 > par3) {
			f3 = par3;
		}
		if (f3 < -par3) {
			f3 = -par3;
		}
		return par1 + f3;
	}

	private void func_82216_a(final int par1, final EntityLivingBase par2EntityLivingBase) {
		func_82209_a(par1, par2EntityLivingBase.posX, par2EntityLivingBase.posY + par2EntityLivingBase.getEyeHeight() * 0.5, par2EntityLivingBase.posZ, par1 == 0 && rand.nextFloat() < 0.001f);
	}

	private void func_82209_a(final int par1, final double par2, final double par4, final double par6, final boolean par8) {
		final double d3 = func_82214_u(par1);
		final double d4 = func_82208_v(par1);
		final double d5 = func_82213_w(par1);
		final double d6 = par2 - d3;
		final double d7 = par4 - d4;
		final double d8 = par6 - d5;
		if (updatecount == 1) {
			final EntityNethengeicWitherShot entitywitherskull = new EntityNethengeicWitherShot(worldObj, this, d6, d7, d8, getform());
			if (par8) {
				entitywitherskull.setInvulnerable(true);
			}
			entitywitherskull.posY = d4 - 1;
			entitywitherskull.posX = d3;
			entitywitherskull.posZ = d5;
			worldObj.spawnEntityInWorld(entitywitherskull);
			updatecount = getAtkSpeed();
		}
		else if (updatecount > 1) {
			--updatecount;
		}
	}

	public void attackEntityWithRangedAttack(final EntityLivingBase par1EntityLivingBase, final float par2) {
		func_82216_a(0, par1EntityLivingBase);
	}

	protected void despawnEntity() {
		entityAge = 0;
	}

	@SideOnly(Side.CLIENT)
	public int getBrightnessForRender(final float par1) {
		return 15728880;
	}

	protected void fall(final float par1) {
	}

	public void addPotionEffect(final PotionEffect par1PotionEffect) {
	}

	protected boolean isAIEnabled() {
		return true;
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1100.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.4500000238418579);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(100.0);
	}

	@SideOnly(Side.CLIENT)
	public float func_82207_a(final int par1) {
		return field_82221_e[par1];
	}

	@SideOnly(Side.CLIENT)
	public float func_82210_r(final int par1) {
		return field_82220_d[par1];
	}

	public int func_82212_n() {
		return dataWatcher.getWatchableObjectInt(20);
	}

	public void func_82215_s(final int par1) {
		dataWatcher.updateObject(20, par1);
	}

	public int getWatchedTargetId(final int par1) {
		return dataWatcher.getWatchableObjectInt(17 + par1);
	}

	public void func_82211_c(final int par1, final int par2) {
		dataWatcher.updateObject(17 + par1, par2);
	}

	public boolean isArmored() {
		return getHealth() <= getMaxHealth() / 2.0f;
	}

	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}

	public void mountEntity(final Entity par1Entity) {
		ridingEntity = null;
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		if (par1DamageSource == DamageSource.inWall) {
			BossClear.clear((int)posX, (int)posY, (int)posZ, worldObj, this);
		}
		final Entity entity = par1DamageSource.getSourceOfDamage();
		return !par1DamageSource.isExplosion() && super.attackEntityFrom(par1DamageSource, par2);
	}

	public void onDeath(final DamageSource d) {
		if (!worldObj.isRemote && d.getEntity() != null && d.getEntity() instanceof EntityPlayer) {
			final EntityPlayer p = (EntityPlayer)d.getEntity();

			IChatComponent msg = StringUtil.getLocaleWithArguments("message.mob.nethengeicWither.kill", p.getDisplayName());

			for (final EntityPlayer e : (List<EntityPlayer>)p.worldObj.getEntitiesWithinAABB(EntityPlayer.class, p.boundingBox.expand(50.0, 50.0, 50.0))) {
				e.addChatMessage(msg);
			}

			PlayerContainer cont = PlayerContainer.getProperties(p);

			if (cont.getLevel(Hunter) >= 30)
				cont.addExperience(10000, Hunter);
		}
		super.onDeath(d);
	}

	static {
		attackEntitySelector = new IEntitySelector() {
			private static final String __OBFID = "CL_00001662";

			public boolean isEntityApplicable(final Entity par1Entity) {
				return par1Entity instanceof EntityLivingBase && ((EntityLivingBase)par1Entity).getCreatureAttribute() != EnumCreatureAttribute.UNDEAD;
			}
		};
	}
}
