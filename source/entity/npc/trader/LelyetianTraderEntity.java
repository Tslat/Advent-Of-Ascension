package net.tslat.aoa3.entity.npc.trader;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoAWeapons;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.npc.AoATraderRecipe;
import net.tslat.aoa3.util.WorldUtil;

public class LelyetianTraderEntity extends AoATrader {
	public LelyetianTraderEntity(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level, AoADimensions.LELYETIA.key);
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.SILVER_COIN.get(), 10), new ItemStack(AoAItems.YELLOW_SPORES.get(), 5), new ItemStack(AoAWeapons.GAUGE_RIFLE.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.SILVER_COIN.get(), 10), new ItemStack(AoAItems.ORANGE_SPORES.get(), 5), new ItemStack(AoAWeapons.GAUGE_RIFLE.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.ZHINX_DUST.get()), new ItemStack(AoAItems.COPPER_COIN.get(), 4)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.SILVER_COIN.get(), 2), new ItemStack(AoABlocks.LELYETIAN_GLASS.get(), 64)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get(), 10), new ItemStack(AoABlocks.LELYETIAN_GLASS.get(), 14)));
	}
}
