package net.tslat.aoa3.entity.base;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IWorld;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;

public abstract class AoAAmbientNPC extends CreatureEntity implements INPC {
	public AoAAmbientNPC(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new SwimGoal(this));
		goalSelector.addGoal(1, new PanicGoal(this, 1));
		goalSelector.addGoal(1, new AvoidEntityGoal<AoAMeleeMob>(this, AoAMeleeMob.class, 8f, 0.8d, 1d));
		goalSelector.addGoal(2, new OpenDoorGoal(this, true));
		goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 3f, 1f));
		goalSelector.addGoal(4, new RandomWalkingGoal(this, 0.6d));
		goalSelector.addGoal(5, new LookRandomlyGoal(this));
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return null;
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

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getBaseMaxHealth());
		getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(getBaseMovementSpeed());
		getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16);
	}

	protected abstract double getBaseMaxHealth();

	protected abstract double getBaseMovementSpeed();

	@Nullable
	protected abstract String getInteractMessage(ItemStack heldItem);

	protected boolean isFixedTradesList() {
		return false;
	}

	@Override
	public boolean canSpawn(IWorld world, SpawnReason reason) {
		return checkSpawnChance(reason) && isValidLightLevel(reason) && canSpawnAt(reason, world.getBlockState(getPosition().down()));
	}

	protected boolean canSpawnAt(SpawnReason reason, BlockState blockState) {
		return reason == SpawnReason.SPAWNER || blockState.canEntitySpawn(world, getPosition(), getType());
	}

	protected int getSpawnChanceFactor() {
		return 1;
	}

	private boolean checkSpawnChance(SpawnReason reason) {
		return EntityUtil.isNaturalSpawnReason(reason) || getSpawnChanceFactor() <= 1 || rand.nextInt(getSpawnChanceFactor()) == 0;
	}

	protected boolean isValidLightLevel(SpawnReason reason) {
		if (world.getDimension().getType() != DimensionType.OVERWORLD)
			return true;

		BlockPos blockpos = new BlockPos(getPosX(), getBoundingBox().minY, getPosZ());

		if (world.getLightFor(LightType.SKY, blockpos) > rand.nextInt(32)) {
			return true;
		}
		else {
			int light = world.isThundering() ? world.getNeighborAwareLightSubtracted(blockpos, 10) : (int)world.getBrightness(blockpos) * 15;

			return light > rand.nextInt(8);
		}
	}

	@Override
	protected boolean processInteract(PlayerEntity player, Hand hand) {
		ItemStack heldStack = player.getHeldItem(hand);

		if (heldStack.getItem() == Items.NAME_TAG) {
			heldStack.interactWithEntity(player, this, hand);

			return true;
		}

		if (!world.isRemote) {
			if (hand == Hand.MAIN_HAND) {
				String msg = getInteractMessage(heldStack);

				if (msg != null)
					PlayerUtil.notifyPlayer((ServerPlayerEntity)player, msg, TextFormatting.GRAY);
			}
		}

		return super.processInteract(player, hand);
	}
}
