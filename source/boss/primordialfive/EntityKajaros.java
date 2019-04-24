package net.nevermine.boss.primordialfive;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.nevermine.boss.EntityBoss;
import net.nevermine.container.PlayerContainer;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityKajaros extends EntityMob implements EntityBoss {
	private int musicTick;

	public EntityKajaros(final World par1World) {
		super(par1World);
		musicTick = 1;
		setSize(1.2f, 2.1f);
	}

	protected String getLivingSound() {
		return "nevermine:PrimordialLiving";
	}

	protected String getDeathSound() {
		return "nevermine:PrimordialDeath";
	}

	protected String getHurtSound() {
		return "nevermine:PrimordialLiving";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	public boolean attackEntityAsMob(final Entity par1) {
		if (super.attackEntityAsMob(par1)) {
			if (par1 instanceof EntityLivingBase) {
				((EntityLivingBase)par1).addPotionEffect(new PotionEffect(Potion.blindness.id, 150, 2));
				((EntityLivingBase)par1).addPotionEffect(new PotionEffect(Potion.confusion.id, 150, 2));
				((EntityLivingBase)par1).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 150, 2));
				heal(20.0f);
			}

			return true;
		}

		return false;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		--musicTick;

		if (musicTick == 0) {
			musicTick = 290;
			playSound("nevermine:MusicPrimordialFive", 2.8f, 1.0f);
		}

		if (rand.nextInt(75) == 33 && getLastAttacker() != null) {
			setPosition(getLastAttacker().posX, getLastAttacker().posY, getLastAttacker().posZ);
		}
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);

		if (var1.getEntity() != null && var1.getEntity() instanceof EntityPlayer) {
			PlayerContainer cont = PlayerContainer.getProperties((EntityPlayer)var1.getEntity());

			if (cont.getLevel(Hunter) >= 30)
				cont.addExperience(800, Hunter);
		}

		transform();
	}

	private void transform() {
		if (!worldObj.isRemote) {
			final EntityMiskel var2 = new EntityMiskel(worldObj, musicTick);
			var2.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
			worldObj.spawnEntityInWorld(var2);
			if (!worldObj.isRemote) {
				setDead();
			}
		}
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(23.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1750.0);
	}
}
