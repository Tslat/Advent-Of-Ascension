package net.nevermine.projectiles.gun;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;
import net.nevermine.minion.entity.EntityRosid;
import net.nevermine.projectiles.HardProjectile;

import java.util.List;

public class EntitySpecialMetalPellet extends EntityThrowable implements HardProjectile {
	private float damage;
	private int age;
	private int ability;

	public EntitySpecialMetalPellet(final World par1World) {
		super(par1World);
		age = 0;
	}

	public EntitySpecialMetalPellet(final World par1World, final EntityPlayer par3EntityPlayer, final float dmg, final int effect) {
		super(par1World, par3EntityPlayer);
		age = 0;
		damage = dmg;
		ability = effect;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntitySpecialMetalPellet(final World par1World, final double par2, final double par4, final double par6) {
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

		if (movingobjectposition.entityHit instanceof EntityDragonPart) {
			switch (ability) {
				case 1:
					if (rand.nextInt(10) == 0) {
						final EntityRosid var5 = new EntityRosid(worldObj);

						var5.setPosition(posX, posY + 1.0, posZ);
						worldObj.spawnEntityInWorld(var5);
					}
					break;
				case 2:
					worldObj.createExplosion(getThrower(), posX, posY, posZ, 1.5f, false);
					break;
				case 3:
					final EntityRosid var5 = new EntityRosid(worldObj);

					var5.setPosition(posX, posY + 1.0, posZ);
					worldObj.spawnEntityInWorld(var5);
					break;
				case 4:
					if (((EntityLivingBase)movingobjectposition.entityHit).getHealth() > damage) {
						worldObj.addWeatherEffect(new EntityLightningBolt(worldObj, posX, posY, posZ));
					}
					break;
				default:
					break;
			}

			((EntityDragonPart)movingobjectposition.entityHit).entityDragonObj.attackEntityFromPart((EntityDragonPart)movingobjectposition.entityHit, DamageSource.causeThrownDamage(this, getThrower()), damage);
		}
		else if (movingobjectposition.entityHit instanceof EntityEnderCrystal) {
			movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), damage);
		}
		else if (!worldObj.isRemote && movingobjectposition.entityHit instanceof EntityLivingBase) {
			switch (ability) {
				case 1:
					if (rand.nextInt(10) == 0) {
						final EntityRosid var5 = new EntityRosid(worldObj);

						var5.setPosition(posX, posY + 1.0, posZ);
						worldObj.spawnEntityInWorld(var5);
					}
					break;
				case 2:
					worldObj.createExplosion(getThrower(), posX, posY, posZ, 1.5f, false);
					break;
				case 3:
					final EntityRosid var5 = new EntityRosid(worldObj);

					var5.setPosition(posX, posY + 1.0, posZ);
					worldObj.spawnEntityInWorld(var5);
					break;
				case 4:
					if (((EntityLivingBase)movingobjectposition.entityHit).getHealth() > damage) {
						worldObj.addWeatherEffect(new EntityLightningBolt(worldObj, posX, posY, posZ));
					}
					break;
				case 5:
					((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(Potion.poison.id, 100, 2));
					break;
				case 6:
					((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 2));
					break;
				case 7:
					for (final EntityMob e : (List<EntityMob>)worldObj.getEntitiesWithinAABB(EntityMob.class, boundingBox.expand(6.0, 6.0, 6.0))) {
						e.addPotionEffect(new PotionEffect(Potion.wither.id, 100, 0));
					}
					break;
				default:
					break;
			}

			EntityUtil.shootEntity(movingobjectposition.entityHit, getThrower(), this, damage);
		}

		if (!worldObj.isRemote) {
			setDead();
		}
	}

	public void onUpdate() {
		super.onUpdate();
		if (age == 20) {
			if (!worldObj.isRemote) {
				setDead();
			}
		}
		else {
			++age;
		}
	}
}
