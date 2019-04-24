package net.nevermine.mob.entity.nether;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.izer.equipment.Armorizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.mob.ai.HuntAttempt;
import net.nevermine.mob.placement.EntityHunter;
import net.nevermine.mob.placement.EntityNoFire;
import net.nevermine.mob.placement.EntityNoRange;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityNethengeicBeast extends EntityMob implements EntityNoRange, EntityHunter, EntityNoFire {

	public int getLevReq() {
		return 65;
	}

	public EntityNethengeicBeast(final World par1World) {
		super(par1World);
		setSize(1.0f, 1.35f);
		isImmuneToFire = true;
	}

	protected String getLivingSound() {
		return "nevermine:NethengeicBeastLiving";
	}

	protected String getDeathSound() {
		return "nevermine:NethengeicBeastDeath";
	}

	protected String getHurtSound() {
		return "nevermine:NethengeicBeastHit";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("nevermine:HeavyStep", 0.85f, 1.0f);
	}

	private Item dropBanner() {
		return Item.getItemFromBlock(SpecialBlockizer.NethengeicBanner);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.SilverCoin, 2);

		if (rand.nextInt(40) == 17) {
			int pick = rand.nextInt(4);
			if (pick == 1) {
				dropItem(Armorizer.NethengeicHelmet, 1);
			}
			else if (pick == 2) {
				dropItem(Armorizer.NethengeicBoots, 1);
			}
			else if (pick == 3) {
				dropItem(Armorizer.NethengeicLeggings, 1);
			}
			else {
				dropItem(Armorizer.NethengeicChestplate, 1);
			}
		}

		if (rand.nextInt(100) == 3) {
			dropItem(Weaponizer.NethengeicSlugger, 1);
		}

		if (rand.nextInt(100) == 7) {
			dropItem(Weaponizer.NethengeicSword, 1);
		}

		if (rand.nextInt(200) == 144) {
			dropItem(Itemizer.UpgradeKitNether, 1);
		}

		if (rand.nextBoolean()) {
			dropItem(Itemizer.CoinsNether, 2);
		}

		if (rand.nextInt(7) == 0) {
			dropItem(dropBanner(), 1);
		}

		if (rand.nextInt(20) == 4) {
			dropItem(Itemizer.NethengeicCallstone, 1);
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
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(450.0);
	}

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);

		if (!worldObj.isRemote && var1.getEntity() != null && var1.getEntity() instanceof EntityPlayer) {
			PlayerContainer.getProperties((EntityPlayer)var1.getEntity()).addExperience(560.0f, Hunter);
		}
	}

	public boolean attackEntityAsMob(final Entity par1) {
		if (super.attackEntityAsMob(par1)) {
			if (par1 instanceof EntityLivingBase) {
				(par1).setFire(10);
			}
			return true;
		}
		return false;
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		final Entity entity = par1DamageSource.getSourceOfDamage();
		return HuntAttempt.Hunt(entity, getLevReq(), par1DamageSource) && super.attackEntityFrom(par1DamageSource, par2);
	}
}
