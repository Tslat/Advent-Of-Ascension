package net.nevermine.boss.elusive;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.nevermine.assist.StringUtil;
import net.nevermine.boss.EntityBoss;
import net.nevermine.container.PlayerContainer;
import net.nevermine.event.creature.BossClear;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.izer.equipment.Weaponizer;

import java.util.List;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityElusive extends EntityMob implements EntityBoss {
	private int spawnTick;
	private int teleTick;
	private int musictick;

	public EntityElusive(final World par1World) {
		super(par1World);
		musictick = 6;
		spawnTick = 250;
		teleTick = 300;
		setSize(1.1f, 1.9f);
	}

	protected String getLivingSound() {
		switch (rand.nextInt(3)) {
			case 0: {
				return "nevermine:ElusiveLiving1";
			}
			case 1: {
				return "nevermine:ElusiveLiving2";
			}
			case 2: {
				return "nevermine:ElusiveLiving3";
			}
			default: {
				return "nevermine:ElusiveLiving1";
			}
		}
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		if (par1DamageSource == DamageSource.inWall) {
			BossClear.clear((int)posX, (int)posY, (int)posZ, worldObj, this);
		}
		return super.attackEntityFrom(par1DamageSource, par2);
	}

	private Item dropBanner() {
		return Item.getItemFromBlock(SpecialBlockizer.IllusionBanner);
	}

	private Item dropStatue() {
		return Item.getItemFromBlock(SpecialBlockizer.ElusiveStatue);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(dropStatue(), 1);
		dropItem(dropBanner(), 1);

		final int wep = rand.nextInt(4);

		if (wep == 1) {
			dropItem(Weaponizer.MindBlaster, 1);
		}
		else if (wep == 2) {
			dropItem(Weaponizer.IllusionSword, 1);
		}
		else if (wep == 3) {
			dropItem(Weaponizer.IllusionSMG, 1);
		}
		else {
			dropItem(Weaponizer.IllusionRevolver, 1);
		}

	}

	protected String getDeathSound() {
		return "nevermine:ElusiveDeath";
	}

	protected String getHurtSound() {
		return "nevermine:ElusiveHit";
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(15.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(2000.0);
	}

	public void onLivingUpdate() {
		if (musictick == 4) {
			playSound("nevermine:MusicElusive", 3.0f, 1.0f);
			musictick = 540;
		}
		else {
			--musictick;
		}
		if (spawnTick == 0 && !worldObj.isRemote) {
			final EntityElusiveClone var2 = new EntityElusiveClone(worldObj);
			var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
			worldObj.spawnEntityInWorld(var2);
			spawnTick = 250;
		}
		else {
			--spawnTick;
		}
		if (teleTick < 4) {
			if (entityToAttack != null) {
				teleportToEntity(entityToAttack);
			}
			teleTick = 300;
		}
		--teleTick;
		if (rand.nextInt(400) == 43) {
			final EntityPlayer var3 = worldObj.getClosestVulnerablePlayerToEntity(this, 60.0);
			if (var3 == null || var3.getDistanceToEntity(this) > 60.0f) {
				return;
			}
			if (!worldObj.isRemote) {
				setPosition(var3.posX, var3.posY, var3.posZ);
			}
		}
		super.onLivingUpdate();
	}

	public boolean attackEntityAsMob(final Entity par1Entity) {
		super.attackEntityAsMob(par1Entity);
		if (entityToAttack != null) {
			if (par1Entity instanceof EntityLivingBase) {
				addPotionEffect(new PotionEffect(14, 40, 2));
				spawnTick -= 30;
				((EntityLivingBase)par1Entity).addPotionEffect(new PotionEffect(15, 90, 2));
				if (spawnTick < 125) {
					((EntityLivingBase)par1Entity).addPotionEffect(new PotionEffect(9, 80, 8));
				}
			}
			return true;
		}
		return false;
	}

	protected boolean teleportToEntity(final Entity par1Entity) {
		Vec3 vec3 = Vec3.createVectorHelper(posX - par1Entity.posX, boundingBox.minY + height / 2.0f - par1Entity.posY + par1Entity.getEyeHeight(), posZ - par1Entity.posZ);
		vec3 = vec3.normalize();
		final double d0 = 16.0;
		final double d2 = posX + (rand.nextDouble() - 0.5) * 8.0 - vec3.xCoord * d0;
		final double d3 = posY + (rand.nextInt(16) - 8) - vec3.yCoord * d0;
		final double d4 = posZ + (rand.nextDouble() - 0.5) * 8.0 - vec3.zCoord * d0;
		return teleportTo(d2, d3, d4);
	}

	protected boolean teleportTo(final double par1, final double par3, final double par5) {
		final EnderTeleportEvent event = new EnderTeleportEvent(this, par1, par3, par5, 0.0f);
		if (MinecraftForge.EVENT_BUS.post(event)) {
			return false;
		}
		final double d3 = posX;
		final double d4 = posY;
		final double d5 = posZ;
		posX = event.targetX;
		posY = event.targetY;
		posZ = event.targetZ;
		boolean flag = false;
		final int i = MathHelper.floor_double(posX);
		int j = MathHelper.floor_double(posY);
		final int k = MathHelper.floor_double(posZ);
		if (worldObj.blockExists(i, j, k)) {
			boolean flag2 = false;
			while (!flag2 && j > 0) {
				final Block block = worldObj.getBlock(i, j - 1, k);
				if (block.getMaterial().blocksMovement()) {
					flag2 = true;
				}
				else {
					--posY;
					--j;
				}
			}
			if (flag2) {
				setPosition(posX, posY, posZ);
				if (worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox)) {
					flag = true;
				}
			}
		}
		if (!flag) {
			setPosition(d3, d4, d5);
			return false;
		}
		final short short1 = 128;
		for (int l = 0; l < short1; ++l) {
			final double d6 = l / (short1 - 1.0);
			final float f = (rand.nextFloat() - 0.5f) * 0.2f;
			final float f2 = (rand.nextFloat() - 0.5f) * 0.2f;
			final float f3 = (rand.nextFloat() - 0.5f) * 0.2f;
			final double d7 = d3 + (posX - d3) * d6 + (rand.nextDouble() - 0.5) * width * 2.0;
			final double d8 = d4 + (posY - d4) * d6 + rand.nextDouble() * height;
			final double d9 = d5 + (posZ - d5) * d6 + (rand.nextDouble() - 0.5) * width * 2.0;
			worldObj.spawnParticle("portal", d7, d8, d9, (double)f, (double)f2, (double)f3);
		}
		worldObj.playSoundEffect(d3, d4, d5, "mob.endermen.portal", 1.0f, 1.0f);
		playSound("mob.endermen.portal", 1.0f, 1.0f);
		return true;
	}

	public void onDeath(final DamageSource d) {
		super.onDeath(d);

		if (!worldObj.isRemote && d.getEntity() != null && d.getEntity() instanceof EntityPlayer) {
			final EntityPlayer p = (EntityPlayer)d.getEntity();

			IChatComponent msg = StringUtil.getLocaleWithArguments("message.mob.elusive.kill", p.getDisplayName());

			for (final EntityPlayer e : (List<EntityPlayer>)p.worldObj.getEntitiesWithinAABB(EntityPlayer.class, p.boundingBox.expand(50.0, 50.0, 50.0))) {
				e.addChatMessage(msg);
			}

			PlayerContainer cont = PlayerContainer.getProperties(p);

			if (cont.getLevel(Hunter) >= 30)
				cont.addExperience(5000, Hunter);
		}
	}
}
