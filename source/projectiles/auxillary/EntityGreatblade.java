package net.nevermine.projectiles.auxillary;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.ConfigurationHelper;
import net.nevermine.assist.EntityUtil;
import net.nevermine.minion.entity.EntityRosid;
import net.nevermine.projectiles.energy.EntityWitherPierce;
import net.nevermine.resource.energy.energyHelper;

import java.util.List;

public class EntityGreatblade extends EntityThrowable {
	private float damage;
	private int age;
	private int effect;

	public EntityGreatblade(final World par1World) {
		super(par1World);
		age = 0;
	}

	public EntityGreatblade(final World par1World, final EntityPlayer par3EntityPlayer, final float dmg, final int ability) {
		super(par1World, par3EntityPlayer);
		age = 0;
		damage = dmg;
		effect = ability;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityGreatblade(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
		age = 0;
	}

	protected float getGravityVelocity() {
		return 0.0f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
			Block bl = worldObj.getBlock(movingobjectposition.blockX, movingobjectposition.blockY, movingobjectposition.blockZ);

			if (!bl.getMaterial().blocksMovement())
				return;
		}

		if (!worldObj.isRemote && movingobjectposition.entityHit instanceof EntityLiving) {
			final EntityLivingBase attacker = getThrower();

			if (attacker != null && attacker.getHeldItem() != null) {
				damage *= 1 + EnchantmentHelper.getEnchantmentLevel(ConfigurationHelper.esever, attacker.getHeldItem()) * 0.2;

				final int eCrush = EnchantmentHelper.getEnchantmentLevel(ConfigurationHelper.ecrush, attacker.getHeldItem());

				if (eCrush == 1) {
					final float f4 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
					movingobjectposition.entityHit.addVelocity(motionX * 2.0 * 0.6000000238418579 / f4, 0.1, motionZ * 2.0 * 0.6000000238418579 / f4);
				}
				else if (eCrush == 2) {
					final float f4 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
					movingobjectposition.entityHit.addVelocity(motionX * 2.8 * 0.6000000238418579 / f4, 0.1, motionZ * 2.8 * 0.6000000238418579 / f4);
				}

				if (effect != 8)
					attacker.getHeldItem().damageItem(1, attacker);
			}

			switch (effect) {
				case 1:
					((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(Potion.poison.id, 100, 3));
					break;
				case 2:
					((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(Potion.wither.id, 100, 1));

					if (rand.nextBoolean())
						attacker.worldObj.spawnEntityInWorld(new EntityWitherPierce(attacker.worldObj, attacker, 10.0f, 2));
					break;
				case 3:
					if (rand.nextInt(3) == 0) {
						final float f4 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
						movingobjectposition.entityHit.addVelocity(motionX * 2.0 * 0.6000000238418579 / f4, 0.1, motionZ * 2.0 * 0.6000000238418579 / f4);
					}
					break;
				case 4:
					if (rand.nextInt(3) == 0)
						((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 30, 100));
					break;
				case 5:
					(movingobjectposition.entityHit).setFire(8);
					break;
				case 6:
					if (rand.nextInt(10) == 0) {
						final EntityRosid var5 = new EntityRosid(worldObj);

						var5.setPosition(posX, posY + 1.0, posZ);
						worldObj.spawnEntityInWorld(var5);
					}
					break;
				case 7:
					if (EntityUtil.getPercentageOfMaxHealth(attacker) < 50)
						attacker.setHealth(attacker.getHealth() + 1.0f);
					break;
				case 9:
					if (attacker instanceof EntityPlayer && EntityUtil.getPercentageOfMaxHealth(attacker) < 33)
						energyHelper.getProperties((EntityPlayer)attacker).regen(60.0f);
					break;
				case 10:
					if (rand.nextInt(20) == 0)
						attacker.setHealth(attacker.getMaxHealth());
					break;
				case 11:
					if (rand.nextInt(20) == 0)
						attacker.addPotionEffect(new PotionEffect(Potion.blindness.id, 160, 0));
					break;
				case 12:
					if (rand.nextInt(20) == 0)
						attacker.attackEntityFrom(DamageSource.causeMobDamage(attacker), damage);
					break;
				case 13:
					if (rand.nextInt(5) == 0) {
						for (final EntityLivingBase e : (List<EntityLivingBase>)worldObj.getEntitiesWithinAABB(EntityLivingBase.class, attacker.boundingBox.expand(3.0, 3.0, 3.0))) {
							e.attackEntityFrom(DamageSource.causeThrownDamage(this, attacker), damage);
						}
					}
					break;
				case 14:
					worldObj.createExplosion(attacker, movingobjectposition.entityHit.posX, movingobjectposition.entityHit.posY, movingobjectposition.entityHit.posZ, 1.5f, false);
					break;
				case 15:
					int pick = rand.nextInt(4);

					if (pick == 0) {
						attacker.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 40, 0));
					}
					else if (pick == 1) {
						attacker.addPotionEffect(new PotionEffect(Potion.resistance.id, 40, 0));
					}
					else if (pick == 2) {
						attacker.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 40, 0));
					}
					else {
						attacker.addPotionEffect(new PotionEffect(Potion.regeneration.id, 40, 0));
					}
					break;
				case 16:
					if (!attacker.worldObj.isRemote) {
						attacker.setPositionAndUpdate(movingobjectposition.entityHit.posX, movingobjectposition.entityHit.posY, movingobjectposition.entityHit.posZ);
					}
					break;
				default:
					break;
			}

			movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, attacker), damage);
		}

		setDead();
	}

	public void onUpdate() {
		super.onUpdate();
		if (age == 1 && !worldObj.isRemote) {
			setDead();
		}
		++age;
	}
}
