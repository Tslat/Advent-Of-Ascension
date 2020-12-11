package net.tslat.aoa3.entity.npc.lottoman;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.npc.AoATraderRecipe;

public class BaronLottomanEntity extends LottomanEntity {
	public BaronLottomanEntity(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public boolean canDespawn(double distanceToClosestPlayer) {
		return world.getDimension().getType() != AoADimensions.BARATHOS.type();
	}

	@Override
	protected boolean isOverworldNPC() {
		return false;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.BARON_TOKENS.get(), 28), new ItemStack(AoAItems.LOTTO_TOTEM.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.BARON_TOKENS.get(), 15), new ItemStack(AoAItems.WEAPONS_CASE.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.BARON_TOKENS.get(), 10), new ItemStack(AoAItems.RUNE_BOX.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.BARON_TOKENS.get(), 21), new ItemStack(AoAItems.TREASURE_BOX.get())));
	}
}
