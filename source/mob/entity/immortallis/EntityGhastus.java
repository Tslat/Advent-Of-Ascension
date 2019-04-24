package net.nevermine.mob.entity.immortallis;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.container.PlayerContainer;

import java.util.List;

import static net.nevermine.container.PlayerContainer.Deities.Erebon;

public class EntityGhastus extends EntityMob {
	public EntityGhastus(final World par1World) {
		super(par1World);
		setSize(1.0f, 1.0f);
	}

	protected String getLivingSound() {
		return "nevermine:ApparitionLiving";
	}

	protected String getDeathSound() {
		return "nevermine:ApparitionDeath";
	}

	protected String getHurtSound() {
		return "nevermine:ApparitionHit";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 1.0f, 1.0f);
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);
		if (!worldObj.isRemote && var1.getEntity() != null && var1.getEntity() instanceof EntityPlayer) {
			PlayerContainer cont = PlayerContainer.getProperties((EntityPlayer)var1.getEntity());

			cont.adjustTribute(Erebon, 4);

			if (cont.getTribute(Erebon) == 200)
				((EntityPlayer)var1.getEntity()).addChatMessage(StringUtil.getLocale("message.feedback.immortallisProgression.evilSpiritsEnd"));
		}
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		for (final EntityMob e : (List<EntityMob>)worldObj.getEntitiesWithinAABB(EntityMob.class, boundingBox.expand(7.0, 7.0, 7.0))) {
			e.heal(0.5f);
		}
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(20.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.1);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1.0);
	}
}
