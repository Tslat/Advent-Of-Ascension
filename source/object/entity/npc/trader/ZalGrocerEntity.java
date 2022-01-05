package net.tslat.aoa3.object.entity.npc.trader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.object.entity.base.AoATrader;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class ZalGrocerEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ITrade[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.trade(Items.CARROT, 3).cost(AoAItems.COPPER_COIN).xp(1).stock(32),
					BuildableTrade.trade(Items.POTATO, 2).cost(AoAItems.COPPER_COIN).xp(1).stock(32),
					BuildableTrade.trade(Items.MELON_SLICE, 4).cost(AoAItems.COPPER_COIN).xp(1).stock(32),
					BuildableTrade.trade(Items.BEETROOT, 2).cost(AoAItems.COPPER_COIN).xp(1).stock(32))
			.trades(2,
					BuildableTrade.trade(Items.COD).cost(AoAItems.COPPER_COIN, 3).stock(32),
					BuildableTrade.trade(Items.SALMON).cost(AoAItems.COPPER_COIN, 3).stock(32),
					BuildableTrade.trade(Items.COOKIE, 3).cost(AoAItems.COPPER_COIN).stock(32)).build();

	public ZalGrocerEntity(EntityType<? extends AoATrader> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 0.6875f;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level, AoADimensions.LUNALUS.key);
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ITrade[]> getTradesMap() {
		return TRADES;
	}
}
