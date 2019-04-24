package net.nevermine.boss.bane;

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
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.boss.EntityBoss;
import net.nevermine.container.PlayerContainer;
import net.nevermine.event.creature.BossClear;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.izer.equipment.Weaponizer;

import java.util.List;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityBane extends EntityMob implements EntityBoss {
	private int musicTick;

	public EntityBane(final World par1World) {
		super(par1World);
		musicTick = 1;
		setSize(1.2f, 2.1f);
	}

	protected String getLivingSound() {
		return "nevermine:BaneLiving";
	}

	protected String getDeathSound() {
		return "nevermine:BaneDeath";
	}

	protected String getHurtSound() {
		return "nevermine:BaneLiving";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	public void onDeath(final DamageSource d) {
		super.onDeath(d);

		if (!worldObj.isRemote && d.getEntity() instanceof EntityPlayer) {
			final EntityPlayer p = (EntityPlayer)d.getEntity();

			IChatComponent msg = StringUtil.getLocaleWithArguments("message.mob.bane.kill", p.getDisplayName());

			for (final EntityPlayer e : (List<EntityPlayer>)p.worldObj.getEntitiesWithinAABB(EntityPlayer.class, p.boundingBox.expand(50.0, 50.0, 50.0))) {
				e.addChatMessage(msg);
			}

			for (final EntityBaneClone e : (List<EntityBaneClone>)p.worldObj.getEntitiesWithinAABB(EntityBaneClone.class, p.boundingBox.expand(30, 30, 30))) {
				e.setDead();;
			}

			PlayerContainer cont = PlayerContainer.getProperties(p);

			if (cont.getLevel(Hunter) >= 30)
				cont.addExperience(5000.0f, Hunter);
		}
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		--musicTick;

		if (musicTick == 0) {
			musicTick = 175;
			playSound("nevermine:MusicBane", 2.8f, 1.0f);
		}

		if (rand.nextInt(400) == 43) {
			final EntityPlayer var1 = worldObj.getClosestVulnerablePlayerToEntity(this, 60.0);

			if (var1 == null || var1.getDistanceToEntity(this) > 60.0f) {
				return;
			}

			if (!worldObj.isRemote) {
				setPosition(var1.posX, var1.posY, var1.posZ);
			}
		}

		if (worldObj.isRemote)
			return;

		if (rand.nextInt(200) == 163) {
			final EntityBaneClone var2 = new EntityBaneClone(worldObj);
			var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
			worldObj.spawnEntityInWorld(var2);

			final EntityBaneClone var3 = new EntityBaneClone(worldObj);
			var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
			worldObj.spawnEntityInWorld(var3);

			final EntityBaneClone var4 = new EntityBaneClone(worldObj);
			var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
			worldObj.spawnEntityInWorld(var4);

			final EntityBaneClone var5 = new EntityBaneClone(worldObj);
			var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
			worldObj.spawnEntityInWorld(var5);

			final EntityBaneClone var6 = new EntityBaneClone(worldObj);
			var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
			worldObj.spawnEntityInWorld(var6);

			final EntityBaneClone var7 = new EntityBaneClone(worldObj);
			var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
			worldObj.spawnEntityInWorld(var7);
		}

		if (rand.nextInt(300) == 241) {
			final EntityBaneBig var8 = new EntityBaneBig(worldObj);
			var8.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
			worldObj.spawnEntityInWorld(var8);
		}
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Item.getItemFromBlock(SpecialBlockizer.BaneStatue), 1);

		final int choose = rand.nextInt(4);

		if (choose == 1) {
			dropItem(Weaponizer.HauntedGreatblade, 1);
		}
		else if (choose == 2) {
			dropItem(Weaponizer.HauntersStaff, 1);
		}
		else if (choose == 3) {
			dropItem(Weaponizer.HauntedBow, 1);
		}
		else {
			dropItem(Weaponizer.GhoulGasser, 1);
		}

	}

	public boolean attackEntityAsMob(final Entity par1Entity) {
		super.attackEntityAsMob(par1Entity);
		if (entityToAttack != null) {
			addPotionEffect(new PotionEffect(14, 40, 2));

			if (par1Entity instanceof EntityLivingBase) {
				((EntityLivingBase)par1Entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 80, 1));
			}
			return true;
		}
		return false;
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		if (par1DamageSource == DamageSource.inWall) {
			BossClear.clear((int)posX, (int)posY, (int)posZ, worldObj, this);
		}

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(10.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1750.0);
	}
}
