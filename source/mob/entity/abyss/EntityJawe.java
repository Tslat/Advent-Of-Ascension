package net.nevermine.mob.entity.abyss;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.mob.ai.HuntAttempt;
import net.nevermine.mob.placement.EntityHunter;
import net.nevermine.mob.placement.EntityNoRange;

import java.util.List;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityJawe extends EntityMob implements EntityNoRange, EntityHunter {
	private int counter;
	private boolean pull;

	public int getLevReq() {
		return 14;
	}

	public EntityJawe(final World par1World) {
		super(par1World);
		counter = 80;
		pull = false;
		setSize(0.7f, 0.9f);
	}

	protected String getLivingSound() {
		return "nevermine:JaweLiving";
	}

	protected String getDeathSound() {
		return "nevermine:JaweDeath";
	}

	protected String getHurtSound() {
		return "nevermine:JaweHit";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL;
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	public void onDeath(final DamageSource src) {
		super.onDeath(src);
		if (!worldObj.isRemote && src.getEntity() != null && src.getEntity() instanceof EntityPlayer) {
			PlayerContainer.getProperties((EntityPlayer)src.getEntity()).addExperience(17.0f, Hunter);
		}
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		final Entity entity = par1DamageSource.getSourceOfDamage();
		return HuntAttempt.Hunt(entity, getLevReq(), par1DamageSource) && super.attackEntityFrom(par1DamageSource, par2);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));

		if (rand.nextBoolean()) {
			dropItem(Itemizer.CoinsAbyss, 2);
		}
		if (rand.nextInt(120) == 65) {
			dropItem(Weaponizer.BombLauncher, 1);
		}
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		--counter;

		if (pull && counter == 0) {
			counter = 100;
		}
		if (!pull && counter == 0) {
			counter = 25;
		}
		if (pull) {
			for (final EntityPlayer e : (List<EntityPlayer>)worldObj.getEntitiesWithinAABB(EntityPlayer.class, boundingBox.expand(11.0, 11.0, 11.0))) {
				e.addVelocity(Math.signum(posX - e.posX) * 0.229, 0.0, Math.signum(posZ - e.posZ) * 0.229);
				playSound("nevermine:JaweYell", 1.55f, 1.0f);
			}
		}
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(7.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.3);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(60.0);
	}
}
