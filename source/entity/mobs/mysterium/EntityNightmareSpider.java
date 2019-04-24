package net.tslat.aoa3.entity.mobs.mysterium;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.HunterEntity;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityNightmareSpider extends AoAMeleeMob implements HunterEntity {
	public static final float entityWidth = 1.4f;
	private static final DataParameter<Byte> CLIMBING = EntityDataManager.<Byte>createKey(EntityNightmareSpider.class, DataSerializers.BYTE);

	public EntityNightmareSpider(World world) {
		super(world, entityWidth, 0.8125f);
	}

	@Override
	public float getEyeHeight() {
		return 0.59275f;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(CLIMBING, (byte)0);
	}

	@Override
	protected PathNavigate createNavigator(World world) {
		return new PathNavigateClimber(this, world);
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.4;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 60;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 10;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobNightmareSpiderLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobNightmareSpiderDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobNightmareSpiderHit;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundEvents.ENTITY_SPIDER_STEP;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public void setInWeb() {}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (!world.isRemote)
			setBesideClimbableBlock(this.collidedHorizontally);
	}

	@Override
	public boolean isOnLadder() {
		return isBesideClimbableBlock();
	}

	public boolean isBesideClimbableBlock() {
		return (this.dataManager.get(CLIMBING) & 1) != 0;
	}

	public void setBesideClimbableBlock(boolean climbing) {
		byte climbingBit = this.dataManager.get(CLIMBING);

		if (climbing) {
			climbingBit = (byte)(climbingBit | 1);
		}
		else {
			climbingBit = (byte)(climbingBit & -2);
		}

		this.dataManager.set(CLIMBING, climbingBit);
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(4) == 0)
			dropItem(ItemRegister.nightmareFlakes, 1 + rand.nextInt(1 + lootingMod));

		if (rand.nextInt(7) == 0)
			dropItem(Item.getItemFromBlock(BlockRegister.bannerHaunted), 1);
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase) {
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 10, 100, true, true));
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 80, 10, true, true));
		}
	}

	@Override
	public int getHunterReq() {
		return 20;
	}

	@Override
	public float getHunterXp() {
		return 60;
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}
}
