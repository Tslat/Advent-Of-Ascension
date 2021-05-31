package net.tslat.aoa3.entity.npc.trader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoAWeapons;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class ShyreArcherEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ITrade[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.trade(Items.ARROW).cost(AoAItems.COPPER_COIN).xp(1).stock(64),
					BuildableTrade.trade(Items.SPECTRAL_ARROW).cost(AoAItems.COPPER_COIN, 6).xp(5))
			.trades(3,
					BuildableTrade.trade(AoAItems.ANCIENT_RING).cost(AoAItems.SHYREGEM, 2).cost(AoAItems.SHYRESTONE_INGOT, 2).xp(25))
			.trades(4,
					BuildableTrade.trade(AoAWeapons.SUNSHINE_BOW).cost(AoAItems.SHYREGEM, 7).cost(AoAItems.SHYRESTONE_INGOT, 12).xp(40).stock(5),
					BuildableTrade.trade(AoAWeapons.GIGA_CANNON).cost(AoAWeapons.ULTRA_CANNON).cost(AoAItems.SILVER_COIN, 9).xp(30).stock(9999)).build();

	public ShyreArcherEntity(EntityType<? extends AoATrader> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level, AoADimensions.SHYRELANDS.key);
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ITrade[]> getTradesMap() {
		return TRADES;
	}
}
