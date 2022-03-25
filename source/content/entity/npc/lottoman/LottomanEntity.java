package net.tslat.aoa3.content.entity.npc.lottoman;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.content.entity.base.AoATrader;

import javax.annotation.Nullable;

public class LottomanEntity extends AoATrader {
	protected static final Int2ObjectMap<VillagerTrades.ITrade[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.trade(AoAItems.WEAPONS_CASE).cost(AoAItems.COPPER_COIN, 31).xp(12),
					BuildableTrade.trade(AoAItems.RUNE_BOX).cost(AoAItems.COPPER_COIN, 14).xp(8),
					BuildableTrade.trade(AoAItems.TREASURE_BOX).cost(AoAItems.COPPER_COIN, 41).xp(20),
					BuildableTrade.trade(AoAItems.LOTTO_TOTEM).cost(AoAItems.SILVER_COIN, 3).xp(25)).build();

	public LottomanEntity(EntityType<? extends AoATrader> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected boolean isOverworldNPC() {
		return true;
	}

	@Override
	public int getMaxTradesToUnlock(int newProfessionLevel) {
		return newProfessionLevel == 1 ? 4 : 2;
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ITrade[]> getTradesMap() {
		return TRADES;
	}
}
