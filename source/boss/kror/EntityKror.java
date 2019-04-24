package net.nevermine.boss.kror;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.boss.EntityBoss;
import net.nevermine.container.PlayerContainer;
import net.nevermine.event.creature.BossClear;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.izer.equipment.Weaponizer;

import java.util.List;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityKror extends EntityMob implements EntityBoss {
	private int musicTick;

	public EntityKror(final World par1World) {
		super(par1World);
		musicTick = 1;
		setSize(2.2f, 3.7f);
	}

	protected String getLivingSound() {
		return "nevermine:KrorLiving";
	}

	protected String getDeathSound() {
		return "nevermine:KrorDeath";
	}

	protected String getHurtSound() {
		return "nevermine:KrorLiving";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("nevermine:HeavyStep", 0.85f, 1.0f);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Item.getItemFromBlock(SpecialBlockizer.KrorStatue), 1);

		final int choose = rand.nextInt(3);

		if (choose == 1) {
			dropItem(Weaponizer.RockerRifle, 1);
		}
		else if (choose == 2) {
			dropItem(Weaponizer.BoulderBomber, 1);
		}
		else {
			dropItem(Weaponizer.SubterraneanGreatblade, 1);
		}
	}

	public void onDeath(final DamageSource d) {
		super.onDeath(d);
		if (!worldObj.isRemote && d.getEntity() instanceof EntityPlayer) {
			final EntityPlayer p = (EntityPlayer)d.getEntity();

			IChatComponent msg = StringUtil.getLocaleWithArguments("message.mob.kror.kill", p.getDisplayName());

			for (final EntityPlayer e : (List<EntityPlayer>)p.worldObj.getEntitiesWithinAABB(EntityPlayer.class, p.boundingBox.expand(50.0, 50.0, 50.0))) {
				e.addChatMessage(msg);
			}

			PlayerContainer cont = PlayerContainer.getProperties((EntityPlayer)d.getEntity());

			if (cont.getLevel(Hunter) >= 30)
				cont.addExperience(5000, Hunter);
		}
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		--musicTick;
		if (musicTick == 0) {
			musicTick = 310;
			playSound("nevermine:MusicKror", 2.8f, 1.0f);
		}
		if (rand.nextInt(200) == 22) {
			for (final EntityPlayer e : (List<EntityPlayer>)worldObj.getEntitiesWithinAABB(EntityPlayer.class, boundingBox.expand(20.0, 20.0, 20.0))) {
				if (!e.capabilities.isCreativeMode)
					e.addVelocity(Math.signum(posX - e.posX) * 0.229, 0.0, Math.signum(posZ - e.posZ) * 0.229);

				playSound("nevermine:KrorLiving", 1.65f, 1.0f);
			}
		}
		for (final EntityPlayer e : (List<EntityPlayer>)worldObj.getEntitiesWithinAABB(EntityPlayer.class, boundingBox.expand(4.0, 4.0, 4.0))) {
			if (!e.capabilities.isCreativeMode)
				e.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
		}
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(60.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(2200.0);
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		final Entity entity = par1DamageSource.getSourceOfDamage();
		if (par1DamageSource == DamageSource.inWall) {
			BossClear.clear((int)posX, (int)posY, (int)posZ, worldObj, this);
		}

		if (entity instanceof EntityThrowable && ((EntityThrowable)entity).getThrower() != null) {
			((EntityThrowable)entity).getThrower().attackEntityFrom(DamageSource.causeMobDamage(this), 10.0f);
		}

		if (entity instanceof EntityArrow) {
			par1DamageSource.getEntity().attackEntityFrom(DamageSource.causeMobDamage(this), 10.0f);
		}

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	public boolean attackEntityAsMob(final Entity par1) {
		if (super.attackEntityAsMob(par1)) {
			if (par1 instanceof EntityLivingBase) {
				((EntityLivingBase)par1).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 150, 2));
			}
			return true;
		}
		return false;
	}
}
