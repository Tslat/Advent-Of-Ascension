package net.tslat.aoa3.entity.npc.trader;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoAWeapons;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.npc.AoATraderRecipe;
import net.tslat.aoa3.util.WorldUtil;

public class ToyMerchantEntity extends AoATrader {
	public ToyMerchantEntity(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level, AoADimensions.CELEVE.key);
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.CIRCUS_COIN.get(), 2), new ItemStack(AoAItems.BALLOON.get(), 10)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.CIRCUS_COIN.get(), 30), new ItemStack(AoAItems.GRAVITATOR.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.CIRCUS_COIN.get(), 10), new ItemStack(AoAItems.TOY_GYROCOPTER.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.CIRCUS_COIN.get(), 45), ItemStack.EMPTY, new ItemStack(AoAItems.SMILEY_UPGRADE_KIT.get()), 0, 1));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.CIRCUS_COIN.get(), 12), new ItemStack(AoAWeapons.CONFETTI_CLUSTER.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.CIRCUS_COIN.get(), 18), new ItemStack(AoAWeapons.BALLOON_BOMBER.get())));
	}
}
