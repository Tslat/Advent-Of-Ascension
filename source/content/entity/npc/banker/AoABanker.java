package net.tslat.aoa3.content.entity.npc.banker;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.WorldUtil;

public abstract class AoABanker extends PathfinderMob {
	public AoABanker(EntityType<? extends PathfinderMob> entityType, Level world) {
		super(entityType, world);

		((GroundPathNavigation)getNavigation()).setCanOpenDoors(true);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new FloatGoal(this));
		goalSelector.addGoal(1, new AvoidEntityGoal<AoAMeleeMob>(this, AoAMeleeMob.class, 8f, 0.8d, 1d));
		goalSelector.addGoal(2, new OpenDoorGoal(this, true));
		goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 3f, 1f));
		goalSelector.addGoal(4, new RandomStrollGoal(this, 0.6d));
		goalSelector.addGoal(5, new RandomLookAroundGoal(this));
	}

	protected boolean isOverworldNPC() {
		return false;
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 1;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !isOverworldNPC() || !WorldUtil.isWorld(level(), Level.OVERWORLD) || tickCount >= 48000;
	}

	@Override
	protected InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack heldStack = player.getItemInHand(hand);

		if (heldStack.getItem() == Items.NAME_TAG) {
			InteractionResult result = heldStack.interactLivingEntity(player, this, hand);

			if (result.consumesAction())
				return result;
		}

		if (isAlive() && !player.isShiftKeyDown()) {
			if (player instanceof ServerPlayer pl)
				openScreen(pl);

			return InteractionResult.sidedSuccess(level().isClientSide);
		}

		return super.mobInteract(player, hand);
	}

	protected abstract void openScreen(ServerPlayer player);
}