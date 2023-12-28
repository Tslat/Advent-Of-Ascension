package net.tslat.aoa3.content.entity.npc.trader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.item.AoAArmour;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.item.AoAWeapons;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.entity.base.AoATrader;
import net.tslat.aoa3.util.WorldUtil;
import org.jetbrains.annotations.Nullable;


public class GorbEngineerEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.trade(AoAItems.SILVER_COIN).cost(AoAItems.POWER_CORE).xp(15).stock(12),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 8).cost(AoAItems.SCRAP_METAL).xp(5))
			.trades(4,
					BuildableTrade.trade(AoAArmour.PHANTASM_ARMOUR.helmet).cost(AoAItems.PHANTASM, 10).cost(AoAItems.SILVER_COIN, 2).xp(50).stock(5),
					BuildableTrade.trade(AoAArmour.PHANTASM_ARMOUR.chestplate).cost(AoAItems.PHANTASM, 15).cost(AoAItems.SILVER_COIN, 2).xp(50).stock(5),
					BuildableTrade.trade(AoAArmour.PHANTASM_ARMOUR.leggings).cost(AoAItems.PHANTASM, 12).cost(AoAItems.SILVER_COIN, 2).xp(50).stock(5),
					BuildableTrade.trade(AoAArmour.PHANTASM_ARMOUR.boots).cost(AoAItems.PHANTASM, 8).cost(AoAItems.SILVER_COIN, 2).xp(50).stock(5),
					BuildableTrade.trade(AoAWeapons.ULTRAFLAME).cost(AoAItems.GOLD_COIN, 3).xp(75).stock(5)).build();

	public GorbEngineerEntity(EntityType<? extends AoATrader> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 1.5f;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level(), AoADimensions.MYSTERIUM.key);
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ItemListing[]> getTradesMap() {
		return TRADES;
	}
}
