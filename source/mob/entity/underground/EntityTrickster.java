package net.nevermine.mob.entity.underground;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.izer.equipment.Weaponizer;

import java.util.List;

public class EntityTrickster extends EntityMob {
	private int cooldown;
	private int inviscount;

	public EntityTrickster(final World par1World) {
		super(par1World);
		cooldown = 160;
		inviscount = 0;
		setSize(0.6f, 1.75f);
	}

	protected String getLivingSound() {
		return "nevermine:TricksterLiving";
	}

	protected String getDeathSound() {
		return "nevermine:TricksterHit";
	}

	protected String getHurtSound() {
		return "nevermine:TricksterHit";
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && posY < 20.0 && isValidLightLevel() && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty();
	}

	private Item dropBanner() {
		return Item.getItemFromBlock(SpecialBlockizer.IllusionBanner);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		if (rand.nextInt(20) == 13) {
			dropItem(Weaponizer.MiniCannon, 1);
		}
		if (rand.nextInt(7) == 0) {
			dropItem(dropBanner(), 1);
		}
		if (rand.nextInt(6) == 4) {
			dropItem(Itemizer.RealmstoneAbyss, 2);
		}
		if (rand.nextInt(5) == 4) {
			dropItem(Itemizer.RealmstoneDeeplands, 2);
		}
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
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
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(55.0);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (cooldown > 0) {
			--cooldown;
		}
		if (inviscount > 1) {
			--inviscount;
		}
		if (cooldown < 2 && !worldObj.isRemote) {
			addPotionEffect(new PotionEffect(Potion.invisibility.id, 60, 2));
			inviscount = 60;
			cooldown = 240;
			worldObj.playSoundAtEntity(this, "nevermine:TricksterInvisible", 1.0f, 1.0f);
		}
		if (!worldObj.isRemote && inviscount == 1 && ((List<EntityPlayer>)worldObj.getEntitiesWithinAABB(EntityTricksterClone.class, boundingBox.expand(10.0, 10.0, 10.0))).size() <= 5) {
			final EntityTricksterClone var2 = new EntityTricksterClone(worldObj);

			var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
			worldObj.spawnEntityInWorld(var2);
			inviscount = 0;
		}
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		final Entity entity = par1DamageSource.getSourceOfDamage();
		return !par1DamageSource.isExplosion() && super.attackEntityFrom(par1DamageSource, par2);
	}
}
