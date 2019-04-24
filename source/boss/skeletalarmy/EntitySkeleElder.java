package net.nevermine.boss.skeletalarmy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntitySkeleElder extends EntityMob {
	private int wave;

	public EntitySkeleElder(final World par1World) {
		super(par1World);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(), false));
		tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0, true));
		tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue()));
		tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		tasks.addTask(8, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		setSize(0.7f, 3.2f);
		if (wave == 0) {
			wave = 1;
		}
	}

	protected String getLivingSound() {
		return "mob.skeleton.say";
	}

	protected String getDeathSound() {
		return "mob.skeleton.death";
	}

	protected String getHurtSound() {
		return "mob.skeleton.hurt";
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.1);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(60.0);
	}

	protected boolean isAIEnabled() {
		return true;
	}

	private void transform() {
		if (!worldObj.isRemote) {
			if (wave == 1) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 2;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkelepig var3 = new EntitySkelepig(worldObj);
				var3.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var3);
				final EntitySkelepig var4 = new EntitySkelepig(worldObj);
				var4.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var4);
				setDead();
			}
			else if (wave == 2) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 3;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkeleHopper var5 = new EntitySkeleHopper(worldObj);
				var5.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var5);
				setDead();
			}
			else if (wave == 3) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 4;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkeleHopper var5 = new EntitySkeleHopper(worldObj);
				var5.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var5);
				final EntitySkeleHopper var6 = new EntitySkeleHopper(worldObj);
				var6.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var6);
				setDead();
			}
			else if (wave == 4) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 5;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkeleman var7 = new EntitySkeleman(worldObj);
				var7.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var7);
			}
			else if (wave == 5) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 6;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkeleman var7 = new EntitySkeleman(worldObj);
				var7.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var7);
				final EntitySkeleman var8 = new EntitySkeleman(worldObj);
				var8.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var8);
			}
			else if (wave == 6) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 7;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkeleHopper var5 = new EntitySkeleHopper(worldObj);
				var5.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var5);
				final EntitySkelepig var4 = new EntitySkelepig(worldObj);
				var4.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var4);
			}
			else if (wave == 7) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 8;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkeleHopper var5 = new EntitySkeleHopper(worldObj);
				var5.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var5);
				final EntitySkelepig var4 = new EntitySkelepig(worldObj);
				var4.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var4);
				final EntitySkeleman var9 = new EntitySkeleman(worldObj);
				var9.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var9);
			}
			else if (wave == 8) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 9;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkeleHopper var5 = new EntitySkeleHopper(worldObj);
				var5.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var5);
				final EntitySkelepig var4 = new EntitySkelepig(worldObj);
				var4.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var4);
				final EntitySkeleman var9 = new EntitySkeleman(worldObj);
				var9.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var9);
				final EntitySkeleHopper var10 = new EntitySkeleHopper(worldObj);
				var10.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var10);
				final EntitySkelepig var11 = new EntitySkelepig(worldObj);
				var11.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var11);
			}
			else if (wave == 9) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 10;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkeleHopper var5 = new EntitySkeleHopper(worldObj);
				var5.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var5);
				final EntitySkelepig var4 = new EntitySkelepig(worldObj);
				var4.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var4);
				final EntitySkeleman var9 = new EntitySkeleman(worldObj);
				var9.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var9);
				final EntitySkeleHopper var10 = new EntitySkeleHopper(worldObj);
				var10.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var10);
				final EntitySkelepig var11 = new EntitySkelepig(worldObj);
				var11.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var11);
				final EntitySkeleman var12 = new EntitySkeleman(worldObj);
				var9.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var12);
			}
			else if (wave == 10) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 11;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkeleman var13 = new EntitySkeleman(worldObj);
				var13.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var13);
				final EntitySkeleman var14 = new EntitySkeleman(worldObj);
				var14.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var14);
				final EntitySkeleman var15 = new EntitySkeleman(worldObj);
				var15.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var15);
			}
			else if (wave == 11) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 12;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkeleman var13 = new EntitySkeleman(worldObj);
				var13.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var13);
				final EntitySkeleman var14 = new EntitySkeleman(worldObj);
				var14.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var14);
				final EntitySkeleman var15 = new EntitySkeleman(worldObj);
				var15.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var15);
				final EntitySkelepig var16 = new EntitySkelepig(worldObj);
				var16.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var16);
				final EntitySkelepig var17 = new EntitySkelepig(worldObj);
				var17.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var17);
				final EntitySkelepig var18 = new EntitySkelepig(worldObj);
				var18.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var18);
			}
			else if (wave == 12) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 13;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkelepigStrong var19 = new EntitySkelepigStrong(worldObj);
				var19.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var19);
			}
			else if (wave == 13) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 14;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkeleHopperStrong var20 = new EntitySkeleHopperStrong(worldObj);
				var20.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var20);
			}
			else if (wave == 14) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 15;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkelemanStrong var21 = new EntitySkelemanStrong(worldObj);
				var21.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var21);
			}
			else if (wave == 15) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 16;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkelepigStrong var19 = new EntitySkelepigStrong(worldObj);
				var19.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var19);
				final EntitySkelepigStrong var22 = new EntitySkelepigStrong(worldObj);
				var22.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var22);
				final EntitySkelepigStrong var23 = new EntitySkelepigStrong(worldObj);
				var23.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var23);
			}
			else if (wave == 16) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 17;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkeleHopperStrong var20 = new EntitySkeleHopperStrong(worldObj);
				var20.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var20);
				final EntitySkeleHopperStrong var24 = new EntitySkeleHopperStrong(worldObj);
				var24.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var24);
				final EntitySkeleHopperStrong var25 = new EntitySkeleHopperStrong(worldObj);
				var25.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var25);
			}
			else if (wave == 17) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 18;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkelemanStrong var21 = new EntitySkelemanStrong(worldObj);
				var21.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var21);
				final EntitySkelemanStrong var26 = new EntitySkelemanStrong(worldObj);
				var26.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var26);
				final EntitySkelemanStrong var27 = new EntitySkelemanStrong(worldObj);
				var27.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var27);
			}
			else if (wave == 18) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 19;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkelemanStrong var21 = new EntitySkelemanStrong(worldObj);
				var21.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var21);
				final EntitySkelemanStrong var26 = new EntitySkelemanStrong(worldObj);
				var26.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var26);
				final EntitySkelepigStrong var28 = new EntitySkelepigStrong(worldObj);
				var28.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var28);
				final EntitySkelepigStrong var29 = new EntitySkelepigStrong(worldObj);
				var29.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var29);
				final EntitySkeleHopperStrong var30 = new EntitySkeleHopperStrong(worldObj);
				var30.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var30);
				final EntitySkeleHopperStrong var31 = new EntitySkeleHopperStrong(worldObj);
				var31.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var31);
			}
			else if (wave == 19) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 20;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkelemanStrong var21 = new EntitySkelemanStrong(worldObj);
				var21.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var21);
				final EntitySkelemanStrong var26 = new EntitySkelemanStrong(worldObj);
				var26.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var26);
				final EntitySkelepigStrong var28 = new EntitySkelepigStrong(worldObj);
				var28.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var28);
				final EntitySkelepigStrong var29 = new EntitySkelepigStrong(worldObj);
				var29.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var29);
				final EntitySkeleHopperStrong var30 = new EntitySkeleHopperStrong(worldObj);
				var30.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var30);
				final EntitySkeleHopperStrong var31 = new EntitySkeleHopperStrong(worldObj);
				var31.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var31);
				final EntitySkeleman var32 = new EntitySkeleman(worldObj);
				var32.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var32);
				final EntitySkeleman var33 = new EntitySkeleman(worldObj);
				var33.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var33);
				final EntitySkelepig var34 = new EntitySkelepig(worldObj);
				var34.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var34);
				final EntitySkelepig var35 = new EntitySkelepig(worldObj);
				var35.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var35);
				final EntitySkeleHopper var36 = new EntitySkeleHopper(worldObj);
				var36.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var36);
				final EntitySkeleHopper var37 = new EntitySkeleHopper(worldObj);
				var37.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var37);
			}
			else if (wave == 20) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 21;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkelepigElite var38 = new EntitySkelepigElite(worldObj);
				var38.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var38);
			}
			else if (wave == 21) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 22;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkeleHopperElite var39 = new EntitySkeleHopperElite(worldObj);
				var39.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var39);
			}
			else if (wave == 22) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 23;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkelemanElite var40 = new EntitySkelemanElite(worldObj);
				var40.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var40);
			}
			else if (wave == 23) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 24;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkelepigElite var38 = new EntitySkelepigElite(worldObj);
				var38.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var38);
				final EntitySkelepigElite var41 = new EntitySkelepigElite(worldObj);
				var41.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var41);
				final EntitySkelepigElite var42 = new EntitySkelepigElite(worldObj);
				var42.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var42);
			}
			else if (wave == 24) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 25;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkeleHopperElite var39 = new EntitySkeleHopperElite(worldObj);
				var39.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var39);
				final EntitySkeleHopperElite var43 = new EntitySkeleHopperElite(worldObj);
				var43.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var43);
				final EntitySkeleHopperElite var44 = new EntitySkeleHopperElite(worldObj);
				var44.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var44);
			}
			else if (wave == 25) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 26;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkelemanElite var40 = new EntitySkelemanElite(worldObj);
				var40.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var40);
				final EntitySkelemanElite var45 = new EntitySkelemanElite(worldObj);
				var45.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var45);
				final EntitySkelemanElite var46 = new EntitySkelemanElite(worldObj);
				var46.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var46);
			}
			else if (wave == 26) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 27;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkelemanElite var40 = new EntitySkelemanElite(worldObj);
				var40.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var40);
				final EntitySkelemanElite var45 = new EntitySkelemanElite(worldObj);
				var45.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var45);
				final EntitySkelepigElite var47 = new EntitySkelepigElite(worldObj);
				var47.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var47);
				final EntitySkelepigElite var48 = new EntitySkelepigElite(worldObj);
				var48.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var48);
				final EntitySkeleHopperElite var49 = new EntitySkeleHopperElite(worldObj);
				var49.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var49);
				final EntitySkeleHopperElite var50 = new EntitySkeleHopperElite(worldObj);
				var50.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var50);
			}
			else if (wave == 27) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 28;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkelemanElite var40 = new EntitySkelemanElite(worldObj);
				var40.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var40);
				final EntitySkelemanElite var45 = new EntitySkelemanElite(worldObj);
				var45.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var45);
				final EntitySkelemanElite var46 = new EntitySkelemanElite(worldObj);
				var46.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var46);
				final EntitySkelepigElite var51 = new EntitySkelepigElite(worldObj);
				var51.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var51);
				final EntitySkelepigElite var52 = new EntitySkelepigElite(worldObj);
				var52.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var52);
				final EntitySkelepigElite var53 = new EntitySkelepigElite(worldObj);
				var53.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var53);
				final EntitySkeleHopperElite var54 = new EntitySkeleHopperElite(worldObj);
				var54.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var54);
				final EntitySkeleHopperElite var55 = new EntitySkeleHopperElite(worldObj);
				var55.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var55);
				final EntitySkeleHopperElite var56 = new EntitySkeleHopperElite(worldObj);
				var56.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var56);
			}
			else if (wave == 28) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 29;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkelemanElite var40 = new EntitySkelemanElite(worldObj);
				var40.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var40);
				final EntitySkelemanElite var45 = new EntitySkelemanElite(worldObj);
				var45.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var45);
				final EntitySkelemanElite var57 = new EntitySkelemanElite(worldObj);
				var57.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var57);
				final EntitySkelemanElite var58 = new EntitySkelemanElite(worldObj);
				var58.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var58);
				final EntitySkelepigElite var59 = new EntitySkelepigElite(worldObj);
				var59.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var59);
				final EntitySkelepigElite var60 = new EntitySkelepigElite(worldObj);
				var60.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var60);
				final EntitySkelepigElite var61 = new EntitySkelepigElite(worldObj);
				var61.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var61);
				final EntitySkelepigElite var62 = new EntitySkelepigElite(worldObj);
				var62.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var62);
			}
			else if (wave == 29) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 30;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkelepigElite var63 = new EntitySkelepigElite(worldObj);
				var63.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var63);
				final EntitySkelepigElite var64 = new EntitySkelepigElite(worldObj);
				var64.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var64);
				final EntitySkelepigElite var65 = new EntitySkelepigElite(worldObj);
				var65.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var65);
				final EntitySkelepigElite var66 = new EntitySkelepigElite(worldObj);
				var66.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var66);
				final EntitySkelepigElite var67 = new EntitySkelepigElite(worldObj);
				var67.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var67);
				final EntitySkeleHopperElite var68 = new EntitySkeleHopperElite(worldObj);
				var68.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var68);
				final EntitySkeleHopperElite var69 = new EntitySkeleHopperElite(worldObj);
				var69.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var69);
				final EntitySkeleHopperElite var70 = new EntitySkeleHopperElite(worldObj);
				var70.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var70);
				final EntitySkeleHopperElite var71 = new EntitySkeleHopperElite(worldObj);
				var71.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var71);
				final EntitySkeleHopperElite var72 = new EntitySkeleHopperElite(worldObj);
				var72.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var72);
				final EntitySkelemanElite var73 = new EntitySkelemanElite(worldObj);
				var73.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var73);
				final EntitySkelemanElite var74 = new EntitySkelemanElite(worldObj);
				var74.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var74);
				final EntitySkelemanElite var75 = new EntitySkelemanElite(worldObj);
				var75.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var75);
				final EntitySkelemanElite var76 = new EntitySkelemanElite(worldObj);
				var76.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var76);
				final EntitySkelemanStrong var77 = new EntitySkelemanStrong(worldObj);
				var77.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var77);
				final EntitySkelemanStrong var78 = new EntitySkelemanStrong(worldObj);
				var78.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var78);
			}
			else if (wave == 30) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 31;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkelepigElite var63 = new EntitySkelepigElite(worldObj);
				var63.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var63);
				final EntitySkelepigElite var64 = new EntitySkelepigElite(worldObj);
				var64.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var64);
				final EntitySkelepigElite var65 = new EntitySkelepigElite(worldObj);
				var65.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var65);
				final EntitySkelepigElite var66 = new EntitySkelepigElite(worldObj);
				var66.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var66);
				final EntitySkelepigElite var67 = new EntitySkelepigElite(worldObj);
				var67.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var67);
				final EntitySkelepigElite var79 = new EntitySkelepigElite(worldObj);
				var79.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var79);
				final EntitySkelepigElite var80 = new EntitySkelepigElite(worldObj);
				var80.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var80);
				final EntitySkelepigElite var81 = new EntitySkelepigElite(worldObj);
				var81.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var81);
			}
			else if (wave == 31) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 32;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkeleHopperElite var82 = new EntitySkeleHopperElite(worldObj);
				var82.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var82);
				final EntitySkeleHopperElite var83 = new EntitySkeleHopperElite(worldObj);
				var83.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var83);
				final EntitySkeleHopperElite var84 = new EntitySkeleHopperElite(worldObj);
				var84.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var84);
				final EntitySkeleHopperElite var85 = new EntitySkeleHopperElite(worldObj);
				var85.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var85);
				final EntitySkeleHopperElite var86 = new EntitySkeleHopperElite(worldObj);
				var86.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var86);
				final EntitySkeleHopperElite var87 = new EntitySkeleHopperElite(worldObj);
				var87.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var87);
				final EntitySkeleHopperElite var88 = new EntitySkeleHopperElite(worldObj);
				var88.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var88);
				final EntitySkeleHopperElite var89 = new EntitySkeleHopperElite(worldObj);
				var89.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var89);
			}
			else if (wave == 32) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 33;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkelemanElite var90 = new EntitySkelemanElite(worldObj);
				var90.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var90);
				final EntitySkelemanElite var91 = new EntitySkelemanElite(worldObj);
				var91.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var91);
				final EntitySkelemanElite var92 = new EntitySkelemanElite(worldObj);
				var92.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var92);
				final EntitySkelemanElite var93 = new EntitySkelemanElite(worldObj);
				var93.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var93);
				final EntitySkelemanElite var94 = new EntitySkelemanElite(worldObj);
				var94.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var94);
				final EntitySkelemanElite var95 = new EntitySkelemanElite(worldObj);
				var95.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var95);
				final EntitySkelemanElite var96 = new EntitySkelemanElite(worldObj);
				var96.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var96);
				final EntitySkelemanElite var97 = new EntitySkelemanElite(worldObj);
				var97.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var97);
			}
			else if (wave == 33) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 34;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkelepigElite var63 = new EntitySkelepigElite(worldObj);
				var63.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var63);
				final EntitySkelepigElite var64 = new EntitySkelepigElite(worldObj);
				var64.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var64);
				final EntitySkelepigElite var65 = new EntitySkelepigElite(worldObj);
				var65.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var65);
				final EntitySkelepigElite var66 = new EntitySkelepigElite(worldObj);
				var66.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var66);
				final EntitySkelepigElite var67 = new EntitySkelepigElite(worldObj);
				var67.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var67);
				final EntitySkelepigElite var79 = new EntitySkelepigElite(worldObj);
				var79.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var79);
				final EntitySkelepigElite var80 = new EntitySkelepigElite(worldObj);
				var80.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var80);
				final EntitySkelepigElite var81 = new EntitySkelepigElite(worldObj);
				var81.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var81);
				final EntitySkelepigElite var98 = new EntitySkelepigElite(worldObj);
				var98.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var98);
				final EntitySkelepigElite var99 = new EntitySkelepigElite(worldObj);
				var99.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var99);
				final EntitySkelepigElite var100 = new EntitySkelepigElite(worldObj);
				var100.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var100);
				final EntitySkelepigElite var101 = new EntitySkelepigElite(worldObj);
				var101.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var101);
			}
			else if (wave == 34) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 35;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkeleHopperElite var82 = new EntitySkeleHopperElite(worldObj);
				var82.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var82);
				final EntitySkeleHopperElite var83 = new EntitySkeleHopperElite(worldObj);
				var83.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var83);
				final EntitySkeleHopperElite var84 = new EntitySkeleHopperElite(worldObj);
				var84.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var84);
				final EntitySkeleHopperElite var85 = new EntitySkeleHopperElite(worldObj);
				var85.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var85);
				final EntitySkeleHopperElite var86 = new EntitySkeleHopperElite(worldObj);
				var86.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var86);
				final EntitySkeleHopperElite var87 = new EntitySkeleHopperElite(worldObj);
				var87.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var87);
				final EntitySkeleHopperElite var88 = new EntitySkeleHopperElite(worldObj);
				var88.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var88);
				final EntitySkeleHopperElite var89 = new EntitySkeleHopperElite(worldObj);
				var89.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var89);
				final EntitySkeleHopperElite var102 = new EntitySkeleHopperElite(worldObj);
				var102.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var102);
				final EntitySkeleHopperElite var103 = new EntitySkeleHopperElite(worldObj);
				var103.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var103);
				final EntitySkeleHopperElite var104 = new EntitySkeleHopperElite(worldObj);
				var104.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var104);
				final EntitySkeleHopperElite var105 = new EntitySkeleHopperElite(worldObj);
				var105.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var105);
			}
			else if (wave == 35) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 36;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkelemanElite var90 = new EntitySkelemanElite(worldObj);
				var90.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var90);
				final EntitySkelemanElite var91 = new EntitySkelemanElite(worldObj);
				var91.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var91);
				final EntitySkelemanElite var92 = new EntitySkelemanElite(worldObj);
				var92.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var92);
				final EntitySkelemanElite var93 = new EntitySkelemanElite(worldObj);
				var93.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var93);
				final EntitySkelemanElite var94 = new EntitySkelemanElite(worldObj);
				var94.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var94);
				final EntitySkelemanElite var95 = new EntitySkelemanElite(worldObj);
				var95.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var95);
				final EntitySkelemanElite var96 = new EntitySkelemanElite(worldObj);
				var96.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var96);
				final EntitySkelemanElite var97 = new EntitySkelemanElite(worldObj);
				var97.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var97);
				final EntitySkelemanElite var106 = new EntitySkelemanElite(worldObj);
				var106.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var106);
				final EntitySkelemanElite var107 = new EntitySkelemanElite(worldObj);
				var107.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var107);
				final EntitySkelemanElite var108 = new EntitySkelemanElite(worldObj);
				var108.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var108);
				final EntitySkelemanElite var109 = new EntitySkelemanElite(worldObj);
				var109.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var109);
			}
			else if (wave == 36) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 37;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkelepigElite var110 = new EntitySkelepigElite(worldObj);
				var110.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var110);
				final EntitySkelepigElite var111 = new EntitySkelepigElite(worldObj);
				var111.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var111);
				final EntitySkelepigElite var112 = new EntitySkelepigElite(worldObj);
				var112.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var112);
				final EntitySkelepigElite var113 = new EntitySkelepigElite(worldObj);
				var113.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var113);
				final EntitySkelepigElite var114 = new EntitySkelepigElite(worldObj);
				var114.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var114);
				final EntitySkelepigElite var115 = new EntitySkelepigElite(worldObj);
				var115.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var115);
				final EntitySkeleHopperElite var116 = new EntitySkeleHopperElite(worldObj);
				var116.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var116);
				final EntitySkeleHopperElite var117 = new EntitySkeleHopperElite(worldObj);
				var117.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var117);
				final EntitySkeleHopperElite var118 = new EntitySkeleHopperElite(worldObj);
				var118.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var118);
				final EntitySkeleHopperElite var119 = new EntitySkeleHopperElite(worldObj);
				var119.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var119);
				final EntitySkeleHopperElite var120 = new EntitySkeleHopperElite(worldObj);
				var120.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var120);
				final EntitySkeleHopperElite var121 = new EntitySkeleHopperElite(worldObj);
				var121.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var121);
				final EntitySkelemanElite var122 = new EntitySkelemanElite(worldObj);
				var122.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var122);
				final EntitySkelemanElite var123 = new EntitySkelemanElite(worldObj);
				var123.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var123);
				final EntitySkelemanElite var124 = new EntitySkelemanElite(worldObj);
				var124.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var124);
				final EntitySkelemanElite var125 = new EntitySkelemanElite(worldObj);
				var125.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var125);
				final EntitySkelemanElite var126 = new EntitySkelemanElite(worldObj);
				var126.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var126);
				final EntitySkelemanElite var127 = new EntitySkelemanElite(worldObj);
				var127.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var127);
				final EntitySkelemanStrong var128 = new EntitySkelemanStrong(worldObj);
				var128.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var128);
				final EntitySkelemanStrong var129 = new EntitySkelemanStrong(worldObj);
				var129.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var129);
				final EntitySkelepigStrong var130 = new EntitySkelepigStrong(worldObj);
				var130.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var130);
				final EntitySkelepigStrong var131 = new EntitySkelepigStrong(worldObj);
				var131.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var131);
				final EntitySkeleHopperStrong var132 = new EntitySkeleHopperStrong(worldObj);
				var132.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var132);
				final EntitySkeleHopperStrong var133 = new EntitySkeleHopperStrong(worldObj);
				var133.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var133);
			}
			else if (wave == 37) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 38;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkelemanElite var90 = new EntitySkelemanElite(worldObj);
				var90.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var90);
				final EntitySkelemanElite var91 = new EntitySkelemanElite(worldObj);
				var91.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var91);
				final EntitySkelemanElite var92 = new EntitySkelemanElite(worldObj);
				var92.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var92);
				final EntitySkelemanElite var93 = new EntitySkelemanElite(worldObj);
				var93.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var93);
				final EntitySkelemanElite var94 = new EntitySkelemanElite(worldObj);
				var94.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var94);
				final EntitySkelemanElite var95 = new EntitySkelemanElite(worldObj);
				var95.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var95);
				final EntitySkelemanElite var96 = new EntitySkelemanElite(worldObj);
				var96.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var96);
				final EntitySkelemanElite var97 = new EntitySkelemanElite(worldObj);
				var97.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var97);
				final EntitySkelemanElite var106 = new EntitySkelemanElite(worldObj);
				var106.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var106);
				final EntitySkelemanElite var107 = new EntitySkelemanElite(worldObj);
				var107.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var107);
				final EntitySkelemanElite var108 = new EntitySkelemanElite(worldObj);
				var108.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var108);
				final EntitySkelemanElite var109 = new EntitySkelemanElite(worldObj);
				var109.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var109);
				final EntitySkelemanElite var134 = new EntitySkelemanElite(worldObj);
				var134.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var134);
				final EntitySkelemanElite var135 = new EntitySkelemanElite(worldObj);
				var135.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var135);
				final EntitySkelemanElite var136 = new EntitySkelemanElite(worldObj);
				var136.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var136);
			}
			else if (wave == 38) {
				final EntitySkeleElder var2 = new EntitySkeleElder(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				var2.wave = 39;
				worldObj.spawnEntityInWorld(var2);
				final EntitySkelemanElite var90 = new EntitySkelemanElite(worldObj);
				var90.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var90);
				final EntitySkelemanElite var91 = new EntitySkelemanElite(worldObj);
				var91.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var91);
				final EntitySkelemanElite var92 = new EntitySkelemanElite(worldObj);
				var92.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var92);
				final EntitySkelemanElite var93 = new EntitySkelemanElite(worldObj);
				var93.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var93);
				final EntitySkelemanElite var94 = new EntitySkelemanElite(worldObj);
				var94.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var94);
				final EntitySkelemanElite var95 = new EntitySkelemanElite(worldObj);
				var95.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var95);
				final EntitySkelemanElite var96 = new EntitySkelemanElite(worldObj);
				var96.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var96);
				final EntitySkelemanElite var97 = new EntitySkelemanElite(worldObj);
				var97.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var97);
				final EntitySkelemanElite var106 = new EntitySkelemanElite(worldObj);
				var106.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var106);
				final EntitySkelemanElite var107 = new EntitySkelemanElite(worldObj);
				var107.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var107);
				final EntitySkelepigElite var137 = new EntitySkelepigElite(worldObj);
				var137.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var137);
				final EntitySkelepigElite var138 = new EntitySkelepigElite(worldObj);
				var138.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var138);
				final EntitySkelepigElite var139 = new EntitySkelepigElite(worldObj);
				var139.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var139);
				final EntitySkelepigElite var140 = new EntitySkelepigElite(worldObj);
				var140.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var140);
				final EntitySkelepigElite var141 = new EntitySkelepigElite(worldObj);
				var141.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var141);
				final EntitySkelepigElite var142 = new EntitySkelepigElite(worldObj);
				var142.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var142);
				final EntitySkelepigElite var143 = new EntitySkelepigElite(worldObj);
				var143.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var143);
				final EntitySkelepigElite var144 = new EntitySkelepigElite(worldObj);
				var144.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var144);
				final EntitySkelepigElite var145 = new EntitySkelepigElite(worldObj);
				var145.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var145);
				final EntitySkelepigElite var146 = new EntitySkelepigElite(worldObj);
				var146.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var146);
				final EntitySkeleHopperElite var147 = new EntitySkeleHopperElite(worldObj);
				var147.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var147);
				final EntitySkeleHopperElite var148 = new EntitySkeleHopperElite(worldObj);
				var148.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var148);
				final EntitySkeleHopperElite var149 = new EntitySkeleHopperElite(worldObj);
				var149.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var149);
				final EntitySkeleHopperElite var150 = new EntitySkeleHopperElite(worldObj);
				var150.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var150);
				final EntitySkeleHopperElite var151 = new EntitySkeleHopperElite(worldObj);
				var151.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var151);
				final EntitySkeleHopperElite var152 = new EntitySkeleHopperElite(worldObj);
				var152.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var152);
				final EntitySkeleHopperElite var153 = new EntitySkeleHopperElite(worldObj);
				var153.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var153);
				final EntitySkeleHopperElite var154 = new EntitySkeleHopperElite(worldObj);
				var154.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var154);
				final EntitySkeleHopperElite var155 = new EntitySkeleHopperElite(worldObj);
				var155.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var155);
				final EntitySkeleHopperElite var156 = new EntitySkeleHopperElite(worldObj);
				var156.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var156);
			}
			else if (wave == 39) {
				final EntitySkeletron var157 = new EntitySkeletron(worldObj);
				var157.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var157);
			}
		}
	}

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);
		transform();
	}
}
