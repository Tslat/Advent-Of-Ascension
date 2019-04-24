package net.nevermine.projectiles.auxillary;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.nevermine.izer.Blockizer;
import net.nevermine.izer.Itemizer;
import net.nevermine.minion.entity.EntityRosid;

import java.util.List;

public class EntityElementalArrow extends EntityArrow {
	private int field_145791_d;
	private int field_145792_e;
	private int field_145789_f;
	private Block field_145790_g;
	private int inData;
	private boolean inGround;
	private int ability;
	private int ticksInGround;
	private int ticksInAir;
	private double damage;
	private int knockbackStrength;
	private int age;
	private int pierce;
	private int dmgIncrease;
	private boolean archergun;

	public EntityElementalArrow(final World par1World) {
		super(par1World);
		field_145791_d = -1;
		field_145792_e = -1;
		field_145789_f = -1;
		damage = 5.0;
		age = 0;
		pierce = 0;
		dmgIncrease = 0;
		renderDistanceWeight = 10.0;
		setSize(0.5f, 0.5f);
	}

	public EntityElementalArrow(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World);
		field_145791_d = -1;
		field_145792_e = -1;
		field_145789_f = -1;
		damage = 5.0;
		age = 0;
		pierce = 0;
		dmgIncrease = 0;
		renderDistanceWeight = 10.0;
		setSize(0.5f, 0.5f);
		setPosition(par2, par4, par6);
		yOffset = 0.0f;
	}

	public EntityElementalArrow(final World par1World, final EntityLivingBase par2EntityLivingBase, final EntityLivingBase par3EntityLivingBase, final float par4, final float par5) {
		super(par1World);
		field_145791_d = -1;
		field_145792_e = -1;
		field_145789_f = -1;
		damage = 5.0;
		age = 0;
		pierce = 0;
		dmgIncrease = 0;
		renderDistanceWeight = 10.0;
		shootingEntity = par2EntityLivingBase;
		if (par2EntityLivingBase instanceof EntityPlayer) {
			canBePickedUp = 1;
		}
		posY = par2EntityLivingBase.posY + par2EntityLivingBase.getEyeHeight() - 0.10000000149011612;
		final double d0 = par3EntityLivingBase.posX - par2EntityLivingBase.posX;
		final double d2 = par3EntityLivingBase.boundingBox.minY + par3EntityLivingBase.height / 3.0f - posY;
		final double d3 = par3EntityLivingBase.posZ - par2EntityLivingBase.posZ;
		final double d4 = MathHelper.sqrt_double(d0 * d0 + d3 * d3);
		if (d4 >= 1.0E-7) {
			final float f2 = (float)(Math.atan2(d3, d0) * 180.0 / 3.141592653589793) - 90.0f;
			final float f3 = (float)(-(Math.atan2(d2, d4) * 180.0 / 3.141592653589793));
			final double d5 = d0 / d4;
			final double d6 = d3 / d4;
			setLocationAndAngles(par2EntityLivingBase.posX + d5, posY, par2EntityLivingBase.posZ + d6, f2, f3);
			yOffset = 0.0f;
			final float f4 = (float)d4 * 0.2f;
			setThrowableHeading(d0, d2 + f4, d3, par4, par5);
		}
	}

	public EntityElementalArrow(final World par1World, final EntityLivingBase par2EntityLivingBase, final float par3, final int effect, final boolean arch) {
		super(par1World);
		field_145791_d = -1;
		field_145792_e = -1;
		field_145789_f = -1;
		damage = 5.0;
		age = 0;
		pierce = 0;
		dmgIncrease = 0;
		renderDistanceWeight = 10.0;
		shootingEntity = par2EntityLivingBase;
		ability = effect;
		archergun = arch;
		if (par2EntityLivingBase instanceof EntityPlayer && !((EntityPlayer)par2EntityLivingBase).capabilities.isCreativeMode) {
			canBePickedUp = 1;
		}
		setSize(0.5f, 0.5f);
		setLocationAndAngles(par2EntityLivingBase.posX, par2EntityLivingBase.posY + par2EntityLivingBase.getEyeHeight(), par2EntityLivingBase.posZ, par2EntityLivingBase.rotationYaw, par2EntityLivingBase.rotationPitch);
		posX -= MathHelper.cos(rotationYaw / 180.0f * 3.1415927f) * 0.16f;
		posY -= 0.10000000149011612;
		posZ -= MathHelper.sin(rotationYaw / 180.0f * 3.1415927f) * 0.16f;
		setPosition(posX, posY, posZ);
		yOffset = 0.0f;
		motionX = -MathHelper.sin(rotationYaw / 180.0f * 3.1415927f) * MathHelper.cos(rotationPitch / 180.0f * 3.1415927f);
		motionZ = MathHelper.cos(rotationYaw / 180.0f * 3.1415927f) * MathHelper.cos(rotationPitch / 180.0f * 3.1415927f);
		motionY = -MathHelper.sin(rotationPitch / 180.0f * 3.1415927f);
		setThrowableHeading(motionX, motionY, motionZ, par3 * 1.5f, 1.0f);
	}

	protected void entityInit() {
		dataWatcher.addObject(16, (byte)0);
	}

	public void setThrowableHeading(double par1, double par3, double par5, final float par7, final float par8) {
		final float f2 = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
		par1 /= f2;
		par3 /= f2;
		par5 /= f2;
		par1 += rand.nextGaussian() * (rand.nextBoolean() ? -1 : 1) * 0.007499999832361937 * par8;
		par3 += rand.nextGaussian() * (rand.nextBoolean() ? -1 : 1) * 0.007499999832361937 * par8;
		par5 += rand.nextGaussian() * (rand.nextBoolean() ? -1 : 1) * 0.007499999832361937 * par8;
		par1 *= par7;
		par3 *= par7;
		par5 *= par7;
		motionX = par1;
		motionY = par3;
		motionZ = par5;
		final float f3 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
		final float n = (float)(Math.atan2(par1, par5) * 180.0 / 3.141592653589793);
		rotationYaw = n;
		prevRotationYaw = n;
		final float n2 = (float)(Math.atan2(par3, f3) * 180.0 / 3.141592653589793);
		rotationPitch = n2;
		prevRotationPitch = n2;
		ticksInGround = 0;
	}

	@SideOnly(Side.CLIENT)
	public void setPositionAndRotation2(final double par1, final double par3, final double par5, final float par7, final float par8, final int par9) {
		setPosition(par1, par3, par5);
		setRotation(par7, par8);
	}

	@SideOnly(Side.CLIENT)
	public void setVelocity(final double par1, final double par3, final double par5) {
		motionX = par1;
		motionY = par3;
		motionZ = par5;
		if (prevRotationPitch == 0.0f && prevRotationYaw == 0.0f) {
			final float f = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
			final float n = (float)(Math.atan2(par1, par5) * 180.0 / 3.141592653589793);
			rotationYaw = n;
			prevRotationYaw = n;
			final float n2 = (float)(Math.atan2(par3, f) * 180.0 / 3.141592653589793);
			rotationPitch = n2;
			prevRotationPitch = n2;
			prevRotationPitch = rotationPitch;
			prevRotationYaw = rotationYaw;
			setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
			ticksInGround = 0;
		}
	}

	public void onUpdate() {
		super.onEntityUpdate();
		++age;
		if (ability == 17 && age % 2 == 0) {
			++dmgIncrease;
		}
		if (archergun && age % 4 == 0) {
			++dmgIncrease;
		}
		if (ability == 19) {
			final int xpos = (int)Math.floor(posX);
			final int zpos = (int)Math.floor(posZ);
			if (worldObj.getBlock(xpos, (int)Math.floor(posY), zpos) == Blocks.air) {
				int n = (int)Math.floor(posY);
				while (n > 0) {
					if (worldObj.getBlock(xpos, n, zpos) != Blocks.air) {
						if (worldObj.getBlock(xpos, n, zpos).isOpaqueCube()) {
							worldObj.setBlock(xpos, n + 1, zpos, Blockizer.blockOrangeAcid);
							break;
						}
						break;
					}
					else {
						--n;
					}
				}
			}
		}
		if (prevRotationPitch == 0.0f && prevRotationYaw == 0.0f) {
			final float f = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
			final float n2 = (float)(Math.atan2(motionX, motionZ) * 180.0 / 3.141592653589793);
			rotationYaw = n2;
			prevRotationYaw = n2;
			final float n3 = (float)(Math.atan2(motionY, f) * 180.0 / 3.141592653589793);
			rotationPitch = n3;
			prevRotationPitch = n3;
		}
		final Block block = worldObj.getBlock(field_145791_d, field_145792_e, field_145789_f);
		if (block.getMaterial() != Material.air) {
			block.setBlockBoundsBasedOnState(worldObj, field_145791_d, field_145792_e, field_145789_f);
			final AxisAlignedBB axisalignedbb = block.getCollisionBoundingBoxFromPool(worldObj, field_145791_d, field_145792_e, field_145789_f);
			if (axisalignedbb != null && axisalignedbb.isVecInside(Vec3.createVectorHelper(posX, posY, posZ))) {
				inGround = true;
			}
		}
		if (arrowShake > 0) {
			--arrowShake;
		}
		if (inGround) {
			final int j = worldObj.getBlockMetadata(field_145791_d, field_145792_e, field_145789_f);
			if (block == field_145790_g && j == inData) {
				++ticksInGround;
				if (ticksInGround == 1200) {
					setDead();
				}
			}
			else {
				inGround = false;
				motionX *= rand.nextFloat() * 0.2f;
				motionY *= rand.nextFloat() * 0.2f;
				motionZ *= rand.nextFloat() * 0.2f;
				ticksInGround = 0;
				ticksInAir = 0;
			}
		}
		else {
			++ticksInAir;
			Vec3 vec31 = Vec3.createVectorHelper(posX, posY, posZ);
			Vec3 vec32 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
			MovingObjectPosition movingobjectposition = worldObj.func_147447_a(vec31, vec32, false, true, false);
			vec31 = Vec3.createVectorHelper(posX, posY, posZ);
			vec32 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
			if (movingobjectposition != null) {
				vec32 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
			}
			Entity entity = null;
			final List<Entity> list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(1.0, 1.0, 1.0));
			double d0 = 0.0;
			for (int i = 0; i < list.size(); ++i) {
				final Entity entity2 = list.get(i);
				if (entity2.canBeCollidedWith() && (entity2 != shootingEntity || ticksInAir >= 5)) {
					final float f2 = 0.3f;
					final AxisAlignedBB axisalignedbb2 = entity2.boundingBox.expand((double)f2, (double)f2, (double)f2);
					final MovingObjectPosition movingobjectposition2 = axisalignedbb2.calculateIntercept(vec31, vec32);
					if (movingobjectposition2 != null) {
						final double d2 = vec31.distanceTo(movingobjectposition2.hitVec);
						if (d2 < d0 || d0 == 0.0) {
							entity = entity2;
							d0 = d2;
						}
					}
				}
			}
			if (entity != null) {
				movingobjectposition = new MovingObjectPosition(entity);
			}
			if (movingobjectposition != null && movingobjectposition.entityHit != null && movingobjectposition.entityHit instanceof EntityPlayer) {
				final EntityPlayer entityplayer = (EntityPlayer)movingobjectposition.entityHit;
				if (entityplayer.capabilities.disableDamage || (shootingEntity instanceof EntityPlayer && !((EntityPlayer)shootingEntity).canAttackPlayer(entityplayer))) {
					movingobjectposition = null;
				}
			}
			if (movingobjectposition != null) {
				if (movingobjectposition.entityHit instanceof EntityDragonPart || movingobjectposition.entityHit instanceof EntityEnderCrystal) {
					movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeArrowDamage(this, this), (float)damage);
				}
				else if (movingobjectposition.entityHit instanceof EntityLivingBase) {
					final float f3 = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
					int k = MathHelper.ceiling_double_int(f3 * damage);
					if (getIsCritical()) {
						k += rand.nextInt(k / 2 + 2);
					}
					if (ability == 11 && rand.nextInt(3) == 2) {
						k *= 2;
					}
					if (ability == 12) {
						k += 8;
					}
					if (ability == 13) {
						k = k * 2 + 4;
					}
					if (ability == 10) {
						k = rand.nextInt(14) + 7;
					}
					if (ability == 17) {
						k += dmgIncrease;
					}
					if (archergun) {
						k += dmgIncrease;
					}
					DamageSource damagesource = null;
					if (shootingEntity == null) {
						damagesource = DamageSource.causeArrowDamage(this, this);
						if (ability == 1 && movingobjectposition.entityHit instanceof EntityLivingBase) {
							worldObj.createExplosion(shootingEntity, posX, posY, posZ, 2.0f, false);
						}
						if (ability == 2 && movingobjectposition.entityHit instanceof EntityLivingBase) {
							((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(2, 40, 2));
						}
						if (ability == 3 && movingobjectposition.entityHit instanceof EntityLivingBase) {
							((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(20, 40, 2));
						}
						if (ability == 4 && movingobjectposition.entityHit instanceof EntityLivingBase) {
							((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(19, 40, 2));
						}
						if (ability == 5 && movingobjectposition.entityHit instanceof EntityLivingBase) {
							((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(18, 40, 2));
						}
						if (ability == 6 && movingobjectposition.entityHit instanceof EntityLivingBase) {
							movingobjectposition.entityHit.setFire(5);
						}
						if (ability == 7 && movingobjectposition.entityHit instanceof EntityLivingBase && !worldObj.isRemote) {
							worldObj.addWeatherEffect(new EntityLightningBolt(worldObj, posX, posY, posZ));
						}
						if (ability == 9 && rand.nextInt(10) == 7 && !worldObj.isRemote) {
							final EntityRosid var5 = new EntityRosid(worldObj);
							var5.setPosition(posX, posY + 1.0, posZ);
							worldObj.spawnEntityInWorld(var5);
						}
						if (ability == 16 && rand.nextInt(4) == 3 && !worldObj.isRemote) {
							final EntityRosid var5 = new EntityRosid(worldObj);
							var5.setPosition(posX, posY + 1.0, posZ);
							worldObj.spawnEntityInWorld(var5);
						}
						if (ability == 15 && movingobjectposition.entityHit instanceof EntityLivingBase) {
							((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(19, 170, 4));
						}
					}
					else {
						damagesource = DamageSource.causeArrowDamage(this, shootingEntity);
						if (ability == 1) {
							worldObj.createExplosion(this, posX, posY, posZ, 2.0f, false);
						}
						if (ability == 2 && movingobjectposition.entityHit instanceof EntityLivingBase) {
							((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(2, 40, 2));
						}
						if (ability == 3 && movingobjectposition.entityHit instanceof EntityLivingBase) {
							((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(20, 40, 2));
						}
						if (ability == 4 && movingobjectposition.entityHit instanceof EntityLivingBase) {
							((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(19, 40, 2));
						}
						if (ability == 5 && movingobjectposition.entityHit instanceof EntityLivingBase) {
							((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(18, 40, 2));
						}
						if (ability == 6 && movingobjectposition.entityHit instanceof EntityLivingBase) {
							movingobjectposition.entityHit.setFire(5);
						}
						if (ability == 7 && !worldObj.isRemote) {
							worldObj.spawnEntityInWorld(new EntityLightningBolt(worldObj, posX, posY, posZ));
						}
						if (ability == 8 && shootingEntity instanceof EntityPlayer) {
							((EntityPlayer)shootingEntity).heal(3.0f);
						}
						if (ability == 14 && shootingEntity instanceof EntityPlayer) {
							((EntityPlayer)shootingEntity).heal(10.0f);
						}
						if (ability == 15) {
							((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(19, 170, 4));
						}
						if (ability == 9 && rand.nextInt(10) == 7 && !worldObj.isRemote) {
							final EntityRosid var5 = new EntityRosid(worldObj);
							var5.setPosition(posX, posY + 1.0, posZ);
							worldObj.spawnEntityInWorld(var5);
						}
						if (ability == 16 && rand.nextInt(4) == 3 && !worldObj.isRemote) {
							final EntityRosid var5 = new EntityRosid(worldObj);
							var5.setPosition(posX, posY + 1.0, posZ);
							worldObj.spawnEntityInWorld(var5);
						}
					}
					if (isBurning() && !(movingobjectposition.entityHit instanceof EntityEnderman)) {
						movingobjectposition.entityHit.setFire(5);
					}
					if (movingobjectposition.entityHit.attackEntityFrom(damagesource, (float)k)) {
						if (movingobjectposition.entityHit instanceof EntityLivingBase) {
							final EntityLivingBase entitylivingbase = (EntityLivingBase)movingobjectposition.entityHit;
							if (!worldObj.isRemote) {
								entitylivingbase.setArrowCountInEntity(entitylivingbase.getArrowCountInEntity() + 1);
							}
							if (knockbackStrength > 0) {
								final float f4 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
								if (f4 > 0.0f) {
									movingobjectposition.entityHit.addVelocity(motionX * knockbackStrength * 0.6000000238418579 / f4, 0.1, motionZ * knockbackStrength * 0.6000000238418579 / f4);
								}
							}
							if (shootingEntity != null && shootingEntity instanceof EntityLivingBase) {
								EnchantmentHelper.func_151384_a(entitylivingbase, shootingEntity);
								EnchantmentHelper.func_151385_b((EntityLivingBase)shootingEntity, entitylivingbase);
							}
							if (shootingEntity != null && movingobjectposition.entityHit != shootingEntity && movingobjectposition.entityHit instanceof EntityPlayer && shootingEntity instanceof EntityPlayerMP) {
								((EntityPlayerMP)shootingEntity).playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(6, 0.0f));
							}
						}
						playSound("random.bowhit", 1.0f, 1.2f / (rand.nextFloat() * 0.2f + 0.9f));
						if (!(movingobjectposition.entityHit instanceof EntityEnderman) && ability != 18) {
							setDead();
						}
						else if (!(movingobjectposition.entityHit instanceof EntityEnderman)) {
							if (pierce == 0) {
								++pierce;
							}
							else {
								setDead();
							}
						}
					}
					else {
						motionX *= -0.10000000149011612;
						motionY *= -0.10000000149011612;
						motionZ *= -0.10000000149011612;
						rotationYaw += 180.0f;
						prevRotationYaw += 180.0f;
						ticksInAir = 0;
					}
				}
				else {
					field_145791_d = movingobjectposition.blockX;
					field_145792_e = movingobjectposition.blockY;
					field_145789_f = movingobjectposition.blockZ;
					field_145790_g = block;
					inData = worldObj.getBlockMetadata(field_145791_d, field_145792_e, field_145789_f);
					motionX = (float)(movingobjectposition.hitVec.xCoord - posX);
					motionY = (float)(movingobjectposition.hitVec.yCoord - posY);
					motionZ = (float)(movingobjectposition.hitVec.zCoord - posZ);
					final float f3 = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
					posX -= motionX / f3 * 0.05000000074505806;
					posY -= motionY / f3 * 0.05000000074505806;
					posZ -= motionZ / f3 * 0.05000000074505806;
					playSound("random.bowhit", 1.0f, 1.2f / (rand.nextFloat() * 0.2f + 0.9f));
					inGround = true;
					arrowShake = 7;
					setIsCritical(false);
					if (field_145790_g.getMaterial() != Material.air) {
						field_145790_g.onEntityCollidedWithBlock(worldObj, field_145791_d, field_145792_e, field_145789_f, this);
					}
				}
			}
			if (getIsCritical()) {
				for (int i = 0; i < 4; ++i) {
					worldObj.spawnParticle("crit", posX + motionX * i / 4.0, posY + motionY * i / 4.0, posZ + motionZ * i / 4.0, -motionX, -motionY + 0.2, -motionZ);
				}
			}
			posX += motionX;
			posY += motionY;
			posZ += motionZ;
			final float f3 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
			rotationYaw = (float)(Math.atan2(motionX, motionZ) * 180.0 / 3.141592653589793);
			rotationPitch = (float)(Math.atan2(motionY, f3) * 180.0 / 3.141592653589793);
			while (rotationPitch - prevRotationPitch < -180.0f) {
				prevRotationPitch -= 360.0f;
			}
			while (rotationPitch - prevRotationPitch >= 180.0f) {
				prevRotationPitch += 360.0f;
			}
			while (rotationYaw - prevRotationYaw < -180.0f) {
				prevRotationYaw -= 360.0f;
			}
			while (rotationYaw - prevRotationYaw >= 180.0f) {
				prevRotationYaw += 360.0f;
			}
			rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2f;
			rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2f;
			float f5 = 0.99f;
			final float f2 = 0.05f;
			if (isInWater()) {
				for (int l = 0; l < 4; ++l) {
					final float f4 = 0.25f;
					worldObj.spawnParticle("bubble", posX - motionX * f4, posY - motionY * f4, posZ - motionZ * f4, motionX, motionY, motionZ);
				}
				f5 = 0.8f;
			}
			if (isWet()) {
				extinguish();
			}
			motionX *= f5;
			motionY *= f5;
			motionZ *= f5;
			motionY -= f2;
			setPosition(posX, posY, posZ);
			func_145775_I();
		}
	}

	public void writeEntityToNBT(final NBTTagCompound par1NBTTagCompound) {
		par1NBTTagCompound.setShort("xTile", (short)field_145791_d);
		par1NBTTagCompound.setShort("yTile", (short)field_145792_e);
		par1NBTTagCompound.setShort("zTile", (short)field_145789_f);
		par1NBTTagCompound.setShort("life", (short)ticksInGround);
		par1NBTTagCompound.setByte("inTile", (byte)Block.getIdFromBlock(field_145790_g));
		par1NBTTagCompound.setByte("inData", (byte)inData);
		par1NBTTagCompound.setByte("shake", (byte)arrowShake);
		par1NBTTagCompound.setByte("inGround", (byte)(inGround ? 1 : 0));
		par1NBTTagCompound.setByte("pickup", (byte)canBePickedUp);
		par1NBTTagCompound.setDouble("damage", damage);
	}

	public void readEntityFromNBT(final NBTTagCompound par1NBTTagCompound) {
		field_145791_d = par1NBTTagCompound.getShort("xTile");
		field_145792_e = par1NBTTagCompound.getShort("yTile");
		field_145789_f = par1NBTTagCompound.getShort("zTile");
		ticksInGround = par1NBTTagCompound.getShort("life");
		field_145790_g = Block.getBlockById(par1NBTTagCompound.getByte("inTile") & 0xFF);
		inData = (par1NBTTagCompound.getByte("inData") & 0xFF);
		arrowShake = (par1NBTTagCompound.getByte("shake") & 0xFF);
		inGround = (par1NBTTagCompound.getByte("inGround") == 1);
		if (par1NBTTagCompound.hasKey("damage", 99)) {
			damage = par1NBTTagCompound.getDouble("damage");
		}
		if (par1NBTTagCompound.hasKey("pickup", 99)) {
			canBePickedUp = par1NBTTagCompound.getByte("pickup");
		}
		else if (par1NBTTagCompound.hasKey("player", 99)) {
			canBePickedUp = (par1NBTTagCompound.getBoolean("player") ? 1 : 0);
		}
	}

	public void onCollideWithPlayer(final EntityPlayer par1EntityPlayer) {
		if (!worldObj.isRemote && inGround && arrowShake <= 0) {
			boolean flag = canBePickedUp == 1 || (canBePickedUp == 2 && par1EntityPlayer.capabilities.isCreativeMode);
			if (canBePickedUp == 1 && !par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Itemizer.ElementalArrow, 1))) {
				flag = false;
			}
			if (flag) {
				playSound("random.pop", 0.2f, ((rand.nextFloat() - rand.nextFloat()) * 0.7f + 1.0f) * 2.0f);
				par1EntityPlayer.onItemPickup(this, 1);
				setDead();
			}
		}
	}

	protected boolean canTriggerWalking() {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public float getShadowSize() {
		return 0.0f;
	}

	public void setDamage(final double par1) {
		damage = par1;
	}

	public double getDamage() {
		return damage;
	}

	public void setKnockbackStrength(final int par1) {
		knockbackStrength = par1;
	}

	public boolean canAttackWithItem() {
		return false;
	}

	public void setIsCritical(final boolean par1) {
		final byte b0 = dataWatcher.getWatchableObjectByte(16);
		if (par1) {
			dataWatcher.updateObject(16, (byte)(b0 | 0x1));
		}
		else {
			dataWatcher.updateObject(16, (byte)(b0 & 0xFFFFFFFE));
		}
	}

	public boolean getIsCritical() {
		final byte b0 = dataWatcher.getWatchableObjectByte(16);
		return (b0 & 0x1) != 0x0;
	}
}
