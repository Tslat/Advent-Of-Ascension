package net.tslat.aoa3.content.entity.npc.trader;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.tslat.aoa3.common.container.CorruptedTravellerContainer;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class CorruptedTravellerEntity extends CreatureEntity {
	public CorruptedTravellerEntity(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);

		setGlowing(AoAConfig.SERVER.easyCorruptedTravellers.get());
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

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level, AoADimensions.OVERWORLD.key);
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

	protected void openGui(PlayerEntity player) {
		NetworkHooks.openGui((ServerPlayerEntity)player, new INamedContainerProvider() {
			@Override
			public ITextComponent getDisplayName() {
				return CorruptedTravellerEntity.this.getDisplayName();
			}

			@Nullable
			@Override
			public Container createMenu(int screenId, PlayerInventory inv, PlayerEntity player) {
				return new CorruptedTravellerContainer(screenId, player.inventory, CorruptedTravellerEntity.this);
			}
		}, buffer -> buffer.writeInt(getId()));
	}
}
