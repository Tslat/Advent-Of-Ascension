package net.tslat.aoa3.entity.npc.lottoman;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.npc.AoATraderRecipe;
import net.tslat.aoa3.util.WorldUtil;

public class TwinklingLottomanEntity extends LottomanEntity {
	public TwinklingLottomanEntity(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level, AoADimensions.HAVEN.key);
	}

	@Override
	protected boolean isOverworldNPC() {
		return false;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.HAVEN_TOKENS.get(), 28), new ItemStack(AoAItems.LOTTO_TOTEM.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.HAVEN_TOKENS.get(), 15), new ItemStack(AoAItems.WEAPONS_CASE.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.HAVEN_TOKENS.get(), 10), new ItemStack(AoAItems.RUNE_BOX.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.HAVEN_TOKENS.get(), 21), new ItemStack(AoAItems.TREASURE_BOX.get())));
	}
}
