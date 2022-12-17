package net.tslat.aoa3.content.entity.npc.trader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.entity.base.AoATrader;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class ZalGrocerEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.trade(Items.CARROT, 3).cost(AoAItems.COPPER_COIN).xp(1).stock(32),
					BuildableTrade.trade(Items.POTATO, 2).cost(AoAItems.COPPER_COIN).xp(1).stock(32),
					BuildableTrade.trade(Items.MELON_SLICE, 4).cost(AoAItems.COPPER_COIN).xp(1).stock(32),
					BuildableTrade.trade(Items.BEETROOT, 2).cost(AoAItems.COPPER_COIN).xp(1).stock(32))
			.trades(2,
					BuildableTrade.trade(Items.COD).cost(AoAItems.COPPER_COIN, 3).stock(32),
					BuildableTrade.trade(Items.SALMON).cost(AoAItems.COPPER_COIN, 3).stock(32),
					BuildableTrade.trade(Items.COOKIE, 3).cost(AoAItems.COPPER_COIN).stock(32)).build();

	public ZalGrocerEntity(EntityType<? extends AoATrader> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 0.6875f;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level, AoADimensions.LUNALUS.key);
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ItemListing[]> getTradesMap() {
		return TRADES;
	}
}
