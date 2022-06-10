package net.tslat.aoa3.content.entity.base;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.WorldUtil;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.HashMap;

public abstract class AoAAmbientNPC extends PathfinderMob implements Npc, IAnimatable {
	private final AnimationFactory animationFactory = new AnimationFactory(this);
	private final HashMap<String, Integer> animationStates = new HashMap<>(1);

	public AoAAmbientNPC(EntityType<? extends PathfinderMob> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new FloatGoal(this));
		goalSelector.addGoal(1, new PanicGoal(this, 1));
		goalSelector.addGoal(1, new AvoidEntityGoal<AoAMeleeMob>(this, AoAMeleeMob.class, 8f, 0.8d, 1d));
		goalSelector.addGoal(2, new OpenDoorGoal(this, true));
		goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 3f, 1f));
		goalSelector.addGoal(4, new RandomStrollGoal(this, 0.6d));
		goalSelector.addGoal(5, new RandomLookAroundGoal(this));
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

	@Override
	public boolean checkSpawnRules(LevelAccessor world, MobSpawnType reason) {
		return checkSpawnChance(reason) && isValidLightLevel(reason) && canSpawnAt(reason, world.getBlockState(blockPosition().below()));
	}

	protected boolean canSpawnAt(MobSpawnType reason, BlockState blockState) {
		return reason == MobSpawnType.SPAWNER || blockState.isValidSpawn(level, blockPosition(), getType());
	}

	protected int getSpawnChanceFactor() {
		return 1;
	}

	private boolean checkSpawnChance(MobSpawnType reason) {
		return EntityUtil.isNaturalSpawnReason(reason) || getSpawnChanceFactor() <= 1 || random.nextInt(getSpawnChanceFactor()) == 0;
	}

	protected boolean isValidLightLevel(MobSpawnType reason) {
		if (!WorldUtil.isWorld(level, AoADimensions.OVERWORLD.key))
			return true;

		BlockPos blockpos = new BlockPos(getX(), getBoundingBox().minY, getZ());

		if (level.getBrightness(LightLayer.SKY, blockpos) > random.nextInt(32)) {
			return true;
		}
		else {
			int light = level.isThundering() ? level.getMaxLocalRawBrightness(blockpos, 10) : level.getMaxLocalRawBrightness(blockpos);

			return light > random.nextInt(8);
		}
	}

	@Override
	protected InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack heldStack = player.getItemInHand(hand);

		if (heldStack.getItem() == Items.NAME_TAG) {
			heldStack.interactLivingEntity(player, this, hand);

			return InteractionResult.SUCCESS;
		}

		if (!level.isClientSide) {
			if (hand == InteractionHand.MAIN_HAND) {
				String msg = getInteractMessage(heldStack);

				if (msg != null)
					PlayerUtil.notifyPlayer(player, Component.translatable(msg).withStyle(ChatFormatting.GRAY));
			}
		}

		return super.mobInteract(player, hand);
	}

	@Override
	public void registerControllers(AnimationData data) {}

	@Override
	public AnimationFactory getFactory() {
		return animationFactory;
	}
}
