package net.tslat.aoa3.content.entity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.HashMap;

public abstract class AoAAnimal extends Animal implements GeoEntity {
	private final HashMap<String, Integer> animationStates = new HashMap<>(1);
	private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	public AoAAnimal(EntityType<? extends Animal> entityType, Level world) {
		super(entityType, world);
	}

	protected void registerGoals() {
		goalSelector.addGoal(0, new FloatGoal(this));
		goalSelector.addGoal(1, new PanicGoal(this, 1.5D));
		goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
		goalSelector.addGoal(7, new RandomLookAroundGoal(this));

		if (isBreedable()) {
			goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
			goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));

			if (getTemptItem() != null)
				goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(getTemptItem()), false));
		}
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag) {
		xpReward = (int)(getAttributeValue(Attributes.MAX_HEALTH) / 25f);

		return super.finalizeSpawn(world, difficulty, reason, spawnData, dataTag);
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

	@Nullable
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return null;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState block) {
		SoundEvent stepSound = getStepSound(pos, block);

		if (stepSound == null) {
			super.playStepSound(pos, block);
		}
		else {
			playSound(stepSound, 0.55f, 1.0F);
		}
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return stack.getItem() == getTemptItem();
	}

	protected boolean isBreedable() {
		return false;
	}

	@Nullable
	protected Item getTemptItem() {
		return null;
	}

	@Nullable
	@Override
	public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob partner) {
		return null;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.geoCache;
	}
}
