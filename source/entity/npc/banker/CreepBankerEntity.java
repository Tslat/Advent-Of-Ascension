package net.tslat.aoa3.entity.npc.banker;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.tslat.aoa3.common.container.BankerContainer;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.npc.AoATraderRecipe;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class CreepBankerEntity extends AoATrader {
	public CreepBankerEntity(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level, AoADimensions.CREEPONIA.key);
	}

	@Override
	protected ActionResultType mobInteract(PlayerEntity player, Hand hand) {
		ItemStack heldStack = player.getItemInHand(hand);

		if (heldStack.getItem() == AoAItems.BLANK_REALMSTONE.get() && heldStack.getItem().interactLivingEntity(heldStack, player, this, hand).consumesAction())
			return ActionResultType.SUCCESS;

		return super.mobInteract(player, hand);
	}

	@Override
	protected void openGui(PlayerEntity player) {
		NetworkHooks.openGui((ServerPlayerEntity)player, new INamedContainerProvider() {
			@Override
			public ITextComponent getDisplayName() {
				return CreepBankerEntity.this.getDisplayName();
			}

			@Nullable
			@Override
			public Container createMenu(int screenId, PlayerInventory inv, PlayerEntity player) {
				return new BankerContainer(screenId, player.inventory, CreepBankerEntity.this);
			}
		}, buffer -> buffer.writeInt(getId()));
	}

	@Override
	protected boolean isFixedTradesList() {
		return true;
	}

	@Override
	protected void getTradesList(NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(ItemStack.EMPTY, ItemStack.EMPTY));
	}
}
