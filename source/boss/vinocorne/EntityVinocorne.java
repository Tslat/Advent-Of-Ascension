package net.nevermine.boss.vinocorne;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
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

public class EntityVinocorne extends EntityMob implements EntityBoss {
	private int counter;
	private int musicTick;

	public EntityVinocorne(final World par1World) {
		super(par1World);
		counter = 0;
		musicTick = 1;
		setSize(1.2f, 2.1f);
	}

	protected String getLivingSound() {
		return "nevermine:VinocorneLiving";
	}

	protected String getDeathSound() {
		return "nevermine:VinocorneDeath";
	}

	protected String getHurtSound() {
		return "nevermine:VinocorneLiving";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		if (par1DamageSource == DamageSource.inWall) {
			BossClear.clear((int)posX, (int)posY, (int)posZ, worldObj, this);
		}
		return super.attackEntityFrom(par1DamageSource, par2);
	}

	public void onDeath(final DamageSource d) {
		super.onDeath(d);

		if (!worldObj.isRemote && d.getEntity() instanceof EntityPlayer) {
			final EntityPlayer p = (EntityPlayer)d.getEntity();

			IChatComponent msg = StringUtil.getLocaleWithArguments("message.mob.vinocorne.kill", p.getDisplayName());

			for (final EntityPlayer e : (List<EntityPlayer>)p.worldObj.getEntitiesWithinAABB(EntityPlayer.class, p.boundingBox.expand(50.0, 50.0, 50.0))) {
				e.addChatMessage(msg);
			}

			PlayerContainer cont = PlayerContainer.getProperties(p);

			if (cont.getLevel(Hunter) >= 30)
				cont.addExperience(1400, Hunter);
		}
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		--musicTick;

		if (musicTick == 0) {
			musicTick = 270;
			playSound("nevermine:MusicVinocorne", 2.8f, 1.0f);
		}

		++counter;

		if (counter == 70) {
			final int choose = rand.nextInt(5);

			if (choose == 1) {
				if (!worldObj.isRemote) {
					final EntityBlueFlower var2 = new EntityBlueFlower(worldObj);
					var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
					worldObj.spawnEntityInWorld(var2);
				}
			}
			else if (choose == 2) {
				if (!worldObj.isRemote) {
					final EntityYellowFlower var3 = new EntityYellowFlower(worldObj);
					var3.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
					worldObj.spawnEntityInWorld(var3);
				}
			}
			else if (choose == 3) {
				if (!worldObj.isRemote) {
					final EntityOrangeFlower var4 = new EntityOrangeFlower(worldObj);
					var4.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
					worldObj.spawnEntityInWorld(var4);
				}
			}
			else if (choose == 4) {
				if (!worldObj.isRemote) {
					final EntityGreenFlower var5 = new EntityGreenFlower(worldObj);
					var5.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
					worldObj.spawnEntityInWorld(var5);
				}
			}
			else if (!worldObj.isRemote) {
				final EntityPurpleFlower var6 = new EntityPurpleFlower(worldObj);
				var6.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var6);
			}

			counter = 0;
		}
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Item.getItemFromBlock(SpecialBlockizer.VinocorneStatue), 1);
		final int weapon = rand.nextInt(5);

		if (weapon == 1) {
			dropItem(Weaponizer.RosidianBow, 1);
		}
		else if (weapon == 2) {
			dropItem(Weaponizer.RosidianSword, 1);
		}
		else if (weapon == 3) {
			dropItem(Weaponizer.RosidianGreatblade, 1);
		}
		else if (weapon == 4) {
			dropItem(Weaponizer.RosidRifle, 1);
		}
		else if (weapon == 0) {
			dropItem(Weaponizer.RosidianStaff, 1);
		}
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(10.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(2500.0);
	}
}
