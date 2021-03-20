package net.tslat.aoa3.entity.npc.lottoman;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.npc.AoATraderRecipe;
import net.tslat.aoa3.util.WorldUtil;

public class CreeponiaLottomanEntity extends LottomanEntity {
	public CreeponiaLottomanEntity(EntityType<? extends CreatureEntity> entityType, World world) {
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
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.CREEPONIA_TOKENS.get(), 28), new ItemStack(AoAItems.LOTTO_TOTEM.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.CREEPONIA_TOKENS.get(), 15), new ItemStack(AoAItems.WEAPONS_CASE.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.CREEPONIA_TOKENS.get(), 10), new ItemStack(AoAItems.RUNE_BOX.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.CREEPONIA_TOKENS.get(), 21), new ItemStack(AoAItems.TREASURE_BOX.get())));
	}
}
