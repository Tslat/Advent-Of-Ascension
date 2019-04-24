package net.nevermine.mob.entity.nether;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.mob.placement.EntityHunter;
import net.nevermine.mob.placement.EntityNoFire;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityFlamewalker extends EntityMob implements EntityNoFire, EntityHunter {
	public EntityFlamewalker(final World par1World) {
		super(par1World);
		setSize(0.8f, 2.2f);
		isImmuneToFire = true;
	}

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);
		if (!worldObj.isRemote && var1.getEntity() != null && var1.getEntity() instanceof EntityPlayer) {
			PlayerContainer.getProperties((EntityPlayer)var1.getEntity()).addExperience(17.0f, Hunter);
		}
	}

	protected String getLivingSound() {
		return "nevermine:FlamewalkerLiving";
	}

	protected String getDeathSound() {
		return "nevermine:FlamewalkerDeath";
	}

	protected String getHurtSound() {
		return "nevermine:FlamewalkerHit";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));

		if (rand.nextInt(35) == 13) {
			dropItem(Weaponizer.Ultraflame, 1);
		}

		if (rand.nextInt(3) == 2) {
			dropItem(Itemizer.CoinsNether, 3);
		}
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.1);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (!worldObj.isRemote) {
			if (getHealth() > 7.0f) {
				int var1 = MathHelper.floor_double(posX);
				int var2 = MathHelper.floor_double(posZ);
				for (var1 = 0; var1 < 4; ++var1) {
					var2 = MathHelper.floor_double(posX + (var1 % 2 * 2 - 1) * 0.25f);
					final int var3 = MathHelper.floor_double(posY);
					final int var4 = MathHelper.floor_double(posZ + (var1 / 2 % 2 * 2 - 1) * 0.25f);
					if (worldObj.getBlock(var2, var3 - 1, var4) == Blocks.netherrack && worldObj.getBlock(var2, var3, var4) == Blocks.air) {
						worldObj.setBlock(var2, var3, var4, Blocks.fire);
					}
				}
			}
		}
	}

	public int getLevReq() {
		return 0;
	}
}
