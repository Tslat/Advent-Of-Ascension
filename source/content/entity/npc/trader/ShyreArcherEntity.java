package net.tslat.aoa3.content.entity.npc.trader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.item.AoAWeapons;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.entity.base.AoATrader;
import net.tslat.aoa3.util.WorldUtil;
import org.jetbrains.annotations.Nullable;


public class ShyreArcherEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.forItem(Items.ARROW).itemCost(AoAItems.COPPER_COIN).xp(1).stock(64),
					BuildableTrade.forItem(Items.SPECTRAL_ARROW).itemCost(AoAItems.COPPER_COIN, 6).xp(5))
			.trades(3,
					BuildableTrade.forItem(AoAItems.ANCIENT_RING).itemCost(AoAItems.SHYREGEM, 2).itemCost(AoAItems.SHYRESTONE_INGOT, 2).xp(25))
			.trades(4,
					BuildableTrade.forItem(AoAWeapons.SUNSHINE_BOW).itemCost(AoAItems.SHYREGEM, 7).itemCost(AoAItems.SHYRESTONE_INGOT, 12).xp(40).stock(5),
					BuildableTrade.forItem(AoAWeapons.GIGA_CANNON).itemCost(AoAWeapons.ULTRA_CANNON).itemCost(AoAItems.SILVER_COIN, 9).xp(30).stock(9999)).build();

	public ShyreArcherEntity(EntityType<? extends AoATrader> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level(), AoADimensions.SHYRELANDS);
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ItemListing[]> getTradesMap() {
		return TRADES;
	}
}
