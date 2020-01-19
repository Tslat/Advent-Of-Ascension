package net.tslat.aoa3.entity.base;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;
import java.util.TreeSet;

public abstract class AoAAnimal extends EntityAnimal {
	protected final TreeSet<Enums.MobProperties> mobProperties;

	public AoAAnimal(World world, float entityWidth, float entityHeight) {
		super(world);

		mobProperties = this instanceof SpecialPropertyEntity ? new TreeSet<Enums.MobProperties>() : null;
		experienceValue = ((int)getBaseMaxHealth() / 25);

		setSize(entityWidth, entityHeight);
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIPanic(this, 1.5d));
		tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0D));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(7, new EntityAILookIdle(this));

		if (isBreedable()) {
			tasks.addTask(2, new EntityAIMate(this, 1.0D));
			tasks.addTask(4, new EntityAIFollowParent(this, 1.25D));

			if (getTemptItem() != null)
				tasks.addTask(3, new EntityAITempt(this, 1.25D, getTemptItem(), false));
		}
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();

		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getBaseMaxHealth());
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(getBaseMovementSpeed());
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(getBaseKnockbackResistance());
	}

	protected abstract double getBaseMaxHealth();

	protected abstract double getBaseMovementSpeed();

	protected abstract double getBaseKnockbackResistance();

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return null;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return null;
	}

	protected SoundEvent getStepSound() {
		return SoundEvents.ENTITY_COW_STEP;
	}

	@Override
	public boolean getCanSpawnHere() {
		return checkSpawnChance() && super.getCanSpawnHere();
	}

	protected int getSpawnChanceFactor() {
		return 1;
	}

	private boolean checkSpawnChance() {
		return getSpawnChanceFactor() <= 1 || rand.nextInt(getSpawnChanceFactor()) == 0;
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return stack.getItem() == getTemptItem();
	}

	protected boolean isBreedable() {
		return false;
	}

	@Nullable
	protected Item getTemptItem() {
		return null;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block block) {
		if (getStepSound() != null)
			playSound(getStepSound(), 0.55f, 1.0F);
	}

	@Nullable
	@Override
	public EntityAgeable createChild(EntityAgeable mate) {
		return null;
	}
}
