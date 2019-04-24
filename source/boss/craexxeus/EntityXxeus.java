package net.nevermine.boss.craexxeus;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.boss.EntityBoss;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.izer.equipment.Toolizer;
import net.nevermine.izer.equipment.Weaponizer;

import java.util.List;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityXxeus extends EntityMob implements EntityBoss {
	private int musictick;
	private int jumpcount = 1;

	public EntityXxeus(World par1World) {
		super(par1World);
		setSize(1.25F, 3.0F);
	}

	public EntityXxeus(World par1World, int mt) {
		this(par1World);
		musictick = mt;
	}

	protected String getLivingSound() {
		return "nevermine:XxeusLiving";
	}

	private void dropStatue() {
		if (rand.nextBoolean()) {
			dropItem(Item.getItemFromBlock(SpecialBlockizer.CraexxeusStatue), 1);
		}
		else {
			dropItem(Item.getItemFromBlock(SpecialBlockizer.XxeusStatue), 1);
		}
	}

	protected void dropFewItems(boolean par1, int par2) {
		int choose = rand.nextInt(4);

		if (choose == 1) {
			dropItem(Weaponizer.UltimatumStaff, 1);
		}
		else if (choose == 2) {
			dropItem(Toolizer.Chainsaw, 1);
		}
		else if (choose == 3) {
			dropItem(Weaponizer.Paralyzer, 1);
		}
		else {
			dropItem(Weaponizer.GodsGreatblade, 1);
		}

		dropStatue();
	}

	protected String getDeathSound() {
		return "nevermine:XxeusDeath";
	}

	protected String getHurtSound() {
		return "nevermine:XxeusHit";
	}

	protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
		playSound("nevermine:HeavyStep", 1.85F, 1.0F);
	}

	protected Entity findPlayerToAttack() {
		EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
		return (entityPlayer != null) && (canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();

		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(25.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0D);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(3000.0D);
	}

	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(16, new Integer(100));
	}

	public float getAIMoveSpeed() {
		return 2.1F;
	}

	public void moveEntityWithHeading(float par1, float par2) {
		if (isInWater()) {
			double d0 = posY;
			moveFlying(par1, par2, 0.52F);
			moveEntity(motionX, motionY, motionZ);
			motionX *= 0.800000011920929D;
			motionY *= 0.800000011920929D;
			motionZ *= 0.800000011920929D;
			motionY -= 0.02D;

			if ((isCollidedHorizontally) && (isOffsetPositionInLiquid(motionX, motionY + 0.6000000238418579D - posY + d0, motionZ))) {
				motionY = 0.30000001192092896D;
			}
		}
		else if (handleLavaMovement()) {
			double d0 = posY;
			moveFlying(par1, par2, 0.02F);
			moveEntity(motionX, motionY, motionZ);
			motionX *= 0.5D;
			motionY *= 0.5D;
			motionZ *= 0.5D;
			motionY -= 0.02D;

			if ((isCollidedHorizontally) && (isOffsetPositionInLiquid(motionX, motionY + 0.6000000238418579D - posY + d0, motionZ))) {
				motionY = 0.30000001192092896D;
			}
		}
		else {
			float f2 = 0.91F;

			float f3 = 0.16277136F / (f2 * f2 * f2);
			float f4;
			if (onGround) {
				f4 = getAIMoveSpeed() * f3;
			}
			else {
				f4 = jumpMovementFactor;
			}

			moveFlying(par1, par2, f4);
			f2 = 0.91F;

			if (isOnLadder()) {
				float f5 = 0.15F;

				if (motionX < -f5) {
					motionX = (-f5);
				}

				if (motionX > f5) {
					motionX = f5;
				}

				if (motionZ < -f5) {
					motionZ = (-f5);
				}

				if (motionZ > f5) {
					motionZ = f5;
				}

				fallDistance = 0.0F;

				if (motionY < -0.15D) {
					motionY = -0.15D;
				}
			}

			moveEntity(motionX, motionY, motionZ);

			if ((isCollidedHorizontally) && (isOnLadder())) {
				motionY = 0.2D;
			}

			if ((worldObj.isRemote) && ((!worldObj.blockExists((int)posX, 0, (int)posZ)) || (!worldObj.getChunkFromBlockCoords((int)posX, (int)posZ).isChunkLoaded))) {
				if (posY > 0.0D) {
					motionY = -0.1D;
				}
				else {
					motionY = 0.0D;
				}

			}
			else {
				motionY -= 0.08D;
			}

			motionY *= 0.9800000190734863D;
			motionX *= f2;
			motionZ *= f2;
		}

		prevLimbSwingAmount = limbSwingAmount;
		double d0 = posX - prevPosX;
		double d1 = posZ - prevPosZ;
		float f6 = MathHelper.sqrt_double(d0 * d0 + d1 * d1) * 4.0F;

		if (f6 > 1.0F) {
			f6 = 1.0F;
		}

		limbSwingAmount += (f6 - limbSwingAmount) * 0.4F;
		limbSwing += limbSwingAmount;
	}

	public void onLivingUpdate() {
		if (jumpcount == 60) {
			if (entityToAttack != null) {
				if (entityToAttack.posY > posY) {
					motionY = 0.8500000238418579D;
				}
				else {
					motionY = 0.44999998807907104D;
				}
				motionZ = ((entityToAttack.posZ - posZ) * 0.16500000655651093D);
				motionX = ((entityToAttack.posX - posX) * 0.16500000655651093D);
			}
			jumpcount = 0;
			playSound("nevermine:XxeusDash", 3.0F, 1.0F);
		}
		jumpcount += 1;

		super.onLivingUpdate();
		if (ticksExisted % 40 == 0) {
			for (EntityPlayer e : (List<EntityPlayer>)worldObj.getEntitiesWithinAABB(EntityPlayer.class, boundingBox.expand(6.0D, 6.0D, 6.0D))) {
				e.attackEntityFrom(DamageSource.causeMobDamage(this), 16.0F);
			}
		}

		if ((ticksExisted % 100 == 0) && (rand.nextInt(2) == 1)) {
			for (EntityPlayer e : (List<EntityPlayer>)worldObj.getEntitiesWithinAABB(EntityPlayer.class, boundingBox.expand(31.0D, 31.0D, 31.0D))) {
				if (!worldObj.isRemote && e.capabilities.isFlying && !e.capabilities.isCreativeMode) {
					e.addChatMessage(StringUtil.getColourLocale("message.feedback.boss.flyCheat", EnumChatFormatting.LIGHT_PURPLE));
					e.addPotionEffect(new PotionEffect(Potion.poison.id, 60, 1));
				}
			}
		}
		if (musictick == 0) {
			playSound("nevermine:MusicXxeus", 6.0F, 1.0F);
			musictick = 345;
		}
		else {
			musictick -= 1;
		}
	}

	public void onDeath(DamageSource d) {
		super.onDeath(d);

		if (!worldObj.isRemote && d.getEntity() instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer)d.getEntity();

			IChatComponent msg = StringUtil.getLocaleWithArguments("message.mob.xxeus.kill", p.getDisplayName());

			for (final EntityPlayer e : (List<EntityPlayer>)p.worldObj.getEntitiesWithinAABB(EntityPlayer.class, p.boundingBox.expand(50.0, 50.0, 50.0))) {
				e.addChatMessage(msg);
			}

			PlayerContainer cont = PlayerContainer.getProperties((EntityPlayer)d.getEntity());

			if (cont.getLevel(Hunter) >= 30)
				cont.addExperience(20000, Hunter);
		}
	}
}
