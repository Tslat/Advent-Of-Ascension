package net.nevermine.boss.graw;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.boss.EntityBoss;
import net.nevermine.container.PlayerContainer;
import net.nevermine.event.creature.BossClear;
import net.nevermine.izer.Blockizer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.mob.placement.EntityNoRange;
import net.nevermine.projectiles.enemy.EntityGrawShot;

import java.util.List;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityGraw extends EntityFlying implements IMob, EntityNoRange, EntityBoss {
	public int courseChangeCooldown;
	public double waypointX;
	public double waypointY;
	public double waypointZ;
	private Entity targetedEntity;
	private int pullcounter;
	private boolean isPull;
	private int musicTick;
	private int aggroCooldown;
	public int prevAttackCounter;
	public int attackCounter;
	private int explosionStrength;
	private static final String __OBFID = "CL_00001689";

	public EntityGraw(final World par1World) {
		super(par1World);
		pullcounter = 0;
		isPull = false;
		musicTick = 1;
		explosionStrength = 3;
		setSize(5.0f, 3.5f);
		isImmuneToFire = false;
		experienceValue = 5;
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		if (par1DamageSource == DamageSource.inWall) {
			BossClear.clear((int)posX, (int)posY, (int)posZ, worldObj, this);
		}
		return super.attackEntityFrom(par1DamageSource, par2);
	}

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);

		if (!worldObj.isRemote && var1.getEntity() instanceof EntityPlayer) {
			final EntityPlayer p = (EntityPlayer)var1.getEntity();

			IChatComponent msg = StringUtil.getLocaleWithArguments("message.mob.graw.kill", p.getDisplayName());

			for (final EntityPlayer e : (List<EntityPlayer>)p.worldObj.getEntitiesWithinAABB(EntityPlayer.class, p.boundingBox.expand(50.0, 50.0, 50.0))) {
				e.addChatMessage(msg);
			}

			PlayerContainer cont = PlayerContainer.getProperties(p);

			if (cont.getLevel(Hunter) >= 30)
				cont.addExperience(5000.0f, Hunter);
		}
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Item.getItemFromBlock(SpecialBlockizer.GrawStatue), 1);

		final int pick = rand.nextInt(4);

		if (pick == 1) {
			dropItem(Weaponizer.Wrecker, 1);
		}
		else if (pick == 2) {
			dropItem(Weaponizer.ChiliChugger, 1);
		}
		else if (pick == 3) {
			dropItem(Weaponizer.FireflyStaff, 1);
		}
		else {
			dropItem(Weaponizer.PulseCannon, 1);
		}
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (worldObj.difficultySetting == EnumDifficulty.PEACEFUL) {
			despawnEntity();
		}

		--musicTick;

		if (musicTick == 0) {
			musicTick = 195;
			playSound("nevermine:MusicGraw", 3.0f, 1.0f);
		}

		if (ticksExisted % 17 == 0) {
			for (int i = (int)posX - 7; i < (int)(posX + 7.0); ++i) {
				for (int j = (int)posY - 2; j < (int)(posY + 5.0); ++j) {
					for (int k = (int)posZ - 7; k < (int)(posZ + 7.0); ++k) {
						if (!worldObj.isRemote) {
							final Block b = worldObj.getBlock(i, j, k);
							if (b == Blockizer.WoodAchony || b == Blockizer.WoodChurry || b == Blockizer.LelyetianCore || b == Blockizer.LeavesAchony || b == Blockizer.LeavesChurry) {
								worldObj.setBlock(i, j, k, Blocks.air);
							}
						}
					}
				}
			}
		}
		if (posY > 110.0) {
			motionY -= 4.5;
			motionX += -1.5f + rand.nextInt(3);
			motionZ += -1.5f + rand.nextInt(3);
		}
		if (pullcounter == 0) {
			if (isPull) {
				isPull = false;
				pullcounter = 340;
			}
			else {
				isPull = true;
				pullcounter = 60;
			}
		}
		if (!isPull) {
			--pullcounter;
			for (final EntityPlayer e : (List<EntityPlayer>)worldObj.getEntitiesWithinAABB(EntityPlayer.class, boundingBox.expand(85.0, 85.0, 85.0))) {
				if (e.capabilities.isCreativeMode)
					continue;

				e.addVelocity(Math.signum(posX - e.posX) * 0.008, Math.signum(posY - e.posY) * 0.005, Math.signum(posZ - e.posZ) * 0.008);
			}
		}
		else {
			--pullcounter;
			for (final EntityPlayer e : (List<EntityPlayer>)worldObj.getEntitiesWithinAABB(EntityPlayer.class, boundingBox.expand(85.0, 85.0, 85.0))) {
				if (e.capabilities.isCreativeMode)
					continue;

				e.addVelocity(Math.signum(posX - e.posX) * 0.139, Math.signum(posY - e.posY) * 0.04, Math.signum(posZ - e.posZ) * 0.139);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public boolean func_110182_bF() {
		return dataWatcher.getWatchableObjectByte(16) != 0;
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox) && rand.nextInt(4) == 2;
	}

	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(16, (byte)0);
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(2500.0);
	}

	protected void updateEntityActionState() {
		if (!this.worldObj.isRemote && this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL) {
			setDead();
		}

		if (!worldObj.isRemote && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox)) {
			setDead();
		}
		despawnEntity();
		prevAttackCounter = attackCounter;
		final double d0 = waypointX - posX;
		final double d2 = waypointY - posY;
		final double d3 = waypointZ - posZ;
		double d4 = d0 * d0 + d2 * d2 + d3 * d3;
		if (d4 < 1.0 || d4 > 3600.0) {
			waypointX = posX + (rand.nextFloat() * 2.0f - 1.0f) * 16.0f;
			waypointY = posY + (rand.nextFloat() * 1.5f - 1.0f) * 16.0f;
			waypointZ = posZ + (rand.nextFloat() * 2.0f - 1.0f) * 16.0f;
		}
		if (courseChangeCooldown-- <= 0) {
			courseChangeCooldown += rand.nextInt(5) + 2;
			d4 = MathHelper.sqrt_double(d4);
			if (isCourseTraversable(waypointX, waypointY, waypointZ, d4)) {
				motionX += d0 / d4 * 0.1;
				motionY += d2 / d4 * 0.1;
				motionZ += d3 / d4 * 0.1;
			}
			else {
				waypointX = posX;
				waypointY = posY;
				waypointZ = posZ;
			}
		}
		if (targetedEntity != null && targetedEntity.isDead) {
			targetedEntity = null;
		}
		if (targetedEntity == null || aggroCooldown-- <= 0) {
			targetedEntity = worldObj.getClosestVulnerablePlayerToEntity(this, 100.0);
			if (targetedEntity != null) {
				aggroCooldown = 10;
			}
		}
		final double d5 = 64.0;
		if (targetedEntity != null && targetedEntity.getDistanceSqToEntity(this) < d5 * d5) {
			final double d6 = targetedEntity.posX - posX;
			final double d7 = targetedEntity.boundingBox.minY + targetedEntity.height / 2.0f - (posY + height / 2.0f);
			final double d8 = targetedEntity.posZ - posZ;
			final float n = -(float)Math.atan2(d6, d8) * 180.0f / 3.1415927f;
			rotationYaw = n;
			renderYawOffset = n;
			if (canEntityBeSeen(targetedEntity)) {
				++attackCounter;
				if (attackCounter == 5) {
					final EntityGrawShot entitylargefireball = new EntityGrawShot(worldObj, this);
					final double var3 = targetedEntity.posX - posX;
					final double var4 = targetedEntity.posY + targetedEntity.getEyeHeight() - 1.100000023841858 - entitylargefireball.posY;
					final double var5 = targetedEntity.posZ - posZ;
					final float var6 = MathHelper.sqrt_double(var3 * var3 + var5 * var5) * 0.2f;
					entitylargefireball.setThrowableHeading(var3, var4 + var6, var5, 1.6f, 12.0f);
					worldObj.spawnEntityInWorld(entitylargefireball);
					attackCounter = -10;
				}
			}
			else if (attackCounter > 0) {
				--attackCounter;
			}
		}
		else {
			final float n2 = -(float)Math.atan2(motionX, motionZ) * 180.0f / 3.1415927f;
			rotationYaw = n2;
			renderYawOffset = n2;
			if (attackCounter > 0) {
				--attackCounter;
			}
		}
		if (!worldObj.isRemote) {
			final byte b1 = dataWatcher.getWatchableObjectByte(16);
			final byte b2 = (byte)((attackCounter > 10) ? 1 : 0);
			if (b1 != b2) {
				dataWatcher.updateObject(16, b2);
			}
		}
	}

	private boolean isCourseTraversable(final double par1, final double par3, final double par5, final double par7) {
		final double d4 = (waypointX - posX) / par7;
		final double d5 = (waypointY - posY) / par7;
		final double d6 = (waypointZ - posZ) / par7;
		final AxisAlignedBB axisalignedbb = boundingBox.copy();
		for (int i = 1; i < par7; ++i) {
			axisalignedbb.offset(d4, d5, d6);
			if (!worldObj.getCollidingBoundingBoxes(this, axisalignedbb).isEmpty()) {
				return false;
			}
		}
		return true;
	}

	protected String getLivingSound() {
		return "nevermine:GrawLiving";
	}

	protected String getHurtSound() {
		return "nevermine:GrawHit";
	}

	protected String getDeathSound() {
		return "nevermine:GrawDeath";
	}

	public int getMaxSpawnedInChunk() {
		return 8;
	}
}
