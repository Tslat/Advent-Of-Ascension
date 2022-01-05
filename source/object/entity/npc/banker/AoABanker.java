package net.tslat.aoa3.object.entity.npc.banker;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.tslat.aoa3.object.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.WorldUtil;

public abstract class AoABanker extends CreatureEntity {
	public AoABanker(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);

		((GroundPathNavigator)getNavigation()).setCanOpenDoors(true);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new SwimGoal(this));
		goalSelector.addGoal(1, new AvoidEntityGoal<AoAMeleeMob>(this, AoAMeleeMob.class, 8f, 0.8d, 1d));
		goalSelector.addGoal(2, new OpenDoorGoal(this, true));
		goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 3f, 1f));
		goalSelector.addGoal(4, new RandomWalkingGoal(this, 0.6d));
		goalSelector.addGoal(5, new LookRandomlyGoal(this));
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
		return !isOverworldNPC() || !WorldUtil.isWorld(level, World.OVERWORLD) || tickCount >= 48000;
	}

	@Override
	protected ActionResultType mobInteract(PlayerEntity player, Hand hand) {
		ItemStack heldStack = player.getItemInHand(hand);

		if (heldStack.getItem() == Items.NAME_TAG) {
			ActionResultType result = heldStack.interactLivingEntity(player, this, hand);

			if (result.consumesAction())
				return result;
		}

		if (isAlive() && !player.isShiftKeyDown()) {
			if (!level.isClientSide)
				openGui(player);

			return ActionResultType.sidedSuccess(level.isClientSide);
		}

		return super.mobInteract(player, hand);
	}

	protected abstract void openGui(PlayerEntity player);
}