package net.tslat.aoa3.entity.base;

import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.INPC;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IWorld;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.PlayerUtil;

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

	@Nullable
	protected abstract String getInteractMessage(ItemStack heldItem);

	protected boolean isFixedTradesList() {
		return false;
	}

	@Override
	public boolean checkSpawnRules(IWorld world, SpawnReason reason) {
		return checkSpawnChance(reason) && isValidLightLevel(reason) && canSpawnAt(reason, world.getBlockState(blockPosition().below()));
	}

	protected boolean canSpawnAt(SpawnReason reason, BlockState blockState) {
		return reason == SpawnReason.SPAWNER || blockState.isValidSpawn(level, blockPosition(), getType());
	}

	protected int getSpawnChanceFactor() {
		return 1;
	}

	private boolean checkSpawnChance(SpawnReason reason) {
		return EntityUtil.isNaturalSpawnReason(reason) || getSpawnChanceFactor() <= 1 || random.nextInt(getSpawnChanceFactor()) == 0;
	}

	protected boolean isValidLightLevel(SpawnReason reason) {
		if (!WorldUtil.isWorld(level, AoADimensions.OVERWORLD.key))
			return true;

		BlockPos blockpos = new BlockPos(getX(), getBoundingBox().minY, getZ());

		if (level.getBrightness(LightType.SKY, blockpos) > random.nextInt(32)) {
			return true;
		}
		else {
			int light = level.isThundering() ? level.getMaxLocalRawBrightness(blockpos, 10) : (int)level.getBrightness(blockpos) * 15;

			return light > random.nextInt(8);
		}
	}

	@Override
	protected ActionResultType mobInteract(PlayerEntity player, Hand hand) {
		ItemStack heldStack = player.getItemInHand(hand);

		if (heldStack.getItem() == Items.NAME_TAG) {
			heldStack.interactLivingEntity(player, this, hand);

			return ActionResultType.SUCCESS;
		}

		if (!level.isClientSide) {
			if (hand == Hand.MAIN_HAND) {
				String msg = getInteractMessage(heldStack);

				if (msg != null)
					PlayerUtil.notifyPlayer((ServerPlayerEntity)player, new TranslationTextComponent(msg).withStyle(TextFormatting.GRAY));
			}
		}

		return super.mobInteract(player, hand);
	}
}
