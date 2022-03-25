package net.tslat.aoa3.content.entity.npc.trader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.content.entity.base.AoATrader;

import javax.annotation.Nullable;

public class TrollTraderEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ITrade[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.trade(Blocks.SAND, 64).cost(AoAItems.COPPER_COIN, 10).xp(5).stock(64),
					BuildableTrade.trade(Items.INK_SAC).cost(AoAItems.COPPER_COIN, 3).stock(32),
					BuildableTrade.trade(Items.PRISMARINE_SHARD, 4).cost(AoAItems.SILVER_COIN).xp(15),
					BuildableTrade.trade(Items.PRISMARINE_CRYSTALS, 2).cost(AoAItems.SILVER_COIN).xp(15)).build();

	public TrollTraderEntity(EntityType<? extends AoATrader> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.625f;
	}

	@Override
	protected boolean isOverworldNPC() {
		return true;
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ITrade[]> getTradesMap() {
		return TRADES;
	}
}
