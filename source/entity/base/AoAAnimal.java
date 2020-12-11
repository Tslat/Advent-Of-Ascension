package net.tslat.aoa3.entity.base;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.util.EntityUtil;

import javax.annotation.Nullable;
import java.util.Random;

public abstract class AoAAnimal extends AnimalEntity {
	public AoAAnimal(EntityType<? extends AnimalEntity> entityType, World world) {
		super(entityType, world);

		experienceValue = ((int)getBaseMaxHealth() / 25);
	}

	protected void registerGoals() {
		goalSelector.addGoal(0, new SwimGoal(this));
		goalSelector.addGoal(1, new PanicGoal(this, 1.5D));
		goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		goalSelector.addGoal(7, new LookRandomlyGoal(this));

		if (isBreedable()) {
			goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
			goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));

			if (getTemptItem() != null)
				goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.fromItems(getTemptItem()), false));
		}
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();

		getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getBaseMaxHealth());
		getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(getBaseMovementSpeed());
		getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(getBaseKnockbackResistance());
		getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(getBaseArmor());
	}

	protected abstract double getBaseMaxHealth();

	protected abstract double getBaseMovementSpeed();

	protected double getBaseKnockbackResistance() {
		return 0;
	}

	protected double getBaseArmor() {
		return 0;
	}

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

	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return SoundEvents.ENTITY_COW_STEP;
	}

	public static boolean meetsSpawnConditions(EntityType<? extends MobEntity> type, IWorld world, SpawnReason reason, BlockPos pos, Random random) {
		return true;
	}

	@Override
	public boolean canSpawn(IWorld world, SpawnReason reason) {
		if (!EntityUtil.isNaturalSpawnReason(reason))
			return true;

		BlockPos checkPos = new BlockPos(this);
		BlockState spawnBlock = world.getBlockState(checkPos.down());

		return (spawnBlock.isIn(Tags.Blocks.DIRT) || spawnBlock.isIn(Tags.Blocks.STONE) || spawnBlock.isIn(AoATags.Blocks.GRASS)) && checkSpawningLightConditions();
	}

	protected int getSpawnChanceFactor() {
		return 1;
	}

	private boolean checkSpawnChance() {
		return getSpawnChanceFactor() <= 1 || rand.nextInt(getSpawnChanceFactor()) == 0;
	}

	protected boolean checkSpawningLightConditions() {
		return world.getBrightness(getPosition()) > 0.5f;
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
	protected void playStepSound(BlockPos pos, BlockState block) {
		if (getStepSound(pos, block) != null)
			playSound(getStepSound(pos, block), 0.55f, 1.0F);
	}

	@Nullable
	@Override
	public AgeableEntity createChild(AgeableEntity partner) {
		return null;
	}
}
